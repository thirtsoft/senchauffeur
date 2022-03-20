import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { ToastrService } from 'ngx-toastr';
import { MatDialog } from '@angular/material/dialog';
import { LocalityService } from './../../../services/locality.service';
import { AddresseDto } from './../../../models/locality';

@Component({
  selector: 'app-list-locality',
  templateUrl: './list-locality.component.html',
  styleUrls: ['./list-locality.component.scss']
})
export class ListLocalityComponent implements OnInit {

  localityListDTO: AddresseDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private localiteService: LocalityService,
              private dialog: MatDialog,
              private router: Router,
              public toastr: ToastrService
  ){}

  ngOnInit(): void {
    this.getListLocalitieDTOs();
  }

  getListLocalitieDTOs() {
    this.localiteService.getLocaliteDTOOrderByIdDesc().subscribe(
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
    this.router.navigate(['/admin/accueil/localite']);
  }


  onDeleteLocality(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette Ville ?')) {
      this.localiteService.deleteLocalityDTO(id).subscribe(data => {
        this.toastr.error('avec succès','Ville supprimée', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.getListLocalitieDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }

  }


}
