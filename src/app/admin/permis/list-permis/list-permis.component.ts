import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { MatDialog } from '@angular/material/dialog';

import { FormBuilder } from '@angular/forms';
import { DialogService } from './../../../services/dialog.service';
import { ToastrService } from 'ngx-toastr';
import { PermisService } from './../../../services/permis.service';
import { PermisDto } from './../../../models/permis';

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
<<<<<<< HEAD
  //            public toastr: ToastrService,
 //             private dialogService: DialogService,
=======
   //           public toastr: ToastrService,
   //           private dialogService: DialogService,
>>>>>>> dev
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
    this.router.navigate(['/backend/admin/permis']);
  }

  onDeletePermis(id: number): void{
    this.permisService.deletePermisDTO(id).subscribe(data => {
      this.getListPermisDTOs();
        }
    ,
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }

/*

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
    });
  }
*/

 /*  onDeletePermis(id: number): void{
    this.dialogService.openConfirmDialog('Etes-vous sur de vouloir Supprimer cet donnée ?')
    .afterClosed().subscribe((response: any) =>{
      if(response){
        this.permisService.deletePermisDTO(id).subscribe(data => {
          this.toastr.warning('Permis supprimé avec succès!');
          this.getListPermisDTOs();
        });
      }
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  } */
<<<<<<< HEAD

  onDeletePermis(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer ce PErmis ?')) {
      this.permisService.deletePermisDTO(id).subscribe(data => {
        this.getListPermisDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }
=======
>>>>>>> dev

}
