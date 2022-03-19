import { UtilisateurService } from './../../../services/utilisateur.service';
import { UtilisateurDto } from './../../../models/utilisateur';
import { AddressService } from './../../../services/address.service';
import { PermisService } from './../../../services/permis.service';
import { AddresseDto } from './../../../models/address';
import { PermisDto } from './../../../models/permis';
import { NavigationEnd, Router, ActivatedRoute } from '@angular/router';
import { AnnonceService } from './../../../services/annonce.service';
import { AnnonceDto } from './../../../models/annonce';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-view-annonce',
  templateUrl: './view-annonce.component.html',
  styleUrls: ['./view-annonce.component.scss']
})
export class ViewAnnonceComponent implements OnInit {

  addEditAnnonceDTO: AnnonceDto = new AnnonceDto();
  listPermisDTOs: PermisDto[];
  listAddressDTOs: AddresseDto[];
  listUtilisateurDTOs: UtilisateurDto[];

  data;
  paramId :any = 0;
  Errors = {status:false, msg:''};
  mySubscription: any;

  constructor(private crudApi: AnnonceService,
              private permisService: PermisService,
              private addressService: AddressService,
              private userService: UtilisateurService,
              private router: Router,
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
      this.getAnnonceDTOById(this.paramId);
    }

    this.getListPermisDTOs();

    this.crudApi.getUserId();

    this.getListAddressesDTOs();

    this.getListUtilisateurDTOs();

  }

  getAnnonceDTOById(id: number) {
    console.log('getOne');
    this.crudApi.getAnnonceDTOById(id).subscribe(
      (response: AnnonceDto) => {
        console.log('data--', response);
        this.addEditAnnonceDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getListPermisDTOs() {
    this.permisService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        this.listPermisDTOs = response;
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

  getListUtilisateurDTOs() {
    this.userService.getUtilisateurDTOs().subscribe(
      (response: UtilisateurDto[]) => {
        this.listUtilisateurDTOs = response;
      }, (error: HttpErrorResponse) => {
        alert(error.message);
      }
    )
  }



  goBack() {
    this.router.navigate([`/admin/accueil/annonces`]);
  }

}
