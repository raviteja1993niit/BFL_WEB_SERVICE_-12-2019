import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import * as $ from 'jquery';
import * as toastr from 'toastr';
import { ProfileEntityDTO } from '../../Model/ProfileEntityDTO';
import { RelationalSearchDTO } from '../../Model/RelationalSearchDTO';
import { RelationalSearchTableDTO } from '../../Model/RelationalSearchTableDTO';
import { RelationalSearchParameter } from '../../Model/RelationalSearchParameter';
import { RelationalsearchService } from "../../core/relationalsearch.service";

@Component({
  selector: 'app-relationalsearch',
  templateUrl: './relationalsearch.component.html',
  styleUrls: ['./relationalsearch.component.css']
})
export class RelationalsearchComponent implements OnInit {

  result: String;
  error: String;
  showTable: boolean = false;
  inputFieldValue: boolean = false;
  static tabData: String = 'relationComponent';
  profileEntities = new Array<ProfileEntityDTO>();
  identityRowData: Array<String> = new Array<String>();
  identityRowHeader: Array<String> = new Array<String>();
  customerRowData: Array<String> = new Array<String>();
  customerRowHeader: Array<String> = new Array<String>();
  relationalSearchDTO: RelationalSearchDTO = new RelationalSearchDTO();
  relationalSearchTableDTO: RelationalSearchTableDTO = new RelationalSearchTableDTO();
  relationalSearchParameter: RelationalSearchParameter = new RelationalSearchParameter();
  displayRelationalSearchTableDTO: RelationalSearchTableDTO = new RelationalSearchTableDTO();
  currentTabIndex = 0;

  constructor(private relationalsearchService: RelationalsearchService, private router: Router) {
    if (RelationalsearchComponent.tabData === 'identityComponent') {
      RelationalsearchComponent.tabData = 'relationComponent';
    } else {
      window.sessionStorage.removeItem("identityHeaderRow");
      window.sessionStorage.removeItem("identityDataRow");
    }
  }

  ngOnInit() {
    if (RelationalsearchComponent.tabData === 'customerComponent') {
      RelationalsearchComponent.tabData = 'relationComponent';
    } else {
      window.sessionStorage.removeItem("customerHeaderRow");
      window.sessionStorage.removeItem("customerDataRow");
    }
    // this.getAllRelationalProfiles();
    this.getAllActiveProfiles();
    window.sessionStorage.removeItem('profileId');
    this.getRelationalSearchTable();
  }

  getAllRelationalProfiles() {
    this.relationalsearchService.getAllRelationalProfiles().subscribe(data => {
      console.log('Relational Profile : ' + data.length)
      if (data != undefined && data.length > 0) {
        this.profileEntities = data;
      } else {
        toastr.error('Profile not loaded..!!!');
      }
    });
  }

  getAllActiveProfiles() {
    this.relationalsearchService.getAllActiveProfiles().subscribe(data => {
      this.profileEntities = data;
    });
  }

  getRelationalSearchTable() {
    this.relationalsearchService.getRelationalSearchTable().subscribe(retValue => {
      this.relationalSearchDTO = new RelationalSearchDTO();
      this.relationalSearchDTO = retValue;
      console.log('RelationalsearchComponent getRelationalSearchTable() : ' + JSON.stringify(retValue));
      this.setUIParamValue();
      this.currentTabIndex = 0;
      this.displayRelationalSearchTableDTO = this.relationalSearchDTO.tables[this.currentTabIndex];
    });
  }

  setUIParamValue() {
    console.log('identityHeaderRow ' + window.sessionStorage.getItem("identityHeaderRow"));
    console.log('identityDataRow ' + window.sessionStorage.getItem("identityDataRow"));
    if (window.sessionStorage.getItem("identityHeaderRow") != null && window.sessionStorage.getItem("identityDataRow") != null) {
      this.identityRowData = new Array<String>();
      this.identityRowHeader = new Array<String>();
      this.identityRowHeader = JSON.parse(window.sessionStorage.getItem("identityHeaderRow"));
      this.identityRowData = JSON.parse(window.sessionStorage.getItem("identityDataRow"));
      let uiTabCount = this.relationalSearchDTO.tables.length;
      for (let i = 0; i < this.identityRowHeader.length; i++) {
        const headerCoulmnName = this.identityRowHeader[i];
        const rowColumnData = this.identityRowData[i];
        for (let j = 0; j < uiTabCount; j++) {
          const inputColumnCount = this.relationalSearchDTO.tables[j].relationalSearchParameter.length;
          for (let k = 0; k < inputColumnCount; k++) {
            const engineName = this.relationalSearchDTO.tables[j].relationalSearchParameter[k].engineName;
            if (engineName == headerCoulmnName) {
              this.relationalSearchDTO.tables[j].relationalSearchParameter[k].parameterValue = rowColumnData;
            }
          }
        }
      }
    }

    if (window.sessionStorage.getItem("customerHeaderRow") != null && window.sessionStorage.getItem("customerDataRow") != null) {
      this.customerRowData = new Array<String>();
      this.customerRowHeader = new Array<String>();
      this.customerRowHeader = JSON.parse(window.sessionStorage.getItem("customerHeaderRow"));
      this.customerRowData = JSON.parse(window.sessionStorage.getItem("customerDataRow"));
      let uiTabCount = this.relationalSearchDTO.tables.length;
      for (let i = 0; i < this.customerRowHeader.length; i++) {
        const headerCoulmnName = this.customerRowHeader[i];
        const rowColumnData = this.customerRowData[i];
        for (let j = 0; j < uiTabCount; j++) {
          const inputColumnCount = this.relationalSearchDTO.tables[j].relationalSearchParameter.length;
          for (let k = 0; k < inputColumnCount; k++) {
            const engineName = this.relationalSearchDTO.tables[j].relationalSearchParameter[k].engineName;
            if (engineName == headerCoulmnName) {
              this.relationalSearchDTO.tables[j].relationalSearchParameter[k].parameterValue = rowColumnData;
            }
          }
        }
      }
    }
  }

  getProfileById(profileId: string) {
    $('#err').hide();
    if (profileId != undefined) {
      window.sessionStorage.setItem("profileId", profileId);
    }
  }

  submitRelationalSearchRequest() {
    this.result = '';
    this.error = '';
    this.inputFieldValue = false;
    let tabCount = this.relationalSearchDTO.tables.length;
    for (let i = 0; i < tabCount; i++) {
      const inputFieldCount = this.relationalSearchDTO.tables[i].relationalSearchParameter.length;
      for (let j = 0; j < inputFieldCount; j++) {
        const paramValue = this.relationalSearchDTO.tables[i].relationalSearchParameter[j].parameterValue;
        if (paramValue != '' && paramValue != null) {
          this.inputFieldValue = true;
        }
      }
    }

    if (window.sessionStorage.getItem("profileId") != null && this.inputFieldValue == true) {
      this.relationalsearchService.submitRelationalSearchRequest(this.relationalSearchDTO, window.sessionStorage.getItem("profileId")).subscribe(retValue => {
        this.relationalSearchDTO = new RelationalSearchDTO();
        this.relationalSearchDTO = retValue;
        console.log('RelationalsearchComponent : ' + JSON.stringify(this.relationalSearchDTO));
        if (this.relationalSearchDTO.results != null) {         //this.relationalSearchDTO.results.length > 0
          this.showTable = true;
        } else {
          toastr.warning('Engine Not started');
        }
      });
      this.showTable = true;
    }
    if (window.sessionStorage.getItem("profileId") == null) {
      toastr.error('Select Any Profile to Continue...!!!');
    }
    if (this.inputFieldValue == false) {
      toastr.warning('Enter any input field value...!!!');
    }
  }

  //for preview when click on tab
  onTabSubmit(fileIndex: any, fileName: any) {
    for (let i = 0; i < this.relationalSearchDTO.tables.length; i++) {
      if (fileName === this.relationalSearchDTO.tables[i].tableName) {
        this.displayRelationalSearchTableDTO = this.relationalSearchDTO.tables[i];
        this.currentTabIndex = i;
      }
    }
  }

  reset() {
    this.result = '';
    this.router.navigate(['/relationalsearch']);
  }
}
