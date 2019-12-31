import { ProfileIdentityDTO } from "./ProfileIdentityDTO";

export class ProfileEntityDTO {
    profileIdentity: ProfileIdentityDTO = new ProfileIdentityDTO();
    profileName: String;
    profileDescription: String;
    active: String;
    matchingRuleCSV: String;
    residualsCSV: String;
    weightagesCSV: String;
    scaleStringentCSV: String;
    rankingCSV: String;
    maker: String;
    checker: String;
    approvalTs: Date;
    tenantId: String;
    approved: String;
}