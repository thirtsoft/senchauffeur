import { HttpErrorResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
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

  currentLocalityId: number;

  previousLocalityId: number = 1;

  searchMode: boolean = false;

  constructor(public chauffService: ChauffeurService,
              private router: Router,
              private route: ActivatedRoute,
              private fb: FormBuilder
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=> {
      this.getListChauffeurDTOs();
    });
  //  this.getListChauffeurDTOByPageables();

  }

  public getChauffeurListDTOs() {
    this.chauffService.getChauffeurDTOs().subscribe(
      (response: ChauffeurDto[]) => {
        this.chauffeurListDTO = response;
        console.log(this.chauffeurListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public getListChauffeurDTOs() {
    this.searchMode = this.route.snapshot.paramMap.has('disponibility');
    if (this.searchMode) {
      this.getChauffeurListDTOsByDisponibility();
    }else {
      this.handlerListChauffeurDTOs();
    }
  }

  handlerListChauffeurDTOs() {
    const hasLocality: boolean = this.route.snapshot.paramMap.has('id');
    if (hasLocality) {
      this.currentLocalityId = +this.route.snapshot.paramMap.get('id');
    }else {
      this.currentLocalityId = 1;
    }
    if (this.previousLocalityId != this.currentLocalityId) {
      this.currentPage = 1;
    }
    this.previousLocalityId = this.currentLocalityId;
    this.chauffService.getListChauffeurDTOByLocalityPageable(
      this.currentLocalityId,
      this.currentPage - 1,
      this.size
    ).subscribe(this.processResult());
  }

  processResult() {
    return data => {
      this.totalPages = data['totalPages'];
      this.pages = new Array(data['totalPages']);
      this.chauffeurListDTO = data['content'];
    }

  }

  // Liste des produits par page
  getListChauffeurDTOByPageable() {
    this.chauffService.getListChauffeurDTOByPageable(this.currentPage, this.size)
      .subscribe(data=> {
        this.totalPages = data['totalPages'];
        this.pages = new Array(data['totalPages']);
        this.chauffeurListDTO = data['content'];
        console.log(data);
      },err=> {
        console.log(err);
      });

  }

  getChauffeurListDTOsByDisponibility() {
    const disponility: string = this.route.snapshot.paramMap.get('disponibility');
    this.chauffService.getListChauffeurDTOByDisponibility(disponility).subscribe(
      data  => {
        this.chauffeurListDTO = data;
      }

    )

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

  onPageChauffeur(i) {
    this.currentPage = i;
    this.getListChauffeurDTOByPageables();
  }


}
