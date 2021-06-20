import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { AnnonceService } from './../../../services/annonce.service';
import { AnnonceDto } from './../../../models/annonce';

@Component({
  selector: 'app-job-detail',
  templateUrl: './job-detail.component.html',
  styleUrls: ['./job-detail.component.scss']
})
export class JobDetailComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  annonceDTO: AnnonceDto;
  reference;
  currentTime;

  searchMode: boolean = false;

  constructor(private annonceService: AnnonceService,
              private router: Router,
              private fb: FormBuilder,
              private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=> {
      this.getAnnonceDTOByReference();
    });
  }

  public getAnnonceDTOByReference() {
    const ref: string = this.route.snapshot.paramMap.get('reference');
    this.annonceService.getAnnonceDTOByReference(ref).subscribe(
      response => {
        this.annonceDTO = response;
        console.log(this.annonceDTO);
        }
        ,(error: HttpErrorResponse) => {
      alert(error.message);
    });


  }

  getTS() {
    return this.currentTime;
  }


}
