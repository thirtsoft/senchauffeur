
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';

import { UtilisateurService } from 'src/app/services/utilisateur.service';
import { UtilisateurDto } from 'src/app/models/utilisateur';

@Component({
  selector: 'app-view-recruteur',
  templateUrl: './view-recruteur.component.html',
  styleUrls: ['./view-recruteur.component.scss']
})
export class ViewRecruteurComponent implements OnInit {

  recruteurDTO: UtilisateurDto;

  paramId :any = 0;

  constructor(public crudApi: UtilisateurService,
              private router: Router,
              private route: ActivatedRoute,
  ) {}

  ngOnInit(): void {
    this.paramId = this.route.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getRecruteurDTOById(this.paramId);
    }
  }

  getRecruteurDTOById(id: number) {
    console.log('getOne');
    this.crudApi.getUtilisateurDTOById(id).subscribe(
      (response: UtilisateurDto) => {
        console.log('data--', response);
        this.recruteurDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

}
