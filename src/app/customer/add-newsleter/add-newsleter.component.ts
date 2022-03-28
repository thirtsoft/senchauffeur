import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { NewsleterService } from './../../services/newsleter.service';
import { NgForm } from '@angular/forms';
import { NewsleterDto } from './../../models/newsleter';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-newsleter',
  templateUrl: './add-newsleter.component.html',
  styleUrls: ['./add-newsleter.component.scss']
})
export class AddNewsleterComponent implements OnInit {

  addEditNewsleterDTO: NewsleterDto = new NewsleterDto();

  mySubscription: any;
  addNewsleterForm: NgForm;

  constructor(private crudApi: NewsleterService,
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

  onAddNewsleter() {
    console.log(this.addEditNewsleterDTO);
    this.crudApi.addNewsleterDTO(this.addEditNewsleterDTO).subscribe(
      (response: NewsleterDto) => {
        this.toastr.success('à notre Newsleter','Vous étes bien inscrit', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.router.navigateByUrl("").then(() => {
          });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

}
