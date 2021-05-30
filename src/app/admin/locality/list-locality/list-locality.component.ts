import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { DialogService } from './../../../services/dialog.service';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
import { CreateLocalityComponent } from './../create-locality/create-locality.component';
import { LocalityService } from './../../../services/locality.service';
import { AddresseDto } from './../../../models/locality';

@Component({
  selector: 'app-list-locality',
  templateUrl: './list-locality.component.html',
  styleUrls: ['./list-locality.component.scss']
})
export class ListLocalityComponent implements OnInit {

  localityListDTO: AddresseDto[];
  addEditLocalityDTO: AddresseDto;

  id : number;
  p : number=1;
  searchText;

  constructor(private localiteService: LocalityService,
              private dialog: MatDialog,
              private router: Router,
              public toastr: ToastrService,
              private dialogService: DialogService,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
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

  addEditLocality(i) {}

  public onDeleteLocality(locityDTO: AddresseDto): void{
    this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        this.localiteService.deleteLocalityDTO(locityDTO.id).subscribe(data => {
          this.toastr.warning('Addresse supprimé avec succès!');
          this.localityListDTO = this.localityListDTO.filter(u => u !== locityDTO);
          this.getListLocalitieDTOs();
        });
      }
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }

}
