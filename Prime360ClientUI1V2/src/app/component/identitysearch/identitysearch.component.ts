import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { IdentitySearchDTO } from "../../Model/IdentitySearchDTO";
import { NSPRequestResultsDTO } from '../../Model/ReqResult';
import { RelationalsearchComponent } from '../relationalsearch/relationalsearch.component';
import { CustomersearchComponent } from '../customersearch/customersearch.component';
import { IdentitysearchService } from '../../core/identitysearch.service';
import * as toastr from 'toastr';

@Component({
  selector: 'app-identitysearch',
  templateUrl: './identitysearch.component.html',
  styleUrls: ['./identitysearch.component.css']
})
export class IdentitysearchComponent implements OnInit {

  result: String;
  error: string;
  retValue: String;
  equality: String;
  equalityType: String;
  showTable: boolean = false;
  equalityArray = new Array<String>();
  equalityInputArray = new Array<String>();
  identitySearchDTO: IdentitySearchDTO = new IdentitySearchDTO();

  constructor(private _identityService: IdentitysearchService, private router: Router) { }

  ngOnInit() {
    this.getAllEquality();
    window.sessionStorage.removeItem("custProfileId");
    window.sessionStorage.removeItem("profileId");
  }

  getAllEquality() {
    this._identityService.getAllEquality().subscribe(response => {
      this.equalityArray = response;
    });
  }

  submitIdentitySearchRequest() {
    this.result = '';
    this.identitySearchDTO = new IdentitySearchDTO();
    if (this.equality != null && this.equalityType != null) {
      this.equalityInputArray = new Array<String>();
      this.equalityInputArray[0] = this.equalityType;
      this.equalityInputArray[1] = this.equality;

      this._identityService.submitIdentitySearchRequest(this.equalityInputArray).subscribe(response => {
        this.retValue = response;
        console.log('JSON.stringify(this.retValue) : ' + JSON.stringify(this.retValue));
        if (JSON.stringify(this.retValue) != 'false') {
          console.log('this.retValue ' + JSON.stringify(this.retValue));
          this.identitySearchDTO = new IdentitySearchDTO();
          this.result = "Data submitted Successfully With RequestId : " + this.retValue;
          this.getIdentitySearchResultsByRequestId(this.retValue);
          this.showTable = true;
        } else {
          toastr.warning('Engine not started...!!!');
          toastr.warning('Request Posting failed...!!!');
        }
      });
    } else {
      toastr.warning('Equality / Equality Type missing...!!!');
    }
  }

  getIdentitySearchResultsByRequestId(requestId: String) {
    this.identitySearchDTO = new IdentitySearchDTO();
    this._identityService.getIdentitySearchResultsByRequestId(requestId).subscribe(response => {
      this.identitySearchDTO = response;
      console.log('this.identitySearchDTO : ' + JSON.stringify(this.identitySearchDTO));
      this.result = "Data submitted Successfully With RequestId : " + this.retValue;
    });
  }

  openCustomerTab(headerRow: Array<String>, dataRow: Array<String>) {
    window.sessionStorage.setItem("identityHeaderRow", JSON.stringify(headerRow));
    window.sessionStorage.setItem("identityDataRow", JSON.stringify(dataRow));
    // CustomersearchComponent.tabData = 'identityComponent';
    this.router.navigate(['/customersearch']);
  }

  openRelationalTab(headerRow: Array<String>, dataRow: Array<String>) {
    window.sessionStorage.setItem("identityHeaderRow", JSON.stringify(headerRow));
    window.sessionStorage.setItem("identityDataRow", JSON.stringify(dataRow));
    RelationalsearchComponent.tabData = 'identityComponent';
    this.router.navigate(['/relationalsearch']);
  }

  reset() {
    this.result = '';
    this.router.navigate(['/navigate']);
  }
}
