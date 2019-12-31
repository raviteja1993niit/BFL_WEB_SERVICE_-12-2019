import { CustomerSearchParameter } from "./CustomerSearchParameter";

export class CustomerSearchTableDTO {
    tableName: String;
    customerSearchParameters: Array<CustomerSearchParameter> = new Array<CustomerSearchParameter>();
}