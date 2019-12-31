import { Injectable } from '@angular/core';
import {AppComponent} from "../app.component";
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { ResponseData } from '../Model/response-data';

@Injectable({
  providedIn: 'root'
})
export class PasswordService {

  constructor(private http : HttpClient) { }

  public changePassword(passwordDto) : Observable<ResponseData>{
    return this.http.post<ResponseData>(AppComponent.API_ADMIN_URL+'user/changepassword',passwordDto);
  }

  public forgetPassword(passwordDto) : Observable<ResponseData>{
    return this.http.post<ResponseData>(AppComponent.API_ADMIN_URL+'user/forgetPassword',passwordDto);
  }
}
