import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { BulkFileDetailsDTO } from '../Model/bulkFileDetailsDTO';

@Injectable({
  providedIn: 'root'
})
export class FilestatusService {

  constructor(private _http: HttpClient) { }

  public getBulkUploadDetails(): Observable<Array<BulkFileDetailsDTO>> {
    return this._http.get<Array<BulkFileDetailsDTO>>(AppComponent.API_URL + 'requestPosting/bulkrequest/getallbulkdetails');
  }

  public reProcess(psxBatchId_reProcess: String): Observable<string> {
    return this._http.get<string>(AppComponent.API_URL + 'requestPosting/bulkrequest/reprocess/'+psxBatchId_reProcess);
  }
}
