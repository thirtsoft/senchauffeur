import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CreateChauffeurComponent } from './../create-chauffeur/create-chauffeur.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { Chauffeur, ChauffeurDto } from './../../../models/chauffeur';

@Component({
  selector: 'app-list-chauffeur',
  templateUrl: './list-chauffeur.component.html',
  styleUrls: ['./list-chauffeur.component.scss']
})
export class ListChauffeurComponent implements OnInit {

  chauffeurList: Chauffeur[];
  chauffeurListDTO: ChauffeurDto[];
  editChauffeur: Chauffeur;
  deleteChauffeur: Chauffeur;

  id : number;
  p : number=1;
  searchText;

  constructor(private chauffeurService: ChauffeurService,
              private dialog: MatDialog,
              private router: Router){}

  ngOnInit(): void {
    this.getlistChauffeurs();
    this.getListChauffeurDTOs();
  }

  public getListChauffeurDTOs() {
    this.chauffeurService.getChauffeurDTOs().subscribe(
      (response: ChauffeurDto[]) => {
        this.chauffeurListDTO = response;
        console.log(this.chauffeurListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  public getlistChauffeurs(): void {
    this.chauffeurService.getChauffeurs().subscribe(
      (response: Chauffeur[]) => {
        this.chauffeurList = response;
        console.log(this.chauffeurList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddChauffeur() {
    this.openNoteDialog(null);
  }

  openNoteDialog(data?: any){
    const dialogRef = this.dialog.open(CreateChauffeurComponent, {
      disableClose: true,
      autoFocus : true ,
      width : "50%",
      data: data
    } );

    dialogRef.afterClosed().subscribe(result => {
      if(result && data == null){
        this.chauffeurListDTO.push(result);
      }
      // this.refreshData();
    });
  }

  addEditChauffeur(i) {

  }
  public onDeleteChauffeur(chauffId: number): void {
    this.chauffeurService.deleteChauffeurDTO(chauffId).subscribe(
      (response: void) => {
        console.log(response);
        this.getListChauffeurDTOs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
