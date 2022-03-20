import { Observable } from 'rxjs';
import { Locality, AddresseDto } from './../models/locality';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
//import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class LocalityService {

  public apiServerUrl = environment.apiBaseUrl;

 // public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-chauffeurs/v1";

  constructor(private http: HttpClient) {
  }

  public getLocaliteDTOs(): Observable<AddresseDto[]> {
    return this.http.get<AddresseDto[]>(`${this.apiServerUrl}/addresses/all`);
  }

  public getLocaliteDTOOrderByIdDesc(): Observable<AddresseDto[]> {
    return this.http.get<AddresseDto[]>(`${this.apiServerUrl}/addresses/searchAddressOrderByIdDesc`);
  }

  public getLocalityDTOById(locId: number): Observable<AddresseDto> {
    return this.http.get<AddresseDto>(`${this.apiServerUrl}/addresses/findById/${locId}`);
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
