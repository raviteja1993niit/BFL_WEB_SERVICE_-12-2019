import { Component, OnInit } from '@angular/core';
import { EodFilestatusService } from '../../core/Eodfilestatus.service';
import { EodfilestatusDTO } from '../../Model/EodfilestatusDTO';
import { Error_Records_Info_T } from '../../Model/Error_Records_Info_T';
import { Router } from '@angular/router';
import * as toastr from 'toastr';

@Component({
  selector: 'app-Eodfilestatus',
  templateUrl: './Eodfilestatus.component.html',
  styleUrls: ['./Eodfilestatus.component.css']
})
export class EodFilestatusComponent implements OnInit {

  public tableWidget: any;
  isLoading: boolean = false;
  show: boolean = false;
  private EodfilestatusDTO: Array<EodfilestatusDTO> = new Array<EodfilestatusDTO>(); 
  private Error_Records_Info_T: Array<Error_Records_Info_T> = new Array<Error_Records_Info_T>();
  constructor(private router: Router, private EodfilestatusService: EodFilestatusService) { }
state:boolean=false;
  private initDatatable(): void {
    let exampleId: any = $('#example3');
    this.tableWidget = exampleId.DataTable({
      select: true,
      scrollX: true,
    });
  }

  ngOnInit() {

    this.isLoading = true;
    this.EodfilestatusService.getEodFileStatusDetails().subscribe(res => {
      this.isLoading = false;
      this.EodfilestatusDTO = res;
       console.log('EOD File Stats Component :this.EodfilestatusDTO : ' + JSON.stringify(this.EodfilestatusDTO));
    });
  }

  bulkUploadResult(psxBatchId: String, pageNo: number) {
    window.sessionStorage.setItem("psxBatchId", JSON.stringify(psxBatchId));
    window.sessionStorage.setItem("pageNo", JSON.stringify(pageNo));
    this.router.navigate(['/bulkuploadresult']);
  }

  reprocess(psxBatchId_reProcess: String) {
    this.EodfilestatusService.reProcess(psxBatchId_reProcess).subscribe((res) => {
      console.log('EodFile Stats Component : '+res);
      if(res != 'Reprocess Failed') {
        toastr.success('Reprocess done...!!!');
      } else {
        toastr.warning('Reprocess failed...!!!');
      }
    });
  }
  getErrorData(batchId:String)
  {
    this.EodfilestatusService.getErrorData(batchId).subscribe((res) => {
      this.Error_Records_Info_T = res;
      if(res != undefined)
      {
        this.state=true;
      }
      console.log('EodFile getErrorData : '+JSON.stringify(res));
    });
  }

  
}
