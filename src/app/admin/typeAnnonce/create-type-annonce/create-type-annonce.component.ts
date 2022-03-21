import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';
import { TypeAnnonceService } from './../../../services/type-annonce.service';
import { TypeAnnonceDto } from './../../../models/type-annonce';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-type-annonce',
  templateUrl: './create-type-annonce.component.html',
  styleUrls: ['./create-type-annonce.component.scss']
})
export class CreateTypeAnnonceComponent implements OnInit {

  typeAnnonceDTO: TypeAnnonceDto = new TypeAnnonceDto();

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private crudApi: TypeAnnonceService,
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
      this.getTypeAnnonceDTOById(this.paramId);
    }

  }

  getTypeAnnonceDTOById(id: number) {
    this.crudApi.getTypeAnnonceDtOById(id).subscribe(
      (response: TypeAnnonceDto) => {
        this.typeAnnonceDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  onAddTypeAnnonce() {
    this.crudApi.addTypeAnnonceDtos(this.typeAnnonceDTO).subscribe(
      (response: TypeAnnonceDto) => {
        this.toastr.success('avec succès','typeAnnonce Ajoutée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("admin/accueil/typeAnnonces").then(() => {

        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onUpdateTypeAnnonce() {
    this.crudApi.updateTypeAnnonceDtos(this.typeAnnonceDTO.id, this.typeAnnonceDTO).subscribe(
      (response: TypeAnnonceDto) => {
        this.toastr.warning('avec succès','typeAnnonce Modifié', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("admin/accueil/typeAnnonces").then(() => {

        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  goBack() {
    this.router.navigateByUrl("admin/accueil/typeAnnonces");
  }


}
