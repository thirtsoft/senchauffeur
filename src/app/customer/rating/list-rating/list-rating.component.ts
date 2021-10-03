import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NotationService } from './../../../services/notation.service';
import { NotationDto } from './../../../models/notation';

@Component({
  selector: 'app-list-rating',
  templateUrl: './list-rating.component.html',
  styleUrls: ['./list-rating.component.scss']
})
export class ListRatingComponent implements OnInit {

  notificationListDTO: NotationDto[];

  public currentTime: number = 0;

  searchMode: boolean = false;

  starRating = 0;

  currentRating = 4;

  maxRatingValue = 5;

  constructor(private ratingService: NotationService
  ){ }

  ngOnInit(): void {
    this.getListOfTop3RatingOrderByCreatedDateDesc();

  }


  public getListOfTop3RatingOrderByCreatedDateDesc() {
    this.ratingService.getTop3RatingOrderByCreatedDateDesc()
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
