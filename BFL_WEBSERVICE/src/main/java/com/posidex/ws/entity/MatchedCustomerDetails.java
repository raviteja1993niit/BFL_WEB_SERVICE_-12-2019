package com.posidex.ws.entity;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "name", "motherName", "fatherName", "spouseName", "doi",
		"dob1", "dob2", "dob3", "dob4", "pan", "passport", "voterID",
		"drivingLIC", "aadhaar", "gender", "crnNo", "ckycNo", "officeState",
		"permanentState", "residenceState", "temporaryState", "presentState",
		"contactState", "preferredState", "gstin", "corporatelicence",
		"officeAddress", "officeCity", "officePin", "permanentAddress",
		"permanentCity", "permanentPin", "presentAddress", "presentCity",
		"presentPin", "emergencyAddress", "emergencyCity", "emergencyPin",
		"contactAddress", "contactCity", "contactPin", "postalAddress",
		"postalCity", "postalPin", "resindenceAddress", "residenceCity",
		"residencePin", "preferredAddress", "preferredCity", "preferredPin",
		"temporaryAddress", "temporaryCity", "temporaryPin", "presentEmail",
		"emaergencyEmail", "contactEmail", "officeEmail", "preferredEmail",
		"permanentEmail", "residenceEmail", "postalEmail", "temporaryEmail",
		"officePhone", "permanentPhone", "residencePhone", "presentPhone",
		"emergencyPhone", "postalPhone", "preferredPhone", "contactPhone",
		"temporaryPhone", "officeStreetNumber", "country",
		"permanentStreetNumber", "residenceStreetNumber", "tempStreetNumber",
		"eventType", "oldPsxBatchId", "rationCardNo", "presentCountry",
		"preferredCountry", "contactCountry", "postalCountry", "officeCountry",
		"permanentCountry", "teporaryCountry", "residenceCountry",
		"timestamp1", "timestamp2", "area", "policyNumber", "countryOfOrigin",
		"landmark", "mobile", "dateOfInCorporation", "tanNo", "processType",
		"applicatType", "employerName", "accountNumber", "creditCardNumber",
		"processFlag", "sourceSystem", "ucinFlag", "aadhaarNo", "caNumber",
		"cin", "din", "registrationNo", "customerNo", "customerId",
		"sourceSysId", "segment", "filterString1", "filterStrig2",
		"filterString3", "filterString4", "filterString5", "filterDate1",
		"filterDate2", "filterNumber1", "filterNumber2", "filterNumber3",
		"filterNumber4", "filterNumber5", "filterString6", "filterString7",
		"filterString8", "filterString9", "batchId", "record_Type",
		"matchingRule", "cust_unq_id", "mathcedPercentage", "mathcedId",
		"matchType", "insert_time", "lchgtime", "customer_contact_type",
		"permanent_std_code", "preferred_std_code", "present_std_code",
		"emergency_std_code", " temporary_std_code", "contact_std_code",
		"residence_std_code", "offical_std_code", "postal_std_code", "dealId",
		"product", "matchProfile", "dataSource", "request_id",
		"request_id_bfl", "request_id_fin", "firstName", "middleName",
		"lastName"

})
public class MatchedCustomerDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8119006423505867103L;

	@JsonProperty("NAME")
	private String name;

	@JsonIgnore
	private String cust_unq_id;

	@JsonIgnore
	private String area_id;

	@JsonProperty("MOTHER_NAME")
	private String motherName;

	@JsonProperty("FATHER_NAME")
	private String fatherName;

	@JsonProperty("SPOUSE_NAME")
	private String spouseName;

	@JsonProperty("DOI")
	private String doi;

	@JsonProperty("DOB1")
	private String dob1;

	@JsonProperty("DOB2")
	private String dob2;

	@JsonProperty("DOB3")
	private String dob3;

	@JsonProperty("DOB4")
	private String dob4;

	@JsonProperty("PAN")
	private String pan;

	@JsonProperty("PASSPORT")
	private String passport;

	@JsonProperty("VOTERID")
	private String voterID;

	@JsonProperty("DRIVINGLIC")
	private String drivingLIC;

	@JsonProperty("AADHAAR")
	private String aadhaar;

	@JsonProperty("GENDER")
	private String gender;

	@JsonProperty("CRNNO")
	private String crnNo;

	@JsonProperty("CKYCNO")
	private String ckycNo;

	@JsonProperty("OFFICE_STATE")
	private String officeState;

	@JsonProperty("PERMANENT_STATE")
	private String permanentState;

	@JsonProperty("RESIDENCE_STATE")
	private String residenceState;

	@JsonProperty("TEMPORARY_STATE")
	private String temporaryState;

	@JsonProperty("PRESENT_STATE")
	private String presentState;

	@JsonProperty("CONTACT_STATE")
	private String contactState;

	@JsonProperty("PREFERRED_STATE")
	private String preferredState;

	@JsonProperty("GSTIN")
	private String gstin;

	@JsonProperty("CORPORATELICENCE")
	private String corporatelicence;

	@JsonProperty("OFFICE_ADDRESS")
	private String officeAddress;

	@JsonProperty("OFFICE_CITY")
	private String officeCity;

	@JsonProperty("OFFICE_PIN")
	private String officePin;

	@JsonProperty("PERMANENT_ADDRESS")
	private String permanentAddress;

	@JsonProperty("PERMANENT_CITY")
	private String permanentCity;

	@JsonProperty("PERMANENT_PIN")
	private String permanentPin;

	@JsonProperty("PRESENT_ADDRESS")
	private String presentAddress;

	@JsonProperty("PRESENT_CITY")
	private String presentCity;

	@JsonProperty("PRESENT_PIN")
	private String presentPin;

	@JsonProperty("EMERGENCY_ADDRESS")
	private String emergencyAddress;

	@JsonProperty("EMERGENCY_CITY")
	private String emergencyCity;

	@JsonProperty("EMERGENCY_PIN")
	private String emergencyPin;

	@JsonProperty("CONTACT_ADDRESS")
	private String contactAddress;

	@JsonProperty("CONTACT_CITY")
	private String contactCity;

	@JsonProperty("CONTACT_PIN")
	private String contactPin;

	@JsonProperty("POSTAL_ADDRESS")
	private String postalAddress;

	@JsonProperty("POSTAL_CITY")
	private String postalCity;

	@JsonProperty("POSTAL_PIN")
	private String postalPin;

	@JsonProperty("RESIDENCE_ADDRESS")
	private String resindenceAddress;

	@JsonProperty("RESIDENCE_CITY")
	private String residenceCity;

	@JsonProperty("RESIDENCE_PIN")
	private String residencePin;

	@JsonProperty("PREFERRED_ADDRESS")
	private String preferredAddress;

	@JsonProperty("PREFERRED_CITY")
	private String preferredCity;

	@JsonProperty("PREFERRED_PIN")
	private String preferredPin;

	@JsonProperty("TEMPORARY_ADDRESS")
	private String temporaryAddress;

	@JsonProperty("TEMPORARY_CITY")
	private String temporaryCity;

	@JsonProperty("TEMPORARY_PIN")
	private String temporaryPin;

	@JsonProperty("PRESENT_EMAIL")
	private String presentEmail;

	@JsonProperty("EMERGENCY_EMAIL")
	private String emaergencyEmail;

	@JsonProperty("CONTACT_EMAIL")
	private String contactEmail;

	@JsonProperty("OFFICE_EMAIL")
	private String officeEmail;

	@JsonProperty("PREFERRED_EMAIL")
	private String preferredEmail;

	@JsonProperty("PERMANENT_EMAIL")
	private String permanentEmail;

	@JsonProperty("RESIDENCE_EMAIL")
	private String residenceEmail;

	@JsonProperty("POSTAL_EMAIL")
	private String postalEmail;

	@JsonProperty("TEMPORARY_EMAIL")
	private String temporaryEmail;

	@JsonProperty("OFFICE_PHONE")
	private String officePhone;

	@JsonProperty("PERMANENT_PHONE")
	private String permanentPhone;

	@JsonProperty("RESIDENCE_PHONE")
	private String residencePhone;

	@JsonProperty("PRESENT_PHONE")
	private String presentPhone;

	@JsonProperty("EMERGENCY_PHONE")
	private String emergencyPhone;

	@JsonProperty("POSTAL_PHONE")
	private String postalPhone;

	@JsonProperty("PREFERRED_PHONE")
	private String preferredPhone;

	@JsonProperty("CONTACT_PHONE")
	private String contactPhone;

	@JsonProperty("TEMPORARY_PHONE")
	private String temporaryPhone;

	@JsonProperty("OFFICE_STREET_NUMBER")
	private String officeStreetNumber;

	@JsonProperty("COUNTRY")
	private String country;

	@JsonProperty("PERMANENT_STREET_NUMBER")
	private String permanentStreetNumber;

	@JsonProperty("RESIDENCE_STREET_NUMBER")
	private String residenceStreetNumber;

	@JsonProperty("TEMP_STREET_NUMBER")
	private String tempStreetNumber;

	@JsonProperty("EVENTTYPE")
	private String eventType;

	@JsonProperty("OLD_PSX_BATCH_ID")
	private String oldPsxBatchId;

	@JsonProperty("RATIONCARD_NO")
	private String rationCardNo;

	@JsonProperty("PRESENT_COUNTRY")
	private String presentCountry;

	@JsonProperty("PREFERRED_COUNTRY")
	private String preferredCountry;

	@JsonProperty("CONTACT_COUNTRY")
	private String contactCountry;

	@JsonProperty("POSTAL_COUNTRY")
	private String postalCountry;

	@JsonProperty("OFFICE_COUNTRY")
	private String officeCountry;

	@JsonProperty("PERMANENT_COUNTRY")
	private String permanentCountry;

	@JsonProperty("TEMPORARY_COUNTRY")
	private String teporaryCountry;

	@JsonProperty("RESIDENCE_COUNTRY")
	private String residenceCountry;

	@JsonProperty("TIMESTAMP1")
	private String timestamp1;

	@JsonProperty("TIMESTAMP2")
	private String timestamp2;

	@JsonProperty("AREA")
	private String area;

	@JsonProperty("POLICY_NUMBER")
	private String policyNumber;

	@JsonProperty("COUNTRY_OF_ORIGIN")
	private String countryOfOrigin;

	@JsonProperty("LANDMARK")
	private String landmark;

	@JsonProperty("MOBILE")
	private String mobile;

	@JsonProperty("DATE_OF_INCORPORATION")
	private String dateOfInCorporation;

	@JsonProperty("TAN_NO")
	private String tanNo;

	@JsonProperty("PROCESS_TYPE")
	private String processType;

	@JsonProperty("APPLICANT_TYPE")
	private String applicatType;

	@JsonProperty("EMPOYER_NAME")
	private String employerName;

	@JsonProperty("ACCOUNT_NUMBER")
	private String accountNumber;

	@JsonProperty("CREDIT_CARD_NUMBER")
	private String creditCardNumber;

	@JsonProperty("PROCESS_FLAG")
	private String processFlag;

	@JsonProperty("SOURCE_SYSTEM")
	private String sourceSystem;

	@JsonIgnore
	private String psxsourceSystem;

	@JsonProperty("UCIN_FLAG")
	private String ucinFlag;

	/*
	 * @JsonProperty("GENDER") private String gender;
	 */

	@JsonProperty("AADHAR_NO")
	private String aadhaarNo;

	@JsonProperty("CA_NUMBER")
	private String caNumber;

	@JsonProperty("CIN")
	private String cin;

	@JsonProperty("DIN")
	private String din;

	@JsonProperty("REGISTRATION_NO")
	private String registrationNo;

	@JsonProperty("CUSTOMER_NO")
	private long customerNo;

	@JsonProperty("MATCHED_PERCENTAGE")
	private String mathcedPercentage;

	@JsonProperty("MATCHED_ID")
	private String mathcedId;

	@JsonProperty("MATCH_PROFILE")
	private String matchProfile;

	@JsonProperty("DATASOURCE")
	private String dataSource;

	@JsonProperty("REQUEST_ID")
	private String request_id;

	@JsonProperty("REQUEST_ID_BFL")
	private String request_id_bfl;

	@JsonProperty("REQUEST_ID_FIN")
	private String request_id_fin;

	@JsonProperty("FIRST_NAME")
	private String firstName;

	@JsonProperty("MIDDLE_NAME")
	private String middleName;

	@JsonProperty("APPLN_NO")
	private String applNO;

	@JsonProperty("LOAN_APP_NO")
	private String loanApplNO;

	@JsonProperty("CUSTOMER_TYPE")
	private String customerType;

	@JsonProperty("LAST_NAME")
	private String lastName;

	public String getApplNO() {
		return applNO;
	}

	public void setApplNO(String applNO) {
		this.applNO = applNO;
	}

	public String getLoanApplNO() {
		return loanApplNO;
	}

	public void setLoanApplNO(String loanApplNO) {
		this.loanApplNO = loanApplNO;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getMatchProfile() {
		return matchProfile;
	}

	public void setMatchProfile(String matchProfile) {
		this.matchProfile = matchProfile;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getRequest_id() {
		return request_id;
	}

	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	public String getRequest_id_bfl() {
		return request_id_bfl;
	}

	public void setRequest_id_bfl(String request_id_bfl) {
		this.request_id_bfl = request_id_bfl;
	}

	public String getRequest_id_fin() {
		return request_id_fin;
	}

	public void setRequest_id_fin(String request_id_fin) {
		this.request_id_fin = request_id_fin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String miildeName) {
		this.middleName = miildeName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(long customerNo) {
		this.customerNo = customerNo;
	}

	@JsonProperty("CUSTOMER_ID")
	private String customerId;

	@JsonProperty("SOURCE_SYS_ID")
	private String sourceSysId;

	@JsonProperty("SEGMENT")
	private String segment;

	@JsonProperty("FILLER_STRING_1")
	private String filterString1;

	@JsonProperty("FILLER_STRING_2")
	private String filterStrig2;

	@JsonProperty("FILLER_STRING_3")
	private String filterString3;

	@JsonProperty("FILLER_STRING_4")
	private String filterString4;

	@JsonProperty("FILLER_STRING_5")
	private String filterString5;

	@JsonProperty("FILLER_DATE_1")
	private Date filterDate1;

	@JsonProperty("FILLER_DATE_2")
	private Date filterDate2;

	@JsonProperty("FILLER_NUMBER_1")
	private long filterNumber1;

	@JsonProperty("FILLER_NUMBER_2")
	private long filterNumber2;

	@JsonProperty("FILLER_NUMBER_3")
	private long filterNumber3;

	@JsonProperty("FILLER_NUMBER_4")
	private long filterNumber4;

	@JsonProperty("FILLER_NUMBER_5")
	private long filterNumber5;

	@JsonProperty("MATCHING_RULE")
	private String matchingRule;
	/*
	 * @JsonProperty("SOURCE_SYSTEM") private String sourceSystem;
	 */

	@JsonProperty("FILLER_STRING_6")
	private String filterString6;

	@JsonProperty("FILLER_STRING_7")
	private String filterString7;

	@JsonProperty("FILLER_STRING_8")
	private String filterString8;

	@JsonProperty("FILLER_STRING_9")
	private String filterString9;

	@JsonProperty("BATCHID")
	private String batchId;

	@JsonProperty("RECORD_TYPE")
	private String record_Type;

	@JsonProperty("MATCH_TYPE")
	private String matchType;

	// ----- 10-10-2019
	@JsonProperty("INSERT_TIME")
	private String insert_time;

	@JsonProperty("LCHGTIME")
	private String lchgtime;

	@JsonProperty("CUSTOMER_CONTACT_TYPE")
	private String customer_contact_type;

	@JsonProperty("PERMANENT_STD_CODE")
	private String permanent_std_code;

	@JsonProperty("PREFERRED_STD_CODE")
	private String preferred_std_code;

	@JsonProperty("PRESENT_STD_CODE")
	private String present_std_code;

	@JsonProperty("EMERGENCY_STD_CODE")
	private String emergency_std_code;

	@JsonProperty("TEMPORARY_STD_CODE")
	private String temporary_std_code;

	@JsonProperty("CONTACT_STD_CODE")
	private String contact_std_code;

	@JsonProperty("RESIDENCE_STD_CODE")
	private String residence_std_code;

	@JsonProperty("OFFICE_STD_CODE")
	private String offical_std_code;

	@JsonProperty("POSTAL_STD_CODE")
	private String postal_std_code;

	@JsonProperty("DEAL_ID")
	private String dealId;

	@JsonProperty("PRODUCT")
	private String product;

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getInsert_time() {
		return insert_time;
	}

	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}

	public String getLchgtime() {
		return lchgtime;
	}

	public void setLchgtime(String lchgtime) {
		this.lchgtime = lchgtime;
	}

	public String getCustomer_contact_type() {
		return customer_contact_type;
	}

	public void setCustomer_contact_type(String customer_contact_type) {
		this.customer_contact_type = customer_contact_type;
	}

	public String getPermanent_std_code() {
		return permanent_std_code;
	}

	public void setPermanent_std_code(String permanent_std_code) {
		this.permanent_std_code = permanent_std_code;
	}

	public String getPreferred_std_code() {
		return preferred_std_code;
	}

	public void setPreferred_std_code(String preferred_std_code) {
		this.preferred_std_code = preferred_std_code;
	}

	public String getPresent_std_code() {
		return present_std_code;
	}

	public void setPresent_std_code(String present_std_code) {
		this.present_std_code = present_std_code;
	}

	public String getEmergency_std_code() {
		return emergency_std_code;
	}

	public void setEmergency_std_code(String emergency_std_code) {
		this.emergency_std_code = emergency_std_code;
	}

	public String getTemporary_std_code() {
		return temporary_std_code;
	}

	public void setTemporary_std_code(String temporary_std_code) {
		this.temporary_std_code = temporary_std_code;
	}

	public String getContact_std_code() {
		return contact_std_code;
	}

	public void setContact_std_code(String contact_std_code) {
		this.contact_std_code = contact_std_code;
	}

	public String getResidence_std_code() {
		return residence_std_code;
	}

	public void setResidence_std_code(String residence_std_code) {
		this.residence_std_code = residence_std_code;
	}

	public String getOffical_std_code() {
		return offical_std_code;
	}

	public void setOffical_std_code(String offical_std_code) {
		this.offical_std_code = offical_std_code;
	}

	public String getPostal_std_code() {
		return postal_std_code;
	}

	public void setPostal_std_code(String postal_std_code) {
		this.postal_std_code = postal_std_code;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getDob1() {
		return dob1;
	}

	public void setDob1(String dob1) {
		this.dob1 = dob1;
	}

	public String getDob2() {
		return dob2;
	}

	public void setDob2(String dob2) {
		this.dob2 = dob2;
	}

	public String getDob3() {
		return dob3;
	}

	public void setDob3(String dob3) {
		this.dob3 = dob3;
	}

	public String getDob4() {
		return dob4;
	}

	public void setDob4(String dob4) {
		this.dob4 = dob4;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getVoterID() {
		return voterID;
	}

	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}

	public String getDrivingLIC() {
		return drivingLIC;
	}

	public void setDrivingLIC(String drivingLIC) {
		this.drivingLIC = drivingLIC;
	}

	public String getAadhaar() {
		return aadhaar;
	}

	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCrnNo() {
		return crnNo;
	}

	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
	}

	public String getCkycNo() {
		return ckycNo;
	}

	public void setCkycNo(String ckycNo) {
		this.ckycNo = ckycNo;
	}

	public String getOfficeState() {
		return officeState;
	}

	public void setOfficeState(String officeState) {
		this.officeState = officeState;
	}

	public String getPermanentState() {
		return permanentState;
	}

	public void setPermanentState(String permanentState) {
		this.permanentState = permanentState;
	}

	public String getResidenceState() {
		return residenceState;
	}

	public void setResidenceState(String residenceState) {
		this.residenceState = residenceState;
	}

	public String getTemporaryState() {
		return temporaryState;
	}

	public void setTemporaryState(String temporaryState) {
		this.temporaryState = temporaryState;
	}

	public String getPresentState() {
		return presentState;
	}

	public void setPresentState(String presentState) {
		this.presentState = presentState;
	}

	public String getContactState() {
		return contactState;
	}

	public void setContactState(String contactState) {
		this.contactState = contactState;
	}

	public String getPreferredState() {
		return preferredState;
	}

	public void setPreferredState(String preferredState) {
		this.preferredState = preferredState;
	}

	public String getGstin() {
		return gstin;
	}

	public void setGstin(String gstin) {
		this.gstin = gstin;
	}

	public String getCorporatelicence() {
		return corporatelicence;
	}

	public void setCorporatelicence(String corporatelicence) {
		this.corporatelicence = corporatelicence;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getOfficeCity() {
		return officeCity;
	}

	public void setOfficeCity(String officeCity) {
		this.officeCity = officeCity;
	}

	public String getOfficePin() {
		return officePin;
	}

	public void setOfficePin(String officePin) {
		this.officePin = officePin;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public String getPermanentCity() {
		return permanentCity;
	}

	public void setPermanentCity(String permanentCity) {
		this.permanentCity = permanentCity;
	}

	public String getPermanentPin() {
		return permanentPin;
	}

	public void setPermanentPin(String permanentPin) {
		this.permanentPin = permanentPin;
	}

	public String getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(String presentAddress) {
		this.presentAddress = presentAddress;
	}

	public String getPresentCity() {
		return presentCity;
	}

	public void setPresentCity(String presentCity) {
		this.presentCity = presentCity;
	}

	public String getPresentPin() {
		return presentPin;
	}

	public void setPresentPin(String presentPin) {
		this.presentPin = presentPin;
	}

	public String getEmergencyAddress() {
		return emergencyAddress;
	}

	public void setEmergencyAddress(String emergencyAddress) {
		this.emergencyAddress = emergencyAddress;
	}

	public String getEmergencyCity() {
		return emergencyCity;
	}

	public void setEmergencyCity(String emergencyCity) {
		this.emergencyCity = emergencyCity;
	}

	public String getEmergencyPin() {
		return emergencyPin;
	}

	public void setEmergencyPin(String emergencyPin) {
		this.emergencyPin = emergencyPin;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getContactCity() {
		return contactCity;
	}

	public void setContactCity(String contactCity) {
		this.contactCity = contactCity;
	}

	public String getContactPin() {
		return contactPin;
	}

	public void setContactPin(String contactPin) {
		this.contactPin = contactPin;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getPostalCity() {
		return postalCity;
	}

	public void setPostalCity(String postalCity) {
		this.postalCity = postalCity;
	}

	public String getPostalPin() {
		return postalPin;
	}

	public void setPostalPin(String postalPin) {
		this.postalPin = postalPin;
	}

	public String getResindenceAddress() {
		return resindenceAddress;
	}

	public void setResindenceAddress(String resindenceAddress) {
		this.resindenceAddress = resindenceAddress;
	}

	public String getResidenceCity() {
		return residenceCity;
	}

	public void setResidenceCity(String residenceCity) {
		this.residenceCity = residenceCity;
	}

	public String getResidencePin() {
		return residencePin;
	}

	public void setResidencePin(String residencePin) {
		this.residencePin = residencePin;
	}

	public String getPreferredAddress() {
		return preferredAddress;
	}

	public void setPreferredAddress(String preferredAddress) {
		this.preferredAddress = preferredAddress;
	}

	public String getPreferredCity() {
		return preferredCity;
	}

	public void setPreferredCity(String preferredCity) {
		this.preferredCity = preferredCity;
	}

	public String getPreferredPin() {
		return preferredPin;
	}

	public void setPreferredPin(String preferredPin) {
		this.preferredPin = preferredPin;
	}

	public String getTemporaryAddress() {
		return temporaryAddress;
	}

	public void setTemporaryAddress(String temporaryAddress) {
		this.temporaryAddress = temporaryAddress;
	}

	public String getTemporaryCity() {
		return temporaryCity;
	}

	public void setTemporaryCity(String temporaryCity) {
		this.temporaryCity = temporaryCity;
	}

	public String getTemporaryPin() {
		return temporaryPin;
	}

	public void setTemporaryPin(String temporaryPin) {
		this.temporaryPin = temporaryPin;
	}

	public String getPresentEmail() {
		return presentEmail;
	}

	public void setPresentEmail(String presentEmail) {
		this.presentEmail = presentEmail;
	}

	public String getEmaergencyEmail() {
		return emaergencyEmail;
	}

	public void setEmaergencyEmail(String emaergencyEmail) {
		this.emaergencyEmail = emaergencyEmail;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getOfficeEmail() {
		return officeEmail;
	}

	public void setOfficeEmail(String officeEmail) {
		this.officeEmail = officeEmail;
	}

	public String getPreferredEmail() {
		return preferredEmail;
	}

	public void setPreferredEmail(String preferredEmail) {
		this.preferredEmail = preferredEmail;
	}

	public String getPermanentEmail() {
		return permanentEmail;
	}

	public void setPermanentEmail(String permanentEmail) {
		this.permanentEmail = permanentEmail;
	}

	public String getResidenceEmail() {
		return residenceEmail;
	}

	public void setResidenceEmail(String residenceEmail) {
		this.residenceEmail = residenceEmail;
	}

	public String getPostalEmail() {
		return postalEmail;
	}

	public void setPostalEmail(String postalEmail) {
		this.postalEmail = postalEmail;
	}

	public String getTemporaryEmail() {
		return temporaryEmail;
	}

	public void setTemporaryEmail(String temporaryEmail) {
		this.temporaryEmail = temporaryEmail;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getPermanentPhone() {
		return permanentPhone;
	}

	public void setPermanentPhone(String permanentPhone) {
		this.permanentPhone = permanentPhone;
	}

	public String getResidencePhone() {
		return residencePhone;
	}

	public void setResidencePhone(String residencePhone) {
		this.residencePhone = residencePhone;
	}

	public String getPresentPhone() {
		return presentPhone;
	}

	public void setPresentPhone(String presentPhone) {
		this.presentPhone = presentPhone;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}

	public String getPostalPhone() {
		return postalPhone;
	}

	public void setPostalPhone(String postalPhone) {
		this.postalPhone = postalPhone;
	}

	public String getPreferredPhone() {
		return preferredPhone;
	}

	public void setPreferredPhone(String preferredPhone) {
		this.preferredPhone = preferredPhone;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getTemporaryPhone() {
		return temporaryPhone;
	}

	public void setTemporaryPhone(String temporaryPhone) {
		this.temporaryPhone = temporaryPhone;
	}

	public String getOfficeStreetNumber() {
		return officeStreetNumber;
	}

	public void setOfficeStreetNumber(String officeStreetNumber) {
		this.officeStreetNumber = officeStreetNumber;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPermanentStreetNumber() {
		return permanentStreetNumber;
	}

	public void setPermanentStreetNumber(String permanentStreetNumber) {
		this.permanentStreetNumber = permanentStreetNumber;
	}

	public String getResidenceStreetNumber() {
		return residenceStreetNumber;
	}

	public void setResidenceStreetNumber(String residenceStreetNumber) {
		this.residenceStreetNumber = residenceStreetNumber;
	}

	public String getTempStreetNumber() {
		return tempStreetNumber;
	}

	public void setTempStreetNumber(String tempStreetNumber) {
		this.tempStreetNumber = tempStreetNumber;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getOldPsxBatchId() {
		return oldPsxBatchId;
	}

	public void setOldPsxBatchId(String oldPsxBatchId) {
		this.oldPsxBatchId = oldPsxBatchId;
	}

	public String getRationCardNo() {
		return rationCardNo;
	}

	public void setRationCardNo(String rationCardNo) {
		this.rationCardNo = rationCardNo;
	}

	public String getPresentCountry() {
		return presentCountry;
	}

	public void setPresentCountry(String presentCountry) {
		this.presentCountry = presentCountry;
	}

	public String getPreferredCountry() {
		return preferredCountry;
	}

	public void setPreferredCountry(String preferredCountry) {
		this.preferredCountry = preferredCountry;
	}

	public String getContactCountry() {
		return contactCountry;
	}

	public void setContactCountry(String contactCountry) {
		this.contactCountry = contactCountry;
	}

	public String getPostalCountry() {
		return postalCountry;
	}

	public void setPostalCountry(String postalCountry) {
		this.postalCountry = postalCountry;
	}

	public String getOfficeCountry() {
		return officeCountry;
	}

	public void setOfficeCountry(String officeCountry) {
		this.officeCountry = officeCountry;
	}

	public String getPermanentCountry() {
		return permanentCountry;
	}

	public void setPermanentCountry(String permanentCountry) {
		this.permanentCountry = permanentCountry;
	}

	public String getTeporaryCountry() {
		return teporaryCountry;
	}

	public void setTeporaryCountry(String teporaryCountry) {
		this.teporaryCountry = teporaryCountry;
	}

	public String getResidenceCountry() {
		return residenceCountry;
	}

	public void setResidenceCountry(String residenceCountry) {
		this.residenceCountry = residenceCountry;
	}

	public String getTimestamp1() {
		return timestamp1;
	}

	public void setTimestamp1(String timestamp1) {
		this.timestamp1 = timestamp1;
	}

	public String getTimestamp2() {
		return timestamp2;
	}

	public void setTimestamp2(String timestamp2) {
		this.timestamp2 = timestamp2;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPolicyNumber() {
		return policyNumber;
	}

	public void setPolicyNumber(String policyNumber) {
		this.policyNumber = policyNumber;
	}

	public String getCountryOfOrigin() {
		return countryOfOrigin;
	}

	public void setCountryOfOrigin(String countryOfOrigin) {
		this.countryOfOrigin = countryOfOrigin;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getDateOfInCorporation() {
		return dateOfInCorporation;
	}

	public void setDateOfInCorporation(String dateOfInCorporation) {
		this.dateOfInCorporation = dateOfInCorporation;
	}

	public String getTanNo() {
		return tanNo;
	}

	public void setTanNo(String tanNo) {
		this.tanNo = tanNo;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getApplicatType() {
		return applicatType;
	}

	public void setApplicatType(String applicatType) {
		this.applicatType = applicatType;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	public String getUcinFlag() {
		return ucinFlag;
	}

	public void setUcinFlag(String ucinFlag) {
		this.ucinFlag = ucinFlag;
	}

	public String getAadhaarNo() {
		return aadhaarNo;
	}

	public void setAadhaarNo(String aadhaarNo) {
		this.aadhaarNo = aadhaarNo;
	}

	public String getCaNumber() {
		return caNumber;
	}

	public void setCaNumber(String caNumber) {
		this.caNumber = caNumber;
	}

	public String getCin() {
		return cin;
	}

	public void setCin(String cin) {
		this.cin = cin;
	}

	public String getDin() {
		return din;
	}

	public void setDin(String din) {
		this.din = din;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getSourceSysId() {
		return sourceSysId;
	}

	public void setSourceSysId(String sourceSysId) {
		this.sourceSysId = sourceSysId;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getFilterString1() {
		return filterString1;
	}

	public void setFilterString1(String filterString1) {
		this.filterString1 = filterString1;
	}

	public String getFilterStrig2() {
		return filterStrig2;
	}

	public void setFilterStrig2(String filterStrig2) {
		this.filterStrig2 = filterStrig2;
	}

	public String getFilterString3() {
		return filterString3;
	}

	public void setFilterString3(String filterString3) {
		this.filterString3 = filterString3;
	}

	public String getFilterString4() {
		return filterString4;
	}

	public void setFilterString4(String filterString4) {
		this.filterString4 = filterString4;
	}

	public String getFilterString5() {
		return filterString5;
	}

	public void setFilterString5(String filterString5) {
		this.filterString5 = filterString5;
	}

	public Date getFilterDate1() {
		return filterDate1;
	}

	public void setFilterDate1(Date filterDate1) {
		this.filterDate1 = filterDate1;
	}

	public Date getFilterDate2() {
		return filterDate2;
	}

	public void setFilterDate2(Date filterDate2) {
		this.filterDate2 = filterDate2;
	}

	public long getFilterNumber1() {
		return filterNumber1;
	}

	public void setFilterNumber1(long filterNumber1) {
		this.filterNumber1 = filterNumber1;
	}

	public long getFilterNumber2() {
		return filterNumber2;
	}

	public void setFilterNumber2(long filterNumber2) {
		this.filterNumber2 = filterNumber2;
	}

	public long getFilterNumber3() {
		return filterNumber3;
	}

	public void setFilterNumber3(long filterNumber3) {
		this.filterNumber3 = filterNumber3;
	}

	public long getFilterNumber4() {
		return filterNumber4;
	}

	public void setFilterNumber4(long filterNumber4) {
		this.filterNumber4 = filterNumber4;
	}

	public long getFilterNumber5() {
		return filterNumber5;
	}

	public void setFilterNumber5(long filterNumber5) {
		this.filterNumber5 = filterNumber5;
	}

	public String getFilterString6() {
		return filterString6;
	}

	public void setFilterString6(String filterString6) {
		this.filterString6 = filterString6;
	}

	public String getFilterString7() {
		return filterString7;
	}

	public void setFilterString7(String filterString7) {
		this.filterString7 = filterString7;
	}

	public String getFilterString8() {
		return filterString8;
	}

	public void setFilterString8(String filterString8) {
		this.filterString8 = filterString8;
	}

	public String getFilterString9() {
		return filterString9;
	}

	public void setFilterString9(String filterString9) {
		this.filterString9 = filterString9;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getRecord_Type() {
		return record_Type;
	}

	public void setRecord_Type(String record_Type) {
		this.record_Type = record_Type;
	}

	public String getMatchingRule() {
		return matchingRule;
	}

	public void setMatchingRule(String matchingRule) {
		this.matchingRule = matchingRule;
	}

	public String getPsxsourceSystem() {
		return psxsourceSystem;
	}

	public void setPsxsourceSystem(String psxsourceSystem) {
		this.psxsourceSystem = psxsourceSystem;
	}

	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getCust_unq_id() {
		return cust_unq_id;
	}

	public void setCust_unq_id(String cust_unq_id) {
		this.cust_unq_id = cust_unq_id;
	}

	public String getMathcedPercentage() {
		return mathcedPercentage;
	}

	public void setMathcedPercentage(String mathcedPercentage) {
		this.mathcedPercentage = mathcedPercentage;
	}

	public String getMathcedId() {
		return mathcedId;
	}

	public void setMathcedId(String mathcedId) {
		this.mathcedId = mathcedId;
	}

	@Override
	public String toString() {
		return "MatchedCustomerDetails [name=" + name + ", cust_unq_id="
				+ cust_unq_id + ", area_id=" + area_id + ", motherName="
				+ motherName + ", fatherName=" + fatherName + ", spouseName="
				+ spouseName + ", doi=" + doi + ", dob1=" + dob1 + ", dob2="
				+ dob2 + ", dob3=" + dob3 + ", dob4=" + dob4 + ", pan=" + pan
				+ ", passport=" + passport + ", voterID=" + voterID
				+ ", drivingLIC=" + drivingLIC + ", aadhaar=" + aadhaar
				+ ", gender=" + gender + ", crnNo=" + crnNo + ", ckycNo="
				+ ckycNo + ", officeState=" + officeState + ", permanentState="
				+ permanentState + ", residenceState=" + residenceState
				+ ", temporaryState=" + temporaryState + ", presentState="
				+ presentState + ", contactState=" + contactState
				+ ", preferredState=" + preferredState + ", gstin=" + gstin
				+ ", corporatelicence=" + corporatelicence + ", officeAddress="
				+ officeAddress + ", officeCity=" + officeCity + ", officePin="
				+ officePin + ", permanentAddress=" + permanentAddress
				+ ", permanentCity=" + permanentCity + ", permanentPin="
				+ permanentPin + ", presentAddress=" + presentAddress
				+ ", presentCity=" + presentCity + ", presentPin=" + presentPin
				+ ", emergencyAddress=" + emergencyAddress + ", emergencyCity="
				+ emergencyCity + ", emergencyPin=" + emergencyPin
				+ ", contactAddress=" + contactAddress + ", contactCity="
				+ contactCity + ", contactPin=" + contactPin
				+ ", postalAddress=" + postalAddress + ", postalCity="
				+ postalCity + ", postalPin=" + postalPin
				+ ", resindenceAddress=" + resindenceAddress
				+ ", residenceCity=" + residenceCity + ", residencePin="
				+ residencePin + ", preferredAddress=" + preferredAddress
				+ ", preferredCity=" + preferredCity + ", preferredPin="
				+ preferredPin + ", temporaryAddress=" + temporaryAddress
				+ ", temporaryCity=" + temporaryCity + ", temporaryPin="
				+ temporaryPin + ", presentEmail=" + presentEmail
				+ ", emaergencyEmail=" + emaergencyEmail + ", contactEmail="
				+ contactEmail + ", officeEmail=" + officeEmail
				+ ", preferredEmail=" + preferredEmail + ", permanentEmail="
				+ permanentEmail + ", residenceEmail=" + residenceEmail
				+ ", postalEmail=" + postalEmail + ", temporaryEmail="
				+ temporaryEmail + ", officePhone=" + officePhone
				+ ", permanentPhone=" + permanentPhone + ", residencePhone="
				+ residencePhone + ", presentPhone=" + presentPhone
				+ ", emergencyPhone=" + emergencyPhone + ", postalPhone="
				+ postalPhone + ", preferredPhone=" + preferredPhone
				+ ", contactPhone=" + contactPhone + ", temporaryPhone="
				+ temporaryPhone + ", officeStreetNumber=" + officeStreetNumber
				+ ", country=" + country + ", permanentStreetNumber="
				+ permanentStreetNumber + ", residenceStreetNumber="
				+ residenceStreetNumber + ", tempStreetNumber="
				+ tempStreetNumber + ", eventType=" + eventType
				+ ", oldPsxBatchId=" + oldPsxBatchId + ", rationCardNo="
				+ rationCardNo + ", presentCountry=" + presentCountry
				+ ", preferredCountry=" + preferredCountry
				+ ", contactCountry=" + contactCountry + ", postalCountry="
				+ postalCountry + ", officeCountry=" + officeCountry
				+ ", permanentCountry=" + permanentCountry
				+ ", teporaryCountry=" + teporaryCountry
				+ ", residenceCountry=" + residenceCountry + ", timestamp1="
				+ timestamp1 + ", timestamp2=" + timestamp2 + ", area=" + area
				+ ", policyNumber=" + policyNumber + ", countryOfOrigin="
				+ countryOfOrigin + ", landmark=" + landmark + ", mobile="
				+ mobile + ", dateOfInCorporation=" + dateOfInCorporation
				+ ", tanNo=" + tanNo + ", processType=" + processType
				+ ", applicatType=" + applicatType + ", employerName="
				+ employerName + ", accountNumber=" + accountNumber
				+ ", creditCardNumber=" + creditCardNumber + ", processFlag="
				+ processFlag + ", sourceSystem=" + sourceSystem
				+ ", psxsourceSystem=" + psxsourceSystem + ", ucinFlag="
				+ ucinFlag + ", aadhaarNo=" + aadhaarNo + ", caNumber="
				+ caNumber + ", cin=" + cin + ", din=" + din
				+ ", registrationNo=" + registrationNo + ", customerNo="
				+ customerNo + ", mathcedPercentage=" + mathcedPercentage
				+ ", mathcedId=" + mathcedId + ", matchProfile=" + matchProfile
				+ ", dataSource=" + dataSource + ", request_id=" + request_id
				+ ", request_id_bfl=" + request_id_bfl + ", request_id_fin="
				+ request_id_fin + ", firstName=" + firstName + ", middleName="
				+ middleName + ", applNO=" + applNO + ", loanApplNO="
				+ loanApplNO + ", customerType=" + customerType + ", lastName="
				+ lastName + ", customerId=" + customerId + ", sourceSysId="
				+ sourceSysId + ", segment=" + segment + ", filterString1="
				+ filterString1 + ", filterStrig2=" + filterStrig2
				+ ", filterString3=" + filterString3 + ", filterString4="
				+ filterString4 + ", filterString5=" + filterString5
				+ ", filterDate1=" + filterDate1 + ", filterDate2="
				+ filterDate2 + ", filterNumber1=" + filterNumber1
				+ ", filterNumber2=" + filterNumber2 + ", filterNumber3="
				+ filterNumber3 + ", filterNumber4=" + filterNumber4
				+ ", filterNumber5=" + filterNumber5 + ", matchingRule="
				+ matchingRule + ", filterString6=" + filterString6
				+ ", filterString7=" + filterString7 + ", filterString8="
				+ filterString8 + ", filterString9=" + filterString9
				+ ", batchId=" + batchId + ", record_Type=" + record_Type
				+ ", matchType=" + matchType + ", insert_time=" + insert_time
				+ ", lchgtime=" + lchgtime + ", customer_contact_type="
				+ customer_contact_type + ", permanent_std_code="
				+ permanent_std_code + ", preferred_std_code="
				+ preferred_std_code + ", present_std_code=" + present_std_code
				+ ", emergency_std_code=" + emergency_std_code
				+ ", temporary_std_code=" + temporary_std_code
				+ ", contact_std_code=" + contact_std_code
				+ ", residence_std_code=" + residence_std_code
				+ ", offical_std_code=" + offical_std_code
				+ ", postal_std_code=" + postal_std_code + ", dealId=" + dealId
				+ ", product=" + product + "]";
	}

}
