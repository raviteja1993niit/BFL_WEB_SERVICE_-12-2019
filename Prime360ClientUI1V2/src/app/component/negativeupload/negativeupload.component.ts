import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '../../../../node_modules/@angular/forms';
import { NegativeuploadserviceService } from '../../services/negativeuploadservice.service';
import { Router } from '@angular/router';
import * as toastr from 'toastr';


@Component({
  selector: 'app-negativeupload',
  templateUrl: './negativeupload.component.html',
  styleUrls: ['./negativeupload.component.css']
})
export class NegativeuploadComponent implements OnInit {

  fileToUpload: File = null;
  uploadForm :FormGroup;
  batchidpop:string;
  status:boolean=true;
  errorMsg:string;
  constructor(private negativeService:NegativeuploadserviceService,private _fb: FormBuilder) { }
 
  ngOnInit() {
    this.uploadForm = this._fb.group({
      file: new FormControl('file', [Validators.required]),
    });
  }
  uploadNegativeEodFile() {
 
    // const input = {
    //   'dataSource': this.selectedDataSource,
    //   'fileData': this.fileToUpload
    // };

//     let formdata: FormData = new FormData();
//     formdata.append('file',this.fileToUpload);
//  this.negativeService.negativeUploadService(formdata).subscribe(
//       response => {
//         var batchid=response.
//         console.log("File Uploaded Successfully")
//         // alert("File Uploaded Successfully");
//         toastr.success("File Uploaded Successfully");
//       },
//       error => {
//         console.log(error);
//         toastr.error("Fail To Upload File");
//       });

if(this.fileToUpload == null)
{ 
  toastr.error('Select File to Upload...!!!');
  this.errorMsg="";
      this.errorMsg="Please Select File ";
  this.status=false;
}
  if(this.fileToUpload.name.endsWith(".txt") || this.fileToUpload.name.endsWith(".csv") ){
{

      this.negativeService.negativeUploadService(this.fileToUpload).subscribe(
        response => {
          if(response != undefined)
          {
            this.status=true;
          }
          var batchid=response.data;
         this.batchidpop=batchid;
          console.log("NEGATIVE AREA File Uploaded Successfully for BATCH_ID :"+batchid);
         // alert("File Uploaded Successfully");
         toastr.success("NEGATIVE AREA File Uploaded Successfully for BATCH_ID :"+batchid);
        },
        error => {
          console.log(error);
          toastr.error("Fail To Upload File");
         
        });
      }
    }
    else{
      this.status=false;
      toastr.error("Unsupported File Format... ");
      
      this.errorMsg="";
        
      this.errorMsg="Unsupported File Format... ";
    }
  }
  
  
  handleFileInput(files: FileList) {
    this.fileToUpload = files.item(0);
    console.log(this.fileToUpload);
  }
  
  public downloadCSVFile() {
   let status:boolean= this.negativeService.downloadCSVFile();
   if(status == true)
   {
    toastr.success("File Downloading....");
   }
   else
   {
    toastr.error("File is Fail To Download....");
   }
  }
}
