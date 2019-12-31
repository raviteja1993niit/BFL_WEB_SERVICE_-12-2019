import { Injectable } from '@angular/core';
import { Observable } from '../../../node_modules/rxjs';

import { HttpClient, HttpHeaders, HttpParams } from '../../../node_modules/@angular/common/http';
import { AppComponent } from '../app.component';
@Injectable({
  providedIn: 'root'
})
export class NegativeuploadserviceService {
  
  url = "http://192.168.1.53:8088";

  constructor(private http: HttpClient) { }

  // public negativeUploadService<Response>(input): Observable<Response> {


  //   const options = {
  //     observe: "response" as 'body'
  //   };
   
  //  // const url =this.url+'/requestPosting/uploadnegativeeodfile';
  //   const url = AppComponent.API_URL + '/requestPosting/uploadnegativeeodfile';
  //   return this.http.post<Response>(url, input, options);
  // }
 
  public negativeUploadService(file: File) {

    const formdata: FormData = new FormData();
    formdata.append('file', file);
    const params = new HttpParams();
    const options = {
      params: params,
      reportProgress: true,
    };

    const url = AppComponent.API_URL + '/requestPosting/uploadnegativeeodfile';

    return this.http.post<any>(url, formdata, options);
  }

    public downloadCSVFile():any {
      var link=document.createElement('a');
      let str=AppComponent.API_URL+'requestPosting/bajaj/BFL-NegativeAreaBase';
      console.log(str);
      link.href=str;
      link.download="BFL-PSX_NEGATIVEAREA_BASE.csv";
      link.click();
      return true;
  }
}
