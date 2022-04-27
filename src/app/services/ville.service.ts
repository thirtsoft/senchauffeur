import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ville, VilleDto } from './../models/ville';
//import { environment } from 'src/environments/environment.prod';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class VilleService {

  public apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }
 
  /************************  VilleDto ****************/
  public getVilleDTOs(): Observable<VilleDto[]> {
    return this.http.get<VilleDto[]>(`${this.apiServerUrl}/villes/all`);
  }

  public getVilleDTOById(villeId: number): Observable<VilleDto> {
    return this.http.get<VilleDto>(`${this.apiServerUrl}/villes/${villeId}`);
  }

  public addVilleDto(villeDto: VilleDto): Observable<VilleDto> {
    return this.http.post<VilleDto>(`${this.apiServerUrl}/villes/create`, villeDto);
  }

  public updateVilleDto(villeId: number, villeDto: VilleDto): Observable<VilleDto> {
    return this.http.put<VilleDto>(`${this.apiServerUrl}/villes/update/${villeId}`, villeDto);
  }

  public deleteVilleDto(villeId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/villes/delete/${villeId}`);
  }

}
