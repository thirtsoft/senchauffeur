import { FormBuilder } from '@angular/forms';
import { DialogService } from './../../../services/dialog.service';
import { ToastrService } from 'ngx-toastr';
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

  permisListDTO: PermisDto[];
  addEditPermisDTO: PermisDto;

  id : number;
  p : number=1;
  searchText;

  constructor(private permisService: PermisService,
              private dialog: MatDialog,
              private router: Router,
              public toastr: ToastrService,
              private dialogService: DialogService,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
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
        this.permisListDTO.push(result);
      }
      // this.refreshData();
    });
  }

  addEditPermis(i) {
  }

  onDeletePermis(permis: PermisDto): void{
    this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        this.permisService.deletePermisDTO(permis.id).subscribe(data => {
          this.toastr.warning('Permis supprimé avec succès!');
          this.permisListDTO = this.permisListDTO.filter(u => u !== permis);
          this.getListPermisDTOs();
        });
      }
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }

}
