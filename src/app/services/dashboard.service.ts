import { AnnonceDto } from './../models/annonce';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {


  public apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public countNumberOfAnnonces(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/annonces/NumbersOfAnnonces`);
  }

  public countNumberOfAnnonceByStatusPending(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/annonces/NumbersOfAnnonceByStatusPending`);
  }

  public countNumberOfAnnonceByInMonth(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/annonces/NumbersOfAnnonceInMonth`);
  }

  public countNumbersOfAnnoncePeerMonth(): Observable<AnnonceDto[]>  {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/numberOfAnnonceByMont`);
  }

  public countNumbersOfAnnoncePeerYear(): Observable<AnnonceDto[]>  {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/numberOfAnnonceByYear`);
  }

  public countNumberOfChauffeurs(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/chauffeurs/NumbersOfChauffeurs`);
  }

  public countNumbersOfChauffeursPeerMonth(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/chauffeurs/numberOfChauffeurPeerMonth`);
  }

  public countNumbersOfChauffeursPeerYear(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/chauffeurs/numberOfChauffeurPeerYeer`);
  }

  public countNumberOfRecruteurs(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/recruteurs/NumbersOfRecruteurs`);
  }

}
