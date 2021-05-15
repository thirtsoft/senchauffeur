import { CreateRecruteurComponent } from './../create-recruteur/create-recruteur.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { RecruteurService } from './../../../services/recruteur.service';
import { Recruteur } from './../../../models/recruteur';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-recruteur',
  templateUrl: './list-recruteur.component.html',
  styleUrls: ['./list-recruteur.component.scss']
})
export class ListRecruteurComponent implements OnInit {

  recruteurList: Recruteur[];
  editRecruteur: Recruteur;
  deleteRecruteur: Recruteur;

  id : number;
  p : number=1;
  searchText;

  constructor(private recruteurService: RecruteurService,
   //           private dialog: MatDialog,
              private router: Router){}

  ngOnInit(): void {
    this.getlistRecruteurs();
  }

  public getlistRecruteurs(): void {
    this.recruteurService.getRecruteurs().subscribe(
      (response: Recruteur[]) => {
        this.recruteurList = response;
     //   console.log(this.categories[0].idCategory);
        console.log(this.recruteurList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

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
        this.recruteurList.push(result);
      }
      // this.refreshData();
    });
  }

  addEditRecruteur(i) {

  }
  public onDeleteRecruteur(recId: number): void {
    this.recruteurService.deleteRecruteur(recId).subscribe(
      (response: void) => {
        console.log(response);
        this.getlistRecruteurs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
