import { HttpClient } from '@angular/common/http';
import { Particulier, ParticulierDto } from './../models/particulier';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ParticulierService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getParticuliers(): Observable<Particulier[]> {
    return this.http.get<Particulier[]>(`${this.apiServerUrl}/particuliers/all`);
  }

  public getParticulierById(partId: number): Observable<Particulier> {
    return this.http.get<Particulier>(`${this.apiServerUrl}/particuliers/${partId}`);
  }

  public addParticulier(particulier: Particulier): Observable<Particulier> {
    return this.http.post<Particulier>(`${this.apiServerUrl}/particuliers/create`, particulier);
  }

  public updateParticulier(utilisateur: Particulier): Observable<Particulier> {
    return this.http.put<Particulier>(`${this.apiServerUrl}/particuliers/create`, utilisateur);
  }

  public deleteParticulier(partId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/particuliers/delete/${partId}`);
  }

  /************************ ParticulierDTO ****************/
  public getParticulierDTOs(): Observable<ParticulierDto[]> {
    return this.http.get<ParticulierDto[]>(`${this.apiServerUrl}/particuliers/all`);
  }

  public getParticulierDTOById(partId: number): Observable<ParticulierDto> {
    return this.http.get<ParticulierDto>(`${this.apiServerUrl}/particuliers/${partId}`);
  }

  public addParticulierDTO(particulierDTO: ParticulierDto): Observable<ParticulierDto> {
    return this.http.post<ParticulierDto>(`${this.apiServerUrl}/particuliers/create`, particulierDTO);
  }

  public updateParticulierDTO(utilisateurDTO: ParticulierDto): Observable<ParticulierDto> {
    return this.http.put<ParticulierDto>(`${this.apiServerUrl}/particuliers/create`, utilisateurDTO);
  }

  public deleteParticulierDTO(partId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/particuliers/delete/${partId}`);
  }


}
