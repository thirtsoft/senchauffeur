import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Annonce, AnnonceDto } from './../models/annonce';
import { Injectable } from '@angular/core';
/* import { environment } from 'src/environments/environment'; */
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public getAnnonces(): Observable<Annonce[]> {
    return this.http.get<Annonce[]>(`${this.apiServerUrl}/annonces/all`);
  }

  public getAnnonceById(annonceId: number): Observable<Annonce> {
    return this.http.get<Annonce>(`${this.apiServerUrl}/annonces/${annonceId}`);
  }

  public addAnnonce(annonce: Annonce): Observable<Annonce> {
    return this.http.post<Annonce>(`${this.apiServerUrl}/annonces/create`, annonce);
  }

  public updateAnnonce(annonce: Annonce): Observable<Annonce> {
    return this.http.put<Annonce>(`${this.apiServerUrl}/annonces/create`, annonce);
  }

  public deleteAnnonce(annonceId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/annonces/delete/${annonceId}`);
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
  //  return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByPageables?page=`+page+"&size="+size);
  }

  public getListAnnonceDTOByKeyword(keyword: string): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByKeyword?keyword=`+keyword);
  }

  public getListAnnonceDTOByPermis(pId: number): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnoncesByPermis/${pId}`);
  }

  public countNumberOfAnnonces(): Observable<any>  {
    return this.http.get(`${this.apiServerUrl}/annonces/NumbersOfAnnonces`);
  }

}
