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
  deleteUtilisateurDTO: UtilisateurDto;

  id : number;
  p : number=1;
  searchText;

  constructor(private userService: UtilisateurService,
              private router: Router,
               private dialog: MatDialog,
              public toastr: ToastrService,
              private dialogService: DialogService,
              ){}

  ngOnInit(): void {
    this.getUtilisateurDTOs();
  }

  public getUtilisateurDTOs(): void {
    this.userService.getUtilisateurDTOs().subscribe(
      (response: UtilisateurDto[]) => {
        this.utilisateurDTOList = response;
        console.log(this.utilisateurDTOList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  onAddUtilisateur() {
    this.openNoteDialog(null);
  }

  openNoteDialog(data?: any){
    const dialogRef = this.dialog.open(CreateUtilisateurComponent, {
      disableClose: true,
      autoFocus : true ,
      width : "50%",
      data: data
    } );

    dialogRef.afterClosed().subscribe(result => {
      if(result && data == null){
        this.utilisateurDTOList.push(result);
      }
      // this.refreshData();
    });
  }

  public onDeleteUtilisateur(user: UtilisateurDto): void{
    this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        this.userService.deleteUtilisateurDTO(user.id).subscribe(data => {
          this.toastr.warning('Utilisateur supprimé avec succès!');
          this.utilisateurDTOList = this.utilisateurDTOList.filter(u => u !== user);
          this.getUtilisateurDTOs();
        });
      }
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }


}
