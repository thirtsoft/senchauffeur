import { HttpErrorResponse } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, NgForm, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AuthService } from 'src/app/auth/security/auth.service';
import { UpdatePasswordInfo, UpdateProfileInfo } from 'src/app/auth/security/profile-info';
import { TokenStorageService } from 'src/app/auth/security/token-storage.service';
import { Role } from 'src/app/models/role';
import { UtilisateurDto } from 'src/app/models/utilisateur';
import { UtilisateurService } from 'src/app/services/utilisateur.service';

@Component({
  selector: 'app-update-username',
  templateUrl: './update-username.component.html',
  styleUrls: ['./update-username.component.scss']
})
export class UpdateUsernameComponent implements OnInit {

  formDataProfile: UpdateProfileInfo  = new UpdateProfileInfo();

  constructor(public crudApi: AuthService,
              private tokenService: TokenStorageService,
              public toastr: ToastrService,
              public fb: FormBuilder,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<UpdateUsernameComponent>,
  ) { }

  ngOnInit() {
  }

  infoForm(form?: NgForm) {
    if (form = null)
      form.resetForm();
    this.formDataProfile = {
      username: '',
      name: '',
      newUsername: '',
      email: '',
      mobile: '',
      addressRecruteur: '',
    };
  }

  ResetForm() {
    this.crudApi.dataForm.reset();
  }

  onSubmit() {
    console.log(this.formDataProfile);
    this.crudApi.updateProfilByUsername(this.formDataProfile).
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
