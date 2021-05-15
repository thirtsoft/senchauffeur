import { Permis } from './../models/permis';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PermisService {

  private apiServerUrl = environment.apiBaseUrl;

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

}
