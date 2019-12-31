import { Component, OnInit } from '@angular/core';
import { UploadFileService } from '../fileupload/upload-file.service';
import { Router } from '@angular/router';
import { BulkPreviewWrapper } from 'src/app/Model/BulkPreviewWrapper';
import { HeaderColumnWrapper } from 'src/app/Model/HeaderColumnWrapper';
import { BulkUploadColumnMapping } from 'src/app/Model/BulkUploadColumnMapping';
import { BulkUploadDefn } from 'src/app/Model/BulkUploadDefn';

@Component({
  selector: 'app-fileupload',
  templateUrl: './fileupload.component.html',
  styleUrls: ['./fileupload.component.css']
})
export class FileuploadComponent implements OnInit {
  public tableWidget: any;
  dtOptions: DataTables.Settings = {};
  isLoading: boolean = false;
  uploadedFileName: FileList;
  fileUploadedData: Array<Array<String>>;
  bulkPreviewWrapper = new BulkPreviewWrapper();
  header = new Array<String>();
  headerColumnWrapper: HeaderColumnWrapper;
  bulkUploadColumnMapping: BulkUploadColumnMapping[];
  bulkUploadDefn: BulkUploadDefn;
  fileName: string;
  psxBatchId: string;
  serverFileName: string;
  profileId: number[];
  displayColNames: string[];
  url = '';
  public fileString;
  currentFileUpload: File;
  progress: { percentage: number } = { percentage: 0 };
  constructor(private uploadService: UploadFileService, private router: Router) {
    this.fileString;
  }

  ngAfterViewInit() {
  // this.initDatatable()
  }

  private initDatatable(): void {
    let exampleId: any = $('#example1');
    this.tableWidget = exampleId.DataTable({
      select: true
    });
  }


  ngOnInit() {
    $('.fileupload_datatable').css('display','block');
    if (localStorage.getItem('previewList') != null) {
      this.fileUploadedData = JSON.parse(localStorage.getItem('previewList'));
      localStorage.removeItem;
    }
    this.dtOptions = {
      "scrollX": true
    };
  }

  

  selectFile(event) {
    this.uploadedFileName = event.target.files;
  }
  //for preview file data during upload csv file
  submitUser() {
    this.isLoading = true;
    $('.fileupload_next_button').css('pointer-events', 'unset');
    $('.fileupload_datatable').css('display','block');
    console.log("rahul")
    this.currentFileUpload = this.uploadedFileName.item(0);
    this.fileName = this.currentFileUpload.name;
    this.uploadService.getFileData(this.currentFileUpload).subscribe(res => {

      this.bulkPreviewWrapper = res;
      //this.fileUploadedData = this.bulkPreviewWrapper.previewList;
      this.fileUploadedData = this.bulkPreviewWrapper.previewList;
      localStorage.setItem('previewList', JSON.stringify(this.bulkPreviewWrapper.previewList));
      localStorage.setItem('fileName', this.bulkPreviewWrapper.fileName);
      localStorage.setItem('serverFileName', this.bulkPreviewWrapper.serverFile);
      this.isLoading = false;
      console.log("data:" + JSON.stringify(this.fileUploadedData));
      //localStorage.setItem('psxBatchId',this.bulkUploadDefn.psxbatchId);
      //alert(JSON.stringify(this.bulkUploadDefn));

    });
  }
  //for finding headername(CSV) and displaycolumnname(DB)
  getHeader() {
    this.uploadService.getheaderData(this.bulkPreviewWrapper.serverFile).subscribe(res => {
      this.headerColumnWrapper = res;
      console.log(JSON.stringify(this.headerColumnWrapper));
      for (let i = 0; i < this.headerColumnWrapper.bulkColmappingList.length; i++) {
        this.displayColNames = this.headerColumnWrapper.bulkColmappingList[i].displayColName.split(",");
      }
      // console.log("data :"+this.headerColumnWrapper);
      this.bulkUploadColumnMapping = this.headerColumnWrapper.bulkColmappingList;
      //this.headerColumnWrapper = this.headerColumnWrapper.bulkColmappingList;
      localStorage.setItem("displayColNames", JSON.stringify(this.displayColNames));
      localStorage.setItem('bulkUploadColumnMapping', JSON.stringify(this.bulkUploadColumnMapping));
      // localStorage.setItem('headerColumnWrapper',JSON.stringify(this.headerColumnWrapper.profileIds));
      
      this.router.navigate(["/mapping"]);
    })
  }

  

 

}