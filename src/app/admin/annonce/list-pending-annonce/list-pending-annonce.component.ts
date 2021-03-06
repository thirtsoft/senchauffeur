import { UpdateStatusOfAnnonceComponent } from './../update-status-of-annonce/update-status-of-annonce.component';
import { Component, OnInit, Inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';
import { AnnonceService } from './../../../services/annonce.service';
import { AnnonceDto } from './../../../models/annonce';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-list-pending-annonce',
  templateUrl: './list-pending-annonce.component.html',
  styleUrls: ['./list-pending-annonce.component.scss']
})
export class ListPendingAnnonceComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  roles: string[];

  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  id : number;
  p : number=1;
  searchText;
  formData: FormGroup;

  constructor(private crudApi: AnnonceService,
              private tokenService: TokenStorageService,
              private matDialog: MatDialog,
              private router: Router,
              public fb: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef:MatDialogRef<UpdateStatusOfAnnonceComponent>,
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

    this.getListAnnonceDTOsByStatusPending();
  }

  getListAnnonceDTOsByStatusPending() {
    this.crudApi.getAnnonceDTOByStatusPending().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  addEditStatusAnnonce(item : AnnonceDto) {
    this.crudApi.choixmenu == 'M';
    this.crudApi.formData = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(UpdateStatusOfAnnonceComponent, dialogConfig);

  }

  viewAllAnnonces() {
    this.router.navigate(['/admin/accueil/annonce']);
  }


}
