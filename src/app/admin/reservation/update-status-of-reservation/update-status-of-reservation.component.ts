import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ReservationService } from './../../../services/reservation.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Reservation, ReservationDto } from './../../../models/reservation';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-update-status-of-reservation',
  templateUrl: './update-status-of-reservation.component.html',
  styleUrls: ['./update-status-of-reservation.component.scss']
})
export class UpdateStatusOfReservationComponent implements OnInit {

  reservationDTOList : ReservationDto[];

  statusListData= ['ENCOURS','VALIDEE','REJETEE', 'ARCHIVEE'];

  formData:  FormGroup;

  constructor(public crudApi: ReservationService,
              public toastr: ToastrService,
              public fb: FormBuilder,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<UpdateStatusOfReservationComponent>,
  ) { }

  ngOnInit() {
    this.infoForm()

  }

  infoForm() {
    this.crudApi.formData = this.fb.group({
      id: [this.crudApi.formData.value.id,  [Validators.required]],
      status: [this.crudApi.formData.value.status, [Validators.required]],
    });
  }

  getListReservationDTOs() {
    this.crudApi.getTypeReservationDtoOrderByIdDesc().subscribe(
      response =>{
        this.reservationDTOList = response;
      }
    );
  }

  ResetForm() {
    this.formData.reset();
  }

  onSubmit() {
    this.crudApi.updateStatusOfReservationDTO(this.crudApi.formData.value.id,this.crudApi.formData.value.status).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.warning('avec succès','Status Reservation Modifié', {
        timeOut: 1500,
        positionClass: 'toast-top-right',
      });
      this.router.navigateByUrl("admin/accueil/reservations").then(() => {
        window.location.reload();
      });
    });
  }


}
