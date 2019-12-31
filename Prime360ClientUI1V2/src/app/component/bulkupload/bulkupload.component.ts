import { Component, OnInit, Pipe } from '@angular/core';
import * as $ from 'jquery';
import { Router } from '@angular/router';
import * as toastr from 'toastr';
import { BulkPreviewWrapper } from '../../Model/BulkPreviewWrapper';
import { BulkUploadDto } from '../../Model/BulkUploadDto';
import { HeaderColumnWrapper } from '../../Model/HeaderColumnWrapper';
import { BulkUploadColumnMapping } from '../../Model/BulkUploadColumnMapping';
import { InputMetaInfo } from '../../Model/InputMetaInfo';
import { BaseMetaInfo } from '../../Model/BaseMetaInfo';
import { MappingTables } from '../../Model/MappingTables';
import { BulkuploadService } from '../../core/bulkupload.service';
import { ProfileEntityDTO } from '../../Model/ProfileEntityDTO';

@Component({
  selector: 'app-bulkupload',
  templateUrl: './bulkupload.component.html',
  styleUrls: ['./bulkupload.component.css']
})
export class BulkuploadComponent implements OnInit {

  baseMetaInfo: BaseMetaInfo;
  dtOptions: DataTables.Settings = {};
  uploadedFileName: FileList;
  tableNames = [];
  bulk_table: string = '-1';
  bulkupload: string[] = [];
  currentFileUpload: File;
  fileName: string;
  isLoading: boolean = false;
  bulkPreviewWrapper = new BulkPreviewWrapper();
  fileUploadedData: Array<Array<String>>;
  bulkUploadDto: BulkUploadDto = new BulkUploadDto();
  bulkUploadDtolst: Array<BulkUploadDto> = new Array<BulkUploadDto>();
  headerColumnWrapper = new HeaderColumnWrapper();
  mappingTable = new MappingTables();
  mappingTableArray = new Array<MappingTables>();
  inputMetaInfo: InputMetaInfo = new InputMetaInfo();
  // serverFileName: string;
  csvString: string;
  loadTable = false;
  public tableWidget: any;
  profileEntities: Array<ProfileEntityDTO> = new Array<ProfileEntityDTO>();
  
  currentTabIndex = 0;

  
  // bulkPreviewWrapper1 = new Array<BulkPreviewWrapper>();
  //bulkUploadColumnMapping: BulkUploadColumnMapping[];
  // displayColNames: string[];
  // sort: any = {};
  // fileNames: Array<string>;
  //fileUpload: Array<string>;
  //isLoading2: boolean = false;

  ngAfterViewInit() {
    //this.initDatatable()
  }
  private initDatatable(): void {
    let exampleId: any = $('#example');
    this.tableWidget = exampleId.DataTable({
      select: true
    });
    let exampleId1: any = $('#example1');
    this.tableWidget = exampleId1.DataTable({
      select: true
    });
    let exampleId2: any = $('#example2');
    this.tableWidget = exampleId2.DataTable({
      select: true
    });
    let exampleId3: any = $('#example3');
    this.tableWidget = exampleId3.DataTable({
      select: true
    });
  }
  constructor(private bulkUploadService: BulkuploadService, private router: Router) { }

  ngOnInit() {
    window.sessionStorage.removeItem("selectedProfile");
    this.getAllActiveProfiles();
    this.baseMetaInfo = new BaseMetaInfo();
    this.bulkUploadService.getLatestCsvStringBasedOnSourceSystemName().subscribe(res => {
      this.baseMetaInfo = res;
      if (this.baseMetaInfo.srcSystemName != undefined) {
        this.getTableData();
      }
    });

    this.dtOptions = {
      "order": [[0, "desc"]]
    };

    $(function () {
      var select = <any>$('select');
      select.html(select.find('option').sort(function (x, y) {
        // to change to descending order switch "<" for ">"
        return $(x).text() < $(y).text() ? -1 : 1;
      }));
    });
  }

  getAllActiveProfiles() {
    this.profileEntities = new Array<ProfileEntityDTO>();
    this.bulkUploadService.getAllActiveProfiles().subscribe(resData => {
      this.profileEntities = resData;
      console.log('BulkuploadComponent this.profileEntities : '+this.profileEntities);
    });
  }

  getProfileId(profileId: string) {
    window.sessionStorage.setItem("selectedProfile", profileId);
  }

  selectFile(event) {
    this.uploadedFileName = event.target.files;
  }
  submitFile(fileName: any) {
    

    //for displaying table name in ui
    if (this.tableNames.indexOf(this.bulk_table) > -1) {
      $('.tablename').html((this.bulk_table));
      //for toaster message
      for (let i = 0; i < this.bulkupload.length; i++) {
        if (this.bulkupload[i] === this.bulk_table) {
          toastr.error("File already exist. !");
          return;
        }
      }
      //alert(this.bulk_table);
      this.bulkupload.push(this.bulk_table);
      this.currentTabIndex = this.bulkupload.length-1;
      console.log(this.bulkupload);
    
      //alert(this.bulkupload);
    }
    $('.bulk_datatable').css('display', 'block');
    console.log("rahul");
    this.currentFileUpload = this.uploadedFileName.item(0);
    this.fileName = this.currentFileUpload.name;
    this.isLoading = true;
    this.bulkUploadService.upload(this.currentFileUpload).subscribe(res1 => {
      this.bulkPreviewWrapper = new BulkPreviewWrapper();
      this.bulkPreviewWrapper = res1;
      this.fileUploadedData = this.bulkPreviewWrapper.previewList;
      this.isLoading = false;
      console.log(JSON.stringify(this.fileUploadedData))
      localStorage.setItem('previewList', JSON.stringify(this.bulkPreviewWrapper.previewList));
      localStorage.setItem('fileName', this.bulkPreviewWrapper.fileName);
      localStorage.setItem('serverFileName', this.bulkPreviewWrapper.serverFile);
      //taking as key,value on ui 1st screen
      this.bulkUploadDto = new BulkUploadDto();
      this.bulkUploadDto.bulk_tableName = this.bulk_table;
      this.bulkUploadDto.fileUploadedData = this.fileUploadedData;
      this.bulkUploadDtolst.push(this.bulkUploadDto);
      this.bulkUploadDtolst.forEach(function (item) {
        console.log(JSON.stringify(item));
      });
      //after sending from screen 1 for displaying all tab name with tab data in screen2 (else only one tab data shown)
      this.bulkUploadService.getCsvDatabaseData(this.bulkPreviewWrapper.serverFile).subscribe(res2 => {
        this.headerColumnWrapper = res2;
        console.log('getCsvDatabaseData ::: ' + JSON.stringify(this.headerColumnWrapper));
        if (this.bulkPreviewWrapper.serverFile != null && this.headerColumnWrapper.bulkColmappingList.length > 0) {
          this.mappingTable = new MappingTables();
          this.mappingTable.fileName = this.bulkPreviewWrapper.fileName;
          this.mappingTable.serverFile = this.bulkPreviewWrapper.serverFile;
          this.mappingTable.selectedTableName = this.bulk_table;
          this.mappingTable.headerColumnWrapper = this.headerColumnWrapper;
          let isDuplicate = false;
          let duplicateIndex: number;
          if (this.mappingTableArray.length > 0) {
            for (let index = 0; index < this.mappingTableArray.length; index++) {
              const element = this.mappingTableArray[index];
              if (element.selectedTableName === this.bulk_table) {
                isDuplicate = true;
                duplicateIndex = index;
              }
              if (index === this.mappingTableArray.length - 1) {
                if (!isDuplicate) {
                  this.mappingTableArray.push(this.mappingTable);
                  localStorage.setItem('mappingTablesData', JSON.stringify(this.mappingTableArray));
                } else {
                  this.mappingTableArray.splice(duplicateIndex, 1);
                  this.mappingTableArray.push(this.mappingTable);
                  localStorage.setItem('mappingTablesData', JSON.stringify(this.mappingTableArray));
                }
                console.log(this.mappingTableArray);
              }
            }
          } else {
            this.mappingTableArray.push(this.mappingTable);
            localStorage.setItem('mappingTablesData', JSON.stringify(this.mappingTableArray));
            console.log(this.mappingTableArray);
          }
        }
      });
    });
  }
  //for displaying headercolumnname and displayname
  getHeader() {
    // alert(window.sessionStorage.getItem("selectedProfile"));
    if (window.sessionStorage.getItem("selectedProfile") != null) {
      this.getTableData();
      this.router.navigate(["/mapping"]);
    } else {
      toastr.warning('Select Any Profile...!!!');
    }
  }
  getTableData() {
    console.log(JSON.stringify(this.baseMetaInfo.csvString));
    this.bulkUploadService.getAllTableNames(this.baseMetaInfo).subscribe(data1 => {
      this.inputMetaInfo = data1;
      localStorage.setItem("inputMetaInfo", JSON.stringify(this.inputMetaInfo))
      for (let i = 0; i < this.inputMetaInfo.inputTableMetaInfos.length; i++) {
        this.tableNames[i] = this.inputMetaInfo.inputTableMetaInfos[i].tableAliasName;
        if (i === this.inputMetaInfo.inputTableMetaInfos.length - 1) {
          this.loadTable = true;
        }
      }
    });
  }
  //for preview when click on tablename
  onSubmit(fileName: any) {
    for (let i = 0; i < this.bulkUploadDtolst.length; i++) {
      if (fileName === this.bulkUploadDtolst[i].bulk_tableName) {
        this.fileUploadedData = this.bulkUploadDtolst[i].fileUploadedData;
        this.currentTabIndex = i;
        break;
      }
    }
  }
}
