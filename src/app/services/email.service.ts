import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { EmailDto } from './../models/email';
import { environment } from './../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  public apiServerUrl = environment.apiBaseUrl;

  //public apiServerUrl = "https://server-chauffeur.herokuapp.com/sen-emails/v1";

  constructor(private http: HttpClient) {
  }

  /*************************** EmailDto ********************/
  public getEmailDtos(): Observable<EmailDto[]> {
    return this.http.get<EmailDto[]>(`${this.apiServerUrl}/emails/all`);
  }

  public getEmailDTOsOrderByIdDesc(): Observable<EmailDto[]> {
    return this.http.get<EmailDto[]>(`${this.apiServerUrl}/emails/all`);
  }

  public getEmailDtoById(emailId: number): Observable<EmailDto> {
    return this.http.get<EmailDto>(`${this.apiServerUrl}/emails/${emailId}`);
  }

  public addEmailDto(emailDto: EmailDto): Observable<EmailDto> {
    return this.http.post<EmailDto>(`${this.apiServerUrl}/emails/create`, emailDto);
  }

  public updateEmailDto(emailId: number, emailDto: EmailDto): Observable<EmailDto> {
    return this.http.put<EmailDto>(`${this.apiServerUrl}/emails/update/${emailId}`, emailDto);
  }

  public deleteEmailDto(emailId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/emails/delete/${emailId}`);
  }

  public countNumberOfemails(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/emails/NumbersOfemails`);
  }

}
