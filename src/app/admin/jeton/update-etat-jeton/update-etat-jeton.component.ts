import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { JetonService } from './../../../services/jeton.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { JetonDto } from './../../../models/jeton';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-update-etat-jeton',
  templateUrl: './update-etat-jeton.component.html',
  styleUrls: ['./update-etat-jeton.component.scss']
})
export class UpdateEtatJetonComponent implements OnInit {

  jetonDTOList : JetonDto[];

  etatListData= ['ENCOURS','EPUISEE'];

  formData:  FormGroup;

  constructor(public crudApi: JetonService,
              public toastr: ToastrService,
              public fb: FormBuilder,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<UpdateEtatJetonComponent>,
  ) { }

  ngOnInit() {
    this.infoForm()

  }

  infoForm() {
    this.crudApi.formData = this.fb.group({
      id: [this.crudApi.formData.value.id,  [Validators.required]],
      etat: [this.crudApi.formData.value.etat, [Validators.required]],
    });
  }

  getListAnnonceDTOs() {
    this.crudApi.getJetonDTOsOrderByIdDesc().subscribe(
      response =>{
        this.jetonDTOList = response;
      }
    );
  }

  ResetForm() {
    this.formData.reset();
  }

  onSubmit() {
    this.crudApi.updateEtatOfJetonDTO(this.crudApi.formData.value.id,this.crudApi.formData.value.etat).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.warning('avec succès','Etat Jeton Modifié', {
        timeOut: 1500,
        positionClass: 'toast-top-right',
      });
      this.router.navigateByUrl("admin/accueil/jetons").then(() => {
      });
    });
  }

}
