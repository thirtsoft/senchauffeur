import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from './token-storage.service';
import { FormGroup } from '@angular/forms';
import { ProfileInfo, UpdateUsernameInfo, UpdatePasswordInfo, UpdateProfilInfo } from './profile-info';
import { Observable, throwError } from 'rxjs';
import { Register } from './register';
import { catchError, map } from 'rxjs/operators';

import { UtilisateurDto } from './../../models/utilisateur';
import { Login } from './login';

import { environment } from './../../../environments/environment';

const AUTH_API = 'http://localhost:8081/sen-chauffeurs/v1/auth/';
//const AUTH_API = 'https://server-chauffeur.herokuapp.com/sen-chauffeurs/v1/auth/';


const TOKEN_KEY = 'AuthToken';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiServerUrl = environment.apiBaseUrl;

  loginUrl = 'http://localhost:8081/sen-chauffeurs/v1/auth/authenticated';
//  public loginUrl = 'https://server-chauffeur.herokuapp.com/sen-chauffeurs/v1/auth/authenticated';

  choixmenu : string  = 'A';
  dataForm:  FormGroup;
  listDataUsername: UpdateUsernameInfo;
  listData: UtilisateurDto;
  listDataProfil: ProfileInfo;

  profileInfo: ProfileInfo = {} as ProfileInfo;

  userId;
  user;
  islogin = false ;
  currentUser = {};


  constructor(private http: HttpClient,
              public tokenService: TokenStorageService,
              public route: ActivatedRoute,
              public router: Router) {
  }

  signUp(info: Register): Observable<Register> {
    return this.http.post<Register>(AUTH_API + 'registerUser', info , httpOptions);
  }

  attemptAuth(credentials: Login): Observable<any> {
    return this.http.post(this.loginUrl, {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
    this.islogin = true;
  }

  getUserProfile(id): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/utilisateurs/findById/${id}`, httpOptions).pipe(
      map((res: Response) => {
        return res || {}
      }),
      catchError(this.handleError)
    )
  }

  getUserByUsername(username: string): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + `/getUserByUsername/${username}`);
  }
  getUserById(id: any) {
    return this.http.get(`${this.apiServerUrl}/utilisateurs/findById/${id}`);
  }

  updateProfil(item: UpdateProfilInfo): Observable<UpdateProfilInfo> {
    return this.http.patch<UpdateProfilInfo>(`${this.apiServerUrl}/utilisateurs/updateProfil`, {
      name: item.name,
      username: item.username,
      email: item.email,
      password: item.password,
      confirmPassword: item.confirmPassword
    }, httpOptions);

  }

  updateUsername(item: UpdateUsernameInfo): Observable<UpdateUsernameInfo> {
    return this.http.patch<UpdateUsernameInfo>(`${this.apiServerUrl}/utilisateurs/updateUsernameOfUserByUsername`, {
      username: item.username,
      newUsername: item.newUsername
    }, httpOptions);

  }

  updatePassword(item: UpdatePasswordInfo): Observable<UpdatePasswordInfo> {
    return this.http.patch<UpdatePasswordInfo>(`${this.apiServerUrl}/utilisateurs/updatePasswordByUsername`, {
      username: item.username,
      oldPassword: item.oldPassword,
      newPassword: item.newPassword
    }, httpOptions);
  }

  handleError(error: HttpErrorResponse) {
    let msg = '';
    if (error.error instanceof ErrorEvent) {
      // client-side error
      msg = error.error.message;
    } else {
      // server-side error
      msg = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    return throwError(msg);
  }


}
