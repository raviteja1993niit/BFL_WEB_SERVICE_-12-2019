// import { Component, OnInit } from '@angular/core';
// import { Router } from '@angular/router';
// import * as $ from 'jquery';
// import { ProfileEntityDTO } from '../../Model/ProfileEntityDTO';
// import { CustomerSearchDTO } from "../../Model/CustomerSearchDTO";
// import { CustomerSearchTableDTO } from "../../Model/CustomerSearchTableDTO";
// import { CustomerSearchParameter } from "../../Model/CustomerSearchParameter";
// import { RelationalsearchComponent } from '../relationalsearch/relationalsearch.component';
// import { CustomersearchService } from "../../core/customersearch.service";
// import * as toastr from 'toastr';

// @Component({
//   selector: 'app-customersearch',
//   templateUrl: './customersearch.component.html',
//   styleUrls: ['./customersearch.component.css']
// })
// export class CustomersearchComponent implements OnInit {

//   result: String;
//   error: String;
//   showTable: boolean = false;
//   inputValue: boolean = false;
//   static tabData: String = 'customerComponent';
//   profileEntities = new Array<ProfileEntityDTO>();
//   identityRowData: Array<String> = new Array<String>();
//   identityRowHeader: Array<String> = new Array<String>();
//   customerSearchDTO: CustomerSearchDTO = new CustomerSearchDTO();
//   customerSearchTableDTO: CustomerSearchTableDTO = new CustomerSearchTableDTO();
//   customerSearchParameter: CustomerSearchParameter = new CustomerSearchParameter();
//   displayCustomerSearchTableDTO: CustomerSearchTableDTO = new CustomerSearchTableDTO();

//   customerDto : Array<CustomerSearchParameter> = new Array<CustomerSearchParameter>();
//   contactDto : Array<CustomerSearchParameter> = new Array<CustomerSearchParameter>();
//   addressDto : Array<CustomerSearchParameter> = new Array<CustomerSearchParameter>();
//   emailDto : Array<CustomerSearchParameter> = new Array<CustomerSearchParameter>();
//   currentTabIndex = 0;

//   constructor(private customersearchService: CustomersearchService, private router: Router) {
//     if (CustomersearchComponent.tabData === 'identityComponent') {
//       CustomersearchComponent.tabData = 'customerComponent';
//     } else {
//       window.sessionStorage.removeItem("identityHeaderRow");
//       window.sessionStorage.removeItem("identityDataRow");
//     }
//   }

//   ngOnInit() {
//     this.getAllActiveProfiles();
//     window.sessionStorage.removeItem("custProfileId");
//     this.getCustomerSearchTable();
//   }

//   getAllActiveProfiles() {
//     this.customersearchService.getAllActiveProfiles().subscribe(data => {
//       this.profileEntities = data;
//     });
//   }

//   getCustomerSearchTable() {
//     this.customersearchService.getCustomerSearchTable().subscribe(retValue => {
//       this.customerSearchDTO = new CustomerSearchDTO();
//       this.customerSearchDTO = retValue;
//       console.log('CustomersearchComponent getCustomerSearchTable() : ' + JSON.stringify(retValue));
//       this.setUIParamValue();
//       this.currentTabIndex = 0;
//       this.displayCustomerSearchTableDTO = this.customerSearchDTO.tables[this.currentTabIndex];
//     });
//   }

//   setUIParamValue() {
//     if (window.sessionStorage.getItem("identityHeaderRow") != null && window.sessionStorage.getItem("identityDataRow") != null) {
//       this.identityRowHeader = JSON.parse(window.sessionStorage.getItem("identityHeaderRow"));
//       this.identityRowData = JSON.parse(window.sessionStorage.getItem("identityDataRow"));
//       let uiTabCount = this.customerSearchDTO.tables.length;
//       for (let i = 0; i < this.identityRowHeader.length; i++) {
//         const headerCoulmnName = this.identityRowHeader[i];
//         const rowColumnData = this.identityRowData[i];
//         for (let j = 0; j < uiTabCount; j++) {
//           const inputColumnCount = this.customerSearchDTO.tables[j].customerSearchParameters.length;
//           for (let k = 0; k < inputColumnCount; k++) {
//             const engineName = this.customerSearchDTO.tables[j].customerSearchParameters[k].engineName;
//             if (engineName == headerCoulmnName) {
//               this.customerSearchDTO.tables[j].customerSearchParameters[k].parameterValue = rowColumnData;
//             }
//           }
//         }
//       }
//     }
//   }

//   getProfileById(profileId: string) {
//     $('#err').hide();
//     window.sessionStorage.setItem("custProfileId", profileId);
//   }

//   submitCustomerSearchRequest() {
//     this.result = '';
//     this.error = '';
//     this.inputValue = false;
//     let tabCount = this.customerSearchDTO.tables.length;
//     for (let i = 0; i < tabCount; i++) {
//       const inputFieldCount = this.customerSearchDTO.tables[i].customerSearchParameters.length;
//       for (let j = 0; j < inputFieldCount; j++) {
//         const paramValue = this.customerSearchDTO.tables[i].customerSearchParameters[j].parameterValue;
//         if (paramValue != '' && paramValue != null) {
//           this.inputValue = true;
//         }
//       }
//     }

//     if (window.sessionStorage.getItem("custProfileId") != null && this.inputValue == true) {
//       this.customersearchService.submitCustomerSearchRequest(this.customerSearchDTO, window.sessionStorage.getItem("custProfileId")).subscribe(retValue => {
//         this.showTable = false;
//         this.customerSearchDTO = new CustomerSearchDTO();
//         this.customerSearchDTO = retValue;
//         console.log('CustomersearchComponent : ' + JSON.stringify(this.customerSearchDTO.results));
//         if (this.customerSearchDTO.results != null) {       //this.customerSearchDTO.results.length > 0 && 
//           this.showTable = true;
//         } else {
//           toastr.warning('Engine Not started');
//         }
//       });
//     }
//     if (window.sessionStorage.getItem("custProfileId") == null) {
//       toastr.error('Select Any Profile to Continue...!!!');
//     }
//     if (this.inputValue == false) {
//       toastr.warning('Enter any input field value...!!!');
//     }
//   }

//   callRelationalTab(headerRow: Array<String>, dataRow: Array<String>) {
//     window.sessionStorage.setItem("customerHeaderRow", JSON.stringify(headerRow));
//     window.sessionStorage.setItem("customerDataRow", JSON.stringify(dataRow));
//     RelationalsearchComponent.tabData = 'customerComponent';
//     this.router.navigate(['/relationalsearch']);
//   }

//   //for preview when click on tab
//   onTabSubmit(fileIndex: any, fileName: any) {
//     for (let i = 0; i < this.customerSearchDTO.tables.length; i++) {
//       if (fileName === this.customerSearchDTO.tables[i].tableName) {
//         this.displayCustomerSearchTableDTO = this.customerSearchDTO.tables[i];
//         this.currentTabIndex = i;
//       }
//     }
//     var count = 0;
//     var length = this.displayCustomerSearchTableDTO.customerSearchParameters.length;
//     for (let i = 0; i < length; i++) {
//       if(i>=0 && i < 23){
//         this.customerDto[count] = this.displayCustomerSearchTableDTO.customerSearchParameters[i];
//         count++;
//         if(count == 23){
//           count = 0;
//         }
//       } else if(i >=23 && i < 33){
//         this.addressDto[count] = this.displayCustomerSearchTableDTO.customerSearchParameters[i];
//         count++;
//         if(count == 33-23){
//           count = 0;
//         }
//       } else if(i >= 33 && i < 45){
//         this.contactDto[count] = this.displayCustomerSearchTableDTO.customerSearchParameters[i];
//         count++;
//         if(count == 45-33){
//           count = 0;
//         }
//       } else if(i >= 45 && i < length){
//         this.emailDto[count] = this.displayCustomerSearchTableDTO.customerSearchParameters[i];
//         count++;
//         if(count == length-45){
//           count = 0;
//         }
//       }
      
//     }
//     console.log("customerDto :"+JSON.stringify(this.customerDto));
//     console.log("addressDto :"+JSON.stringify(this.addressDto));
//     console.log("contactDto :"+JSON.stringify(this.contactDto));
//     console.log("emailDto :"+JSON.stringify(this.emailDto));
//   }

//   reset() {
//     this.result = '';
//     this.router.navigate(['/customersearch']);
//   }
// }