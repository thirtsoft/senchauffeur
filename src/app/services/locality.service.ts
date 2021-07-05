import { Observable } from 'rxjs';
import { Locality, AddresseDto } from './../models/locality';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
/* import { environment } from 'src/environments/environment'; */
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class LocalityService {

//  private apiServerUrl = environment.apiBaseUrl;

  public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-chauffeurs/v1";

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

  public getLocaliteDTOs(): Observable<AddresseDto[]> {
    return this.http.get<AddresseDto[]>(`${this.apiServerUrl}/addresses/all`);
  }

  public getLocalityDTOById(locId: number): Observable<AddresseDto> {
    return this.http.get<AddresseDto>(`${this.apiServerUrl}/addresses/${locId}`);
  }

  public addLocalityDTO(localityDTO: AddresseDto): Observable<AddresseDto> {
    return this.http.post<AddresseDto>(`${this.apiServerUrl}/addresses/create`, localityDTO);
  }

  public updateLocalityDTO(locId: number, localityDTO: AddresseDto): Observable<AddresseDto> {
    return this.http.put<AddresseDto>(`${this.apiServerUrl}/addresses/update/${locId}`, localityDTO);
  }

  public deleteLocalityDTO(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/addresses/delete/${noteId}`);
  }

}
