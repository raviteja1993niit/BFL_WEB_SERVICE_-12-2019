import { Component, OnInit } from '@angular/core';
import { BulkUploadResultService } from '../../core/bulk-upload-result.service';
import { RequestResultsWrapper } from '../../Model/requestResultsWrapper';
import { NSPRequestResultsDTO } from '../../Model/ReqResult';

@Component({
  selector: 'app-bulkuploadresult',
  templateUrl: './bulkuploadresult.component.html',
  styleUrls: ['./bulkuploadresult.component.css']
})
export class BulkuploadresultComponent implements OnInit {

  public tableWidget: any;
  isLoading: boolean = false;
  psxBatchId: String;
  pageNo: String;
  retValue: RequestResultsWrapper = new RequestResultsWrapper();
  private bulkInputArray: Array<NSPRequestResultsDTO> = new Array<NSPRequestResultsDTO>();
  showResultsTable: boolean = false;
  showInputTable: boolean = false;
  totalPageCount = new Array<Number>();
  currentPsxBatchId: String;
  private bullSliderNumber: number = 0;

  constructor(private bulkUploadResultService: BulkUploadResultService) { }

  ngOnInit() {
    this.psxBatchId = JSON.parse(window.sessionStorage.getItem("psxBatchId"));
    this.pageNo = JSON.parse(window.sessionStorage.getItem("pageNo"));
    this.getBulkUploadDetails(this.psxBatchId, this.pageNo);
  }

  getBulkUploadDetails(psxBatchId: String, currentPageNo: String) {
    if (psxBatchId != null) {
      this.isLoading = true;
      this.bulkInputArray.length = 0;
      console.log('Bulk Component : '+psxBatchId+' : '+currentPageNo);
      this.bulkUploadResultService.getBulkUploadDetails(this.psxBatchId, currentPageNo).subscribe(res => {
        this.isLoading = false;
        this.retValue = res;

        this.retValue.resultsList.forEach((element, index) => {
          if (element.recordType == 'INPUT') {
            this.bulkInputArray.push(element);
            this.retValue.resultsList.splice(index, 1);
          }
        });

        this.currentPsxBatchId = this.retValue.resultsList[0].psxBatchId;
        this.totalPageCount = Array.from(Array(this.retValue.pageCount), (x, i) => i);
        console.log('this.totalPageCount '+this.totalPageCount);
      });
      this.showInputTable = true;
      this.showResultsTable = true;
    } else {
      //toaster here
    }
  }

  bulkNext() {
    if (this.bullSliderNumber < 9) {
      this.bullSliderNumber = this.bullSliderNumber + 1;
    } else {
      this.bullSliderNumber = 0;
    }
  }

  bulkPrevious() {
    if (this.bullSliderNumber <= 9) {
      this.bullSliderNumber = this.bullSliderNumber - 1;
      if (this.bullSliderNumber == -1) {
        this.bullSliderNumber = 9;
      }
    } else {
      this.bullSliderNumber = 0;
    }
  }

}
