import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from './token-storage.service';
import { FormGroup } from '@angular/forms';
import { ProfileInfo, UpdateUsernameInfo, UpdatePasswordInfo, UpdateProfilInfo, UpdateProfileInfo } from './profile-info';
import { Observable, throwError } from 'rxjs';
import { Register } from './register';
import { catchError, map } from 'rxjs/operators';

import { UtilisateurDto } from './../../models/utilisateur';
import { Login } from './login';

import { environment } from './../../../environments/environment';

//const AUTH_API = 'http://localhost:8081/sen-chauffeurs/v1/auth/';
//const AUTH_API = 'http://62.171.128.8:8081/sen-chauffeurs/v1/auth/';
const AUTH_API = 'http://sunuchauffeur.com/api/v1/auth/';

const TOKEN_KEY = 'AuthToken';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiServerUrl = environment.apiBaseUrl;

 // loginUrl = 'http://localhost:8081/sen-chauffeurs/v1/auth/authenticated';
  //loginUrl = 'http://62.171.128.8:8081/sen-chauffeurs/v1/auth/authenticated';

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

  public signUp(info: Register): Observable<Register> {
    return this.http.post<Register>(AUTH_API + 'registerUser', info , httpOptions);
  }

  public attemptAuth(credentials: Login): Observable<any> {
    return this.http.post(AUTH_API + 'authenticated', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
    this.islogin = true;
  }

  public getUserProfile(id): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/utilisateurs/findById/${id}`, httpOptions).pipe(
      map((res: Response) => {
        return res || {}
      }),
      catchError(this.handleError)
    )
  }

  public getUserByUsername(username: string): Observable<any> {
    return this.http.get<any>(this.apiServerUrl + `/getUserByUsername/${username}`);
  }

  public getUserById(id: any) {
    return this.http.get(`${this.apiServerUrl}/utilisateurs/findById/${id}`);
  }

  public updateProfil(id: number, item: UtilisateurDto): Observable<UtilisateurDto> {
    return this.http.put<UtilisateurDto>(`${this.apiServerUrl}/utilisateurs/update/${id}`, {
      name: item.name,
      username: item.username,
      email: item.email,
      mobile: item.mobile,
      addressRecruteur: item.addressRecruteur
    }, httpOptions);

  }

  public updateProfilByUsername(item: UpdateProfileInfo): Observable<UpdateProfileInfo> {
    return this.http.put<UpdateProfileInfo>(`${this.apiServerUrl}/utilisateurs/updateCustomerProfileByUsername`, {
      name: item.name,
      username: item.username,
      email: item.email,
      mobile: item.mobile,
      addressRecruteur: item.addressRecruteur
    }, httpOptions);

  }

  public updateUsername(item: UpdateUsernameInfo): Observable<UpdateUsernameInfo> {
    return this.http.patch<UpdateUsernameInfo>(`${this.apiServerUrl}/utilisateurs/updateUsernameOfUserByUsername`, {
      username: item.username,
      newUsername: item.newUsername
    }, httpOptions);

  }

  public updatePassword(item: UpdatePasswordInfo): Observable<UpdatePasswordInfo> {
    return this.http.patch<UpdatePasswordInfo>(`${this.apiServerUrl}/utilisateurs/updatePasswordByUsername`, {
      username: item.username,
      oldPassword: item.oldPassword,
      newPassword: item.newPassword
    }, httpOptions);
  }

  public updateUtilisateurDTO(utilisateurId: number, utilisateurDTO: UtilisateurDto): Observable<UtilisateurDto> {
    return this.http.put<UtilisateurDto>(`${this.apiServerUrl}/utilisateurs/update/${utilisateurId}`, utilisateurDTO);
  }

  public handleError(error: HttpErrorResponse) {
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
