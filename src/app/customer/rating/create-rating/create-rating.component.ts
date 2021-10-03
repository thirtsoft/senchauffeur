import { HttpErrorResponse } from '@angular/common/http';
import { ChauffeurDto } from './../../../models/chauffeur';
import { NotationService } from './../../../services/notation.service';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { ActivatedRoute } from '@angular/router';
import { NgForm, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { NotationDto } from '../../../models/notation';


@Component({
  selector: 'app-create-rating',
  templateUrl: './create-rating.component.html',
  styleUrls: ['./create-rating.component.scss']
})
export class CreateRatingComponent implements OnInit {

  addNotificationDTO = new NotationDto();
  addRatingForm: NgForm;
  userId: any;
  ref: string;
  formData:  FormGroup;
  chauffeurDTO: ChauffeurDto;
  currentRating: number = 0;
  maxRatingValue = 5;
  isLoggedIn = false;
  username: string;
  paramId :any = 0;

  constructor(private ratingService: NotationService,
              private chauffService: ChauffeurService,
              private tokenService: TokenStorageService,
              public fb: FormBuilder,
              private actRoute: ActivatedRoute
  ) { }

  get f() { return this.formData.controls; }


  ngOnInit(): void {

    this.infoForm();

    this.getSingleChauffeurDTO();

    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();

      this.ratingService.getUserId();

      this.username = user.username;

    }


  }

  infoForm() {
    this.formData = this.fb.group({
      nbreEtoile: [this.currentRating, Validators.required],
      observation: ['', Validators.required],
    });

  }

  onRateChange(event :number) {
    console.log("The selected rate change ", event);
    this.currentRating = event;
  }

  public getSingleChauffeurDTO() {
    this.paramId = this.actRoute.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getChauffeurDTOById(this.paramId);
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

  onSubmit() {
    console.log(this.formData.value);
    console.log(this.formData.value, this.ref, this.ratingService.id);
    this.ratingService.addRatingToChauffeur(this.formData.value, this.paramId, this.ratingService.id)
      .subscribe(
      (response: NotationDto) => {
        alert("Note Attribué avec succès");
        console.log('Response--', response);

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }

    );

  }




}
