import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AnnonceService } from './../../../services/annonce.service';
import { Annonce, AnnonceDto } from './../../../models/annonce';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-list-annonce',
  templateUrl: './list-annonce.component.html',
  styleUrls: ['./list-annonce.component.scss']
})
export class ListAnnonceComponent implements OnInit {

  annonceListDTO: AnnonceDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private annonceService: AnnonceService,
              private dialog: MatDialog,
              private router: Router,
              public toastr: ToastrService
  ){}

  ngOnInit(): void {
    this.getListAnnonceDTOs();
  }

  public getListAnnonceDTOs() {
    this.annonceService.getAnnonceDTOs().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  onAddAnnonce() {
    this.router.navigate(['/admin/accueil/annonce']);
  }

  onDeleteAnnonce(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer cette annonce ?')) {
      this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
        this.toastr.error('avec succès','Annonce supprimé', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.getListAnnonceDTOs();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }



}
