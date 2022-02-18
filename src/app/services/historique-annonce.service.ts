import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { HistoriqueAnnonceDto } from './../models/historique-annonce';

import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HistoriqueAnnonceService {

  public apiServerUrl = environment.apiBaseUrl;

  //public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-historiqueAnnonces/v1";

  constructor(private http: HttpClient) {
  }

  /*************************** HistoriqueAnnonceDto ********************/
  public getHistoriqueAnnonceDTOs(): Observable<HistoriqueAnnonceDto[]> {
    return this.http.get<HistoriqueAnnonceDto[]>(`${this.apiServerUrl}/historiqueAnnonces/all`);
  }

  public getHistoriqueAnnonceDTOsOrderByIdDesc(): Observable<HistoriqueAnnonceDto[]> {
    return this.http.get<HistoriqueAnnonceDto[]>(`${this.apiServerUrl}/historiqueAnnonces/searchHistoriqueAnnonceByIdDesc`);
  }

  public getHistoriqueAnnonceDTOById(idHistoriqueAnnonce: number): Observable<HistoriqueAnnonceDto> {
    return this.http.get<HistoriqueAnnonceDto>(`${this.apiServerUrl}/historiqueAnnonces/findById/${idHistoriqueAnnonce}`);
  }

  public addHistoriqueAnnonceDTO(historiqueAnnonceDto: HistoriqueAnnonceDto): Observable<HistoriqueAnnonceDto> {
    return this.http.post<HistoriqueAnnonceDto>(`${this.apiServerUrl}/historiqueAnnonces/create`, historiqueAnnonceDto);
  }

  public updateHistoriqueAnnonceDTO(idHistoriqueAnnonce: number, historiqueAnnonceDto: HistoriqueAnnonceDto): Observable<HistoriqueAnnonceDto> {
    return this.http.put<HistoriqueAnnonceDto>(`${this.apiServerUrl}/historiqueAnnonces/update/${idHistoriqueAnnonce}`, historiqueAnnonceDto);
  }

  public deleteHistoriqueAnnonceDTO(idHistoriqueAnnonce: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/historiqueAnnonces/delete/${idHistoriqueAnnonce}`);
  }

  public countNumberOfhistoriqueAnnonces(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/historiqueAnnonces/NumbersOfhistoriqueAnnonces`);
  }

}
