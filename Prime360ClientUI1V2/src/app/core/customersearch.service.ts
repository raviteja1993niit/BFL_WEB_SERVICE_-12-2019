import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { ProfileEntityDTO } from '../Model/ProfileEntityDTO';
import { CustomerSearchDTO } from '../Model/CustomerSearchDTO';
import { ReturnStatement } from '@angular/compiler';

@Injectable({
  providedIn: 'root'
})
export class CustomersearchService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private _httpClient: HttpClient) { }

  getAllActiveProfiles(): Observable<Array<ProfileEntityDTO>> {
    return this._httpClient.get<Array<ProfileEntityDTO>>(AppComponent.API_URL + 'requestPosting/getAllActiveProfiles', this.httpOptions);
  }

  getCustomerSearchTable(): Observable<CustomerSearchDTO> {
    return this._httpClient.get<CustomerSearchDTO>(AppComponent.API_URL + 'requestPosting/getCustomerSearchTable', this.httpOptions);
  }

  submitCustomerSearchRequest(data: CustomerSearchDTO, profileId: string): Observable<CustomerSearchDTO> {
    return this._httpClient.post<CustomerSearchDTO>(AppComponent.API_URL + 'requestPosting/submitCustomerSearchRequest/' + profileId + '/customersearch', data, this.httpOptions);
  }

}
