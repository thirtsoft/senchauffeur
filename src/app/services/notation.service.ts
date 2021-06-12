import { HttpClient } from '@angular/common/http';
import { Notation, NotificationDto } from './../models/notation';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotationService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getNotations(): Observable<Notation[]> {
    return this.http.get<Notation[]>(`${this.apiServerUrl}/notifications/all`);
  }

  public getNotationById(noteId: number): Observable<Notation> {
    return this.http.get<Notation>(`${this.apiServerUrl}/notifications/${noteId}`);
  }

  public addNotation(notation: Notation): Observable<Notation> {
    return this.http.post<Notation>(`${this.apiServerUrl}/notifications/create`, notation);
  }

  public updateNotation(notation: Notation): Observable<Notation> {
    return this.http.put<Notation>(`${this.apiServerUrl}/notifications/create`, notation);
  }

  public deleteNotation(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/notifications/delete/${noteId}`);
  }

  /*********************** NotationDTO *****************/
  public getNotationDTOs(): Observable<NotificationDto[]> {
    return this.http.get<NotificationDto[]>(`${this.apiServerUrl}/notifications/all`);
  }

  public getNotationDTOById(noteId: number): Observable<NotificationDto> {
    return this.http.get<NotificationDto>(`${this.apiServerUrl}/notifications/${noteId}`);
  }

  public addNotationDTO(notationDTO: NotificationDto): Observable<NotificationDto> {
    return this.http.post<NotificationDto>(`${this.apiServerUrl}/notifications/create`, notationDTO);
  }

  public updateNotationDTO(noteId: number, notationDTO: NotificationDto): Observable<NotificationDto> {
    return this.http.put<NotificationDto>(`${this.apiServerUrl}/notifications/update/${noteId}`, notationDTO);
  }

  public deleteNotationDTO(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/notifications/delete/${noteId}`);
  }

}
