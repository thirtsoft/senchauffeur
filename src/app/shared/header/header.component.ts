import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { TokenStorageService } from './../../auth/security/token-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  info: any;
  roles: string[];

  isLoggedIn = false;
  showUserBoard = false;

  username: string;
  email: String;
  userId;
  currentTime: number = 0;

  constructor(private tokenService: TokenStorageService,
              private toastr: ToastrService,
              private router: Router
              )
  {}

  ngOnInit() {
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;
      this.showUserBoard = this.roles.includes('ROLE_USER');

      this.username = user.username;
      this.userId = user.id;

    }
  }

  getProfile() {
    this.router.navigate(['/jobs/' + this.userId]);
  }

  getUserPublication() {
    this.router.navigate(['/jobs/' + this.userId]);
  }


  logout() {
    this.tokenService.signOut();
    this.toastr.info('bye bye a bientot','Vous etes bien déconnecté', {
      timeOut: 1500,
      positionClass: 'toast-top-right',
      });
      this.router.navigateByUrl("/").then(() => {
        window.location.reload();
      });

  }
  getTS() {
    return this.currentTime;
  }

}
