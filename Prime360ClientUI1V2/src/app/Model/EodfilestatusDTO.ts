import { Timestamp } from "rxjs/internal/operators/timestamp";

export class EodfilestatusDTO {
    batchId: String;
    fileName: String;
    processType: String;
    totalCount: Number;
    importCount: Number;
    errorCount: Number;
     insertts: String;
     sourceSystem:String;
   
    
    //  BATCH_ID,ERROR_COUNT,FILE_NAME,IMPORT_COUNT,INSERT_TS,PROCESS_TYPE,SOURCE_SYSTEM,TOTAL_COUNT
}