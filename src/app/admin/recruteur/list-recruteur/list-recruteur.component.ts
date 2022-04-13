import { SendEmailToEmployeurComponent } from './../../email/send-email-to-employeur/send-email-to-employeur.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { UtilisateurService } from './../../../services/utilisateur.service';
import { ToastrService } from 'ngx-toastr';
import { UtilisateurDto } from './../../../models/utilisateur';
import { ActivatedUserComponent } from '../activated-user/activated-user.component';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-list-recruteur',
  templateUrl: './list-recruteur.component.html',
  styleUrls: ['./list-recruteur.component.scss']
})
export class ListRecruteurComponent implements OnInit {

  recruteurListDTO: UtilisateurDto[];

  id : number;
  p : number=1;
  searchText;

  roles: string[];

  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  username: string;
  email: String;
  userId;
  currentTime: number = 0;

  constructor(public crudApi: UtilisateurService,
              private tokenService: TokenStorageService,
              public toastr: ToastrService,
              private matDialog: MatDialog,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showGestionnaireBoard = this.roles.includes("ROLE_GESTIONNAIRE");
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');
      this.showUserBoard = this.roles.includes('ROLE_USER');
    }

    this.getListRecruteurDTOs();
  }

  getListRecruteurDTOs(): void {
    this.crudApi.getAllUtilisateurDtosOrderByIdDesc().subscribe(
      (response: UtilisateurDto[]) => {
        this.recruteurListDTO = response;
        console.log(this.recruteurListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  ActivatedUser(item : UtilisateurDto) {
    this.crudApi.choixmenu = "M";
    this.crudApi.formData = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(ActivatedUserComponent, dialogConfig);

  }

  sendMailToEmployeur(item: UtilisateurDto) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(SendEmailToEmployeurComponent, dialogConfig);
  }

  getTS() {
    return this.currentTime;
  }



}
