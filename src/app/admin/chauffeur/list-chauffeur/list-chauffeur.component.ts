import { SendEmailToChauffeurComponent } from './../../email/send-email-to-chauffeur/send-email-to-chauffeur.component';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { ChauffeurDto } from './../../../models/chauffeur';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';

@Component({
  selector: 'app-list-chauffeur',
  templateUrl: './list-chauffeur.component.html',
  styleUrls: ['./list-chauffeur.component.scss']
})
export class ListChauffeurComponent implements OnInit {

  chauffeurListDTO: ChauffeurDto[];
  
  roles: string[];

  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  userId;

  id : number;
  p : number=1;
  searchText;

  constructor(public chauffeurService: ChauffeurService,
              private tokenService: TokenStorageService,
              public toastr: ToastrService,
              private router: Router,
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

      this.userId = user.id;
    }
    this.getListChauffeurDTOs();
  }

  getListChauffeurDTOs() {
    this.chauffeurService.getChauffeurDTOOrderByIdDesc().subscribe(
      (response: ChauffeurDto[]) => {
        this.chauffeurListDTO = response;
        console.log(this.chauffeurListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddChauffeur() {
    this.router.navigate(['/admin/accueil/chauffeur']);
  }

  sendMailToChauffeur(item: ChauffeurDto) {
    this.chauffeurService.choixmenu = "M";
    this.chauffeurService.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(SendEmailToChauffeurComponent, dialogConfig);
  }

  onDeleteChauffeur(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer ce Chauffeur ?')) {
      this.chauffeurService.deleteChauffeurDTO(id).subscribe(data => {
        this.toastr.error('avec succès','Jeton Chauffeur', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.getListChauffeurDTOs();
        },
        (error: HttpErrorResponse) => {
          this.toastr.error('veuillez contactez administrateur','Chauffeur ne peut pas etre supprimé');
        }
      );
    }
  }

}
