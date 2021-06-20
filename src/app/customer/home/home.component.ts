import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { AnnonceService } from './../../services/annonce.service';
import { AnnonceDto } from './../../models/annonce';
import { Chauffeur, ChauffeurDto } from './../../models/chauffeur';
import { ChauffeurService } from './../../services/chauffeur.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  chauffeurListDTO: ChauffeurDto[];

  id : number;
  p : number=1;
  searchText;

  public currentTime: number = 0;

  currentPermId: number;

  searchMode: boolean = false;

  constructor(private chauffeurService: ChauffeurService,
              private router: Router,
              private fb: FormBuilder,
              private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
  }

  /*
  getChauffeursListDTOs() {
    const hasPermId: boolean = this.route.snapshot.paramMap.has('id');
    if (hasPermId) {
      this.getChauffeurListDTOsByPermis();
    }else {
      this.getListChauffeurDTOs();
    }



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

  public getChauffeurListDTOsByPermis() {
    this.chauffeurService.getListChauffeurDTOByPermis(this.currentPermId).subscribe(
      (response: ChauffeurDto[]) => {
        this.chauffeurListDTO = response;
        console.log("Chauffeur By Permis ID", this.chauffeurListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }



*/
  }


