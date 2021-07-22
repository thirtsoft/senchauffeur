import { Component, OnInit, Inject } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { RecruteurService } from './../../../services/recruteur.service';
import { RecruteurDto } from './../../../models/recruteur';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

@Component({
  selector: 'app-create-recruteur',
  templateUrl: './create-recruteur.component.html',
  styleUrls: ['./create-recruteur.component.scss']
})
export class CreateRecruteurComponent implements OnInit {

  formDataRecruteurDTO: RecruteurDto = new RecruteurDto();

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private recruteurService: RecruteurService,
              private router: Router,
<<<<<<< HEAD
=======
     //         private toastr: ToastrService,
>>>>>>> dev
              public dialog: MatDialog,
              private actRoute: ActivatedRoute,

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
      this.getRecruteurDTOById(this.paramId);
    }

  }

  getRecruteurDTOById(id: number) {
    console.log('getOne');
    this.recruteurService.getRecruteurDTOById(id).subscribe(
      (response: RecruteurDto) => {
        console.log('data--', response);
        this.formDataRecruteurDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public onAddRecruteur() {
    this.recruteurService.addRecruteurDTO(this.formDataRecruteurDTO).subscribe(
      (response: RecruteurDto) => {
<<<<<<< HEAD
=======
  //      this.dialogRef.close();
    //    this.toastr.success("Chauffeur Ajouté avec Succès");
>>>>>>> dev
        this.router.navigate(['/backend/admin/recruteurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateRecruteur() {
    this.recruteurService.updateRecruteurDTO(this.formDataRecruteurDTO.id, this.formDataRecruteurDTO).subscribe(
      (response: RecruteurDto) => {
  //      this.dialogRef.close();
<<<<<<< HEAD
=======
  //      this.toastr.warning("Chauffeur Update avec Succès");
>>>>>>> dev
        this.router.navigate(['/backend/admin/recruteurs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
