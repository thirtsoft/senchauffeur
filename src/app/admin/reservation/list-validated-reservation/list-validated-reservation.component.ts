import { Component, OnInit } from '@angular/core';

import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';

import { ToastrService } from 'ngx-toastr';
import { ReservationService } from './../../../services/reservation.service';
import { ReservationDto } from './../../../models/reservation';

@Component({
  selector: 'app-list-validated-reservation',
  templateUrl: './list-validated-reservation.component.html',
  styleUrls: ['./list-validated-reservation.component.scss']
})
export class ListValidatedReservationComponent implements OnInit {

  listReservationDTO: ReservationDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(public crudApi: ReservationService,
              private router: Router
  ){}

  ngOnInit(): void {
    this.getListAllValidatedReservation();
  }

  public getListAllValidatedReservation() {
    this.crudApi.getListValidatedReservationsDtOs().subscribe(
      (response: ReservationDto[]) => {
        this.listReservationDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  viewAllReservations() {
    this.router.navigate(['/admin/accueil/reservations']);
  }

}
