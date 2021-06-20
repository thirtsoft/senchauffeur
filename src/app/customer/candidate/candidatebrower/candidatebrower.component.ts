import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { Component, OnInit } from '@angular/core';
import { ChauffeurDto } from './../../../models/chauffeur';

@Component({
  selector: 'app-candidatebrower',
  templateUrl: './candidatebrower.component.html',
  styleUrls: ['./candidatebrower.component.scss']
})
export class CandidatebrowerComponent implements OnInit {

  chauffeurListDTO: ChauffeurDto[];

  public size: number = 3;
  public currentPage: number = 1;
  public totalPages: number;
  public pages: Array<number>;

  public currentTime: number = 0;

  currentCategoryId: number;

  previousCategoryId: number = 1;

  searchMode: boolean = false;

  constructor(private chauffService: ChauffeurService,
              private router: Router,
              private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.getListChauffeurDTOByPageables();
  }

  public getListChauffeurDTOByPageables() {
    this.chauffService.getListChauffeurDTOByPageable(this.currentPage - 1, this.size).subscribe(
      (response: ChauffeurDto[]) => {
        console.log("Response Pageable--", response);
        this.totalPages = response['totalPages'];
        this.pages = new Array(response['totalPages']);
        this.chauffeurListDTO = response['content'];
        console.log("Resultat chauffeur Pageable--", this.chauffeurListDTO);
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
    this.getListChauffeurDTOByPageables();
  }


}
