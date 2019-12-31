import { Component, OnInit } from '@angular/core';
import { FilestatusService } from '../../core/filestatus.service';
import { BulkFileDetailsDTO } from '../../Model/bulkFileDetailsDTO';
import { Router } from '@angular/router';
import * as toastr from 'toastr';

@Component({
  selector: 'app-filestatus',
  templateUrl: './filestatus.component.html',
  styleUrls: ['./filestatus.component.css']
})
export class FilestatusComponent implements OnInit {

  public tableWidget: any;
  isLoading: boolean = false;
  private bulkFileDetailsDTO: Array<BulkFileDetailsDTO> = new Array<BulkFileDetailsDTO>();

  constructor(private router: Router, private filestatusService: FilestatusService) { }

  private initDatatable(): void {
    let exampleId: any = $('#example3');
    this.tableWidget = exampleId.DataTable({
      select: true
    });
  }

  ngOnInit() {
    this.isLoading = true;
    this.filestatusService.getBulkUploadDetails().subscribe(res => {
      this.isLoading = false;
      this.bulkFileDetailsDTO = res;
      // console.log('File Stats Component :this.bulkFileDetailsDTO : ' + JSON.stringify(this.bulkFileDetailsDTO));
    });
  }

  bulkUploadResult(psxBatchId: String, pageNo: number) {
    window.sessionStorage.setItem("psxBatchId", JSON.stringify(psxBatchId));
    window.sessionStorage.setItem("pageNo", JSON.stringify(pageNo));
    this.router.navigate(['/bulkuploadresult']);
  }

  reprocess(psxBatchId_reProcess: String) {
    this.filestatusService.reProcess(psxBatchId_reProcess).subscribe((res) => {
      console.log('File Stats Component : '+res);
      if(res != 'Reprocess Failed') {
        toastr.success('Reprocess done...!!!');
      } else {
        toastr.warning('Reprocess failed...!!!');
      }
    });
  }

}
