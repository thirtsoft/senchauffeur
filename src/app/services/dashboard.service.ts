import { ReservationDto } from './../models/reservation';
import { NotationDto } from './../models/notation';
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

  public countNumberOfAnnonceByStatusValidated(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/annonces/NumbersOfAnnonceByStatusValidated`);
  }

  public countNumberOfAnnonceByInMonth(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/annonces/NumbersOfAnnonceInMonth`);
  }
  public countNumberOfAnnoncesInYear(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/annonces/NumbersOfAnnonceInYear`);
  }

  public countNumbersOfAnnoncePeerMonth(): Observable<AnnonceDto[]>  {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/numberOfAnnonceByMonth`);
  }

  public countNumbersOfAnnoncePeerYear(): Observable<AnnonceDto[]>  {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/numberOfAnnonceByYear`);
  }

  public countNumberOfReservationByStatusPending(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/reservations/NumbersOfReservationByStatusPending`);
  }

  public countNumbersOfReservationInYear(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/reservations/NumbersOfReservationInYear`);
  }

  public countNumbersOfReservationsPeerMonth(): Observable<ReservationDto[]>  {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations/numberOfReservationsPeerMonth`);
  }

  public countNumbersOfReservationsPeerYear(): Observable<ReservationDto[]>  {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations/numberOfReservationsPeerYeer`);
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
    return this.http.get(`${this.apiServerUrl}/utilisateurs/NumbersOfRecruteurs`);
  }

  public countNumberOfNotification(): Observable<NotationDto[]> {
    return this.http.get<NotationDto[]>(`${this.apiServerUrl}/notifications/countNumberOfNotification`);
  }

  public countNumberOfNotificationByProductId(noteId: string): Observable<NotationDto> {
    return this.http.get<NotationDto>(`${this.apiServerUrl}/notifications/countNumberOfNotificationByProductId/${noteId}`);
  }

  public countNumberOfEmails(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/emails/countNumberOfEmailInMonth`);
  }

}
