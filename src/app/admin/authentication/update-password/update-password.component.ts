import { Component, OnInit, Inject } from '@angular/core';

import { Router } from '@angular/router';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { FormBuilder, NgForm } from '@angular/forms';

import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { UpdatePasswordInfo } from './../../../auth/security/profile-info';
import { AuthService } from './../../../auth/security/auth.service';

@Component({
  selector: 'app-update-password',
  templateUrl: './update-password.component.html',
  styleUrls: ['./update-password.component.scss']
})
export class UpdatePasswordComponent implements OnInit {

  formDataProfile: UpdatePasswordInfo  = new UpdatePasswordInfo();

  constructor(public crudApi: AuthService,
              private tokenService: TokenStorageService,
              public toastr: ToastrService,
              public fb: FormBuilder,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<UpdatePasswordComponent>,
  ) { }

  ngOnInit() {
  }

  infoForm(form?: NgForm) {
    if (form = null)
      form.resetForm();
    this.formDataProfile = {
      username: '',
      oldPassword: '',
      newPassword: '',
    };
  }

  ResetForm() {
    this.crudApi.dataForm.reset();
  }

  onSubmit() {
    console.log(this.formDataProfile);
    this.crudApi.updatePassword(this.formDataProfile).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.warning('veuillez vous reconnectez','Votre Mot de passe à été modifie avec success', {
        timeOut: 1500,
        positionClass: 'toast-top-right',
      });
      this.logout();
      console.log(data);
    });

  }

  logout(){
    this.tokenService.signOut();
    this.router.navigateByUrl('admin');
  }

}
