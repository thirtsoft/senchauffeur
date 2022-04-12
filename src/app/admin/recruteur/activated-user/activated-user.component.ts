import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { UtilisateurDto } from 'src/app/models/utilisateur';
import { UtilisateurService } from 'src/app/services/utilisateur.service';

@Component({
  selector: 'app-activated-user',
  templateUrl: './activated-user.component.html',
  styleUrls: ['./activated-user.component.scss']
})
export class ActivatedUserComponent implements OnInit {

  listData : UtilisateurDto[];

  constructor(public crudApi: UtilisateurService,
              public toastr: ToastrService,
              public fb: FormBuilder,
              private router : Router
  ) { }

  ngOnInit() {
    if (this.crudApi.choixmenu == "A"){
      this.infoForm()
    };
  }

  infoForm() {
    this.crudApi.formData = this.fb.group({
      id: null,
      active: ['', [Validators.required]],
    });
  }

  getListUtilisateur() {
    this.crudApi.getAllUtilisateurDtosOrderByIdDesc()
    .subscribe(
      response =>{
        this.listData = response;
      }
    );
  }

  ResetForm() {
    this.crudApi.formData.reset();
  }

  onSubmit() {
    console.log(this.crudApi.formData.value);
    console.log(this.crudApi.formData.value.active);
    this.crudApi.activatedUser(this.crudApi.formData.value.id,this.crudApi.formData.value.active)
    .subscribe( data => {
      this.toastr.success("Recruteur Activé avec Succès");
    });
  }

}
