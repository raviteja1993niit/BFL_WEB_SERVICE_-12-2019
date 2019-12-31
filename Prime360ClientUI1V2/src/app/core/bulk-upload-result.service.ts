import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { RequestResultsWrapper } from '../Model/requestResultsWrapper';

@Injectable({
  providedIn: 'root'
})
export class BulkUploadResultService {
  psxBatchId_Pageno: Array<String> = new Array<String>(2);

  private options = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };

  constructor(private _http: HttpClient) { }

  public getBulkUploadDetails(psxBatchId: String, pageNo: String): Observable<RequestResultsWrapper> {
    console.log('Bulk Service : '+psxBatchId+' : '+pageNo);
    this.psxBatchId_Pageno[0] = psxBatchId;
    this.psxBatchId_Pageno[1] = pageNo;
    return this._http.get<RequestResultsWrapper>(AppComponent.API_URL + 'requestPosting/getResultsBybatchId/' + psxBatchId + '/' + pageNo, this.options);
  }

}
