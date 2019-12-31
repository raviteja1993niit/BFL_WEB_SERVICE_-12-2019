import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '../../../node_modules/@angular/common/http';
import { AppComponent } from '../app.component';

@Injectable({
  providedIn: 'root'
})
export class EoduploadserviceService {

  constructor(private http: HttpClient) { }

  // public eodUploadService<Response>(input): Observable<Response> {


  //   const options = {
  //     observe: "response" as 'body'
  //   };

  //   const url = AppComponent.API_URL+'/requestPosting/uploadeodfile';
  //   console.log("input",input.get("dataSource"))
  //   return this.http.post<Response>(url, input, options);
  // }

  public getAllDatasources(): Observable<string[]> {
    return this.http.get<string[]>(AppComponent.API_URL + 'requestPosting/getAllDatasources');
  }


  public eodUploadService(file: File,selectedDataSource: string) {

    const formdata: FormData = new FormData();
    formdata.append('file', file);
    formdata.append('dataSource',selectedDataSource);
    const params = new HttpParams();
    const options = {
      params: params,
      reportProgress: true,
    };

    const url = AppComponent.API_URL + '/requestPosting/uploadeodfile';

    return this.http.post<any>(url, formdata, options);
  }
}
