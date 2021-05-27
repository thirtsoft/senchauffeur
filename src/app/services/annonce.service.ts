import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Annonce, AnnonceDto } from './../models/annonce';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getAnnonces(): Observable<Annonce[]> {
    return this.http.get<Annonce[]>(`${this.apiServerUrl}/annonces/all`);
  }

  public getAnnonceById(annonceId: number): Observable<Annonce> {
    return this.http.get<Annonce>(`${this.apiServerUrl}/annonces/${annonceId}`);
  }

  public addAnnonce(annonce: Annonce): Observable<Annonce> {
    return this.http.post<Annonce>(`${this.apiServerUrl}/annonces/create`, annonce);
  }

  public updateAnnonce(annonce: Annonce): Observable<Annonce> {
    return this.http.put<Annonce>(`${this.apiServerUrl}/annonces/create`, annonce);
  }

  public deleteAnnonce(annonceId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/annonces/delete/${annonceId}`);
  }

  /**************************** ChauffeurDTO ******************/
  public getAnnonceDTOs(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/all`);
  }

  public getAnnonceDTOById(annonceId: number): Observable<AnnonceDto> {
    return this.http.get<AnnonceDto>(`${this.apiServerUrl}/annonces/${annonceId}`);
  }

  public addAnnonceDTO(annonceDTO: AnnonceDto): Observable<AnnonceDto> {
    return this.http.post<AnnonceDto>(`${this.apiServerUrl}/annonces/create`, annonceDTO);
  }

  public updateAnnonceDTO(annonceDTO: AnnonceDto): Observable<AnnonceDto> {
    return this.http.put<AnnonceDto>(`${this.apiServerUrl}/annonces/create`, annonceDTO);
  }

  public deleteAnnonceDTO(annonceId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/annonces/delete/${annonceId}`);
  }

}
