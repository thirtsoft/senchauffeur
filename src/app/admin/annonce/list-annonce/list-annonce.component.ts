import { FormBuilder } from '@angular/forms';
import { DialogService } from './../../../services/dialog.service';
import { ToastrService } from 'ngx-toastr';
import { CreateAnnonceComponent } from './../create-annonce/create-annonce.component';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AnnonceService } from './../../../services/annonce.service';
import { Annonce, AnnonceDto } from './../../../models/annonce';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-annonce',
  templateUrl: './list-annonce.component.html',
  styleUrls: ['./list-annonce.component.scss']
})
export class ListAnnonceComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  editAnnonceDTO: AnnonceDto;

  id : number;
  p : number=1;
  searchText;

  constructor(private annonceService: AnnonceService,
              private dialog: MatDialog,
              private router: Router,
  //            public toastr: ToastrService,
  //            private dialogService: DialogService,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.getListAnnonceDTOs();
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

  onAddAnnonce() {
    this.router.navigate(['/backend/admin/annonce']);
  }
/*
  onAddAnnonce() {
    this.openNoteDialog(null);
  }

  openNoteDialog(data?: any){
    const dialogRef = this.dialog.open(CreateAnnonceComponent, {
      disableClose: true,
      autoFocus : true ,
      width : "50%",
      data: data
    } );

    dialogRef.afterClosed().subscribe(result => {
      if(result && data == null){
        this.annonceListDTO.push(result);
      }
      // this.refreshData();
    });
  }
*/
 /*  onDeleteAnnonce(id: number): void{
    this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
          this.toastr.warning('Annonce supprimé avec succès!');
          this.getListAnnonceDTOs();
        });
      }
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  } */

  onDeleteAnnonce(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
        this.getListAnnonceDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }


}
