import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { HistoriqueLogin, HistoriqueLoginDto } from './../models/historique-login';

import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HistoriqueLoginService {

  public apiServerUrl = environment.apiBaseUrl;

  //public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-historiqueLogins/v1";

  constructor(private http: HttpClient) {
  }

  /*************************** HistoriqueLogin ********************/
  public getHistoriqueLogins(): Observable<HistoriqueLoginDto[]> {
    return this.http.get<HistoriqueLoginDto[]>(`${this.apiServerUrl}/historiqueLogins/all`);
  }

  public getHistoriqueLoginsOrderByIdDesc(): Observable<HistoriqueLoginDto[]> {
    return this.http.get<HistoriqueLoginDto[]>(`${this.apiServerUrl}/historiqueLogins/searchHistoriqueLoginByIdDesc`);
  }

  public getHistoriqueLoginById(idHistoriqueLogin: number): Observable<HistoriqueLoginDto> {
    return this.http.get<HistoriqueLoginDto>(`${this.apiServerUrl}/historiqueLogins/findById/${idHistoriqueLogin}`);
  }

  public addHistoriqueLogin(historiqueLoginDto: HistoriqueLoginDto): Observable<HistoriqueLoginDto> {
    return this.http.post<HistoriqueLoginDto>(`${this.apiServerUrl}/historiqueLogins/create`, historiqueLoginDto);
  }

  public updateHistoriqueLogin(idHistoriqueLogin: number, historiqueLoginDto: HistoriqueLoginDto): Observable<HistoriqueLoginDto> {
    return this.http.put<HistoriqueLoginDto>(`${this.apiServerUrl}/historiqueLogins/update/${idHistoriqueLogin}`, historiqueLoginDto);
  }

  public deleteHistoriqueLogin(idHistoriqueLogin: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/historiqueLogins/delete/${idHistoriqueLogin}`);
  }

  public countNumberOfhistoriqueLogins(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/historiqueLogins/NumbersOfHistoriqueLogins`);
  }

}
