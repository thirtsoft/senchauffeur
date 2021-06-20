import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AnnonceService } from './../../../services/annonce.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { AnnonceDto } from './../../../models/annonce';

@Component({
  selector: 'app-jobbrowser',
  templateUrl: './jobbrowser.component.html',
  styleUrls: ['./jobbrowser.component.scss']
})
export class JobbrowserComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  editAnnonceDTO: AnnonceDto;

  public size: number = 3;
  public currentPage: number = 1;
  public totalPages: number;
  public pages: Array<number>;

  public currentTime: number = 0;

  currentCategoryId: number;

  previousCategoryId: number = 1;

  searchMode: boolean = false;

  constructor(private annonceService: AnnonceService,
              private router: Router,
              private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getListAnnonceDTOByPageables();
  }

  public getListAnnonceDTOByPageables() {
    this.annonceService.getListAnnonceDTOByPageable(this.currentPage - 1, this.size).subscribe(
      (response: AnnonceDto[]) => {
//        this.annonceListDTO = response;
        console.log("Response Pageable--", response);
        this.totalPages = response['totalPages'];
        this.pages = new Array(response['totalPages']);
        this.annonceListDTO = response['content'];
        console.log("Resultat Annonce Pageable--", this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getTS() {
    return this.currentTime;
  }

  onPageArticle(i) {
    this.currentPage = i;
    this.getListAnnonceDTOByPageables();
  }



}
