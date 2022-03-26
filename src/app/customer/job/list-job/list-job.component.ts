import { ToastrService } from 'ngx-toastr';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, NgForm, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';

import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { AuthService } from './../../../auth/security/auth.service';
import { AnnonceService } from './../../../services/annonce.service';
import { AddressService } from './../../../services/address.service';
import { PermisService } from './../../../services/permis.service';

import { UtilisateurDto } from './../../../models/utilisateur';
import { AnnonceDto } from './../../../models/annonce';
import { AddresseDto } from './../../../models/locality';
import { VilleDto } from './../../../models/ville';
import { PermisDto } from './../../../models/permis';

import { NgbDateStruct, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-list-job',
  templateUrl: './list-job.component.html',
  styleUrls: ['./list-job.component.scss']
})
export class ListJobComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  editAnnonceDTO: AnnonceDto;
  listAnnonceDataDTO: AnnonceDto[];

  listDataProfil: UtilisateurDto = new UtilisateurDto();

  currentPage: number = 1;
  totalPages: number;
  pages: Array<number>;

  roles: string[];

  currentTime: number = 0;

  username: string;
  email: String;
  userId;

  customerName: string;
  customerUsername: string;
  customerEmail: string;
  customerMobile: string;
  customerPassword: string;

  currentUser;

  id : number;
  p : number=1;
  searchText;
  paramId :any = 0;
  comId: number;
  Errors = {status:false, msg:''};
  mySubscription: any;

  info: any;

  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  showVendeurBoard = false;

  photo;

  addEditAnnonceDTO: AnnonceDto = new AnnonceDto();
  listPermisDTOs: PermisDto[];
  listVilleDTOs: VilleDto[];
  listAddressDTOs: AddresseDto[];

  listTypeContrats = ["Stage", "CDD", "CDI"];
  listExperiences = ["Débutant", "1ans-3ans", "3ans-5ans", "10ans et plus"];
  listDisponibilites = ["Immediate", "Temps Partiel", "Temps Plein"];

  data;
  addJobForm: NgForm;

  model: NgbDateStruct;
//  today = this.calendar.getToday();
  today;

  formData:  FormGroup;

  get f() { return this.formData.controls; }

  constructor(public annonceService: AnnonceService,
              public tokenService: TokenStorageService,
              public userService: UtilisateurService,
              public authService: AuthService,
              public toastr: ToastrService,
              public fb: FormBuilder,
              public router: Router,
              public matDialog: MatDialog,
              private route: ActivatedRoute,

              private permisService: PermisService,
              private addressService: AddressService,
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
    this.infoForm();
    this.paramId = this.route.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getAnnonceDTOByUserId(this.paramId);

      this.getUtilisateurDTOById(this.paramId);
    }

    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showVendeurBoard = this.roles.includes("ROLE_VENDEUR");
      this.showUserBoard = this.roles.includes('ROLE_USER');

      this.username = user.username;
      this.userId = user.id;
      this.photo = user.photo;

      console.log("Username : " + this.username);

      console.log("IdUser : " + this.userId);
    }

    this.getListPermisDTOs();

    this.getListAddressesDTOs();
  }

  infoForm() {
    this.formData = this.fb.group({
      reference: ['', Validators.required],
      libelle: ['', Validators.required],
      lieuPoste: ['', Validators.required],
      salaire: ['', Validators.required],
      emailPoste: ['', Validators.required],
      time: ['', Validators.required],
      disponibilite: ['', Validators.required],
      experience: ['', Validators.required],
      typeContrat: ['', Validators.required],
      description: ['', Validators.required],
      dateCloture: new Date(),
      permisDto: new PermisDto(),
      addresseDto: new AddresseDto()
    });

  }

  getListAnnonceDTOs() {
    this.annonceService.getAnnonceDTOs().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getAnnonceDTOByUserId(id: number) {
    this.annonceService.getAnnonceDtoByUserIdOrderDesc(id).subscribe(
      (response: AnnonceDto[]) => {
        console.log('data--', response);
        this.listAnnonceDataDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getAnnonceDTOByCustomerId(id: number) {
    this.annonceService.getAnnonceDTOByCustomerId(id).subscribe(
      (response: AnnonceDto) => {
        console.log('data--', response);
        this.addEditAnnonceDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getUtilisateurDTOById(id: number) {
    console.log('getOne');
    this.userService.getUtilisateurDTOById(id).subscribe(
      (response: UtilisateurDto) => {
        console.log('data--', response);
        this.listDataProfil = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


  onAddNewJob() {
    this.router.navigate(['/createJob']);
  }

  getEmploye() {
    const user = this.tokenService.getUser();
    console.log(user.id);
    this.userService.getUtilisateurDTOById(user.id).subscribe(
      response => {
        console.log(response);
        this.listDataProfil = response;
        this.customerName = this.listDataProfil.name;
        this.customerUsername = this.listDataProfil.username;
        this.customerEmail = this.listDataProfil.email;
        this.customerMobile = this.listDataProfil.mobile;
        console.log(this.listDataProfil.name);
        console.log(this.listDataProfil.username);
        console.log(this.listDataProfil.email);
      }
    );
  }

  addEditCustomerUsername(item: UtilisateurDto) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width = "50%";
  //  this.authService.listData = Object.assign({}, item);
  //  this.matDialog.open(UpdateCustomerUsernameComponent, dialogConfig);
  }

  addEditCustomerPassword(item: UtilisateurDto) {
    console.log(item);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width = "50%";
  //  this.authService.listData = Object.assign({}, item);
  //  this.matDialog.open(UpdateCustomerPasswordComponent, dialogConfig);

  }

 

  update() {
    console.log('Data send--', this.listDataProfil);
    this.userService.updateUtilisateurDTO(this.listDataProfil.id, this.listDataProfil).subscribe(
      (response: UtilisateurDto) => {
        this.toastr.warning('avec succès','Profil modifié', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.router.navigateByUrl("/").then(() => {
            window.location.reload();
          });
        
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }

    );

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

  onAddJob() {
    console.log(this.formData.value);
    console.log(this.formData.value,  this.userId);
    this.annonceService.addAnnonceDTOWithUser(this.formData.value,  this.userId)
      .subscribe(
      (response: AnnonceDto) => {
        this.toastr.success('avec succès','Annonce ajouée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.getListAnnonceDTOs();

      },
      (error: HttpErrorResponse) => {
        this.toastr.error("Veuillez remplir tous les champs");
      }

    );

  }


  onUpdateJob() {
    this.annonceService.updateAnnonceDTO(this.addEditAnnonceDTO.id, this.addEditAnnonceDTO).subscribe(
      (response: AnnonceDto) => {
        this.toastr.warning('avec succès','Annonce modifiée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.getListAnnonceDTOs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onDeleteAnnonce(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
        this.toastr.error('avec succès','Annonce supprimé', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.getListAnnonceDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }


  logout() {
    this.tokenService.signOut();
    this.toastr.info('bye bye a bientot','Vous etes bien déconnecté', {
      timeOut: 1500,
      positionClass: 'toast-top-right',
      });
      this.router.navigateByUrl("/").then(() => {
        window.location.reload();
      });

  }




}
