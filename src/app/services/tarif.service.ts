import { Injectable } from '@angular/core';
import { TarifDto } from './../models/tarif';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from './../../environments/environment';


@Injectable({
  providedIn: 'root'
})
export class TarifService {

  public apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getTarifDTOss(): Observable<TarifDto[]> {
    return this.http.get<TarifDto[]>(`${this.apiServerUrl}/tarifs/all`);
  }

  public getTarifDTOsById(chauffId: number): Observable<TarifDto> {
    return this.http.get<TarifDto>(`${this.apiServerUrl}/tarifs/findById/${chauffId}`);
  }

  public addTarifDTOs(tarifDto: TarifDto): Observable<TarifDto> {
    return this.http.post<TarifDto>(`${this.apiServerUrl}/tarifs/create`, tarifDto);
  }

  public updateTarifDTOs(tarifId: number, tarifDto: TarifDto): Observable<TarifDto> {
    return this.http.put<TarifDto>(`${this.apiServerUrl}/tarifs/update/${tarifId}`, tarifDto);
  }

  public deleteTarifDTOs(tarifId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/tarifs/delete/${tarifId}`);
  }

  public getListTarifDTOsByPageable(page: number, size: number): Observable<TarifDto[]> {
    const searchUrl = (this.apiServerUrl+"/tarifs/searchChauffeurByPageables?page="+page+"&size="+size);
    return this.http.get<TarifDto[]>(searchUrl);
  }

  public getListTarifDTOsByKeyword(keyword: string): Observable<TarifDto[]> {
    return this.http.get<TarifDto[]>(`${this.apiServerUrl}/tarifs/searchTarifByKeyword?reference=`+keyword);
  }

  public getListTarifDTOsByAnnoncePageable(annonceId: number, page: number, size: number): Observable<TarifDto[]> {
    const searchUrl = (this.apiServerUrl+"/tarifs/searchTarifByAnnoncePageables?id="+annonceId+"&page="+page+"&size="+size);
    console.log("Search Url---", searchUrl);
    return this.http.get<TarifDto[]>(searchUrl);
  }

  public getListTarifDTOsByAnnonce(pId: number): Observable<TarifDto[]> {
    return this.http.get<TarifDto[]>(`${this.apiServerUrl}/tarifs/searchTarifsByAnnonce/${pId}`);
  }


}
