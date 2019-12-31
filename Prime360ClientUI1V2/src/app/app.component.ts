import { Component, OnInit } from '@angular/core';
import { Http } from '@angular/http';
import { AppConfigService } from './core/app-config.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

// export class AppComponent {
//   static APP_BASE_URL: String = 'http://localhost:8090';
// }

export class AppComponent implements OnInit {
  title = 'Prime360ClientUI1V2';
  static API_URL = "";
  static API_ADMIN_URL = "";
  static menuType = 'client';

  config: any;

  constructor(private https: Http,private appConfigService:AppConfigService) {
    //this.config = configService.loadJSON('/assets/config.json');
  }
  
  configLoaded: boolean = false;

  ngOnInit() {
      this.https.get('assets/prime360ClientConfig.json')
          .subscribe(config => {
            AppComponent.API_URL=this.appConfigService.getConfig("APP_BASE_URL");
            AppComponent.API_ADMIN_URL=this.appConfigService.getConfig("APP_ADMIN_URL");
            console.log('AppComponent.API_URL >> '+AppComponent.API_URL);
            this.configLoaded = true;
          });
  }

}
