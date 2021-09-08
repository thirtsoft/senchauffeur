import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { AnnonceService } from './../../../services/annonce.service';
import { AnnonceDto } from './../../../models/annonce';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-annonce-by-status-encours',
  templateUrl: './annonce-by-status-encours.component.html',
  styleUrls: ['./annonce-by-status-encours.component.scss']
})
export class AnnonceByStatusEncoursComponent implements OnInit {

  annonceListDTO: AnnonceDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(private annonceService: AnnonceService,
              private dialog: MatDialog,
              private router: Router,
     //         public toastr: ToastrService,
     //         private dialogService: DialogService,
  ){}

  ngOnInit(): void {
    this.getListAnnonceDTOsByStatusEncours();
  }

  public getListAnnonceDTOsByStatusEncours() {
    this.annonceService.getAnnonceDTOByStatusEncours().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  Rejeter(annonceDTO) {

  }

  onAddAnnonce() {

  }

  /*  onAddAnnonce() {
    this.router.navigate(['/backend/admin/annonce']);
  }

  onDeleteAnnonce(id: number): void{
    this.annonceService.deleteAnnonceDTO(id).subscribe(data => {
      this.getListAnnonceDTOs();
        }

    ,
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  } */

}
