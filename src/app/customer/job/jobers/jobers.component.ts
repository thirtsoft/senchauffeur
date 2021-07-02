import { AnnonceService } from './../../../services/annonce.service';
import { Component, OnInit } from '@angular/core';
import { ChauffeurDto } from './../../../models/chauffeur';

import { FormBuilder } from '@angular/forms';

import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
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
  searchText;

  constructor(public chauffeurService: ChauffeurService,
              private annonceService: AnnonceService,
              private router: Router,
              private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.getListChauffeurDTOs();

    this.getNumberOfAnnonces();

    this.getNumberOfChauffeurs();
  }

  public getListChauffeurDTOs() {
    this.chauffeurService.getChauffeurDTOs().subscribe(
      (response: ChauffeurDto[]) => {
        this.chauffeurListDTO = response;
        console.log(this.chauffeurListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  getNumberOfChauffeurs(): void {
    this.chauffeurService.countNumberOfChauffeurs().subscribe(data => {
      this.numberOfChauffeur = data;
    });
  }

  getNumberOfAnnonces(): void {
    this.annonceService.countNumberOfAnnonces().subscribe(data => {
      this.numberOfAnnonce = data;
    });
  }



}
