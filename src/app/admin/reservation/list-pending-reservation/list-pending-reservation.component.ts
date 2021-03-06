import { Component, OnInit, Inject } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';

import { ToastrService } from 'ngx-toastr';
import { ReservationService } from './../../../services/reservation.service';
import { ReservationDto } from './../../../models/reservation';

import { UpdateStatusOfReservationComponent } from './../update-status-of-reservation/update-status-of-reservation.component';


@Component({
  selector: 'app-list-pending-reservation',
  templateUrl: './list-pending-reservation.component.html',
  styleUrls: ['./list-pending-reservation.component.scss']
})
export class ListPendingReservationComponent implements OnInit {

  listReservationDTO: ReservationDto[];

  id : number;
  p : number=1;
  searchText;

  constructor(public crudApi: ReservationService,
              private matDialog: MatDialog,
              private router: Router,
              public toastr: ToastrService,
              public fb: FormBuilder,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef:MatDialogRef<UpdateStatusOfReservationComponent>,
  ){}

  ngOnInit(): void {
    this.getListPendingResvation();
  }

  public getListPendingResvation() {
    this.crudApi.getListPendingReservationsDtOs().subscribe(
      (response: ReservationDto[]) => {
        this.listReservationDTO = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }

  addEditStatusReservation(item : ReservationDto) {
    this.crudApi.choixmenu == 'M';
    this.crudApi.formData = this.fb.group(Object.assign({},item));
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.disableClose = true;
    dialogConfig.width="50%";
    this.matDialog.open(UpdateStatusOfReservationComponent, dialogConfig);

  }


  onDeleteReservation(id: number): void{
    if (window.confirm('Etes-vous sure de vouloir supprimer ce Reservation ?')) {
      this.crudApi.deleteReservationDtO(id).subscribe(data => {
        this.toastr.error('avec succ??s','Reservation supprim??', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
           this.getListPendingResvation();
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
      );
    }
  }

  viewAllReservations() {
    this.router.navigate(['/admin/accueil/reservations']);
  }

}
