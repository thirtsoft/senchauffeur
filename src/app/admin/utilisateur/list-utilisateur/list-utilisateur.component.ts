import { CreateUtilisateurComponent } from './../create-utilisateur/create-utilisateur.component';
import { HttpErrorResponse } from '@angular/common/http';
import { DialogService } from './../../../services/dialog.service';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { UtilisateurDto } from './../../../models/utilisateur';
import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

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
              public toastr: ToastrService
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
