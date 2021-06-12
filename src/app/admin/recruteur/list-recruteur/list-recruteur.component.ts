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

  recruteurListDTO: RecruteurDto[];
  addEditRecruteurDTO: RecruteurDto;

  id : number;
  p : number=1;
  searchText;

  constructor(private recruteurService: RecruteurService,
              private dialog: MatDialog,
              private router: Router,
              public toastr: ToastrService,
              private dialogService: DialogService,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.getListRecruteurDTOs();
  }

  public getListRecruteurDTOs(): void {
    this.recruteurService.getRecruteurDTOs().subscribe(
      (response: RecruteurDto[]) => {
        this.recruteurListDTO = response;
        console.log(this.recruteurListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddRecruteur() {
    this.router.navigate(['/backend/chauffeur']);
  }
/*
  onAddRecruteur() {
    this.openNoteDialog(null);
  }
  openNoteDialog(data?: any){
    const dialogRef = this.dialog.open(CreateRecruteurComponent, {
      disableClose: true,
      autoFocus : true ,
      width : "50%",
      data: data
    } );
    dialogRef.afterClosed().subscribe(result => {
      if(result && data == null){
        this.recruteurListDTO.push(result);
      }

    });
  }
*/
  onDeleteRecruteur(id: number): void{
    this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        this.recruteurService.deleteRecruteurDTO(id).subscribe(data => {
          this.toastr.warning('Recruteur supprimé avec succès!');
  //        this.recruteurListDTO = this.recruteurListDTO.filter(u => u !== recruteur);
          this.getListRecruteurDTOs();
        });
      }
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }


}
