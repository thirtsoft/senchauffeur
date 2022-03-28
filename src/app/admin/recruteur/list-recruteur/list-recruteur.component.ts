import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { UtilisateurDto } from './../../../models/utilisateur';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { HttpErrorResponse } from '@angular/common/http';
import { DialogService } from './../../../services/dialog.service';
import { ToastrService } from 'ngx-toastr';
import { RecruteurService } from './../../../services/recruteur.service';
import { RecruteurDto } from './../../../models/recruteur';
import { CreateRecruteurComponent } from './../create-recruteur/create-recruteur.component';
import { FormBuilder } from '@angular/forms';

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
              private router: Router,
              public toastr: ToastrService,
        //      private dialogService: DialogService,
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



}
