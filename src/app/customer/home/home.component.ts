import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { Chauffeur } from './../../models/chauffeur';
import { ChauffeurService } from './../../services/chauffeur.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  chauffeurList : Chauffeur[];

  currentTime: number = 0;

  constructor(private chauffService: ChauffeurService,
              private router: Router
              ){

  }

  ngOnInit(){
    this.handleListChauffeurs();
  }

  public handleListChauffeurs(){
    this.chauffService.getChauffeurs().subscribe(response => {
        this.chauffeurList = response;
        console.log("La liste des chauffeurs" + this.chauffeurList);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  getChauffeurDetails(chauff: Chauffeur) {
    this.router.navigate(['/detail-chauffeur/'+chauff.id]);
  }


}
