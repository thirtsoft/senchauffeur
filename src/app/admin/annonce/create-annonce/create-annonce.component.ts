import { VilleDto } from './../../../models/ville';
import { AddresseDto } from './../../../models/address';
import { AddressService } from './../../../services/address.service';
import { Component, OnInit, Inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { RecruteurService } from './../../../services/recruteur.service';
import { PermisService } from './../../../services/permis.service';
import { AnnonceService } from './../../../services/annonce.service';
import { RecruteurDto } from './../../../models/recruteur';
import { PermisDto } from './../../../models/permis';
import { AnnonceDto } from './../../../models/annonce';


@Component({
  selector: 'app-create-annonce',
  templateUrl: './create-annonce.component.html',
  styleUrls: ['./create-annonce.component.scss']
})
export class CreateAnnonceComponent implements OnInit {

  annonceDTO: AnnonceDto = new AnnonceDto();
  listPermisData: PermisDto[];
  listRecruteurData: RecruteurDto[];
  listVilleDTOs: VilleDto[];
  listAddressDTOs: AddresseDto[];

  listTypeContrats = ["Stage", "Sejour", "CDD", "CDI"];
  listTypeExperiences = ["1ans", "2ans-5ans", "+5ans"];
  listTypeDisponibilites = ["Immediate", "Temps Partial", "Temps Plein"];

  data;
  paramId :any = 0;
  mySubscription: any;

  constructor(private annonceService: AnnonceService,
              private permisService: PermisService,
              private addressService: AddressService,
              private recruteurService: RecruteurService,
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
      this.getAnnonceDTOById(this.paramId);
    }

    this.getListPermisDTOs();

    this.annonceService.getUserId();

   /*  this.getListRecruteurDTOs(); */

    this.getListAddressesDTOs();

  }

  getAnnonceDTOById(id: number) {
    console.log('getOne');
    this.annonceService.getAnnonceDTOById(id).subscribe(
      (response: AnnonceDto) => {
        console.log('data--', response);
        this.annonceDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListPermisDTOs() {
    this.permisService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        this.listPermisData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  getListAddressesDTOs() {
    this.addressService.getAddresseDtos().subscribe(
      (response: AddresseDto[]) => {
        this.listAddressDTOs = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  getListRecruteurDTOs() {
    this.recruteurService.getRecruteurDTOs().subscribe(
      (response: RecruteurDto[]) => {
        this.listRecruteurData = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }

  onAddAnnonce() {
    console.log("Data start");
    console.log(this.annonceDTO);
    console.log("Data USER");
    console.log(this.annonceService.id);
    this.annonceService.addAnnonceDTOWithUser(this.annonceDTO, this.annonceService.id).subscribe(
      (response: AnnonceDto) => {
        this.toastr.success('avec succès','Annonce Ajoutée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("admin/accueil/annonceEncours").then(() => {
    
        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onUpdateAnnonce() {
    this.annonceService.updateAnnonceDTO(this.annonceDTO.id, this.annonceDTO).subscribe(
      (response: AnnonceDto) => {
        this.toastr.warning('avec succès','Annonce Modifiée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("admin/accueil/annonceEncours").then(() => {
    //      window.location.reload();
        });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  goBack() {
    this.router.navigateByUrl("admin/accueil/annonces");
  }


}
