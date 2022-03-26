import { Component, OnInit } from '@angular/core';
import { NgForm, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpErrorResponse } from '@angular/common/http';
import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { Router, ActivatedRoute } from '@angular/router';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { ChauffeurDto } from './../../../models/chauffeur';
import { NotationDto } from './../../../models/notation';
import { NotationService } from './../../../services/notation.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-detail-chauffeur',
  templateUrl: './detail-chauffeur.component.html',
  styleUrls: ['./detail-chauffeur.component.scss']
})
export class DetailChauffeurComponent implements OnInit {

  chauffeurDTO: ChauffeurDto;
  addNotificationDTO = new NotationDto();
  notificationListDTO: NotationDto[];
  addRatingForm: NgForm;
  formData:  FormGroup;
  paramId :any = 0;
  chauffId: any = 0;
  searchMode: boolean = false;
  isLoggedIn = false;
  username: string;

  pdfSrc;

  userId: any;
  ref: string;

  starRating = 0;
  currentRating: number = 0;
  maxRatingValue = 5;

  constructor(public chauffService: ChauffeurService,
              public ratingService: NotationService,
              public tokenService: TokenStorageService,
              private toastr: ToastrService,
              public router: Router,
              public fb: FormBuilder,
              public route: ActivatedRoute,
  ) { }

  get f() { return this.formData.controls; }

  ngOnInit(): void {
    this.infoForm();
    this.paramId = this.route.snapshot.paramMap.get('id');
    console.log('Param--', this.paramId);
    if(this.paramId  && this.paramId  > 0){
      this.getChauffeurDTOById(this.paramId);
    }

    this.getListOfTop4RatingOrderByCreatedDateDescByChauffeurId();

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

  onAddNotation() {
    console.log(this.formData.value);
    console.log(this.formData.value, this.ref, this.ratingService.id);
    this.ratingService.addRatingToChauffeur(this.formData.value, this.paramId, this.ratingService.id)
      .subscribe(
      (response: NotationDto) => {
        this.toastr.success('au chauffeur','Note Attribué avec succès', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("/").then(() => {

        });
      
      },
      (error: HttpErrorResponse) => {
        this.toastr.error("Tous les champs doivent etre remplis")
      }

    );

  }


  getListOfTop4RatingOrderByCreatedDateDescByChauffeurId() {
    this.chauffId = this.route.snapshot.paramMap.get('id');
    console.log("ChauufID " +this.chauffId);
    this.ratingService.getTop4RatingByChauffeurIdOrderByCreatedDateDesc(this.chauffId)
      .subscribe(
      (response: NotationDto[]) => {
        this.notificationListDTO = response;
        console.log(this.notificationListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


}
