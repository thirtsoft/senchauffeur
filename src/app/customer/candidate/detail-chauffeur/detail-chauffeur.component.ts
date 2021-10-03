import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { ChauffeurDto } from './../../../models/chauffeur';
import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { NotationService } from './../../../services/notation.service';

@Component({
  selector: 'app-detail-chauffeur',
  templateUrl: './detail-chauffeur.component.html',
  styleUrls: ['./detail-chauffeur.component.scss']
})
export class DetailChauffeurComponent implements OnInit {

  chauffeurDTO: ChauffeurDto;

  paramId :any = 0;

  searchMode: boolean = false;

  isLoggedIn = false;
  username: string;

  pdfSrc;

  constructor(public chauffService: ChauffeurService,
              private ratingService: NotationService,
              private tokenService: TokenStorageService,
              private router: Router,
              private route: ActivatedRoute,
  ) { }




  ngOnInit(): void {
    this.paramId = this.route.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getChauffeurDTOById(this.paramId);
    }

    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();

      this.ratingService.getUserId();

      this.username = user.username;

    }


  }

  getChauffeurDTOById(id: number) {
    console.log('getOne');
    this.chauffService.getChauffeurDTOById(id).subscribe(
      (response: ChauffeurDto) => {
        console.log('data--', response);
        this.chauffeurDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  openPDF(){


  }


}
