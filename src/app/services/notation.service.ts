import { HttpClient } from '@angular/common/http';
import { NotationDto } from './../models/notation';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class NotationService {

  private apiServerUrl = environment.apiBaseUrl;
  id;

  constructor(private http: HttpClient) {
  }

  /*********************** NotationDTO *****************/
  public getNotationDTOs(): Observable<NotationDto[]> {
    return this.http.get<NotationDto[]>(`${this.apiServerUrl}/notifications/all`);
  }

  public getNotationDTOById(noteId: number): Observable<NotationDto> {
    return this.http.get<NotationDto>(`${this.apiServerUrl}/notifications/${noteId}`);
  }

  public addNotationDTO(notationDTO: NotationDto): Observable<NotationDto> {
    return this.http.post<NotationDto>(`${this.apiServerUrl}/notifications/create`, notationDTO);
  }

  public addNotationDTOToDriver(id: number, notationDTO: NotationDto): Observable<NotationDto> {
    return this.http.post<NotationDto>(`${this.apiServerUrl}/notifications/createWithChauffeur/${id}`, notationDTO);
  }

  public updateNotationDTO(noteId: number, notationDTO: NotationDto): Observable<NotationDto> {
    return this.http.put<NotationDto>(`${this.apiServerUrl}/notifications/update/${noteId}`, notationDTO);
  }

  public deleteNotationDTO(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/notifications/delete/${noteId}`);
  }

}
