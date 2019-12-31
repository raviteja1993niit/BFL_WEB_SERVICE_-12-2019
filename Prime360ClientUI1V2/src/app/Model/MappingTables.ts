import { HeaderColumnWrapper } from "./HeaderColumnWrapper";
import { BulkUploadColumnMapping } from "./BulkUploadColumnMapping";

export class MappingTables {
    selectedTableName: string;
    headerColumnWrapper = new HeaderColumnWrapper();
    bulkColmappingList = new Array<BulkUploadColumnMapping>();
    fileName : string;
    serverFile :string;   
}
