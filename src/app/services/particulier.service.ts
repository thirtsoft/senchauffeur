import { HttpClient } from '@angular/common/http';
import { Particulier, ParticulierDto } from './../models/particulier';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
//import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class ParticulierService {

  public apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
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

  public updateParticulierDTO(partId: number, particulierDTO: ParticulierDto): Observable<ParticulierDto> {
    return this.http.put<ParticulierDto>(`${this.apiServerUrl}/particuliers/update/${partId}`, particulierDTO);
  }

  public deleteParticulierDTO(partId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/particuliers/delete/${partId}`);
  }


}
