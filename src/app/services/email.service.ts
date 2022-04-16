import { FormGroup } from '@angular/forms';
import { ChauffeurDto } from './../models/chauffeur';
import { NewsleterDto } from './../models/newsleter';
import { UtilisateurDto } from './../models/utilisateur';
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

  choixmenu : string  = 'A';
  dataForm:  FormGroup;

  constructor(private http: HttpClient) {
  }

  /*************************** EmailDto ********************/
  public getEmailDTOsOrderByIdDesc(): Observable<EmailDto[]> {
    return this.http.get<EmailDto[]>(`${this.apiServerUrl}/emails/searchAllEmailsOrderByIdDesc`);
  }

  public getEmailDtoById(emailId: number): Observable<EmailDto> {
    return this.http.get<EmailDto>(`${this.apiServerUrl}/emails/findById/${emailId}`);
  } 

  public senEmailToManager(emailDto: EmailDto): Observable<EmailDto> {
    return this.http.post<EmailDto>(`${this.apiServerUrl}/emails/sendMailToManager`, emailDto);
  }

  public responseToEmailByManager(emailDto: EmailDto): Observable<EmailDto> {
    return this.http.post<EmailDto>(`${this.apiServerUrl}/emails/responseMailToCustomer`, emailDto);
  }

  public senEmailToRecruteur(userDTO: UtilisateurDto): Observable<UtilisateurDto> {
    return this.http.post<UtilisateurDto>(`${this.apiServerUrl}/emails/sendToRecruteur`, userDTO);
  }

  public sendToChauffeur(chauffDTO: ChauffeurDto): Observable<ChauffeurDto> {
    return this.http.post<ChauffeurDto>(`${this.apiServerUrl}/emails/sendToChauffeur`, chauffDTO);
  }

  public senEmailToVisitor(visitorDto: NewsleterDto): Observable<NewsleterDto> {
    return this.http.post<NewsleterDto>(`${this.apiServerUrl}/emails/sendToNewsletter`, visitorDto);
  }

   public senEmailAllToVisitor(visitorDto: NewsleterDto[]): Observable<NewsleterDto[]> {
    return this.http.post<NewsleterDto[]>(`${this.apiServerUrl}/emails/sendMailToAllCustomers`, visitorDto);
  }

  public deleteEmailDto(emailId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/emails/delete/${emailId}`);
  }



}
