import { Injectable } from '@angular/core';
import { HttpRequest, HttpEvent, HttpParams, HttpClient } from '@angular/common/http';
import { AppComponent } from '../../app.component';
import { Observable } from 'rxjs/Observable';
import { ProcessDto } from 'src/app/Model/ProcessDto';


@Injectable({
  providedIn: 'root'
})
export class MappingService {

  private baseUrl: string = AppComponent.API_URL + 'Upload';
  private baseUrl1: string = AppComponent.API_URL + 'requestPosting/';

  constructor(private _http: HttpClient) { }
 
  public getAllProfileName() :Observable<string[]>
  {
    return this._http.get<string[]>(this.baseUrl +'/getProfileName');

  }
  public saveUploadData(processDto : ProcessDto) : Observable<string>{
    // const params = new HttpParams();

    // const options = {
    //   params: params,
    //   reportProgress: true,
    // };
    return this._http.post<string>(this.baseUrl1+'processDto',processDto);
  }
 

}