import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './core/auth.guard';
import { IdentitysearchComponent } from './component/identitysearch/identitysearch.component';
import { LoginComponent } from './component/login/login.component';
import { RelationalsearchComponent } from './component/relationalsearch/relationalsearch.component';
import { CustomersearchComponent } from './component/customersearch/customersearch.component';
import { ResultComponent } from './component/result/result.component';
import { FilestatusComponent } from './component/filestatus/filestatus.component';
import { ResetpasswordComponent } from './component/resetpassword/resetpassword.component';
import { MappingComponent } from './component/mapping/mapping.component';
import { BulkuploadresultComponent } from './component/bulkuploadresult/bulkuploadresult.component';
import { WelcomePageComponent } from './component/welcome-page/welcome-page.component';
import { BulkuploadComponent } from './component/bulkupload/bulkupload.component';
import { EoduploadComponent } from './component/eodupload/eodupload.component';
import { NegativeuploadComponent } from './component/negativeupload/negativeupload.component';
import { EodFilestatusComponent } from './component/Eodfilestatus/Eodfilestatus.component'
import {EodstagesComponent} from './component/Eodstages/Eodstages.component'
const routes: Routes = [
    { path: '', component: WelcomePageComponent,canActivate:[AuthGuard]},
    { path: 'welcome', component: WelcomePageComponent,canActivate:[AuthGuard]},
    { path: "login", component: LoginComponent },
    { path: "eodupload", component: EoduploadComponent },
    { path: "negativeupload", component: NegativeuploadComponent },
    { path: 'identitysearch', component: IdentitysearchComponent,canActivate:[AuthGuard]},
    { path: 'relationalsearch', component: RelationalsearchComponent,canActivate:[AuthGuard]},
    // { path: 'relationalsearch/:key', component: RelationalsearchComponent,canActivate:[AuthGuard]},
    { path: 'customersearch', component: CustomersearchComponent,canActivate:[AuthGuard]},
    { path: 'result', component: ResultComponent,canActivate:[AuthGuard]},
    { path: "mapping", component: MappingComponent },
    { path: 'filestatus', component: FilestatusComponent,canActivate:[AuthGuard]},
    { path: 'eodstatistics', component: EodFilestatusComponent,canActivate:[AuthGuard]},
    { path: 'eodstages', component: EodstagesComponent,canActivate:[AuthGuard]},
    { path: 'bulkupload', component: BulkuploadComponent},
    { path: "resetpassword",component:ResetpasswordComponent,canActivate:[AuthGuard]},
    { path: "bulkuploadresult", component: BulkuploadresultComponent,canActivate:[AuthGuard] },
    { path: "upload", component : EoduploadComponent,canActivate:[AuthGuard]},
    
    { path: "**", redirectTo: '' }
]

@NgModule({
    imports: [RouterModule.forRoot(routes,{useHash:true})],
    exports: [RouterModule]
})
export class AppRoutingModule { }