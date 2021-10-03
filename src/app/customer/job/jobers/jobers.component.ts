import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { DashboardService } from './../../../services/dashboard.service';
import { AnnonceService } from './../../../services/annonce.service';
import { Component, OnInit } from '@angular/core';
import { ChauffeurDto } from './../../../models/chauffeur';

import { FormBuilder } from '@angular/forms';

import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';


@Component({
  selector: 'app-jobers',
  templateUrl: './jobers.component.html',
  styleUrls: ['./jobers.component.scss']
})
export class JobersComponent implements OnInit {

  chauffeurListDTO: ChauffeurDto[];

  numberOfChauffeur;
  numberOfAnnonce;

  id : number;
  p : number=1;

  chauffeurListDTOBySelected: ChauffeurDto[];

  currentTime: number = 0;

  searchText: boolean = false;

  isLoggedIn = false;


  username: string;
  userId;

  constructor(public chauffeurService: ChauffeurService,
              private dashboardService: DashboardService,
              private tokenService: TokenStorageService,
              private activeRoute: ActivatedRoute,
              private router: Router,
              private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activeRoute.paramMap.subscribe(()=>{
      this.getListChauffeurDTOs();
      }
    );

    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();

      this.username = user.username;
      this.userId = user.id;
    }

    this.getNumberOfAnnonces();

    this.getNumberOfChauffeurs();
  }

  getListChauffeurDTOs() {
    this.searchText = this.activeRoute.snapshot.paramMap.has('keyword');
    if (this.searchText) {
      // do search work
      this.getChauffeurListDTOsBySearchKeyword();
    } else {
      //display product list
      this.getChauffeurListDTOsBySelectedIsTrue();
    }
  }

  public getChauffeurListDTOsBySelectedIsTrue() {
    this.chauffeurService.getListChauffeurDTOBySelectedIsTrue().subscribe(
      (response: ChauffeurDto[]) => {
        this.chauffeurListDTOBySelected = response;
        console.log(this.chauffeurListDTOBySelected);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getChauffeurListDTOsBySearchKeyword() {
    const keyword: string = this.activeRoute.snapshot.paramMap.get('keyword');
    this.chauffeurService.getListChauffeurDTOByKeyword(keyword).subscribe(
      data  => {
        this.chauffeurListDTOBySelected = data;
      }

    )

  }

  getNumberOfChauffeurs(): void {
    this.dashboardService.countNumberOfChauffeurs().subscribe(data => {
      this.numberOfChauffeur = data;
    });
  }

  getNumberOfAnnonces(): void {
    this.dashboardService.countNumberOfAnnonces().subscribe(data => {
      this.numberOfAnnonce = data;
    });
  }

  onGetDetailChauffeur(id: number) {
    this.router.navigate['/detail-chauffeur/'+id];
  }



}
