import { RelationalSearchTableDTO } from "./RelationalSearchTableDTO";

export class RelationalSearchDTO {
    tables: Array<RelationalSearchTableDTO> = new Array<RelationalSearchTableDTO>();
    resultsTableHeader: Array<String> = new Array<String>();
    results: Array<Array<String>> = new Array<Array<String>>();
}