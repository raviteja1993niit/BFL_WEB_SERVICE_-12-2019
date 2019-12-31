import { ProfileIdentity } from "./profile-identity";

export class ProfileEntity {

    profileDescription: String;
    profileName: String;
    active: String;
    matchingRuleCSV: String;
    weightagesCSV: String;
    residualsCSV: String;
    scaleStringentCSV: String;
    maker: String;
    checker: String;
    approvalTs: Date;
    tenantId: String;
    rankingCSV: String;
    approved: String;
    profileIdentity: ProfileIdentity = new ProfileIdentity();
}
