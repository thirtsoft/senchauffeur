import { ToastrService } from 'ngx-toastr';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from './../security/token-storage.service';
import { AuthService } from './../security/auth.service';
import { Login } from './../security/login';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];
  loginInfo: Login;

  constructor(
        private authService: AuthService,
        private tokenStorage: TokenStorageService,
        private toastr: ToastrService,
        private router: Router
  ) {}

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.roles = this.tokenStorage.getUser().roles;
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
      console.log("Login Success");
      this.toastr.success('bien connecté','Vous etes', {
        timeOut: 1500,
        positionClass: 'toast-top-right',
        });
        this.router.navigateByUrl("").then(() => {
        });
      },
      error => {
        console.log(error);
        this.toastr.error('veuillez vérifier vos identifiants','Error de connection', {
          timeOut: 1500,
          positionClass: 'toast-top-right',
          });
        this.errorMessage = error.error.message;
        this.isLoginFailed = true;
      }
    );
  }



}
