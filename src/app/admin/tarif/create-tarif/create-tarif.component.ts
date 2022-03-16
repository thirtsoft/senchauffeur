import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AnnonceService } from './../../../services/annonce.service';
import { TarifService } from './../../../services/tarif.service';
import { AnnonceDto } from './../../../models/annonce';
import { TarifDto } from './../../../models/tarif';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-create-tarif',
  templateUrl: './create-tarif.component.html',
  styleUrls: ['./create-tarif.component.scss']
})
export class CreateTarifComponent implements OnInit {

  tarifDTO: TarifDto = new TarifDto();
  listAnnonceData: AnnonceDto[];

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private tarifService: TarifService,
              private annonceService: AnnonceService,
           //   private toastr: ToastrService,
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

    this.getListAnnonceDTOs();


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

  getListAnnonceDTOs() {
    this.annonceService.getAnnonceDTOs().subscribe(
      (response: AnnonceDto[]) => {
        this.listAnnonceData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  public onAddTarif() {
    this.tarifService.addTarifDTOs(this.tarifDTO).subscribe(
      (response: TarifDto) => {
  //      this.dialogRef.close();
    //    this.toastr.success("Chauffeur Ajouté avec Succès");
        alert("Tarif Ajouté avec succès");
        this.router.navigate(['/admin/accueil/tarifs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public onUpdateTarif() {
    this.tarifService.updateTarifDTOs(this.tarifDTO.id, this.tarifDTO).subscribe(
      (response: TarifDto) => {
  //      this.dialogRef.close();
  //      this.toastr.warning("Chauffeur Update avec Succès");
        alert("Tarif Modifié avec succès");
        this.router.navigate(['/admin/accueil/tarifs']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }



}
