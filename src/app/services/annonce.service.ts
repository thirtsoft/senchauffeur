import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Annonce, AnnonceDto } from './../models/annonce';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  private apiServerUrl = environment.apiBaseUrl;

  private listners = new Subject<any>();
  listen(): Observable<any> {
    return this.listners.asObservable();
  }
  filter(filterBy: string) {
    this.listners.next(filterBy);
  }

  constructor(private http: HttpClient) {
  }


  /**************************** ChauffeurDTO ******************/
  public getAnnonceDTOs(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/all`);
  }

  public getAnnonceDTOById(annonceId: number): Observable<AnnonceDto> {
    return this.http.get<AnnonceDto>(`${this.apiServerUrl}/annonces/${annonceId}`);
  }

  public getAnnonceDTOByReference(reference: string): Observable<AnnonceDto> {
    return this.http.get<AnnonceDto>(`${this.apiServerUrl}/annonces/searchbyReference/${reference}`);
  }

  public addAnnonceDTO(annonceDTO: AnnonceDto): Observable<AnnonceDto> {
    return this.http.post<AnnonceDto>(`${this.apiServerUrl}/annonces/create`, annonceDTO);
  }

  public updateAnnonceDTO(annonceId: number, annonceDTO: AnnonceDto): Observable<AnnonceDto> {
    return this.http.put<AnnonceDto>(`${this.apiServerUrl}/annonces/update/${annonceId}`, annonceDTO);
  }

  public deleteAnnonceDTO(annonceId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/annonces/delete/${annonceId}`);
  }

  public getListAnnonceDTOByPageable(page: number, size: number): Observable<AnnonceDto[]> {
    const searchUrl = (this.apiServerUrl+"/annonces/searchAnnonceByPageables?page="+page+"&size="+size);
    return this.http.get<AnnonceDto[]>(searchUrl);
  }

  public getListAnnonceDTOByKeyword(reference: string): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByKeyword?reference=`+reference);
  }

  public getListAnnonceDTOByLibeele(libelle: string): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByLibelle?libelle=`+libelle);
  }

  public getListAnnonceDTOByPermis(pId: number): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnoncesByPermis/${pId}`);
  }

  public getListAnnonceDTOByPermisPageable(permisId: number, page: number, size: number): Observable<AnnonceDto[]> {
    const searchUrl = (this.apiServerUrl+"/annonces/searchAnnonceByPermisPageables?id="+permisId+"&page="+page+"&size="+size);
    console.log("Search Url---", searchUrl);
    return this.http.get<AnnonceDto[]>(searchUrl);
  }

  public countNumberOfAnnonces(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/annonces/NumbersOfAnnonces`);
  }

}
