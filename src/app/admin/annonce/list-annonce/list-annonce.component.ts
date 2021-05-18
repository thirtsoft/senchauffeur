import { CreateAnnonceComponent } from './../create-annonce/create-annonce.component';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AnnonceService } from './../../../services/annonce.service';
import { Annonce } from './../../../models/annonce';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-annonce',
  templateUrl: './list-annonce.component.html',
  styleUrls: ['./list-annonce.component.scss']
})
export class ListAnnonceComponent implements OnInit {

  annonceList: Annonce[];
  editAnnonce: Annonce;
  deleteAnnonce: Annonce;

  id : number;
  p : number=1;
  searchText;

  constructor(private annonceService: AnnonceService,
              private dialog: MatDialog,
              private router: Router){}

  ngOnInit(): void {
    this.getListAnnonces();
  }

  public getListAnnonces(): void {
    this.annonceService.getAnnonces().subscribe(
      (response: Annonce[]) => {
        this.annonceList = response;
        console.log(this.annonceList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

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
        this.annonceList.push(result);
      }
      // this.refreshData();
    });
  }

  addEditAnnonce(i) {

  }
  public onDeleteAnnonce(annonceId: number): void {
    this.annonceService.deleteAnnonce(annonceId).subscribe(
      (response: void) => {
        console.log(response);
        this.getListAnnonces();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
