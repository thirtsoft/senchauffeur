import { HttpClient } from '@angular/common/http';
import { Chauffeur } from './../models/chauffeur';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChauffeurService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getChauffeurs(): Observable<Chauffeur[]> {
    return this.http.get<Chauffeur[]>(`${this.apiServerUrl}/chauffeurs/all`);
  }

  public getChauffeurById(chauffId: number): Observable<Chauffeur> {
    return this.http.get<Chauffeur>(`${this.apiServerUrl}/chauffeurs/${chauffId}`);
  }

  public addChauffeur(chauffeur: Chauffeur): Observable<Chauffeur> {
    return this.http.post<Chauffeur>(`${this.apiServerUrl}/chauffeurs/create`, chauffeur);
  }

  public updateChauffeur(chauffeur: Chauffeur): Observable<Chauffeur> {
    return this.http.put<Chauffeur>(`${this.apiServerUrl}/chauffeurs/create`, chauffeur);
  }

  public deleteChauffeur(chauffId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/chauffeurs/delete/${chauffId}`);
  }

}