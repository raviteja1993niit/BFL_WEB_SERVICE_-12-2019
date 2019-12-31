import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { HttpRequest, HttpEvent, HttpParams, HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { AppComponent } from '../../app.component';
import { BulkPreviewWrapper } from 'src/app/Model/BulkPreviewWrapper';
import { HeaderColumnWrapper } from 'src/app/Model/HeaderColumnWrapper';


@Injectable()
export class UploadFileService {

   private baseUrl: string = AppComponent.API_URL + 'Upload';
   private baseUrl1: string = AppComponent.API_URL + 'requestPosting';

  constructor(private _http: HttpClient) { }

  getFileData(file: File) :Observable<BulkPreviewWrapper>{
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    const params = new HttpParams();
    const options = {
      params: params,
      reportProgress: true,
    };
    return this._http.post<BulkPreviewWrapper>(this.baseUrl + '/post', formdata, options);
  }

  getheaderData(serverFile :string) : Observable<HeaderColumnWrapper>{
    console.log("3");
    return this._http.get<HeaderColumnWrapper>(this.baseUrl +'/getCsv/'+serverFile);
  }
 
 
  saveUploadData(csvFileName :string ,headercolumnWrapper :string , profileId :string) :Observable<string>{
        return this._http.post<string>(this.baseUrl1+'bulkrequest'+'/'+profileId,csvFileName);
  }
}