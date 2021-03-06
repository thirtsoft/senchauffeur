import { JetonDto } from './../../../models/jeton';
import { JetonService } from './../../../services/jeton.service';
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

  addEditAnnonceDTO: AnnonceDto = new AnnonceDto();
  listPermisDTOs: PermisDto[];
  listAddressDTOs: AddresseDto[];
  listJetonDTOs: JetonDto[];

  listTypeContrats = ["S??jour", "CDD", "CDI"];
  listDisponibilites = ["Temps Partiel", "Temps Plein"];

  data;
  addJobForm: NgForm;

  formData:  FormGroup;

  get f() { return this.formData.controls; }

  constructor(public annonceService: AnnonceService,
              public tokenService: TokenStorageService,
              public userService: UtilisateurService,
              public authService: AuthService,
              private job: JetonService,
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

      this.getListJetonsDTOByCustomerId(this.paramId);

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

    }

    this.getListPermisDTOs();

    this.getListAddressesDTOs();
  }

  infoForm() {
    this.formData = this.fb.group({
      reference: ['', Validators.required],
      libelle: ['', Validators.required],
      lieuPoste: [''],
      salaire: ['',0],
      emailPoste: ['', 
        Validators.required,
        Validators.pattern('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$')],
      time: ['', Validators.required],
      proExperience: ['', Validators.required],
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

  getListJetonsDTOByCustomerId(id: number) {
    this.job.getJetonDTOsByCustomerIdByIdDesc(id).subscribe(
      (response: JetonDto[]) => {
        console.log('data--', response);
        this.listJetonDTOs = response;
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
    this.userService.getUtilisateurDTOById(user.id).subscribe(
      response => {
        this.listDataProfil = response;
        this.customerName = this.listDataProfil.name;
        this.customerUsername = this.listDataProfil.username;
        this.customerEmail = this.listDataProfil.email;
        this.customerMobile = this.listDataProfil.mobile;
      }
    );
  }

  addEditCustomerUsername(item: UtilisateurDto) {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width = "50%";
  }

  addEditCustomerPassword(item: UtilisateurDto) {
    console.log(item);
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width = "50%";

  }


  update() {
    console.log('Data send--', this.listDataProfil);
    this.userService.updateUtilisateurDTO(this.listDataProfil.id, this.listDataProfil).subscribe(
      (response: UtilisateurDto) => {
        this.toastr.warning('avec succ??s','Profil modifi??', {
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
    this.annonceService.addAnnonceDTOWithUser(this.formData.value,  this.userId)
      .subscribe(
      (response: AnnonceDto) => {
        this.toastr.success('avec succ??s','Annonce ajout??e', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.router.navigateByUrl("jobs/" + this.userId).then(() => {
            window.location.reload();
          });
      },
      (error: HttpErrorResponse) => {
        this.toastr.error("Veuillez remplir tous les champs");
      }

    );

    this.formData = null;

  }


  onUpdateJob() {
    this.annonceService.updateAnnonceDTO(this.addEditAnnonceDTO.id, this.addEditAnnonceDTO).subscribe(
      (response: AnnonceDto) => {
        this.toastr.warning('avec succ??s','Annonce modifi??e', {
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
        this.toastr.error('avec succ??s','Annonce supprim??', {
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
    this.toastr.info('bye bye a bientot','Vous etes bien d??connect??', {
      timeOut: 1500,
      positionClass: 'toast-top-right',
      });
      this.router.navigateByUrl("/").then(() => {
        window.location.reload();
      });

  }




}
