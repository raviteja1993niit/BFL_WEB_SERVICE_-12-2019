import { Component, OnInit } from '@angular/core';
import { ResultService } from '../../core/result.service';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { shallowEqualArrays, forEach } from '@angular/router/src/utils/collection';
import { REquestResultDTO } from '../../Model/REquestResultDTO';
import { FormControl } from '@angular/forms';
import { map, startWith } from 'rxjs/operators';
import { NSPRequestResultsEntity } from '../../Model/NSPRequestResultsEntity';
import { NSPRequestEntity } from '../../Model/NSPRequestEntity';
import { RequestResultsEntity } from '../../Model/RequestResultsEntity';
import {AllAddresses} from '../../Model/Addresses';
@Component({
  selector: 'app-result',
  templateUrl: './result.component.html',
  styleUrls: ['./result.component.css']
})
export class ResultComponent implements OnInit {


  myControl = new FormControl();
  options: string[];
  filteredOptions: Observable<string[]>;
  requestResultsEntity: RequestResultsEntity;
  nspRequestEntity: NSPRequestEntity;
  nspRequestResult:NSPRequestResultsEntity=new NSPRequestResultsEntity();

  nspRequestResultsEntity: Array<NSPRequestResultsEntity> = new Array<NSPRequestResultsEntity>();
  public tableWidget: any;
  isLoading: boolean = false;
  state: boolean = false;
  state2: boolean = false;
  showInputTable: boolean = false;
  showResultsTable: boolean = false;
  inputDataArray = new Array<String>();
  requestId: string;
  reqResultDTO: REquestResultDTO = new REquestResultDTO;
  allAddress:AllAddresses=new AllAddresses;
  requestIdsArr: string[];
  //allAddresses: String;
  addresses: AllAddresses = new AllAddresses;
  

  addressIsThere:boolean;

  constructor(private router: Router, private _resultservice: ResultService) { }

  private initDatatable(): void {
    let exampleId: any = $('#example1');
    this.tableWidget = exampleId.DataTable({
      select: true,
      "bPaginate": true,

    });
    let exampleId1: any = $('#example2');
    this.tableWidget = exampleId1.DataTable({
      select: true,

    });
    let exampleId2: any = $('#example3');
    this.tableWidget = exampleId2.DataTable({
      select: true
    });
  }

  ngAfterViewInit() {

  }

  ngOnInit() {
    this._resultservice.getAllRequestId().subscribe(res => {

      this.requestIdsArr = res;
      console.log(this.requestIdsArr);
      this.filteredOptions = this.myControl.valueChanges
        .pipe(
          startWith(''),
          map(value => this._filter(value))
        );
    });

  }

  _filter(value: string): string[] {
    const filterValue = value;
    return this.requestIdsArr.filter(option => option.includes(filterValue));

  }

  getRecordsBasedOnId(requestId: string) {


    this.isLoading = true;
    this.reqResultDTO.inputTableData.length = 0;


    this._resultservice.getRecordsBasedOnId(requestId).subscribe(res => {
      
      console.log("response >>>>>>>>> ::::"+JSON.stringify(res));

      console.log("nspRequestResultsEntity::::"+JSON.stringify(res.nspRequestResultsEntity));
      console.log("nspRequestEntity::::"+JSON.stringify(res.nspRequestEntity));
    
      this.nspRequestResultsEntity = res.nspRequestResultsEntity;


      //console.log("*************Address data *****************");
      this.addresses=new AllAddresses();
      for(let i=0;i<res.nspRequestResultsEntity.length ;i++){  //How to properly iterate here!!
       // console.log("contact address"+res.nspRequestResultsEntity[i].contact_address);
      //  console.log("Office address"+res.nspRequestResultsEntity[i].office_address)
       // console.log("emergency Address "+res.nspRequestResultsEntity[i].emergency_address);
      //  this.allAddresses=res.nspRequestResultsEntity[i].emergency_address + ""+ res.nspRequestResultsEntity[i].office_address + "" +res.nspRequestResultsEntity[i].contact_address + "" +res.nspRequestResultsEntity[i].permanent_address + "" +res.nspRequestResultsEntity[i].postal_address + "" +res.nspRequestResultsEntity[i].preferred_address + "" +res.nspRequestResultsEntity[i].present_address + "" +res.nspRequestResultsEntity[i].residence_address + "" +res.nspRequestResultsEntity[i].temporary_address;
        
       
        
        this.addresses.contact_address=res.nspRequestResultsEntity[i].contact_address;
        this.addresses.emergency_address=res.nspRequestResultsEntity[i].emergency_address;
        this.addresses.office_address=res.nspRequestResultsEntity[i].office_address;
        this.addresses.permanent_address=res.nspRequestResultsEntity[i].permanent_address;
        this.addresses.postal_address=res.nspRequestResultsEntity[i].postal_address;
        this.addresses.preferred_address=res.nspRequestResultsEntity[i].preferred_address;
        this.addresses.present_address=res.nspRequestResultsEntity[i].present_address;
        this.addresses.residence_address=res.nspRequestResultsEntity[i].residence_address;
        this.addresses.temporary_address=res.nspRequestResultsEntity[i].temporary_address;
       // console.log("Total Address "+res.nspRequestResultsEntity[i].address);
     }
     this.nspRequestResult.alladdress=this.addresses;
     
      if(this.addresses.contact_address || this.addresses.emergency_address ||this.addresses.office_address || this.addresses.permanent_address || this.addresses.postal_address || this.addresses.preferred_address || this.addresses.present_address || this.addresses.residence_address || this.addresses.temporary_address =="" )
      {
        this.addressIsThere=false;
      }
     //this.allAddresses=this.addresses;


      //console.log("Address value ::"+this.nspRequestResultsEntity);
      
      this.nspRequestEntity = res.nspRequestEntity;
     
      if (this.nspRequestEntity.request_id != undefined) {
        this.state = true;
        console.log("data in if::::" + JSON.stringify(this.nspRequestEntity));
      }
      else {

        console.log("data in else::::" + this.nspRequestEntity);
      }


      this.isLoading = false;


      this.inputDataArray = new Array<String>();


    });
    this.showInputTable = true;
    this.showResultsTable = true;

    this.router.navigate(["/result"]);
  }


  onKeydown(event:any) {
    if (event.key === "Enter") {
      console.log(event);
      console.log( event.target.value);
      this.requestId=event.target.value;
      this.getRecordsBasedOnId(this.requestId);


    }
  }
  getAddresses(data:any){
   // alert("data "+JSON.stringify(data));
   // alert("data.address"+data);
    

   this.addresses= data;
  //  data=new this.nspRequestResultsEntity();
  data=undefined;
  // console.log("data in if::::" + JSON.stringify(this.addresses));
 if (this.addresses != undefined) {
    this.state2 = true;
   console.log("data in if::::" + JSON.stringify(this.addresses));
   }
   else {

    console.log("data in else::::" + this.addresses);
   }
  // alert("address "+this.addresses);
   
  }
  

}

