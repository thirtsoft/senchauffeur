import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { JetonService } from './../../../services/jeton.service';
import { UtilisateurDto } from './../../../models/utilisateur';
import { JetonDto } from './../../../models/jeton';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-create-jeton',
  templateUrl: './create-jeton.component.html',
  styleUrls: ['./create-jeton.component.scss']
})
export class CreateJetonComponent implements OnInit {

  jetonDTO: JetonDto = new JetonDto();
  listUtilisateurData: UtilisateurDto[];

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private crudApi: JetonService,
              private userService: UtilisateurService,
              private toastr: ToastrService,
              public dialog: MatDialog,
              private actRoute: ActivatedRoute,
              private router: Router,
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
      this.getJetonDTOById(this.paramId);
    }

    this.getListUtilisateurDTOs();
  }

  getJetonDTOById(id: number) {
    console.log('getOne');
    this.crudApi.getJetonDTOById(id).subscribe(
      (response: JetonDto) => {
        console.log('data--', response);
        this.jetonDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListUtilisateurDTOs() {
    this.userService.getUtilisateurDTOs().subscribe(
      (response: UtilisateurDto[]) => {
        this.listUtilisateurData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onAddJeton() {
    this.crudApi.addJetonDTO(this.jetonDTO).subscribe(
      (response: JetonDto) => {
        this.toastr.success('avec succ??s','Jeton ajout??', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigate(['/admin/accueil/jetons']);
      },
      (error: HttpErrorResponse) => {
        this.toastr.error("Jeton non ajout??")
        alert(error.message);
      }
    );
  }

  onUpdateJeton() {
    this.crudApi.updateJetonDTO(this.jetonDTO.id, this.jetonDTO)
      .subscribe(
        (response: JetonDto) => {
          this.toastr.warning('avec succ??s','Jeton modidifi??', {
            timeOut: 1500,
            positionClass: 'toast-top-right',
          });
        this.router.navigate(['/admin/accueil/jetons']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  goBack() {
    this.router.navigate(['/admin/accueil/jetons']);
  }

}
