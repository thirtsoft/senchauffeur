import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { AnnonceService } from './../../../services/annonce.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { AnnonceDto } from './../../../models/annonce';

@Component({
  selector: 'app-jobbrowser',
  templateUrl: './jobbrowser.component.html',
  styleUrls: ['./jobbrowser.component.scss']
})
export class JobbrowserComponent implements OnInit {

  annonceListDTO: AnnonceDto[];

  size: number = 5;
  currentPage: number = 1;
  totalPages: number;
  pages: Array<number>;

  currentTime: number = 0;

  currentAnnonceId: number;

  previousAnnonceId: number = 1;

  searchMode: boolean = false;

  constructor(private annonceService: AnnonceService,
              private router: Router,
              private route: ActivatedRoute,
              private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=> {
      this.getListAnnonceDTOs();
    });
  }

  getListAnnonceDTOs() {
    this.searchMode = this.route.snapshot.paramMap.has('libelle');
    if (this.searchMode) {
      this.getAnnonceListDTOsByReferenceJob();
    }else {
      this.handlerListAnnonceDTOs();
    }
  }

  handlerListAnnonceDTOs() {
    const hasLocality: boolean = this.route.snapshot.paramMap.has('id');
    if (hasLocality) {
      this.currentAnnonceId = +this.route.snapshot.paramMap.get('id');
    }else {
      this.currentAnnonceId = 1;
    }
    if (this.previousAnnonceId != this.currentAnnonceId) {
      this.currentPage = 1;
    }
    this.previousAnnonceId = this.currentAnnonceId;
    this.annonceService.getListAnnonceDTOByPermisPageable(
      this.currentAnnonceId,
      this.currentPage - 1,
      this.size
    ).subscribe(this.processResult());
  }

  processResult() {
    return data => {
      this.totalPages = data['totalPages'];
      this.pages = new Array(data['totalPages']);
      this.annonceListDTO = data['content'];
    }

  }

  doing() {
    this.getListAnnonceDTOs();
  }

  getAnnonceListDTOsByReferenceJob() {
    const libelle: string = this.route.snapshot.paramMap.get('libelle');
    this.annonceService.getListAnnonceDTOByLibeele(libelle).subscribe(
      data  => {
        this.annonceListDTO = data;
      }

    )

  }

  getListAnnonceDTOByPageables() {
    this.annonceService.getListAnnonceDTOByPageable(
      this.currentPage,
      this.size
      ).subscribe(
      (response: AnnonceDto[]) => {
        this.totalPages = response['totalPages'];
        this.pages = new Array(response['totalPages']);
        this.annonceListDTO = response['content'];
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getTS() {
    return this.currentTime;
  }

  onPageAnnonce(i) {
    this.currentPage = i;
    this.getListAnnonceDTOByPageables();
  }



}
