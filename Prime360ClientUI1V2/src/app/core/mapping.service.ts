import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AppComponent } from '../app.component';
import { ListProcessDto } from '../Model/ListProcessDto';


@Injectable({
  providedIn: 'root'
})
export class MappingService {

  selectedTableNames = new Set<String>();

  constructor(private _http: HttpClient) { }

  saveUploadData(lstProcessDto: ListProcessDto): Observable<Array<string>> {
    console.log(AppComponent.API_URL + 'requestPosting/processDto', lstProcessDto);
    return this._http.post<Array<string>>(AppComponent.API_URL + 'requestPosting/processDto', lstProcessDto);
  }

}
