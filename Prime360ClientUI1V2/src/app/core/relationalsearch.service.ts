import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProfileEntityDTO } from '../Model/ProfileEntityDTO';
import { AppComponent } from '../app.component';
import { RelationalSearchDTO } from '../Model/RelationalSearchDTO';

@Injectable({
  providedIn: 'root'
})
export class RelationalsearchService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private _httpClient: HttpClient) { }

  getAllRelationalProfiles(): Observable<Array<ProfileEntityDTO>> {
    return this._httpClient.get<Array<ProfileEntityDTO>>(AppComponent.API_URL + 'requestPosting/getAllRelationalProfiles', this.httpOptions);
  }

  getAllActiveProfiles(): Observable<Array<ProfileEntityDTO>> {
    return this._httpClient.get<Array<ProfileEntityDTO>>(AppComponent.API_URL + 'requestPosting/getAllActiveProfiles', this.httpOptions);
  }

  getRelationalSearchTable(): Observable<RelationalSearchDTO> {
    return this._httpClient.get<RelationalSearchDTO>(AppComponent.API_URL + 'requestPosting/getRelationalSearchTable', this.httpOptions);
  }

  submitRelationalSearchRequest(data: RelationalSearchDTO, profileId: string): Observable<RelationalSearchDTO> {
    return this._httpClient.post<RelationalSearchDTO>(AppComponent.API_URL + 'requestPosting/submitRelationalSearchRequest/' + profileId + '/relationalsearch', data, this.httpOptions);
  } 
}
