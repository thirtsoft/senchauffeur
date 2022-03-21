import { FormGroup } from '@angular/forms';
import { ReservationDto } from './../models/reservation';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  public apiServerUrl = environment.apiBaseUrl;

  choixmenu : string  = 'A';

  formData:  FormGroup;

  constructor(private http: HttpClient) {
  }

  public getTypeReservationDtOs(): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations/all`);
  }

  public getTypeReservationDtoOrderByIdDesc(): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations/searchAllReservationsOrderByIdDesc`);
  }

  public getListPendingReservationsDtOs(): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations/searchReservationByStatusPending`);
  }

  public getListValidatedReservationsDtOs(): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations/searchReservationByStatusValide`);
  }

  public getReservationDtOById(chauffId: number): Observable<ReservationDto> {
    return this.http.get<ReservationDto>(`${this.apiServerUrl}/reservations/findById/${chauffId}`);
  }

  public addReservationDtO(reservationDto: ReservationDto): Observable<ReservationDto> {
    return this.http.post<ReservationDto>(`${this.apiServerUrl}/reservations/create`, reservationDto);
  }

  public addReservationToChauffeur(reservationDto: ReservationDto, idChauff: number, id:number): Observable<ReservationDto> {
    return this.http.post<ReservationDto>(`${this.apiServerUrl}/reservations/createReservationToChauffeur?idChauff=${idChauff}&id=${id}`, reservationDto);
  }

  public updateReservationDtO(tarifId: number, reservationDto: ReservationDto): Observable<ReservationDto> {
    return this.http.put<ReservationDto>(`${this.apiServerUrl}/reservations/update/${tarifId}`, reservationDto);
  }

  public updateStatusOfReservationDTO(id: number, status: string): Observable<any> {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    let data = {"status":status};
    const urlUpdateStatus = (this.apiServerUrl+"/reservations/updateStatusOfReservation/"+id+"?status="+data.status);
    return this.http.patch<any>(urlUpdateStatus, {headers: headers});

  }

  public getListReservationDtosByEmployeur(empId: number): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations/searchReservationByCustomerId/${empId}`);
  }

  public getListReservationDtosByChauffeur(chauffId: number): Observable<ReservationDto[]> {
    return this.http.get<ReservationDto[]>(`${this.apiServerUrl}/reservations/searchReservationByChauffeurId/${chauffId}`);
  }

  public deleteReservationDtO(tarifId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/reservations/delete/${tarifId}`);
  }


}
