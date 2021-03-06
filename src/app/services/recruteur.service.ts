import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Recruteur, RecruteurDto } from './../models/recruteur';

import { environment } from 'src/environments/environment';
//import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class RecruteurService {

  public apiServerUrl = environment.apiBaseUrl;

 // public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-chauffeurs/v1";

  constructor(private http: HttpClient) {
  }

  public getRecruteurs(): Observable<Recruteur[]> {
    return this.http.get<Recruteur[]>(`${this.apiServerUrl}/recruteurs/all`);
  }

  public getRecruteurById(recId: number): Observable<Recruteur> {
    return this.http.get<Recruteur>(`${this.apiServerUrl}/recruteurs/${recId}`);
  }

  public addRecruteur(recruteur: Recruteur): Observable<Recruteur> {
    return this.http.post<Recruteur>(`${this.apiServerUrl}/recruteurs/create`, recruteur);
  }

  public updateRecruteur(recruteur: Recruteur): Observable<Recruteur> {
    return this.http.put<Recruteur>(`${this.apiServerUrl}/recruteurs/create`, recruteur);
  }

  public deleteRecruteur(recId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/recruteurs/delete/${recId}`);
  }

  /************************ RecruteurDTO ****************/
  public getRecruteurDTOs(): Observable<RecruteurDto[]> {
    return this.http.get<RecruteurDto[]>(`${this.apiServerUrl}/recruteurs/all`);
  }

  public getRecruteurDTOById(recId: number): Observable<RecruteurDto> {
    return this.http.get<RecruteurDto>(`${this.apiServerUrl}/recruteurs/${recId}`);
  }

  public addRecruteurDTO(recruteurDTO: RecruteurDto): Observable<RecruteurDto> {
    return this.http.post<RecruteurDto>(`${this.apiServerUrl}/recruteurs/create`, recruteurDTO);
  }

  public updateRecruteurDTO(permisId: number, recruteurDTO: RecruteurDto): Observable<RecruteurDto> {
    return this.http.put<RecruteurDto>(`${this.apiServerUrl}/recruteurs/update/${permisId}`, recruteurDTO);
  }

  public deleteRecruteurDTO(recId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/recruteurs/delete/${recId}`);
  }


}
