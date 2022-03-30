import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { PermisService } from './../../../services/permis.service';
import { AnnonceService } from './../../../services/annonce.service';
import { PermisDto } from './../../../models/permis';
import { AnnonceDto } from './../../../models/annonce';
import { AddressService } from './../../../services/address.service';
import { AddresseDto } from './../../../models/locality';


@Component({
  selector: 'app-create-job',
  templateUrl: './create-job.component.html',
  styleUrls: ['./create-job.component.scss']
})
export class CreateJobComponent implements OnInit {

  addEditAnnonceDTO: AnnonceDto = new AnnonceDto();
  listPermisDTOs: PermisDto[];
  listAddressDTOs: AddresseDto[];

  listTypeContrats = ["Séjour", "CDD", "CDI"];

  data;
  paramId :any = 0;
  mySubscription: any;
  addJobForm: NgForm;

  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  showVendeurBoard = false;

  
  roles: string[];

  currentTime: number = 0;

  username: string;
  email: String;
  userId;

  constructor(private annonceService: AnnonceService,
              private permisService: PermisService,
              private addressService: AddressService,
              public tokenService: TokenStorageService,
              private toastr: ToastrService,
              private actRoute: ActivatedRoute,
              private router: Router
  ) {
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

    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.userId = user.id;
     
    }

    this.getListPermisDTOs();

    this.annonceService.getUserId();

    this.getListAddressesDTOs();

  }

  getAnnonceDTOById(id: number) {
    console.log('getOne');
    this.annonceService.getAnnonceDTOById(id).subscribe(
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

  onUpdateJob() {
    this.annonceService.updateAnnonceDTO(this.addEditAnnonceDTO.id, this.addEditAnnonceDTO).subscribe(
      (response: AnnonceDto) => {
        this.toastr.warning('avec succès','Annonce Modifiée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.goToAnnonce();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  goToAnnonce() {
    this.router.navigate(['/jobs/' + this.userId ]).then(() => {
    });
  }

  logout() {
    this.tokenService.signOut();
    this.toastr.info('bye bye','Vous etes bien déconnecté', {
      timeOut: 1500,
      positionClass: 'toast-top-right',
      });
      this.router.navigateByUrl("/").then(() => {
        window.location.reload();
      });

  
  }


}
