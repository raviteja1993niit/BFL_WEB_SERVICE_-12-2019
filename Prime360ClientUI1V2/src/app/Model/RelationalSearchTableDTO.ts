import { RelationalSearchParameter } from "./RelationalSearchParameter";

export class RelationalSearchTableDTO {
    tableName: String;
    relationalSearchParameter: Array<RelationalSearchParameter> = new Array<RelationalSearchParameter>();
}