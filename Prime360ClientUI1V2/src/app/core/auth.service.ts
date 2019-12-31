import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from "../app.component";

@Injectable()
export class AuthService {
  
  // APP_ADMIN_URL : string = "http://localhost:8080/" ;
  constructor(private http: HttpClient) {
  }

  attemptAuth(userId: string, password: string): Observable<any> {
    const credentials = { userId: userId, password: password, userType:AppComponent.menuType };
    console.log('attempAuth :: ' + userId + " = " + password);
    console.log('Auth Service AppComponent.API_ADMIN_URL :: ' + AppComponent.API_ADMIN_URL);
    console.log(AppComponent.API_ADMIN_URL + 'token/generate-token', credentials);
    return this.http.post<any>(AppComponent.API_ADMIN_URL + 'token/generate-token', credentials);
  }

  logout(token: string): Observable<any> {
    const authToken = { token: token, userType:AppComponent.menuType};
    console.log('Logout attempAuth :: ' + token);
    return this.http.post<any>(AppComponent.API_ADMIN_URL + 'user/logout', authToken);
  }
}