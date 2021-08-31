import { DashboardService } from 'src/app/services/dashboard.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AnnonceService } from './../../../services/annonce.service';
import { AnnonceDto } from './../../../models/annonce';

@Component({
  selector: 'app-recent-job',
  templateUrl: './recent-job.component.html',
  styleUrls: ['./recent-job.component.scss']
})
export class RecentJobComponent implements OnInit {

  annonceListDTO: AnnonceDto[];
  editAnnonceDTO: AnnonceDto;
  numberOfAnnonce;

  id : number;
  p : number=1;
  searchText;
  permId;

  public size: number = 6;
  public currentPage: number = 1;
  public totalPages: number;
  public pages: Array<number>;

  public currentTime: number = 0;

  currentCategoryId: number;

  previousCategoryId: number = 1;

  searchMode: boolean = false;

  constructor(private annonceService: AnnonceService,
              private dashboardService: DashboardService,
              private router: Router,
              private fb: FormBuilder,
              private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {

    this.getAnnonceListDTOs();

    this.getNumberOfAnnonces();


  }

  public getAnnonceListDTOs() {
    this.annonceService.getListAnnonceDTOBySelectedIsTrue().subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log(this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  getNumberOfAnnonces(): void {
    this.dashboardService.countNumberOfAnnonces().subscribe(data => {
      this.numberOfAnnonce = data;
    });
  }


  public getAnnonceListDTOsByPermiss() {
    const hasPermId: boolean = this.route.snapshot.paramMap.has('id');
    this.annonceService.getListAnnonceDTOByPermis(this.permId).subscribe(
      (response: AnnonceDto[]) => {
        this.annonceListDTO = response;
        console.log("Annonce By Permis ID", this.annonceListDTO);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

  // Liste des produits par page
  getAnnonceDTOByPageable() {
    this.annonceService.getListAnnonceDTOByPageable(this.currentPage, this.size)
      .subscribe(data=> {
        this.totalPages = data['totalPages'];
        this.pages = new Array(data['totalPages']);
        this.annonceListDTO = data['content'];
        console.log(data);
      },err=> {
        console.log(err);
      });

  }

  getAnnonceListDTOsBySearchKeyword() {
    const keyword: string = this.route.snapshot.paramMap.get('keyword');
    this.annonceService.getListAnnonceDTOByKeyword(keyword).subscribe(
      data  => {
        this.annonceListDTO = data;
      }

    )

  }

  processResult() {
    return data => {
      this.totalPages = data['totalPages'];
      this.pages = new Array(data['totalPages']);
      this.annonceListDTO = data['content'];
    }

  }

}
