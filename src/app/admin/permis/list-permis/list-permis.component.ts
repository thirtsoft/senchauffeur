import { CreatePermisComponent } from './../create-permis/create-permis.component';
import { PermisService } from './../../../services/permis.service';
import { Permis, PermisDto } from './../../../models/permis';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-list-permis',
  templateUrl: './list-permis.component.html',
  styleUrls: ['./list-permis.component.scss']
})
export class ListPermisComponent implements OnInit {

  permisList: Permis[];
  permisListDTO: PermisDto[];
  editPermis: Permis;
  deletePermis: Permis;

  id : number;
  p : number=1;
  searchText;

  constructor(private permisService: PermisService,
              private dialog: MatDialog,
              private router: Router){}

  ngOnInit(): void {
    this.getlistPermis();
    this.getListPermisDTOs();
  }

  public getListPermisDTOs() {
    this.permisService.getPermisDTOs().subscribe(
      (response: PermisDto[]) => {
        this.permisListDTO = response;
        console.log(this.permisListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public getlistPermis(): void {
    this.permisService.getPermis().subscribe(
      (response: Permis[]) => {
        this.permisList = response;
        console.log(this.permisList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddPermis() {
    this.openNoteDialog(null);
  }

  openNoteDialog(data?: any){
    const dialogRef = this.dialog.open(CreatePermisComponent, {
      disableClose: true,
      autoFocus : true ,
      width : "50%",
      data: data
    } );

    dialogRef.afterClosed().subscribe(result => {
      if(result && data == null){
        this.permisList.push(result);
      }
      // this.refreshData();
    });
  }

  addEditPermis(i) {

  }
  public onDeletePermis(perId: number): void {
    this.permisService.deletePermisDTO(perId).subscribe(
      (response: void) => {
        console.log(response);
        this.getListPermisDTOs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


}
