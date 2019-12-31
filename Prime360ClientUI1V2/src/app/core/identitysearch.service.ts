import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { NSPRequestResultsDTO } from '../Model/ReqResult';
import { IdentitySearchDTO } from '../Model/IdentitySearchDTO';

@Injectable({
  providedIn: 'root'
})
export class IdentitysearchService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private _httpClient: HttpClient) { }

  getAllEquality(): Observable<Array<String>> {
    return this._httpClient.get<Array<String>>(AppComponent.API_URL + 'requestPosting/getAllEquality', this.httpOptions);
  }

  submitIdentitySearchRequest(equalityData: Array<String>) {
    return this._httpClient.post<String>(AppComponent.API_URL + 'requestPosting/submitIdentitySearchRequest/identitySearch', equalityData, this.httpOptions);
  }

  getIdentitySearchResultsByRequestId(requestId: String): Observable<IdentitySearchDTO> {
    return this._httpClient.get<IdentitySearchDTO>(AppComponent.API_URL + 'requestPosting/getIdentitySearchResultsByRequestId/' + requestId);
  }
}
