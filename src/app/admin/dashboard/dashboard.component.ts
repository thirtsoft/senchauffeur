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

  numberOfChauffeur;
  numberOfAnnonce;
  numberOfRecruteur;

  constructor(public dasboardService: DashboardService,
              private router: Router,

  ) {}

  ngOnInit(): void {

    this.getNumberOfChauffeurs();

    this.getNumberOfRecruteurs();

    this.getNumberOfAnnonces();
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


}
