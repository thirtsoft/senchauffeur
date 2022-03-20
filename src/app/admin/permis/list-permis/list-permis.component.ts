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
              private router: Router,
              public toastr: ToastrService,
              private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.getListPermisDTOs();
  }

  public getListPermisDTOs() {
    this.permisService.getPermisDTOOrderByIdDesc().subscribe(
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
    this.router.navigate(['/admin/accueil/permis']);
  }


  onDeletePermis(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer ce type de Permis ?')) {
      this.permisService.deletePermisDTO(id).subscribe(data => {
        this.toastr.error('avec succès','Permis supprimé', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.getListPermisDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

}
