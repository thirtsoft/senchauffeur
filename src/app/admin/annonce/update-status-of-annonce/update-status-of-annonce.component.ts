import { ToastrService } from 'ngx-toastr';
import { AnnonceService } from './../../../services/annonce.service';
import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AnnonceDto } from './../../../models/annonce';

@Component({
  selector: 'app-update-status-of-annonce',
  templateUrl: './update-status-of-annonce.component.html',
  styleUrls: ['./update-status-of-annonce.component.scss']
})
export class UpdateStatusOfAnnonceComponent implements OnInit {

  annonceDTOList : AnnonceDto[];

  statusListData= ['ENCOURS','VALIDEE','REJETEE'];

  formData:  FormGroup;

  constructor(public crudApi: AnnonceService,
              public toastr: ToastrService,
              public fb: FormBuilder,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<UpdateStatusOfAnnonceComponent>,
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

  getListAnnonceDTOs() {
    this.crudApi.getAnnonceDTOOrderByIdDesc().subscribe(
      response =>{
        this.annonceDTOList = response;
      }
    );
  }

  ResetForm() {
    this.formData.reset();
  }

  onSubmit() {
    this.crudApi.updateStatusOfAnnonceDTO(this.crudApi.formData.value.id,this.crudApi.formData.value.status).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.warning('avec succès','Status Commande Modifié', {
        timeOut: 1500,
        positionClass: 'toast-top-right',
      });
      this.router.navigateByUrl("admin/accueil/annonces").then(() => {
        window.location.reload();
      });
     /*  window.alert('status commande modifié avec succès');
      this.toastr.success("Status Appro Modifier avec Succès");
      this.getListCommandeDTOs();
      this.router.navigate(['/admin/commandes']); */
    });
  }

  updateStatusAppro(){
    this.crudApi.updateStatusOfAnnonceDTO(this.formData.value.id,this.formData.value).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.warning('avec succès','status Modifié', {
        timeOut: 1500,
        positionClass: 'toast-top-right',
      });
      this.getListAnnonceDTOs();
      this.router.navigate(['/admin/accueil/annonces']);
    });
  }


}
