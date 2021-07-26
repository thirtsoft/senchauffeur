import { HttpErrorResponse } from '@angular/common/http';
import { ChauffeurDto } from './../../models/chauffeur';
import { RecruteurDto } from './../../models/recruteur';
import { NotationDto } from './../../models/notation';
import { RecruteurService } from './../../services/recruteur.service';
import { NotationService } from './../../services/notation.service';
import { Router } from '@angular/router';
import { ChauffeurService } from './../../services/chauffeur.service';
import { AnnonceService } from './../../services/annonce.service';
import { Component, OnInit } from '@angular/core';
import { DashboardService } from 'src/app/services/dashboard.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  listNotificationDTO: NotationDto[];
  listRecruteurDTO: RecruteurDto[];
  listChauffeurDTO: ChauffeurDto[];

  numberOfChauffeur;
  numberOfAnnonce;
  numberOfRecruteur;

  constructor(public dasboardService: DashboardService,
              private noteService: NotationService,
              private chauffService: ChauffeurService,
              private recService: RecruteurService,
              private router: Router,

  ) {}

  ngOnInit(): void {

    this.getNumberOfChauffeurs();

    this.getNumberOfRecruteurs();

    this.getNumberOfAnnonces();

    this.getListNotationDtos();

    this.getListRecruteurDTO();
  }

  getNumberOfChauffeurs(): void {
    this.dasboardService.countNumberOfChauffeurs().subscribe(data => {
      this.numberOfChauffeur = data;
    });
  }

  getNumberOfAnnonces(): void {
    this.dasboardService.countNumberOfAnnonces().subscribe(data => {
      this.numberOfAnnonce = data;
    });
  }

  getNumberOfRecruteurs(): void {
    this.dasboardService.countNumberOfRecruteurs().subscribe(data => {
      this.numberOfRecruteur = data;
    });
  }

  public getListNotationDtos() {
    this.noteService.getNotationDTOs().subscribe(
      (response: NotationDto[]) => {
        this.listNotificationDTO = response;
        console.log(this.listNotificationDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  public getListRecruteurDTO() {
    this.recService.getRecruteurDTOs().subscribe(
      (response: RecruteurDto[]) => {
        this.listRecruteurDTO = response;
        console.log(this.listRecruteurDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


}
