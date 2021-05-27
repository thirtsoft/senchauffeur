import { MatDialog } from '@angular/material/dialog';
import { CreateLocalityComponent } from './../create-locality/create-locality.component';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { LocalityService } from './../../../services/locality.service';
import { Locality, AddresseDto } from './../../../models/locality';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-locality',
  templateUrl: './list-locality.component.html',
  styleUrls: ['./list-locality.component.scss']
})
export class ListLocalityComponent implements OnInit {

  localityList: Locality[];
  localityListDTO: AddresseDto[];
  editLocality: Locality;
  deleteLocality: Locality;

  id : number;
  p : number=1;
  searchText;

  constructor(private localiteService: LocalityService,
              private dialog: MatDialog,
              private router: Router){}

  ngOnInit(): void {
    this.getlistLocalities();
    this.getListLocalitieDTOs();
  }

  public getListLocalitieDTOs() {
    this.localiteService.getLocaliteDTOs().subscribe(
      (response: AddresseDto[]) => {
        this.localityListDTO = response;
        console.log(this.localityListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }
  public getlistLocalities(): void {
    this.localiteService.getLocalites().subscribe(
      (response: Locality[]) => {
        this.localityList = response;
        console.log(this.localityList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddLocality() {
    this.openNoteDialog(null);
  }

  openNoteDialog(data?: any){
    const dialogRef = this.dialog.open(CreateLocalityComponent, {
      disableClose: true,
      autoFocus : true ,
      width : "50%",
      data: data
    } );

    dialogRef.afterClosed().subscribe(result => {
      if(result && data == null){
        this.localityListDTO.push(result);
      }
      // this.refreshData();
    });
  }

  addEditLocality(i) {

  }
  public onDeleteLocality(locId: number): void {
    this.localiteService.deleteLocalityDTO(locId).subscribe(
      (response: void) => {
        console.log(response);
        this.getListLocalitieDTOs();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

}
