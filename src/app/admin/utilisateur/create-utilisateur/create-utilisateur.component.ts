import { HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { Router, NavigationEnd, ActivatedRoute } from '@angular/router';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { UtilisateurDto } from './../../../models/utilisateur';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-utilisateur',
  templateUrl: './create-utilisateur.component.html',
  styleUrls: ['./create-utilisateur.component.scss']
})
export class CreateUtilisateurComponent implements OnInit {

  formDataUtilisateurDTO: UtilisateurDto = new UtilisateurDto();

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private userService: UtilisateurService,
              private router: Router,
              private toastr: ToastrService,
              private actRoute: ActivatedRoute

  ){
     //--for reload componant
     this.router.routeReuseStrategy.shouldReuseRoute = () => false;
     this.mySubscription = this.router.events.subscribe((event) => {
       if (event instanceof NavigationEnd) {
         // Trick the Router into believing it's last link wasn't previously loaded
         this.router.navigated = false;
       }
     });
  }

  ngOnInit(): void {
    this.paramId = this.actRoute.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getUtilisateurDTOById(this.paramId);
    }
  }

  getUtilisateurDTOById(id: number) {
    console.log('getOne');
    this.userService.getUtilisateurDTOById(id).subscribe(
      (response: UtilisateurDto) => {
        console.log('data--', response);
        this.formDataUtilisateurDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


  onUpdateUtilisateur() {
    this.userService.updateUtilisateurDTO(this.formDataUtilisateurDTO.id, 
        this.formDataUtilisateurDTO).subscribe(
      (response: UtilisateurDto) => {
        this.toastr.warning('avec succès','Utilisateurs Modifié', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigate(['/admin/accueil/utilisateurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  goBack() {
    this.router.navigate(['/admin/accueil/utilisateurs']);
  }


}
