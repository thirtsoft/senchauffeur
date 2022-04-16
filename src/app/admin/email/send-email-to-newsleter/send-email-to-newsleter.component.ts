import { Component, OnInit, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';

import { ToastrService } from 'ngx-toastr';
import { EmailService } from './../../../services/email.service';
import { NewsleterService } from './../../../services/newsleter.service';
import { NewsleterDto } from './../../../models/newsleter';

@Component({
  selector: 'app-send-email-to-newsleter',
  templateUrl: './send-email-to-newsleter.component.html',
  styleUrls: ['./send-email-to-newsleter.component.scss']
})
export class SendEmailToNewsleterComponent implements OnInit {

  newsletterDTO: NewsleterDto = new NewsleterDto();

  constructor(public crudApi: NewsleterService,
              private mailService: EmailService,
              public fb: FormBuilder,
              public toastr: ToastrService,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<SendEmailToNewsleterComponent>,
  ) { }

  ngOnInit() {
    if (this.crudApi.choixmenu == "A"){
      this.infoForm()
    };
  }

  infoForm() {
    this.crudApi.dataForm = this.fb.group({
      id: null,
      emailVisiteur: ['', [Validators.required]],
      subject: ['', [Validators.required]],
      message: ['', [Validators.required]],
    });

  }

  onSubmit() {
    this.mailService.senEmailToVisitor(this.crudApi.dataForm.value).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.success("Email Envoyé avec Succès");
      this.router.navigate(['/admin/accueil/newsletters']);
    });
  }

}
