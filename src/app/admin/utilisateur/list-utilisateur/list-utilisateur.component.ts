import { CreateUtilisateurComponent } from './../create-utilisateur/create-utilisateur.component';
import { HttpErrorResponse } from '@angular/common/http';
import { DialogService } from './../../../services/dialog.service';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { UtilisateurDto } from './../../../models/utilisateur';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder } from '@angular/forms';
import { ActivatedUserComponent } from '../../recruteur/activated-user/activated-user.component';
import { SendEmailToEmployeurComponent } from '../../email/send-email-to-employeur/send-email-to-employeur.component';

@Component({
  selector: 'app-list-utilisateur',
  templateUrl: './list-utilisateur.component.html',
  styleUrls: ['./list-utilisateur.component.scss']
})
export class ListUtilisateurComponent implements OnInit {

  utilisateurDTOList: UtilisateurDto[];
  
  id : number;
  p : number=1;
  searchText;
  currentTime: number = 0;
  img: boolean;

  constructor(public userService: UtilisateurService,
              private router: Router,
              public toastr: ToastrService,
              private matDialog: MatDialog,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.getUtilisateurDTOs();

    if (this.userService.getUserAvatar(this.id) === null)
      this.img = false;
    else this.img = true;

  }

  getUtilisateurDTOs(): void {
    this.userService.getAllUtilisateurDtosOrderByIdDesc().subscribe(
      (response: UtilisateurDto[]) => {
        this.utilisateurDTOList = response;
        console.log(this.utilisateurDTOList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getTS() {
    return this.currentTime;
  }

  onAddUtilisateur() {
    this.router.navigate(['/admin/accueil/signUp']);
  }

  ActivatedUser(item : UtilisateurDto) {
    this.userService.choixmenu = "M";
    this.userService.formData = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(ActivatedUserComponent, dialogConfig);

  }

  sendMailToEmployeur(item: UtilisateurDto) {
    this.userService.choixmenu = "M";
    this.userService.dataForm = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(SendEmailToEmployeurComponent, dialogConfig);
  }

  onDeleteUtilisateur(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cet utilisateur ?')) {
      this.userService.deleteUtilisateurDTO(id).subscribe(data => {
        this.toastr.error('avec succès','utilisateur supprimée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.getUtilisateurDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }

  }
  

}
