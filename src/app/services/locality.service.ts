import { Observable } from 'rxjs';
import { Locality } from './../models/locality';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LocalityService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getLocalites(): Observable<Locality[]> {
    return this.http.get<Locality[]>(`${this.apiServerUrl}/addresses/all`);
  }

  public getLocalityById(locId: number): Observable<Locality> {
    return this.http.get<Locality>(`${this.apiServerUrl}/addresses/${locId}`);
  }

  public addLocality(locality: Locality): Observable<Locality> {
    return this.http.post<Locality>(`${this.apiServerUrl}/addresses/create`, locality);
  }

  public updateLocality(locality: Locality): Observable<Locality> {
    return this.http.put<Locality>(`${this.apiServerUrl}/addresses/create`, locality);
  }

  public deleteLocality(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/addresses/delete/${noteId}`);
  }

}
