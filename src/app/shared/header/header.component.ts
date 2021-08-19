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
  private roles: string[];

  isLoggedIn = false;
  showAdminBoard = false;
  showUserBoard = false;
  showVendeurBoard = false;

  username: string;
  email: String;
  userId;
  photo;
  currentTime: number = 0;

  constructor(private tokenService: TokenStorageService,
              private router: Router
              )
  {}

  ngOnInit() {
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showVendeurBoard = this.roles.includes("ROLE_VENDEUR");
      this.showUserBoard = this.roles.includes('ROLE_USER');

      this.username = user.username;
      this.userId = user.id;
      this.photo = user.photo;

      console.log("Username : " + this.username);

    }
  }


  logout() {
    this.tokenService.signOut();
    this.router.navigateByUrl("").then(() => {
      window.location.reload();
    });

  }

  getProfile() {
    this.router.navigate(['/home/profile/' + this.userId]);
  }

  getTS() {
    return this.currentTime;
  }

  /*
  reloadPage() {
    window.location.reload();
  }

  reloadCurrentRoute() {
    this.location.reload();
  }
  */

}
