import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { Observable } from 'rxjs';

import { ProfileEntityDTO } from '../Model/ProfileEntityDTO';
import { NSPRequestDTO } from '../Model/reqentity';

@Injectable({
  providedIn: 'root'
})
export class SharedService {

  nspReqDTO: NSPRequestDTO;

  private baseUrl: string = AppComponent.API_URL + 'requestPosting';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private _httpClient: HttpClient) { }

  setter(demo: NSPRequestDTO) {
    this.nspReqDTO = demo;
  }

  getter() {
    return this.nspReqDTO;
  }

  // getAllEquality(): Observable<Array<String>> {
  //   return this._httpClient.get<Array<String>>(this.baseUrl + '/getAllEquality', this.httpOptions);
  // }

  // saveCustomerSearchtData(data: NSPRequestDTO, profileId: string): Observable<String> {
  //   return this._httpClient.post<String>(this.baseUrl + '/saveCustomerSearchtData/' + profileId + '/customersearch', data, this.httpOptions);
  // }

  // saveIdentitySearchData(data: NSPRequestDTO) {
  //   return this._httpClient.post<String>(this.baseUrl + '/saveIdentitySearchData/identitysearch', data, this.httpOptions);
  // }

  // getAllProfiles(): Observable<Array<ProfileEntityDTO>> {
  //   return this._httpClient.get<Array<ProfileEntityDTO>>(this.baseUrl + '/getAllProfiles', this.httpOptions);
  // }

}