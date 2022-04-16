import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { FormBuilder, Validators } from '@angular/forms';
import { EmailService } from './../../../services/email.service';
import { EmailDto } from './../../../models/email';
import { Component, OnInit, Inject } from '@angular/core';

@Component({
  selector: 'app-response-mail',
  templateUrl: './response-mail.component.html',
  styleUrls: ['./response-mail.component.scss']
})
export class ResponseMailComponent implements OnInit {

  constructor(public mailService: EmailService,
              public fb: FormBuilder,
              public toastr: ToastrService,
              private router : Router,
              @Inject(MAT_DIALOG_DATA)  public data,
              public dialogRef:MatDialogRef<ResponseMailComponent>,
  ) { }

  ngOnInit() {
    if (this.mailService.choixmenu == "A"){
      this.infoForm()
    };
  }

  infoForm() {
    this.mailService.dataForm = this.fb.group({
      id: null,
      recipient: ['', [Validators.required]],
      subject: ['', [Validators.required]],
      message: ['', [Validators.required]],
    });

  }

  onSubmit() {
    console.log(this.mailService.dataForm.value);
    this.mailService.responseToEmailByManager(this.mailService.dataForm.value).
    subscribe( data => {
      this.dialogRef.close();
      this.toastr.success("Email Envoyé avec Succès");
      this.router.navigate(['/admin/accueil/emails']);
    });
  }


}
