
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { RecruteurService } from './../../../services/recruteur.service';
import { RecruteurDto } from './../../../models/recruteur';

@Component({
  selector: 'app-view-recruteur',
  templateUrl: './view-recruteur.component.html',
  styleUrls: ['./view-recruteur.component.scss']
})
export class ViewRecruteurComponent implements OnInit {

  recruteurDTO: RecruteurDto;

  paramId :any = 0;

  constructor(public recruService: RecruteurService,
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
    this.recruService.getRecruteurDTOById(id).subscribe(
      (response: RecruteurDto) => {
        console.log('data--', response);
        this.recruteurDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  onSelectCvFile(event){}

  onSelectPhotoFile(event) {}


}
