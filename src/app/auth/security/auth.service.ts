import { Injectable } from '@angular/core';
import { Login } from './login';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { TokenStorageService } from './token-storage.service';
import { FormGroup } from '@angular/forms';
import { ProfileInfo, UpdateUsernameInfo, UpdatePasswordInfo, UpdateProfilInfo } from './profile-info';
import { Observable, throwError } from 'rxjs';
import { Register } from './register';
import { catchError, map } from 'rxjs/operators';

const AUTH_API = 'http://localhost:8081/shop-mania/v1/auth/';

const TOKEN_KEY = 'AuthToken';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private loginUrl = 'http://localhost:8081/shop-sen-chauffeurs/v1/auth/authenticated';
  private baseUrl = 'http://localhost:8081/api/auth';
  private baseUrl_1 = 'http://localhost:8081/alAmine';

  choixmenu : string  = 'A';
  dataForm:  FormGroup;
  listData: ProfileInfo;
  listDataUsername: UpdateUsernameInfo;

  islogin = false ;

  profileInfo: ProfileInfo = {} as ProfileInfo;
  userId;
  user;
  currentUser = {};

  constructor(private http: HttpClient,
    private tokenService: TokenStorageService,
    private route: ActivatedRoute,
    private router: Router) {
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
    return this.http.get(`${this.baseUrl_1}/utilisateurs/${id}`, httpOptions).pipe(
      map((res: Response) => {
        return res || {}
      }),
      catchError(this.handleError)
    )
  }

  getUserByUsername(username: string): Observable<any> {
    return this.http.get<any>(this.baseUrl + `/getUserByUsername/${username}`);
  }
  getUserById(id: any) {
    return this.http.get(`${this.baseUrl_1}/utilisateurs/${id}`);
  }

  updateProfil(item: UpdateProfilInfo): Observable<UpdateProfilInfo> {
    return this.http.patch<UpdateProfilInfo>("//localhost:8081/alAmine/updateProfil", {
      name: item.name,
      username: item.username,
      email: item.email,
      password: item.password,
      confirmPassword: item.confirmPassword
    }, httpOptions);

  }

  updateUsername(item: UpdateUsernameInfo): Observable<UpdateUsernameInfo> {
    return this.http.patch<UpdateUsernameInfo>("//localhost:8081/alAmine/updateUsername", {
      username: item.username,
      newUsername: item.newUsername
    }, httpOptions);

  }

  updatePassword(item: UpdatePasswordInfo): Observable<UpdatePasswordInfo> {
    return this.http.patch<UpdatePasswordInfo>("//localhost:8081/alAmine/updatePassword", {
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
