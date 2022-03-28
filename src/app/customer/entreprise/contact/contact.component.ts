import { HttpErrorResponse } from '@angular/common/http';
import { Router, NavigationEnd } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { EmailService } from './../../../services/email.service';
import { NgForm } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { EmailDto } from './../../../models/email';

@Component({
  selector: 'app-contact',
  templateUrl: './contact.component.html',
  styleUrls: ['./contact.component.scss']
})
export class ContactComponent implements OnInit {

  addEditEmailDTO: EmailDto = new EmailDto();

  mySubscription: any;
  addEmailForm: NgForm;

  constructor(private crudApi: EmailService,
              private toastr: ToastrService,
              private router: Router
  ) {
     //--for reload componant
     this.router.routeReuseStrategy.shouldReuseRoute = () => false;
     this.mySubscription = this.router.events.subscribe((event) => {
       if (event instanceof NavigationEnd) {
         // Trick the Router into believing it's last link wasn't previously loaded
         this.router.navigated = false;
       }
     });
  }

  ngOnInit(): void {}

  sendMailToManager() {
    this.crudApi.senEmailToManager(this.addEditEmailDTO).subscribe(
      (response: EmailDto) => {
        this.toastr.success('Nous vous recontacterez très rapidement','Merci de nous avoir contacté', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.router.navigateByUrl("contact").then(() => {
          });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }


}
