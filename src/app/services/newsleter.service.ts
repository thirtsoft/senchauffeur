import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { NewsleterDto } from './../models/newsleter';

import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NewsleterService {

  public apiServerUrl = environment.apiBaseUrl;

  //public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-NewsleterDtos/v1";

  constructor(private http: HttpClient) {
  }

  /*************************** NewsleterDto ********************/
  public getNewsleterDTOs(): Observable<NewsleterDto[]> {
    return this.http.get<NewsleterDto[]>(`${this.apiServerUrl}/newsleters/all`);
  }

  public getNewsleterDTOsOrderByIdDesc(): Observable<NewsleterDto[]> {
    return this.http.get<NewsleterDto[]>(`${this.apiServerUrl}/newsleters/searchNewsleterOrderByIdDesc`);
  }

  public getNewsleterDTOById(idNewsleter: number): Observable<NewsleterDto> {
    return this.http.get<NewsleterDto>(`${this.apiServerUrl}/newsleters/findById/${idNewsleter}`);
  }

  public addNewsleterDTO(newsleterDto: NewsleterDto): Observable<NewsleterDto> {
    return this.http.post<NewsleterDto>(`${this.apiServerUrl}/newsleters/create`, newsleterDto);
  }

  public updateNewsleterDTO(idNewsleter: number, newsleterDto: NewsleterDto): Observable<NewsleterDto> {
    return this.http.put<NewsleterDto>(`${this.apiServerUrl}/newsleters/update/${idNewsleter}`, newsleterDto);
  }

  public deleteNewsleterDTO(idNewsleter: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/newsleters/delete/${idNewsleter}`);
  }

  public countNumberOfNewsleterDTOs(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/newsleters/NumbersOfNewsleters`);
  }

}
