import { TypeAnnonceService } from './../../../services/type-annonce.service';
import { TypeAnnonceDto } from './../../../models/type-annonce';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { ToastrService } from 'ngx-toastr';

import { AnnonceService } from './../../../services/annonce.service';
import { TarifService } from './../../../services/tarif.service';
import { AnnonceDto } from './../../../models/annonce';
import { TarifDto } from './../../../models/tarif';

@Component({
  selector: 'app-create-tarif',
  templateUrl: './create-tarif.component.html',
  styleUrls: ['./create-tarif.component.scss']
})
export class CreateTarifComponent implements OnInit {

  tarifDTO: TarifDto = new TarifDto();
  listTypeAnnonceData: TypeAnnonceDto[];

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private tarifService: TarifService,
              private typeAService: TypeAnnonceService,
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
      this.getTarifDTOById(this.paramId);
    }

    this.getListTypeAnnonceDTOs();


  }

  getTarifDTOById(id: number) {
    console.log('getOne');
    this.tarifService.getTarifDTOsById(id).subscribe(
      (response: TarifDto) => {
        console.log('data--', response);
        this.tarifDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListTypeAnnonceDTOs() {
    this.typeAService.getTypeTypeAnnonceDtOs().subscribe(
      (response: TypeAnnonceDto[]) => {
        this.listTypeAnnonceData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onAddTarif() {
    this.tarifService.addTarifDTOs(this.tarifDTO).subscribe(
      (response: TarifDto) => {
        this.toastr.success('avec succ??s','Tarif Ajout??e', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("admin/accueil/tarifs").then(() => {

        });
      },
      (error: HttpErrorResponse) => {
        this.toastr.error("Tarrif non ajout??, Veuillez contactez l'administrateur")
      }
    );
  }

  onUpdateTarif() {
    this.tarifService.updateTarifDTOs(this.tarifDTO.id, this.tarifDTO).subscribe(
      (response: TarifDto) => {
        this.toastr.warning('avec succ??s','Tarif Modifi??', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("admin/accueil/tarifs").then(() => {

        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  goBack() {
    this.router.navigateByUrl("admin/accueil/tarifs");
  }



}
