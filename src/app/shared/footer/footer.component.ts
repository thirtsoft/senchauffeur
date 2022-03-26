import { ToastrService } from 'ngx-toastr';
import { TokenStorageService } from './../../auth/security/token-storage.service';
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

  info: any;
  roles: string[];

  isLoggedIn = false;
  showUserBoard = false;

  username: string;
  email: String;
  userId;
  currentTime: number = 0;

  constructor(private crudApi: NewsleterService,
              private tokenService: TokenStorageService,
              private toastr: ToastrService,
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

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;
      this.showUserBoard = this.roles.includes('ROLE_USER');
      this.userId = user.id;

    }

  }

  onAddNewsleter() {
    console.log(this.addEditNewsleterDTO);
    this.crudApi.addNewsleterDTO(this.addEditNewsleterDTO).subscribe(
      (response: NewsleterDto) => {
        this.toastr.success('à notre Newsleter','Vous etes bien inscrit', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
          this.router.navigateByUrl("/").then(() => {
          });
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

  }

}
