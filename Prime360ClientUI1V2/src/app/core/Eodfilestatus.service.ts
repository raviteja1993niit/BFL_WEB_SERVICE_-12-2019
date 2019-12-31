import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { EodfilestatusDTO } from '../Model/EodfilestatusDTO';
import { Error_Records_Info_T } from '../Model/Error_Records_Info_T';
@Injectable({
  providedIn: 'root'
})
export class EodFilestatusService {

  constructor(private _http: HttpClient) { }

  public getEodFileStatusDetails(): Observable<Array<EodfilestatusDTO>> {
    return this._http.get<Array<EodfilestatusDTO>>(AppComponent.API_URL + 'requestPosting/getEodFileStatus');
  }

  public reProcess(psxBatchId_reProcess: String): Observable<string> {
    return this._http.get<string>(AppComponent.API_URL + 'requestPosting/bulkrequest/reprocess/'+psxBatchId_reProcess);
  }
  public getErrorData(batchId:String):Observable<Array<Error_Records_Info_T>>{
    return this._http.get<Array<Error_Records_Info_T>>(AppComponent.API_URL + 'requestPosting/getErrorData/'+ batchId);
  }
}
