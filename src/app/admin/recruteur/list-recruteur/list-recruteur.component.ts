import { SendEmailToEmployeurComponent } from './../../email/send-email-to-employeur/send-email-to-employeur.component';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MatDialogConfig, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { UtilisateurService } from './../../../services/utilisateur.service';
import { ToastrService } from 'ngx-toastr';
import { UtilisateurDto } from './../../../models/utilisateur';

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

  info: any;
  roles: string[];

  isLoggedIn = false;
  showUserBoard = false;
  showManagerBoard = false;
  showAdminBoard = false;

  username: string;
  email: String;
  userId;
  currentTime: number = 0;

  constructor(private crudApi: UtilisateurService,
              public toastr: ToastrService,
              private matDialog: MatDialog,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
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

  sendMailToEmployeur(item: UtilisateurDto) {
    this.crudApi.choixmenu = "M";
    this.crudApi.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(SendEmailToEmployeurComponent, dialogConfig);
  }



}
