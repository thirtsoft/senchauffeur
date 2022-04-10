import { Router } from '@angular/router';
import { TokenStorageService } from './../../../auth/security/token-storage.service';
import { Component, OnInit } from '@angular/core';

import { Login } from './../../../auth/security/login';
import { AuthService } from './../../../auth/security/auth.service';
import { UtilisateurService } from 'src/app/services/utilisateur.service';
import { UtilisateurDto } from 'src/app/models/utilisateur';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.scss']
})
export class SignInComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  loginInfo: Login;
  utilisateurDTOs: UtilisateurDto[];

  showAdminBoard = false;
  showManagerBoard = false;
  showGestionnaireBoard = false;
  showUserBoard = false;

  constructor(private authService: AuthService,
              private userService: UtilisateurService,
              private tokenStorage: TokenStorageService,
              private toastr: ToastrService,
              private router: Router,
  ) {}

  ngOnInit() {
    if(this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showGestionnaireBoard = this.roles.includes("ROLE_GESTIONNAIRE");
      this.showManagerBoard = this.roles.includes('ROLE_MANAGER');
      this.showUserBoard = this.roles.includes('ROLE_USER');
    }
  }

  onSubmit() {
    this.loginInfo = new Login(
      this.form.username,
      this.form.password,
    );

    this.authService.attemptAuth(this.loginInfo).subscribe(data => {
      this.tokenStorage.saveToken(data.accessToken);
      this.tokenStorage.saveUser(data);
      this.tokenStorage.saveUsername(data.username);

      this.isLoginFailed = false;
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;

      if(this.tokenStorage.getUser().roles == 'ROLE_USER') {
        this.toastr.error("Vous navez pas l'accès à cette partie");
        this.logout();
        window.location.reload();
      } else {
        this.router.navigateByUrl("admin/accueil");  
      }
    },
    error => {
      console.log(error);
      this.errorMessage = error.error.message;
      this.toastr.error("Votre nom utilisateur ou mot de passe est incorrect");
      this.isLoginFailed = true;
    }
    );
  }

  logout(): void {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('admin');
  }


}
