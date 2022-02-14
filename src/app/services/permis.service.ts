import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Permis, PermisDto } from './../models/permis';
import { environment } from './../../environments/environment';

//import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class PermisService {

  public apiServerUrl = environment.apiBaseUrl;

//  public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-chauffeurs/v1";

  constructor(private http: HttpClient) {
  }

  public getPermis(): Observable<Permis[]> {
    return this.http.get<Permis[]>(`${this.apiServerUrl}/permis/all`);
  }

  public getPermisById(permisId: number): Observable<Permis> {
    return this.http.get<Permis>(`${this.apiServerUrl}/permis/${permisId}`);
  }

  public addPermis(permis: Permis): Observable<Permis> {
    return this.http.post<Permis>(`${this.apiServerUrl}/permis/create`, permis);
  }

  public updatePermis(permis: Permis): Observable<Permis> {
    return this.http.put<Permis>(`${this.apiServerUrl}/permis/create`, permis);
  }

  public deletePermis(permisId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/permis/delete/${permisId}`);
  }

  /************************  PermisDTO ****************/
  public getPermisDTOs(): Observable<PermisDto[]> {
    return this.http.get<PermisDto[]>(`${this.apiServerUrl}/permis/all`);
  }

  public getPermisDTOById(permisId: number): Observable<PermisDto> {
    return this.http.get<PermisDto>(`${this.apiServerUrl}/permis/${permisId}`);
  }

  public addPermisDTO(permisDTO: PermisDto): Observable<PermisDto> {
    return this.http.post<PermisDto>(`${this.apiServerUrl}/permis/create`, permisDTO);
  }

  public updatePermisDTO(permisId: number, permisDTO: PermisDto): Observable<PermisDto> {
    return this.http.put<PermisDto>(`${this.apiServerUrl}/chauffeurs/update/${permisId}`, permisDTO);
  }

  public deletePermisDTO(permisId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/permis/delete/${permisId}`);
  }

}
