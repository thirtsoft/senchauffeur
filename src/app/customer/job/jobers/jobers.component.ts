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
  addEditChauffeurDTO: ChauffeurDto;

  id : number;
  p : number=1;
  searchText;

  constructor(private chauffeurService: ChauffeurService,
              private router: Router,
              private fb: FormBuilder
  ) {
   }

  ngOnInit(): void {
    this.getListChauffeurDTOs();
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

}
