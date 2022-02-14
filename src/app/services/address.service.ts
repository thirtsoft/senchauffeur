import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddresseDto } from './../models/address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {


  public apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }


  public getAddresseDtos(): Observable<AddresseDto[]> {
    return this.http.get<AddresseDto[]>(`${this.apiServerUrl}/addresses/all`);
  }

  public getAddresseDtoById(addId: number): Observable<AddresseDto> {
    return this.http.get<AddresseDto>(`${this.apiServerUrl}/addresses/${addId}`);
  }

  public addAddresseDto(AddresseDto: AddresseDto): Observable<AddresseDto> {
    return this.http.post<AddresseDto>(`${this.apiServerUrl}/addresses/create`, AddresseDto);
  }

  public updateAddresseDto(addId: number, AddresseDto: AddresseDto): Observable<AddresseDto> {
    return this.http.put<AddresseDto>(`${this.apiServerUrl}/addresses/update/${addId}`, AddresseDto);
  }

  public deleteAddresseDto(noteId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/addresses/delete/${noteId}`);
  }


}
