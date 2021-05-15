import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Utilisateur } from './../models/utilisateur';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UtilisateurService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getUtilisateurs(): Observable<Utilisateur[]> {
    return this.http.get<Utilisateur[]>(`${this.apiServerUrl}/utilisateurs/all`);
  }

  public getUtilisateurById(userId: number): Observable<Utilisateur> {
    return this.http.get<Utilisateur>(`${this.apiServerUrl}/utilisateurs/${userId}`);
  }

  public addUtilisateur(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.post<Utilisateur>(`${this.apiServerUrl}/utilisateurs/create`, utilisateur);
  }

  public updateUtilisateur(utilisateur: Utilisateur): Observable<Utilisateur> {
    return this.http.put<Utilisateur>(`${this.apiServerUrl}/utilisateurs/create`, utilisateur);
  }

  public deleteUtilisateur(userId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/utilisateurs/delete/${userId}`);
  }

}
