import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { JetonDto } from './../models/jeton';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JetonService {

  public apiServerUrl = environment.apiBaseUrl;

  //public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-JetonDtos/v1";

  constructor(private http: HttpClient) {
  }

  /*************************** JetonDto ********************/
  public getJetonDTOs(): Observable<JetonDto[]> {
    return this.http.get<JetonDto[]>(`${this.apiServerUrl}/jetons/all`);
  }

  public getJetonDTOsOrderByIdDesc(): Observable<JetonDto[]> {
    return this.http.get<JetonDto[]>(`${this.apiServerUrl}/jetons/searchJetonsByIdDesc`);
  }

  public getJetonDTOById(idJetonDto: number): Observable<JetonDto> {
    return this.http.get<JetonDto>(`${this.apiServerUrl}/jetons/findById/${idJetonDto}`);
  }

  public addJetonDTO(jetonDto: JetonDto): Observable<JetonDto> {
    return this.http.post<JetonDto>(`${this.apiServerUrl}/jetons/create`, jetonDto);
  }

  public updateJetonDTO(idJetonDto: number, jetonDto: JetonDto): Observable<JetonDto> {
    return this.http.put<JetonDto>(`${this.apiServerUrl}/jetons/update/${idJetonDto}`, jetonDto);
  }

  public deleteJetonDTO(idJetonDto: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/jetons/delete/${idJetonDto}`);
  }

  public countNumberOfJetonDtos(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/jetons/NumbersOfJetonDtos`);
  }

}
