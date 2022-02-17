import { Component, OnInit, ViewChild } from '@angular/core';

import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { FormBuilder } from '@angular/forms';
import { AnnonceService } from './../../../services/annonce.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute, NavigationEnd } from '@angular/router';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { UtilisateurDto } from './../../../models/utilisateur';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { AuthService } from './../../../auth/security/auth.service';
import { AnnonceDto } from './../../../models/annonce';

import { DataTableDirective } from 'angular-datatables';
import { Subject } from 'rxjs';

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

  constructor(public annonceService: AnnonceService,
              public tokenService: TokenStorageService,
              public userService: UtilisateurService,
              public authService: AuthService,
              //            public toastr: ToastrService,
              public fb: FormBuilder,
              public router: Router,
              public matDialog: MatDialog,
              private route: ActivatedRoute,
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
  }

  public getListAnnonceDTOs() {
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

  onAddEditAnnonce(item) {

  }

  onAddEditJeton(item) {}

  onAddNewJob() {
    this.router.navigate(['/createJob']);
  //  this.router.navigate(['/jobs/' + this.userId]);
  }

  onDeleteAnnonce(id: number): void{
  /*  this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        */
        this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
   //       this.toastr.warning('Job supprimé avec succès!');
  //        this.annonceListDTO = this.annonceListDTO.filter(u => u !== annonceDTO);
          this.getListAnnonceDTOs();

    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
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

  viewAnnonce(item) {

  }

  update() {

  }

  logout() {
    this.tokenService.signOut();
    this.router.navigateByUrl('/');
  }



}
