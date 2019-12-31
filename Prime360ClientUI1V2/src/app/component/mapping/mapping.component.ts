import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { BulkUploadColumnMapping } from '../../Model/BulkUploadColumnMapping';
import { HeaderColumnWrapper } from '../../Model/HeaderColumnWrapper';
import { BulkPreviewWrapper } from '../../Model/BulkPreviewWrapper';

import { BulkUploadDto } from '../../Model/BulkUploadDto';
import { MappingTables } from '../../Model/MappingTables';
import { ListProcessDto } from '../../Model/ListProcessDto';
import * as toastr from 'toastr';
import { ProcessDto } from '../../Model/ProcessDto';
import { InputMetaInfo } from '../../Model/InputMetaInfo';
import { MappingService } from '../../core/mapping.service';

@Component({
  selector: 'app-mapping',
  templateUrl: './mapping.component.html',
  styleUrls: ['./mapping.component.css']
})
export class MappingComponent implements OnInit {
  dtOptions: DataTables.Settings = {};
  uploadedFileName: FileList;
  headerColumnWrapper: HeaderColumnWrapper;
  profileIds: number[];
  profileNames: string[];
  psxBatchId: string;
  upload: string = "";
  profileId: number;
  profileName = '-1';
  previewList: Array<Array<string>>;
  fileName: string;
  serverFileName: string;
  bulkPreviewWrapper = new BulkPreviewWrapper();
  fileUploadedData: Array<Array<String>>;
  tableNames: string[] = [];
  selectTableNames = new Set<String>();
  bulkUploadColumnMapping = new Array<BulkUploadColumnMapping>();
  displayColNames = new Array<String>();
  processDto1: ProcessDto;
  lstProcessDto: ListProcessDto = new ListProcessDto();
  bulkUploadDtolst: Array<BulkUploadDto> = new Array<BulkUploadDto>();
  inputMetaInfo = new InputMetaInfo();
  mappingTableArray = new Array<MappingTables>();
  mappingTable: MappingTables;
  loadContent = false;
  currentTableIndex = 0;
  selectTableName : string = "";
  isLoading: boolean = false;
  isLoading2: boolean = false;
  public tableWidget: any;
  
  ngAfterViewInit() {
    this.initDatatable()
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
  constructor(private route: ActivatedRoute, private mappingService: MappingService, private router : Router) { }
  ngOnInit() {
    this.mappingTableArray = JSON.parse(localStorage.getItem('mappingTablesData'));
    for (let i = 0; i < this.mappingTableArray.length; i++) {
      this.mappingTableArray[i].bulkColmappingList = this.mappingTableArray[i].headerColumnWrapper.bulkColmappingList;
    }
    console.log("mapping Array Data:" + JSON.stringify(this.mappingTableArray));
    this.inputMetaInfo = JSON.parse(localStorage.getItem("inputMetaInfo"));
    this.initializeDisplayNames();
  }
  saveUploadData() {
    this.headerColumnWrapper = new HeaderColumnWrapper();
    let processDtoArray = new Array<ProcessDto>();
    this.lstProcessDto = new ListProcessDto();
    for (let index = 0; index < this.mappingTableArray.length; index++) {
      const element = this.mappingTableArray[index];
      console.log("JSON DATA:" + JSON.stringify(this.mappingTableArray));
      this.lstProcessDto = new ListProcessDto();
      this.processDto1 = new ProcessDto();
      this.processDto1.file = element.fileName;
      this.processDto1.serverFile = element.serverFile;
      this.processDto1.tableName = element.selectedTableName;
      alert(JSON.stringify(this.processDto1.tableName));
      this.processDto1.headercolumnWrapper = JSON.stringify(element.headerColumnWrapper);
      processDtoArray.push(this.processDto1);
    }
    this.lstProcessDto.processDtolst = processDtoArray;
    console.log("ListProcessDto" + JSON.stringify(this.lstProcessDto.processDtolst));
    this.mappingService.saveUploadData(this.lstProcessDto).subscribe((retValue) => {
      toastr.success(retValue + " submit successfully");
      console.log("Last Submit Data:" + JSON.stringify(this.lstProcessDto));
      this.ngOnInit();
    });
  }
  onSubmit(tableName: string, index: number) {
    this.isLoading = true;
    this.selectTableName = tableName;
    this.isLoading = false;
    let isFound = false;
    this.bulkUploadColumnMapping = this.mappingTableArray[index].headerColumnWrapper.bulkColmappingList;
    for (let i = 0; i < this.inputMetaInfo.inputTableMetaInfos.length; i++) {
      const element = this.inputMetaInfo.inputTableMetaInfos[i];
      if (!isFound) {
        if (element.tableAliasName === tableName) {
          isFound = true;
          this.currentTableIndex = i;
          this.displayColNames = new Array<String>();
          this.displayColNames = element.colNames;
        }
      }
    }
  }
  initializeDisplayNames() {
    this.isLoading=true;
    for (let j = 0; j < this.inputMetaInfo.inputTableMetaInfos.length; j++) {
      let isTableFound = false;
      const checkTableName = this.inputMetaInfo.inputTableMetaInfos[j].tableAliasName;
      for (let i = 0; i < this.mappingTableArray.length; i++) {
        const selectedTable = this.mappingTableArray[i].selectedTableName;
        if (!isTableFound) {
          if (checkTableName === selectedTable) {
            isTableFound = true;
           for (let k = 0; k < this.mappingTableArray[i].headerColumnWrapper.bulkColmappingList.length; k++) {
              let isColSet = false;
              const bulkDispColName = this.mappingTableArray[i].headerColumnWrapper.bulkColmappingList[k].csvHeaderData;
              let colNames = this.inputMetaInfo.inputTableMetaInfos[j].colNames;
              for (let x = 0; x < colNames.length; x++) {
                const element = colNames[x];
                
                if(!isColSet){
                  if (bulkDispColName.toLowerCase() === element.toLowerCase()) {
                    isColSet = true;
                    this.mappingTableArray[i].headerColumnWrapper.bulkColmappingList[k].displayColName = '' + colNames[x];
                    this.isLoading=false;
                  }
                }
              }
            }
          }// check table Name
        } else {
          break;
        }
      }
    }
  }
  getSelectedValue(dispCol: string, bulkIndex: number) {
    let colNames = this.inputMetaInfo.inputTableMetaInfos[this.currentTableIndex].colNames;
    for (let index = 0; index < colNames.length; index++) {
      const element = colNames[index];
      if (element.toLowerCase() === dispCol.toLowerCase()) {
        this.bulkUploadColumnMapping[bulkIndex].displayColName = '' + element;
      }
    }
  }
}