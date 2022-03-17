import { Router } from '@angular/router';
import { AnnonceService } from './../../../services/annonce.service';
import { AnnonceDto } from './../../../models/annonce';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-list-rejected-annonce',
  templateUrl: './list-rejected-annonce.component.html',
  styleUrls: ['./list-rejected-annonce.component.scss']
})
export class ListRejectedAnnonceComponent implements OnInit {

  annonceListDTO: AnnonceDto[];

  id : number;
  p : number=1;
  searchText;
 
  constructor(private crudApi: AnnonceService,
              private router: Router,
  ){}

  ngOnInit(): void {
    this.getListAnnonceDTOsByStatusReject();
  }

  public getListAnnonceDTOsByStatusReject() {
    this.crudApi.getAnnonceDTOByStatusRejeted().subscribe(
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
