import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FacebookService {

  public apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient) {
  }

  public countNumberOfPagesFollowers(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/facebooks/NumberOfPagesFollowers`);
  }

  public countNumberOfPagesLikes(): Observable<any> {
    return this.http.get(`${this.apiServerUrl}/facebooks/NumberOfPagesLikes`);
  }

}
