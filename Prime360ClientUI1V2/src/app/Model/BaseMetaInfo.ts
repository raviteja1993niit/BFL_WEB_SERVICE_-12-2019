import { BaseMetaInfoIdentity } from "./BaseMetaInfoIdentity";

export class BaseMetaInfo {
    metaInfoIdentity = new BaseMetaInfoIdentity();
    csvString: string;
    srcSystemName: string;
    isEnabled: string;
    maker: string;
    checker: string;
    isApproved: string;
}