import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { TokenStorageService } from './../auth/security/token-storage.service';
import { HttpClient } from '@angular/common/http';
import { NotationDto } from './../models/notation';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class NotationService {

  private apiServerUrl = environment.apiBaseUrl;

  id: any;
  artId: any;

  constructor(private http: HttpClient,
              private tokenService: TokenStorageService) {
  }

  /*********************** NotationDTO *****************/
  public getNotationDTOs(): Observable<NotationDto[]> {
    return this.http.get<NotationDto[]>(`${this.apiServerUrl}/notifications/all`);
  }

  public getTop3RatingOrderByCreatedDateDesc(): Observable<NotationDto[]> {
    return this.http.get<NotationDto[]>(`${this.apiServerUrl}/notifications/searchTop3RatingOrderByCreatedDateDesc`);
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

  public addRatingToChauffeur(notificationDTO: NotationDto, idChauff: number, userId:number): Observable<NotationDto> {
    return this.http.post<NotationDto>(`${this.apiServerUrl}/notifications/createRatingToChauffeur?idChauff=${idChauff}&id=${userId}`, notificationDTO);
  }

  public updateNotationDTO(noteId: number, notationDTO: NotationDto): Observable<NotationDto> {
    return this.http.put<NotationDto>(`${this.apiServerUrl}/notifications/update/${noteId}`, notationDTO);
  }

  public deleteNotationDTO(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/notifications/delete/${noteId}`);
  }

  getUserId() {
    const user = this.tokenService.getUser();
    this.id = user.id
  }

}
