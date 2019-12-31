import { CustomerSearchTableDTO } from "./CustomerSearchTableDTO";

export class CustomerSearchDTO {
    tables: Array<CustomerSearchTableDTO> = new Array<CustomerSearchTableDTO>();
    resultsTableHeader: Array<String> = new Array<String>();
    results: Array<Array<String>> = new Array<Array<String>>();
}