import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Chauffeur, ChauffeurDto } from './../models/chauffeur';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
//import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root'
})
export class ChauffeurService {

  public apiServerUrl = environment.apiBaseUrl;

  //public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-chauffeurs/v1";

  constructor(private http: HttpClient) {
  }

  public getChauffeurs(): Observable<Chauffeur[]> {
    return this.http.get<Chauffeur[]>(`${this.apiServerUrl}/chauffeurs/all`);
  }

  public getChauffeurById(chauffId: number): Observable<Chauffeur> {
    return this.http.get<Chauffeur>(`${this.apiServerUrl}/chauffeurs/${chauffId}`);
  }

  public addChauffeur(chauffeur: Chauffeur): Observable<Chauffeur> {
    return this.http.post<Chauffeur>(`${this.apiServerUrl}/chauffeurs/create`, chauffeur);
  }

  public updateChauffeur(chauffeur: Chauffeur): Observable<Chauffeur> {
    return this.http.put<Chauffeur>(`${this.apiServerUrl}/chauffeurs/create`, chauffeur);
  }

  public deleteChauffeur(chauffId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/chauffeurs/delete/${chauffId}`);
  }

  /*************************** ChauffeurDTO ********************/
  public getChauffeurDTOs(): Observable<ChauffeurDto[]> {
    return this.http.get<ChauffeurDto[]>(`${this.apiServerUrl}/chauffeurs/all`);
  }

  public getChauffeurDTOById(chauffId: number): Observable<ChauffeurDto> {
    return this.http.get<ChauffeurDto>(`${this.apiServerUrl}/chauffeurs/${chauffId}`);
  }

  public addChauffeurDTO(chauffeurDTO: ChauffeurDto): Observable<ChauffeurDto> {
    return this.http.post<ChauffeurDto>(`${this.apiServerUrl}/chauffeurs/create`, chauffeurDTO);
  }

  public addChauffeurDTOWithFiles(formData: FormData): Observable<any> {
    const req = new HttpRequest('POST', `${this.apiServerUrl}/chauffeurs/createWithFiles`, formData, {
      reportProgress: true,
      responseType: 'text'
    });
    return this.http.request(req);

  }

  public updateChauffeurDTO(chauffId: number, chauffeurDTO: ChauffeurDto): Observable<ChauffeurDto> {
    return this.http.put<ChauffeurDto>(`${this.apiServerUrl}/chauffeurs/update/${chauffId}`, chauffeurDTO);
  }

  public deleteChauffeurDTO(chauffId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/chauffeurs/delete/${chauffId}`);
  }

  public getListChauffeurDTOByPageable(page: number, size: number): Observable<ChauffeurDto[]> {
    const searchUrl = (this.apiServerUrl+"/chauffeurs/searchChauffeurByPageables?page="+page+"&size="+size);
    return this.http.get<ChauffeurDto[]>(searchUrl);
  //  return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByPageables?page=`+page+"&size="+size);
  }

  public getListChauffeurDTOBySelectedIsTrue(): Observable<ChauffeurDto[]> {
    return this.http.get<ChauffeurDto[]>(`${this.apiServerUrl}/chauffeurs/searchChauffeurBySelectedIsTrue`);
  }

  public getListChauffeurDTOByKeyword(keyword: string): Observable<ChauffeurDto[]> {
    return this.http.get<ChauffeurDto[]>(`${this.apiServerUrl}/chauffeurs/searchChauffeurByKeyword?keyword=`+keyword);
  }

  public getListChauffeurDTOByDisponibility(disponility: string): Observable<ChauffeurDto[]> {
    return this.http.get<ChauffeurDto[]>(`${this.apiServerUrl}/chauffeurs/searchChauffeurByDisponibilite?disponible=`+disponility);
  }

  public getListChauffeurDTOByKeywordPageable(mc: string, page: number, size: number): Observable<ChauffeurDto[]> {
    const searchUrl = (this.apiServerUrl+"/chauffeurs/searchChauffeurByDisponibityByPageables?id="+mc+"&page="+page+"&size="+size);
    console.log("Search Url---", searchUrl);
    return this.http.get<ChauffeurDto[]>(searchUrl);
  }

  public getListChauffeurDTOByPermisPageable(permisId: number, page: number, size: number): Observable<ChauffeurDto[]> {
    const searchUrl = (this.apiServerUrl+"/chauffeurs/searchChauffeurByPermisPageables?id="+permisId+"&page="+page+"&size="+size);
    console.log("Search Url---", searchUrl);
    return this.http.get<ChauffeurDto[]>(searchUrl);
  }

  public getListChauffeurDTOByLocalityPageable(locId: number, page: number, size: number): Observable<ChauffeurDto[]> {
    const searchUrl = (this.apiServerUrl+"/chauffeurs/searchChauffeurByLocalityPageables?id="+locId+"&page="+page+"&size="+size);
    console.log("Search Url---", searchUrl);
    return this.http.get<ChauffeurDto[]>(searchUrl);
  }

  public getListChauffeurDTOByPermis(pId: number): Observable<ChauffeurDto[]> {
    return this.http.get<ChauffeurDto[]>(`${this.apiServerUrl}/chauffeurs/searchChauffeursByPermis/${pId}`);
  }

  uploadPhotoChauffeurDto(filePhoto: File, idChauffeur): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
    formdata.append('photoArticle', filePhoto);
    const req = new HttpRequest('POST', `${this.apiServerUrl}/chauffeurs/uploadChauffeurPhoto/${idChauffeur}`, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);

  }

  public getPhotoChauffeur() {
    return this.http.get(`${this.apiServerUrl}/chauffeurs/photoChauffeur`);
  }

  uploadCvChauffeurDto(fileCV: File, idChauffeur): Observable<HttpEvent<{}>> {
    let formdata: FormData = new FormData();
    formdata.append('cvChauffeur', fileCV);
    const req = new HttpRequest('POST', `${this.apiServerUrl}/chauffeurs/uploadChauffeurCv/${idChauffeur}`, formdata, {
      reportProgress: true,
      responseType: 'text'
    });

    return this.http.request(req);

  }

  public getCvChauffeur() {
    return this.http.get(`${this.apiServerUrl}/chauffeurs/cvChauffeur`);
  }

  public countNumberOfChauffeurs(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/chauffeurs/NumbersOfChauffeurs`);
  }

}
