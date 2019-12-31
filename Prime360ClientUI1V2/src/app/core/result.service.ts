import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
//import { ResultsDTO } from '../Model/ResultsDTO';
import { REquestResultDTO } from '../Model/REquestResultDTO';

import { NSPRequestResultsDTO } from '../Model/ReqResult';
import { NSPRequestResultsEntity } from '../Model/NSPRequestResultsEntity';
import { NSPRequestEntity } from '../Model/NSPRequestEntity';
import { RequestResultsEntity } from '../Model/RequestResultsEntity';

@Injectable({
  providedIn: 'root'
})
export class ResultService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private _http: HttpClient) { }

  public getAllRequestId(): Observable<string[]> {
    return this._http.get<string[]>(AppComponent.API_URL + 'requestPosting/getAllRequestId');
  }

  
  
  public getRecordsBasedOnId(requestId: String): Observable<RequestResultsEntity> {
  return this._http.get<RequestResultsEntity>(AppComponent.API_URL + 'requestPosting/getRecordsByRequestId/' + requestId);
  }
  
  
}
