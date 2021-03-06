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
  validated: boolean=false;

  size: number = 6;
  currentPage: number = 1;
  totalPages: number;
  pages: Array<number>;

  currentTime: number = 0;

  currentCategoryId: number;

  previousCategoryId: number = 1;

  searchMode: boolean = false;

  constructor(private annonceService: AnnonceService,
              private dashboardService: DashboardService,
              private route: ActivatedRoute,
  ) { }

  ngOnInit(): void {
    this.get6LatestAnnonceListDTOs();
    
    this.getNumberOfAnnonces();
  }

  get6LatestAnnonceListDTOs() {
    this.annonceService.get5LatestAnnonceDTOByOrderByIdDesc().subscribe(
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
    this.dashboardService.countNumberOfAnnonceByStatusValidated().subscribe(data => {
      this.numberOfAnnonce = data;
    });
  }

  getAnnonceListDTOsByPermiss() {
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
