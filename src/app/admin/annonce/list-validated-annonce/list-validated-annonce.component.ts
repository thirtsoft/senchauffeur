import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

import { AnnonceService } from './../../../services/annonce.service';
import { AnnonceDto } from './../../../models/annonce';

@Component({
  selector: 'app-list-validated-annonce',
  templateUrl: './list-validated-annonce.component.html',
  styleUrls: ['./list-validated-annonce.component.scss']
})
export class ListValidatedAnnonceComponent implements OnInit {

  annonceListDTO: AnnonceDto[];

  id : number;
  p : number=1;
  searchText;
 
  constructor(private crudApi: AnnonceService,
              private router: Router,
  ){}

  ngOnInit(): void {
    this.getListAnnonceDTOsByStatusValidated();
  }

  public getListAnnonceDTOsByStatusValidated() {
    this.crudApi.getAnnonceDTOByStatusValidated().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  viewAllAnnonces() {
    this.router.navigate(['/admin/accueil/annonces']);
  }


}
