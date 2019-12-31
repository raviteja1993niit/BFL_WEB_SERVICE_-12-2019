import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from "../app.component";
import { Observable } from 'rxjs/Observable';
import { NavbarParentDto } from '../Model/navbar-parent-dto';


@Injectable()
export class NavbarService {
  constructor(public http: HttpClient) { }

  public getNavMenu(roleId: String): Observable<NavbarParentDto[]> {
    return this.http.get<NavbarParentDto[]>(AppComponent.API_ADMIN_URL + 'admin/user/getmenus/' + roleId + '/' + AppComponent.menuType);
  }
}