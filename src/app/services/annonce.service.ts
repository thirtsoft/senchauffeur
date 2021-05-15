import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Annonce } from './../models/annonce';
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
    return this.http.get<Annonce[]>(`${this.apiServerUrl}/chauffeurs/all`);
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

}
