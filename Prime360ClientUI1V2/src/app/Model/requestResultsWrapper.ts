import { NSPRequestResultsDTO } from "./ReqResult";

export class RequestResultsWrapper {
    resultsList: Array<NSPRequestResultsDTO> = new Array<NSPRequestResultsDTO>();
    pageCount: Number;
}