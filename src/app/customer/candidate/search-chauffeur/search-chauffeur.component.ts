import { DashboardService } from 'src/app/services/dashboard.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-chauffeur',
  templateUrl: './search-chauffeur.component.html',
  styleUrls: ['./search-chauffeur.component.scss']
})
export class SearchChauffeurComponent implements OnInit {

  numberOfChauffeur;

  constructor(private router: Router,
              private dashboardService: DashboardService
  ) { }

  ngOnInit(): void {
    this.getNumberOfChauffeurs();
  }

  searchArticle(keyword: string) {
    console.log("keyword+++", keyword);
    this.router.navigateByUrl('/search/'+keyword);

  }

  getNumberOfChauffeurs(): void {
    this.dashboardService.countNumberOfChauffeurs().subscribe(data => {
      this.numberOfChauffeur = data;
    });
  }

}
