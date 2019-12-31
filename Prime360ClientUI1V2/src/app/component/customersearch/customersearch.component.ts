import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as $ from 'jquery';
import { CustomersearchService } from '../../core/customersearch.service';
import { CustomerSearchDTO } from '../../Model/CustomerSearchDTO';
import { ProfileEntityDTO } from '../../Model/ProfileEntityDTO';
import { CustomerSearchTableDTO } from '../../Model/CustomerSearchTableDTO';
import { CustomerSearchParameter } from '../../Model/CustomerSearchParameter';
import { ParameterDto } from '../../Model/ParameterDto';
import * as toastr from 'toastr';
import { NgForm } from '@angular/forms';


@Component({
  selector: 'app-customersearch',
  templateUrl: './customersearch.component.html',
  styleUrls: ['./customersearch.component.css']
})
export class CustomersearchComponent implements OnInit {

  error: String;
  result: String;
  inputValue: boolean = false;
  showTable: boolean = false;
  currentTabIndex = 1;

  profileEntities = new Array<ProfileEntityDTO>();
  customerSearchDTO: CustomerSearchDTO;
  displayCustomerSearchTableDTO: CustomerSearchTableDTO;
  customerSearchParameters: Array<String> = new Array<String>();
  parameterDto :ParameterDto= new ParameterDto();


  constructor(private customersearchService: CustomersearchService, private router: Router) {
    
  }


  ngOnInit() {

    this.getAllActiveProfiles();
    window.sessionStorage.removeItem("custProfileId");
    //sessionStorage.clear();
   // window.sessionStorage.removeItem("myForm");
    this.getCustomerSearchTable();
  }

  
    
  getAllActiveProfiles() {
    this.customersearchService.getAllActiveProfiles().subscribe(data => {
      this.profileEntities = data;
    });
  }

  getCustomerSearchTable() {
   // alert("submit getCustomerSearchTable  ");
    this.customersearchService.getCustomerSearchTable().subscribe(retValue => {
      this.customerSearchDTO = new CustomerSearchDTO();
    //  console.log("CustomersearchComponent aaaaaaaaaaaaaaaaa "+ JSON.stringify(this.customerSearchDTO));
      this.customerSearchDTO = retValue;
      console.log('CustomersearchComponent getCustomerSearchTable() : ' + JSON.stringify(retValue));
      // this.setUIParamValue();
      this.currentTabIndex = 1;
      this.displayCustomerSearchTableDTO = this.customerSearchDTO.tables[this.currentTabIndex];
      for (let i = 0; i < this.displayCustomerSearchTableDTO.customerSearchParameters.length; i++) {
        var stringData =this.displayCustomerSearchTableDTO.customerSearchParameters[i].parameterValue;
        // alert(""+stringData);
        this.customerSearchParameters.push(stringData);
        
      }
      console.log("Data : "+JSON.stringify(this.customerSearchParameters));
    });
  }

  
  getProfileById(profileId: string) {
        $('#err').hide();
        window.sessionStorage.setItem("custProfileId", profileId);
      }




  submitData(form:NgForm) {

   // alert("submit data");
     var arr = this.parameterDto.getParameterList();
    // console.log(arr);
    for (let i = 0; i < this.displayCustomerSearchTableDTO.customerSearchParameters.length; i++) {
      if(arr[i] !== undefined){
        this.displayCustomerSearchTableDTO.customerSearchParameters[i].parameterValue = arr[i];
      }
     
    }
    this.result = '';
    this.error = '';
    this.inputValue = false;
    let tabCount = this.customerSearchDTO.tables.length;
    for (let i = 0; i < tabCount; i++) {
      const inputFieldCount = this.customerSearchDTO.tables[i].customerSearchParameters.length;
      for (let j = 0; j < inputFieldCount; j++) {
        const paramValue = this.customerSearchDTO.tables[i].customerSearchParameters[j].parameterValue;
        
        
        if (paramValue != '' && paramValue != null) {
          this.inputValue = true;
        }
      }


    }
    if (window.sessionStorage.getItem("custProfileId") == null) {
      toastr.error('Select Any Profile to Continue...!!!');
    }
    
    if (this.inputValue == false) {
      toastr.warning('Enter any input field value...!!!');
    }
    if (window.sessionStorage.getItem("custProfileId") != null && this.inputValue == true) {
      console.log("Submitted data"+JSON.stringify(this.customerSearchDTO));
      
      this.customersearchService.submitCustomerSearchRequest(this.customerSearchDTO, window.sessionStorage.getItem("custProfileId")).subscribe(retValue => {
       
    
        this.showTable = false;
     // this.customerSearchDTO = new CustomerSearchDTO();
       // this.customerSearchDTO = retValue;
      
            console.log("new Return "+JSON.stringify(retValue));

            let requestID = retValue;
          //  let requestID = retValue["request_id"];
               console.log('RequestID_1 '+ requestID);
                   alert("request Id Is submitted successfully   "+requestID);
                  // $('.menu').css('display', 'none');
                  // $('#demographics').css('display', 'none');
                  this.parameterDto=new ParameterDto;
        console.log('CustomersearchComponent result: ' + JSON.stringify(this.customerSearchDTO.results));
        if (retValue != null) {   
              //this.customerSearchDTO.results.length > 0 && 
              this.showTable = true;
        } else {
          toastr.warning('Engine Not started');
        }
      });
    }
  
     this.router.navigate(['/customersearch']);
      this.getCustomerSearchTable();
  }


  demographics() {
    $('.address_menu').removeClass('active');
    $('.contact_menu').removeClass('active');
    $('.demographics_menu').addClass('active');
    $('.email_menu').removeClass('active');
    $('#demographics').css('display', 'block');
    $('.select_profile').css('display', 'block');
    $('#contact').css('display', 'none');
    $('#email').css('display', 'none');
    $('#address').css('display', 'none');
    $('.select_profiles').css('display', 'block');
  }
  contact() {
      $('.contact_menu').addClass('active');
      $('.demographics_menu').removeClass('active');
      $('.address_menu').removeClass('active');
      $('.email_menu').removeClass('active');
      $('#demographics').css('display', 'none');
      $('#email').css('display', 'none');
      $('#address').css('display', 'none');
      $('#contact').css('display', 'block');
      $('.select_profiles').css('display', 'none');
    }
  
  email() {
      $('.email_menu').addClass('active');
      $('.contact_menu').removeClass('active');
      $('.demographics_menu').removeClass('active');
      $('.address_menu').removeClass('active');
      $('#demographics').css('display', 'none');
      $('#contact').css('display', 'none');
      $('#address').css('display', 'none');
      $('#email').css('display', 'block');
      $('.select_profiles').css('display', 'none');
    
  }
  address() {
      $('.address_menu').addClass('active');
      $('.contact_menu').removeClass('active');
      $('.demographics_menu').removeClass('active');
      $('.email_menu').removeClass('active');
      $('#demographics').css('display', 'none');
      $('#contact').css('display', 'none');
      $('#email').css('display', 'none');
      $('#address').css('display', 'block');
      $('.select_profiles').css('display', 'none');
    }
    reset(parameterDto :ParameterDto)
{
  parameterDto= new ParameterDto();

  
}

  }
