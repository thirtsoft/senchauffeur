import { map } from 'rxjs/operators';
import { FormGroup } from '@angular/forms';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { TokenStorageService } from './../auth/security/token-storage.service';
import { AnnonceDto } from './../models/annonce';

@Injectable({
  providedIn: 'root'
})
export class AnnonceService {

  apiServerUrl = environment.apiBaseUrl;

  choixmenu : string  = 'A';
  formData:  FormGroup;

  id;
  currentUser: any = {};

  private listners = new Subject<any>();

  listen(): Observable<any> {
    return this.listners.asObservable();
  }

  filter(filterBy: string) {
    this.listners.next(filterBy);
  }

  constructor(private http: HttpClient,
              private tokenService: TokenStorageService
  ) {
  }

  /**************************** AnnonceDTO ******************/
  public getAnnonceDTOs(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/all`);
  }

  public getAnnonceDTOOrderByIdDesc(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceOrderByIdDesc`);
  }

  public getAnnonceDTOById(annonceId: number): Observable<AnnonceDto> {
    return this.http.get<AnnonceDto>(`${this.apiServerUrl}/annonces/findById/${annonceId}`);
  }

  public getAnnonceDTOByReference(reference: string): Observable<AnnonceDto> {
    return this.http.get<AnnonceDto>(`${this.apiServerUrl}/annonces/searchbyReference/${reference}`);
  }

  public getAnnonceDTOByCustomerId(userId: number): Observable<AnnonceDto> {
    return this.http.get<AnnonceDto>(`${this.apiServerUrl}/annonces/findAnnonceByCustomerId/${userId}`);
  }

  public addAnnonceDTO(annonceDTO: AnnonceDto): Observable<AnnonceDto> {
    return this.http.post<AnnonceDto>(`${this.apiServerUrl}/annonces/create`, annonceDTO);
  }

  public addAnnonceDTOWithUser(annonceDTO: AnnonceDto, id: number): Observable<AnnonceDto> {
    return this.http.post<AnnonceDto>(`${this.apiServerUrl}/annonces/createAnnonceWithUser?id=${id}`, annonceDTO);
  }

  public updateAnnonceDTO(annonceId: number, annonceDTO: AnnonceDto): Observable<AnnonceDto> {
    return this.http.put<AnnonceDto>(`${this.apiServerUrl}/annonces/update/${annonceId}`, annonceDTO);
  }

  public updateStatusOfAnnonceDTO(id: number, status: string): Observable<any> {
    const headers = new HttpHeaders();
    headers.set('Content-Type', 'application/json; charset=utf-8');
    let data = {"status":status};
    const urlUpdateStatus = (this.apiServerUrl+"/annonces/updateStatusOfAnnonce/"+id+"?status="+data.status);
    return this.http.patch<any>(urlUpdateStatus, {headers: headers});

  }

  public deleteAnnonceDTO(annonceId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/annonces/delete/${annonceId}`);
  }

  public getListAnnonceDTOByPageable(page: number, size: number): Observable<AnnonceDto[]> {
    const searchUrl = (this.apiServerUrl+"/annonces/searchAnnonceByPageables?page="+page+"&size="+size);
    return this.http.get<AnnonceDto[]>(searchUrl);
  }

  public getListAnnonceDTOBySelectedIsTrue(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceBySelectedIsTrue`);
  }

  public getListAnnonceDTOByKeyword(reference: string): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByKeyword?reference=`+reference);
  }

  public getListAnnonceDTOByLibeele(libelle: string): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByLibelle?libelle=`+libelle);
  }

  public get5LatestAnnonceDTOByOrderByIdDesc(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/search5LatestAnnonceByIdDesc`);
  }

  public get6LatestAnnonceDTOByStatusValidatedOrderByIdDesc(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/search6ValidateLatestAnnonceByIdDesc`);
  }

  public getAnnonceDTOByStatusPending(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByStatusPending`);
  }

  public getAnnonceDTOByStatusValidated(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByStatusValide`);
  }

  public getAnnonceDTOByStatusRejeted(): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByStatusRejet`);
  }

  public getListAnnonceDTOByPermis(pId: number): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnoncesByPermis/${pId}`);
  }

  public getAnnonceDtoByUserIdOrderDesc(userId: number): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/searchAnnonceByCustomerIdOrderByIdDesc/${userId}`);
  }

  public getListAnnonceDTOByPermisPageable(permisId: number, page: number, size: number): Observable<AnnonceDto[]> {
    const searchUrl = (this.apiServerUrl+"/annonces/searchAnnonceByPermisPageables?id="+permisId+"&page="+page+"&size="+size);
    console.log("Search Url---", searchUrl);
    return this.http.get<AnnonceDto[]>(searchUrl);
  }

  getUserId() {
    const user = this.tokenService.getUser();
    this.id = user.id
  }

  getAllAnnoncesDtos(page,size): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/allAnnonces?page=${page}&size=${size}`).pipe(
      map(
        response => response
      )
    )
  }

  getAllAnnonceDtosByPermisId(id,page,size): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(`${this.apiServerUrl}/annonces/permis?id=${id}&page=${page}&size=${size}`).pipe(
      map(
        response => response
      )
    )
  }

  getAllAnnonceDtosByKey(word,page,size): Observable<AnnonceDto[]> {
    return this.http.get<AnnonceDto[]>(this.apiServerUrl+"/annonces/annoncekey?libelle="+word+"&page="+page+"&size="+size).pipe(
      map(
        response => response
      )
    )
  }

  getAnnonceDtoLength(): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/annonces/annonceSize`).pipe(
      map(
        response => response
      )
    )
  }

  getAnnoncesLengthByPermsId(id): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/annonces/ctpermisIdSize?id=${id}`).pipe(
      map(
        response => response
      )
    )
  }

  getAnnonceDtosLengthByKey(word): Observable<number> {
    return this.http.get<number>(`${this.apiServerUrl}/annonces/keySize?libelle=${word}`).pipe(
      map(
        response => response
      )
    )
  }


}
