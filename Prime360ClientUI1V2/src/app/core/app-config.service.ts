import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigUrl } from '../../app/Model/config-url'

@Injectable({
  providedIn: 'root'
})
export class AppConfigService {

  private appConfig;
  private appUrl: ConfigUrl[];

  constructor(private http: HttpClient) { }

  loadAppConfig() {
    return this.http.get('/assets/prime360ClientConfig.json')
      .toPromise()
      .then(data => {
        this.appUrl = data as ConfigUrl[];
      });
  }

  getConfig(key: String) {
    for (let i = 0; i < this.appUrl.length; i++) {
      if (this.appUrl[i].name == key) {
        this.appConfig = this.appUrl[i].url;
      }
    }
    return this.appConfig;
  }
}