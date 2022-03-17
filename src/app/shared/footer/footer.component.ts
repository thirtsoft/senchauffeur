import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';

import { NewsleterService } from './../../services/newsleter.service';
import { NewsleterDto } from './../../models/newsleter';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  addEditNewsleterDTO: NewsleterDto = new NewsleterDto();

  mySubscription: any;
  addNewsleterForm: NgForm;

  constructor(private crudApi: NewsleterService,
              private actRoute: ActivatedRoute,
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
        alert("Vous étes bien inscrit à notre Newsleter");
        this.router.navigate(['/']);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

}