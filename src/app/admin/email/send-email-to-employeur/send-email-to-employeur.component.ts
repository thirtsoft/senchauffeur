import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { EmailService } from './../../../services/email.service';
import { UtilisateurService } from './../../../services/utilisateur.service';
import { UtilisateurDto } from './../../../models/utilisateur';

@Component({
  selector: 'app-send-email-to-employeur',
  templateUrl: './send-email-to-employeur.component.html',
  styleUrls: ['./send-email-to-employeur.component.scss']
})
export class SendEmailToEmployeurComponent implements OnInit {

  userDTO: UtilisateurDto = new UtilisateurDto();

  constructor(public crudApi: UtilisateurService,
              private mailService: EmailService,
              public fb: FormBuilder,
              public toastr: ToastrService,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<SendEmailToEmployeurComponent>,
  ) { }

  ngOnInit() {
    if (this.crudApi.choixmenu == "A"){
      this.infoForm()
    };
  }

  infoForm() {
    this.crudApi.dataForm = this.fb.group({
      id: null,
      email: ['', [Validators.required]],
      subject: ['', [Validators.required]],
      message: ['', [Validators.required]],
    });

  }

  onSubmit() {
    this.mailService.senEmailToRecruteur(this.crudApi.dataForm.value).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.success("Email Envoyé avec Succès");
      this.router.navigate(['/admin/accueil/recruteurs']);
    });
  }


}
