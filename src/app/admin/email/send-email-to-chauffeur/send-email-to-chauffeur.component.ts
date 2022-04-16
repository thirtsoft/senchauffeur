import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, Validators } from '@angular/forms';
import { EmailService } from './../../../services/email.service';
import { ChauffeurService } from './../../../services/chauffeur.service';
import { ChauffeurDto } from './../../../models/chauffeur';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-send-email-to-chauffeur',
  templateUrl: './send-email-to-chauffeur.component.html',
  styleUrls: ['./send-email-to-chauffeur.component.scss']
})
export class SendEmailToChauffeurComponent implements OnInit {

  chauffDTO: ChauffeurDto = new ChauffeurDto();

  constructor(public crudApi: ChauffeurService,
              private mailService: EmailService,
              public fb: FormBuilder,
              public toastr: ToastrService,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<SendEmailToChauffeurComponent>,
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
    this.mailService.sendToChauffeur(this.crudApi.dataForm.value).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.success("Email Envoyé avec Succès");
      this.router.navigate(['/admin/accueil/chauffeurs']);
    });
  }


}
