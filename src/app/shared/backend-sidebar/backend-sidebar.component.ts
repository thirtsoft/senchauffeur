import { TokenStorageService } from './../../auth/security/token-storage.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-backend-sidebar',
  templateUrl: './backend-sidebar.component.html',
  styleUrls: ['./backend-sidebar.component.scss']
})
export class BackendSidebarComponent implements OnInit {

  roles: string[];

  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  userId;

  constructor(private tokenService: TokenStorageService

  ) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showGestionnaireBoard = this.roles.includes("ROLE_GESTIONNAIRE");
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');
      this.showUserBoard = this.roles.includes('ROLE_USER');

      this.userId = user.id;
    }

  }
}
