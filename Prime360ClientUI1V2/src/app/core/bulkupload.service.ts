import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { AppComponent } from '../app.component';
import { HttpParams, HttpClient, HttpHeaders } from '@angular/common/http';
import { BaseMetaInfo } from '../Model/BaseMetaInfo';
import { InputMetaInfo } from '../Model/InputMetaInfo';
import { BulkPreviewWrapper } from '../Model/BulkPreviewWrapper';
import { HeaderColumnWrapper } from '../Model/HeaderColumnWrapper';
import { ProfileEntityDTO } from '../Model/ProfileEntityDTO';

@Injectable({
  providedIn: 'root'
})
export class BulkuploadService {

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  };
  
  constructor(private httpClient: HttpClient) { }

  upload(file: File): Observable<BulkPreviewWrapper> {
    const formdata: FormData = new FormData();
    formdata.append('file', file);
    const params = new HttpParams();
    const options = {
      params: params,
      reportProgress: true,
    };
    return this.httpClient.post<BulkPreviewWrapper>(AppComponent.API_URL + 'Upload/post', formdata, options);
  }

  getCsvDatabaseData(serverFile: string): Observable<HeaderColumnWrapper> {
    return this.httpClient.get<HeaderColumnWrapper>(AppComponent.API_URL + 'Upload/getCsv/' + serverFile);
  }

  getAllTableNames(baseMetaInfo: BaseMetaInfo): Observable<InputMetaInfo> {
    console.log("BulkuploadService :: " + baseMetaInfo);
    return this.httpClient.post<InputMetaInfo>(AppComponent.API_URL + 'Upload/getDataBasedOnCsvString', baseMetaInfo);
  }

  getLatestCsvStringBasedOnSourceSystemName() {
    return this.httpClient.get<BaseMetaInfo>(AppComponent.API_URL + 'Upload/getLastestRecord');
  }

  getAllActiveProfiles(): Observable<Array<ProfileEntityDTO>> {
    return this.httpClient.get<Array<ProfileEntityDTO>>(AppComponent.API_URL + 'requestPosting/getAllActiveProfiles', this.httpOptions);
  }
}
