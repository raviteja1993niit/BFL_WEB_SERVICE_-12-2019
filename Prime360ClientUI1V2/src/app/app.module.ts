import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { NavbarComponent } from './navbar/navbar.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { DataTablesModule } from 'angular-datatables';
import { FlatpickrModule } from 'angularx-flatpickr';
import * as flatpickr from 'flatpickr';

export function flatpickrFactory() {
  return flatpickr;
}

import { SharedService } from './core/shared.service';

import { AuthService } from './core/auth.service';
import { AuthGuard } from './core/auth.guard';
import { TokenStorage } from './core/token.storage';
import { Interceptor } from './core/inteceptor';
import { NavbarService } from './core/navbar.service';
import { MenuStorageService } from './core/menu-storage.service';
import { PasswordService } from './core/password.service';
import { AppConfigService } from './core/app-config.service';
import { FilestatusComponent } from './component/filestatus/filestatus.component';
import { ResetpasswordComponent } from './component/resetpassword/resetpassword.component';
import { LoginComponent } from './component/login/login.component';
import { CustomersearchComponent } from './component/customersearch/customersearch.component';
import { IdentitysearchComponent } from './component/identitysearch/identitysearch.component';
import { RelationalsearchComponent } from './component/relationalsearch/relationalsearch.component';
import { ResultComponent } from './component/result/result.component';
import { MappingComponent } from './component/mapping/mapping.component';
import { FilestatusService } from './core/filestatus.service';
import { BulkuploadresultComponent } from './component/bulkuploadresult/bulkuploadresult.component';
import { BulkUploadResultService } from './core/bulk-upload-result.service';
import { WelcomePageComponent } from './component/welcome-page/welcome-page.component';
import { MappingService } from './core/mapping.service';
import { BulkuploadService } from './core/bulkupload.service';
import { BulkuploadComponent } from './component/bulkupload/bulkupload.component';

// Angular Components
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { EoduploadComponent } from './component/eodupload/eodupload.component';
import { NegativeuploadComponent } from './component/negativeupload/negativeupload.component';
import { FileuploadComponent } from './component/fileupload/fileupload.component';
import { EodFilestatusComponent } from './component/Eodfilestatus/Eodfilestatus.component';
import { EodstagesComponent } from './component/Eodstages/Eodstages.component';
const appInitializerFn = (appConfig: AppConfigService) => {
  return () => {
    return appConfig.loadAppConfig();
  };
};

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    MappingComponent,
    BulkuploadComponent,
    NavbarComponent,
    CustomersearchComponent,
    IdentitysearchComponent,
    RelationalsearchComponent,
    ResultComponent,
    FilestatusComponent,
    ResetpasswordComponent,
    BulkuploadresultComponent,
    WelcomePageComponent,
    EoduploadComponent,
    NegativeuploadComponent,
    FileuploadComponent,
    EodFilestatusComponent,
    EodstagesComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule,
    DataTablesModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule,
    FlatpickrModule.forRoot()
  ],

  providers: [SharedService, BulkuploadService, MappingService, AppConfigService, AuthService, AuthGuard, TokenStorage, Interceptor, NavbarService, MenuStorageService, PasswordService, FilestatusService, BulkUploadResultService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: Interceptor,
      multi: true,
    }, {
      provide: APP_INITIALIZER,
      useFactory: appInitializerFn,
      multi: true,
      deps: [AppConfigService]
    }],
  bootstrap: [AppComponent]

})
export class AppModule { }
