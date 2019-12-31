import { Component, OnInit } from '@angular/core';
import { EoduploadserviceService } from '../../services/eoduploadservice.service';
import { FormGroup, FormBuilder, FormControl, Validators } from '../../../../node_modules/@angular/forms';
import { toBase64String } from '@angular/compiler/src/output/source_map';
import * as toastr from 'toastr';

@Component({
  selector: 'app-eodupload',
  templateUrl: './eodupload.component.html',
  styleUrls: ['./eodupload.component.css']
})
export class EoduploadComponent implements OnInit {

  selectedDataSource: string;
  //dataSources = [{ "data": "Select Source" }, { "data": "STS" }, { "data": "LEGAL" }, { "data": "EMI" }];
 dataSources:string[]; 
 fileToUpload: File = null;
  uploadForm :FormGroup;
  dataSourceValid:string;
  batchidpop:string;
  status:boolean=false;
  errorMsg:string;
  constructor(private eodService:EoduploadserviceService,private _fb: FormBuilder) { }

  ngOnInit() {
    window.sessionStorage.removeItem("selectedDataSource") 
    this.eodService.getAllDatasources().subscribe(res => {
    //  this.dataSources.push("Select DataSource");
      this.dataSources = res;
      console.log(this.dataSources);
 });
    this.uploadForm = this._fb.group({

      file: new FormControl('file', [Validators.required]),
      dataSource: new FormControl('dataSource', [])

    });

  }



  setSelectedStatus(dataSource: string) {
    console.log(dataSource);
    this.dataSourceValid = dataSource;
    this.selectedDataSource  = dataSource;
    window.sessionStorage.setItem("selectedDataSource", dataSource);
  
  }

  uploadEodFile() {
let check:boolean=false;

    if (window.sessionStorage.getItem("selectedDataSource") == null || this.dataSourceValid == "-1") {
      toastr.error('Select Any DataSource...!!!');
      check=true;
      this.status=false;
      this.errorMsg="Please Select DataSource ";
      }
      if(this.fileToUpload == null)
      {
        if (window.sessionStorage.getItem("selectedDataSource") == null || this.dataSourceValid == "-1") {
         if(check == false){
         toastr.error('Select Any DataSource...!!!');
         this.status=false;
         this.errorMsg="";
         this.errorMsg="Please Select DataSource ";
        }
          }
        toastr.error('Select File to Upload...!!!');
        console.log("Datasource :::"+ this.dataSourceValid)
        if(this.dataSourceValid != undefined){
        this.errorMsg="";
         this.errorMsg="Please Select File ";
        }
        this.status=false;
      }
      

    // const input = {
    //   'dataSource': this.selectedDataSource,
    //   'fileData': this.fileToUpload
    // };

    // let formdata: FormData = new FormData();
 
    
    // formdata.append('file',this.fileToUpload);
    // formdata.append('dataSource',this.selectedDataSource);
    
    // console.log("hii")

    // console.log(formdata.get("dataSource"));
   
    if(this.fileToUpload.name.endsWith(".txt") || this.fileToUpload.name.endsWith(".csv") ){
      if (window.sessionStorage.getItem("selectedDataSource") == null || this.dataSourceValid == "-1"){ 
        if(check == false){
        toastr.error('Select Any DataSource...!!!');
        this.errorMsg="";
        this.errorMsg="Please Select DataSource ";
        }
       
      }
      else{
        
     this.eodService.eodUploadService(this.fileToUpload, this.selectedDataSource).subscribe(
      response => {


        if(response != undefined)
        {
          this.status=true;
        }
        var batchid=response.data;
       this.batchidpop=batchid;
       
        console.log("NEGATIVE BASE File Uploaded Successfully for BATCH_ID :"+batchid +" :::: "+this.fileToUpload.type);
       // alert("File Uploaded Successfully");
      
       toastr.success("NEGATIVE BASE  File Uploaded Successfully for BATCH_ID :"+batchid+ "Status"+this.status);
      
      },
      error => {
        console.log(error);
        toastr.error("Fail To Upload File");
      });
    }
    
    }
    else
    {
        toastr.error("Unsupported File Format... ");
        this.errorMsg="";
        
        this.errorMsg="Unsupported File Format... ";
  }
  
  }





  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
  

    console.log(this.fileToUpload);
  }

}
