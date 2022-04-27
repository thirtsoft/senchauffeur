import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { TypeAnnonceDto } from './../models/type-annonce';

import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TypeAnnonceService {

  public apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getTypeTypeAnnonceDtOs(): Observable<TypeAnnonceDto[]> {
    return this.http.get<TypeAnnonceDto[]>(`${this.apiServerUrl}/typeAnnonces/all`);
  }

  public getTypeTypeAnnonceDtOOrderByIdDesc(): Observable<TypeAnnonceDto[]> {
    return this.http.get<TypeAnnonceDto[]>(`${this.apiServerUrl}/typeAnnonces/searchAllTypeAnnoncesOrderByIdDesc`);
  }

  public getTypeAnnonceDtOById(chauffId: number): Observable<TypeAnnonceDto> {
    return this.http.get<TypeAnnonceDto>(`${this.apiServerUrl}/typeAnnonces/findById/${chauffId}`);
  }

  public addTypeAnnonceDtos(typeAnnonceDto: TypeAnnonceDto): Observable<TypeAnnonceDto> {
    return this.http.post<TypeAnnonceDto>(`${this.apiServerUrl}/typeAnnonces/create`, typeAnnonceDto);
  }

  public updateTypeAnnonceDtos(typeAnId: number, typeAnnonceDto: TypeAnnonceDto): Observable<TypeAnnonceDto> {
    return this.http.put<TypeAnnonceDto>(`${this.apiServerUrl}/typeAnnonces/update/${typeAnId}`, typeAnnonceDto);
  }

  public deleteTypeAnnonceDtos(tarifId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/typeAnnonces/delete/${tarifId}`);
  }

}
