import { NgForm } from '@angular/forms';
import { NotationService } from './../../../services/notation.service';
import { NotationDto } from './../../../models/notation';
import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { ChauffeurDto } from './../../../models/chauffeur';

@Component({
  selector: 'app-detail-chauffeur',
  templateUrl: './detail-chauffeur.component.html',
  styleUrls: ['./detail-chauffeur.component.scss']
})
export class DetailChauffeurComponent implements OnInit {

  chauffeurDTO: ChauffeurDto;
  addEditNotationDTO: NotationDto = new NotationDto();
  addNotationForm: NgForm;

  paramId :any = 0;

  searchMode: boolean = false;

  constructor(public chauffService: ChauffeurService,
              private noteService: NotationService,
              private router: Router,
              private route: ActivatedRoute,
  ) { }




  ngOnInit(): void {
    this.paramId = this.route.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getChauffeurDTOById(this.paramId);
    }
/*
    this.route.paramMap.subscribe(()=> {
      this.getAnnonceDTOByReference();
    });
    */
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

  public onAddNotation() {
    console.log(this.addEditNotationDTO);
    console.log("Note", +this.chauffeurDTO.id);
    this.noteService.addNotationDTOToDriver(this.chauffeurDTO.id,
      this.addEditNotationDTO).subscribe(
      (response: NotationDto) => {
        alert("Notation Ajouté avec success");
        this.router.navigate(['/notations']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }







}
