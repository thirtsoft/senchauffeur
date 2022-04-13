import { UtilisateurService } from './../../services/utilisateur.service';
import { TokenStorageService } from './../../auth/security/token-storage.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { DashboardService } from './../../services/dashboard.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-backend-header',
  templateUrl: './backend-header.component.html',
  styleUrls: ['./backend-header.component.scss']
})
export class BackendHeaderComponent implements OnInit {

  numberOfNotificationInMonth: any;
  numberOfCustomerEmail: any;
  numberOfPendingAnnonce: any;

  info: any;
  roles: string[];

  currentTime: number = 0;

  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;

  username: string;
  email: String;
  userId;
  photo;
  img: boolean;


  constructor(private dashboardService: DashboardService,
              private router: Router,
              public toastr: ToastrService,
              private tokenService: TokenStorageService,
              public userService: UtilisateurService,
  ){}

  ngOnInit(): void {

    this.isLoggedIn = !!this.tokenService.getToken();
    if (this.isLoggedIn) {
      const user = this.tokenService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showGestionnaireBoard = this.roles.includes("ROLE_GESTIONNAIRE");
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');
    
      this.username = user.username;
      this.userId = user.id;
      this.photo = user.photo;

      if (this.userService.getUserAvatar(this.userId) === null)
        this.img = false;
      else this.img = true;

    }

    this.getNumberOfNotificationInMonth();

    this.getNumberOfPendingAnnonce();

    this.getNumberOfEmailInMonth();

  }

  logout() {
    this.tokenService.signOut();
    this.router.navigateByUrl("admin");
  }

  getProfile() {
    this.router.navigate(['/admin/accueil/profile/' + this.userId]);
  }

  goToHistoriqueConnexion() {
    this.router.navigateByUrl("admin/accueil/historiqueLogins");
  }

  getTS() {
    return this.currentTime;
  }

  getNumberOfNotificationInMonth(): void {
    this.dashboardService.countNumberOfNotification()
      .subscribe(response => {
      this.numberOfNotificationInMonth = response;
    });
  }

  getNumberOfPendingAnnonce(): void {
    this.dashboardService.countNumberOfAnnonceByStatusPending()
      .subscribe(response => {
      this.numberOfPendingAnnonce = response;
    });
  }

  getNumberOfEmailInMonth(): void {
    this.dashboardService.countNumberOfEmails()
      .subscribe(response => {
      this.numberOfCustomerEmail = response;
    });
  }


}
