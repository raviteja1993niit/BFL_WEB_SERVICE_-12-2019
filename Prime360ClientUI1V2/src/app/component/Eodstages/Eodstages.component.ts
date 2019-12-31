import { Component, OnInit } from '@angular/core';
import { EodstagesService } from '../../core/Eodstages.service';
import { EodstagesDTO } from '../../Model/EodstagesDTO';

import { Router } from '@angular/router';
import * as toastr from 'toastr';

@Component({
  selector: 'app-Eodstages',
  templateUrl: './Eodstages.component.html',
  styleUrls: ['./Eodstages.component.css']
})
export class EodstagesComponent implements OnInit {
  psxbatchId: string;
  public tableWidget: any;
  isLoading: boolean = false;
  
private intitialData:Array<String>;
  private EodstagesDTO: Array<EodstagesDTO> = new Array<EodstagesDTO>();
  constructor(private router: Router, private EodstagesService: EodstagesService) { }
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
    this.EodstagesService.getEodstagesintialDetails().subscribe(res => {
      this.isLoading = false;
      this.intitialData = res;
       console.log('Eod stages intial Details :this.EodstagesDTO : ' + JSON.stringify(this.intitialData));
    });
  }

 
  getEodstagesDTO(eodbatchid:String)
  {
    console.log("Fetching For Batchid ::: "+eodbatchid);
    this.EodstagesService.getEodstagesDTO(eodbatchid).subscribe((res) => {
      this.EodstagesDTO = res;
      if(res != undefined)
      {
        this.state=true;
      }
      console.log('Eodstage getEodstagesDTO : '+JSON.stringify(res));
    });
  }
}
