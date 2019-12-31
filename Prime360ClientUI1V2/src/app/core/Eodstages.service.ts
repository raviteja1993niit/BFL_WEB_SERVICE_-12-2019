import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AppComponent } from '../app.component';
import { EodstagesDTO } from '../Model/EodstagesDTO';
@Injectable({
  providedIn: 'root'
})
export class EodstagesService {

  constructor(private _http: HttpClient) { }

  public getEodstagesintialDetails(): Observable<Array<String>> {
    return this._http.get<Array<String>>(AppComponent.API_URL + 'requestPosting/getEodStageStatus');
  }


  public getEodstagesDTO(psxbatchid:String):Observable<Array<EodstagesDTO>>{
    console.log("Batch Id >>> "+psxbatchid)
   let bid= psxbatchid.split("[");
    //console.log(bid[0]);
    //alert(bid[0]);
    return this._http.get<Array<EodstagesDTO>>(AppComponent.API_URL + 'requestPosting/getEodStageStatus1/'+ bid[0]);
  }
}
