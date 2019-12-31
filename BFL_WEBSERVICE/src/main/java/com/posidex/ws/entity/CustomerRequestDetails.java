package com.posidex.ws.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "applnNo", "segment", "custSrNo", "applicationType", "firstName", "middleName", "lastName",
		"fatherName", "employerName", "dob1", "dob2", "dob3", "dob4", "pan", "drivingLicenseNumber", "accountNumber",
		"voterId", "creditCardNumber", "customerNo", "lanNo", "lanNo2", "customerType", "dateOfInCorporation", "tanNo",
		"product", "passportNo", "address", "address1", "address2", "address3", "area", "landMark", "city", "pin",
		"std", "landLine1", "landLine2", "mobile", "email", "address1Office", "address2Office", "address3Office",
		"areaOffice", "landMarkOffice", "cityOffice", "pinOffice", "stdOffice", "landLine1Office", "landLine2Office",
		"mobileOffice", "emailOffice", "processFLG", "customerStatus", "matchedId", "matchedPesontage",
		"negativeMatchedId", "sfMatchedId", "sfMatchedPercentage", "batch", "loanAppNo", "matchCount", "stayingSince",
		"cityClassification", "employmentBusiness", "age", "residentType", "creditProgram", "assetCategory",
		"rejMatchedId", "rejMatchedPrcntg", "fraudMatchedId", "fraudMatchedPrcntg", "terrMatchedId", "rejReason",
		"productCode", "errorDesc", "org", "negativeMatchCriteria", "motherName", "aadhaar", "gender", "spouseName",
		"cin", "din", "registrationNo", "office_address", "office_city", "office_pin", "permanent_address",
		"permanent_city", "permanent_pin", "present_address", "present_city", "present_pin", "emergency_address",
		"emergency_city", "emergency_pin", "contact_address", "contact_city", "contact_pin", "postal_address",
		"postal_city", "postal_pin", "residence_address", "residence_city", "residence_pin", "preferred_address",
		"preferred_city", "preferred_pin", "temporary_address", "temporary_city", "temporary_pin", "office_email",
		"permanent_email", "present_email", "emergency_email", "contact_email", "postal_email", "residence_email",
		"preferred_email", "temporary_email", "office_phone", "permanent_phone", "present_phone", "emergency_phone",
		"contact_phone", "postal_phone", "residence_phone", "preferred_phone", "temporary_phone", "crnNo", "office_area"

})

@Component
public class CustomerRequestDetails implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 7780632916749573280L;

	@JsonProperty("APPLN_NO")
	private String applnNo;

	@JsonProperty("SEGMENT")
	private String segment;

	@JsonProperty("AADHAAR")
	private String aadhaar;

	@JsonProperty("GENDER")
	private String gender;

	@JsonProperty("CUST_SR_NO")
	private String custSrNo;

	@JsonProperty("APPLICANT_TYPE")
	private String applicationType;

	// @JsonProperty("NAME")
	@JsonIgnore
	private String name;

	@JsonIgnore
	private Date lchgTime;

	@JsonProperty("MOTHER_NAME")
	private String motherName;

	@JsonProperty("FIRST_NAME")
	private String firstName;

	@JsonProperty("MIDDLE_NAME")
	private String middleName;

	@JsonProperty("LAST_NAME")
	private String lastName;

	@JsonProperty("FATHER_NAME")
	private String fatherName;

	@JsonProperty("SPOUSE_NAME")
	private String spouseName;

	@JsonProperty("EMPLOYER_NAME")
	private String employerName;

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

	@JsonProperty("DRIVING_LICENSE_NUMBER")
	private String drivingLicenseNumber;

	@JsonProperty("ACCOUNT_NUMBER")
	private String accountNumber;

	@JsonProperty("VOTER_ID")
	private String voterId;

	@JsonProperty("CIN")
	private String cin;

	@JsonProperty("CRNNO")
	private String crnNo;

	@JsonProperty("CKYCNO")
	private String ckyCno;

	@JsonProperty("DIN")
	private String din;

	@JsonProperty("REGISTRATION_NO")
	private String registrationNo;

	@JsonProperty("CREDIT_CARD_NUMBER")
	private String creditCardNumber;

	@JsonProperty("CUSTOMER_NO")
	private String customerNo;

	@JsonProperty("LAN_NO")
	private String lanNo;

	@JsonProperty("LAN_2")
	private String lanNo2;

	@JsonProperty("CUSTOMER_TYPE")
	private String customerType;

	@JsonProperty("DATE_OF_INCORPORATION")
	private String dateOfInCorporation;

	@JsonProperty("TAN_NO")
	private String tanNo;

	@JsonProperty("PRODUCT")
	private String product;

	@JsonProperty("PASSPORT_NO")
	private String passportNo;

	@JsonProperty("ADDRESS_1")
	private String address1;

	@JsonProperty("ADDRESS_2")
	private String address2;

	@JsonProperty("ADDRESS_3")
	private String address3;

	@JsonProperty("AREA")
	private String area;

	@JsonProperty("LANDMARK")
	private String landMark;

	@JsonProperty("CITY")
	private String city;

	@JsonProperty("PIN")
	private String pin;

	@JsonProperty("STD")
	private String std;

	@JsonProperty("LANDLINE_1")
	private String landLine1;

	@JsonProperty("LANDLINE_2")
	private String landLine2;

	@JsonProperty("MOBILE")
	private String mobile;

	@JsonProperty("PHONE")
	private String phone;

	@JsonProperty("EMAIL")
	private String email;

	//@JsonProperty("ADDRESS")
	@JsonIgnore
	private String address;

	@JsonProperty("ADDRESS1_OFFICE")
	private String address1Office;

	@JsonProperty("ADDRESS2_OFFICE")
	private String address2Office;

	@JsonProperty("ADDRESS3_OFFICE")
	private String address3Office;

	@JsonProperty("AREA_OFFICE")
	private String areaOffice;

	@JsonProperty("LANDMARK_OFFICE")
	private String landMarkOffice;

	@JsonProperty("CITY_OFFICE")
	private String cityOffice;

	@JsonProperty("PIN_OFFICE")
	private String pinOffice;

	@JsonProperty("STD_OFFICE")
	private String stdOffice;

	@JsonProperty("LANDLINE1_OFFICE")
	private String landLine1Office;

	@JsonProperty("LANDLINE2_OFFICE")
	private String landLine2Office;

	@JsonProperty("MOBILE_OFFICE")
	private String mobileOffice;

	@JsonProperty("EMAIL_OFFICE")
	private String emailOffice;

	@JsonProperty("PROCESS_FLG")
	private String processFLG;

	@JsonProperty("CUSTOMER_STATUS")
	private String customerStatus;

//	@JsonProperty("MATCHED_ID")
	@JsonIgnore
	private String matchedId;

//	@JsonProperty("MATCHED_PERCENTAGE")
	@JsonIgnore
	private String matchedPesontage;

//	@JsonProperty("NEGATIVE_MATCHED_ID")
	@JsonIgnore
	private String negativeMatchedId;

//	@JsonProperty("SF_MATCHED_ID")
	@JsonIgnore
	private String sfMatchedId;

//	@JsonProperty("SF_MATCHED_PERCENTAGE")
	@JsonIgnore
	private String sfMatchedPercentage;

	@JsonProperty("BATCH")
	private String batch;

	@JsonProperty("LOAN_APP_NO")
	private String loanAppNo;

//	@JsonProperty("MATCHCOUNT")
	@JsonIgnore
	private int matchCount;

	@JsonProperty("STAYING_SINCE")
	private String stayingSince;

	@JsonProperty("CITY_CLASSIFICATION")
	private String cityClassification;

	@JsonProperty("EMPLOYMENT_BUSINESS")
	private String employmentBusiness;

	@JsonProperty("AGE")
	private String age;

	@JsonProperty("RESIDENT_TYPE")
	private String residentType;

	@JsonProperty("CREDIT_PROGRAM")
	private String creditProgram;

	@JsonProperty("ASSET_CATEGORY")
	private String assetCategory;

//	@JsonProperty("REJ_MATCHED_ID")
	@JsonIgnore
	private String rejMatchedId;

//	@JsonProperty("REJ_MATCHED_PRCNTG")
	@JsonIgnore
	private String rejMatchedPrcntg;

//	@JsonProperty("FRAUD_MATCHED_ID")
	@JsonIgnore
	private String fraudMatchedId;

//	@JsonProperty("FRAUD_MATCHED_PRCNTG")
	@JsonIgnore
	private String fraudMatchedPrcntg;

//	@JsonProperty("TERR_MATCHED_ID")
	@JsonIgnore
	private String terrMatchedId;

//	@JsonProperty("REJ_RESASON")
	@JsonIgnore
	private String rejReason;
	
	@JsonProperty("PRODUCT_CODE")
	private String productCode;

	@JsonProperty("ERROR_DESC")
	private String errorDesc;

	@JsonProperty("ORG")
	private String org;

//	@JsonProperty("NEGATIVE_MATCH_CRITERIA")
	@JsonIgnore
	private String negativeMatchCriteria;

//	@JsonProperty("OFFICE_ADDRESS")
	@JsonIgnore
	private String office_address;

//	@JsonProperty("OFFICE_AREA")
	@JsonIgnore
	private String office_area;

//	@JsonProperty("OFFICE_CITY")
	@JsonIgnore
	private String office_city;

//	@JsonProperty("OFFICE_PIN")
	@JsonIgnore
	private String office_pin;

//	@JsonProperty("OFFICE_PHONE")
	@JsonIgnore
	private String office_phone;

	@JsonProperty("PERMANENT_ADDRESS")
	private String permanent_address;

	@JsonProperty("PERMANENT_CITY")
	private String permanent_city;

	@JsonProperty("PERMANENT_PIN")
	private String permanent_pin;

//	@JsonProperty("PERMANENT_PHONE")
	@JsonIgnore
	private String permanent_phone;

	@JsonProperty("PRESENT_ADDRESS")
	private String present_address;

	@JsonProperty("PRESENT_CITY")
	private String present_city;

	@JsonProperty("PRESENT_PIN")
	private String present_pin;

	@JsonProperty("PRESENT_PHONE")
	private String present_phone;

	@JsonProperty("EMERGENCY_ADDRESS")
	private String emergency_address;

	@JsonProperty("EMERGENCY_CITY")
	private String emergency_city;

	@JsonProperty("EMERGENCY_PIN")
	private String emergency_pin;

	@JsonProperty("EMERGENCY_PHONE")
	private String emergency_phone;

	@JsonProperty("CONTACT_ADDRESS")
	private String contact_address;

	@JsonProperty("CONTACT_CITY")
	private String contact_city;

	@JsonProperty("CONTACT_PIN")
	private String contact_pin;

	@JsonProperty("CONTACT_PHONE")
	private String contact_phone;

	@JsonProperty("POSTAL_ADDRESS")
	private String postal_address;

	@JsonProperty("POSTAL_CITY")
	private String postal_city;

	@JsonProperty("POSTAL_PIN")
	private String postal_pin;

//	@JsonProperty("POSTAL_PHONE")
	@JsonIgnore
	private String postal_phone;

//	@JsonProperty("RESIDENCE_ADDRESS")
	@JsonIgnore
	private String residence_address;

//	@JsonProperty("RESIDENCE_CITY")
	@JsonIgnore
	private String residence_city;

//	@JsonProperty("RESIDENCE_PIN")
	@JsonIgnore
	private String residence_pin;

//	@JsonProperty("RESIDENCE_PHONE")
	@JsonIgnore
	private String residence_phone;

	@JsonProperty("PREFERRED_ADDRESS")
	private String preferred_address;

	@JsonProperty("PREFERRED_CITY")
	private String preferred_city;

	@JsonProperty("PREFERRED_PIN")
	private String preferred_pin;

//	@JsonProperty("PREFERRED_PHONE")
	@JsonIgnore
	private String preferred_phone;

	@JsonProperty("TEMPORARY_ADDRESS")
	private String temporary_address;

	@JsonProperty("TEMPORARY_CITY")
	private String temporary_city;

	@JsonProperty("TEMPORARY_PIN")
	private String temporary_pin;

//	@JsonProperty("TEMPORARY_PHONE")
	@JsonIgnore
	private String temporary_phone;

//	@JsonProperty("OFFICE_EMAIL")
	@JsonIgnore
	private String office_email;

	@JsonProperty("PERMANENT_EMAIL")
	private String permanent_email;

	@JsonProperty("PRESENT_EMAIL")
	private String present_email;

	@JsonProperty("EMERGENCY_EMAIL")
	private String emergency_email;

	@JsonProperty("CONTACT_EMAIL")
	private String contact_email;

	@JsonProperty("POSTAL_EMAIL")
	private String postal_email;

//	@JsonProperty("RESIDENCE_EMAIL")
	@JsonIgnore
	private String residence_email;

	@JsonProperty("PREFERRED_EMAIL")
	private String preferred_email;

	@JsonProperty("TEMPORARY_EMAIL")
	private String temporary_email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNegativeMatchCriteria() {
		return negativeMatchCriteria;
	}

	public void setNegativeMatchCriteria(String negativeMatchCriteria) {
		this.negativeMatchCriteria = negativeMatchCriteria;
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

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getDrivingLicenseNumber() {
		return drivingLicenseNumber;
	}

	public void setDrivingLicenseNumber(String drivingLicenseNumber) {
		this.drivingLicenseNumber = drivingLicenseNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getLanNo() {
		return lanNo;
	}

	public void setLanNo(String lanNo) {
		this.lanNo = lanNo;
	}

	public String getLanNo2() {
		return lanNo2;
	}

	public void setLanNo2(String lanNo2) {
		this.lanNo2 = lanNo2;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
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

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public String getStd() {
		return std;
	}

	public void setStd(String std) {
		this.std = std;
	}

	public String getLandLine1() {
		return landLine1;
	}

	public void setLandLine1(String landLine1) {
		this.landLine1 = landLine1;
	}

	public String getLandLine2() {
		return landLine2;
	}

	public void setLandLine2(String landLine2) {
		this.landLine2 = landLine2;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress1Office() {
		return address1Office;
	}

	public void setAddress1Office(String address1Office) {
		this.address1Office = address1Office;
	}

	public String getAddress2Office() {
		return address2Office;
	}

	public void setAddress2Office(String address2Office) {
		this.address2Office = address2Office;
	}

	public String getAddress3Office() {
		return address3Office;
	}

	public void setAddress3Office(String address3Office) {
		this.address3Office = address3Office;
	}

	public String getAreaOffice() {
		return areaOffice;
	}

	public void setAreaOffice(String areaOffice) {
		this.areaOffice = areaOffice;
	}

	public String getLandMarkOffice() {
		return landMarkOffice;
	}

	public void setLandMarkOffice(String landMarkOffice) {
		this.landMarkOffice = landMarkOffice;
	}

	public String getCityOffice() {
		return cityOffice;
	}

	public void setCityOffice(String cityOffice) {
		this.cityOffice = cityOffice;
	}

	public String getPinOffice() {
		return pinOffice;
	}

	public void setPinOffice(String pinOffice) {
		this.pinOffice = pinOffice;
	}

	public String getStdOffice() {
		return stdOffice;
	}

	public void setStdOffice(String stdOffice) {
		this.stdOffice = stdOffice;
	}

	public String getLandLine1Office() {
		return landLine1Office;
	}

	public void setLandLine1Office(String landLine1Office) {
		this.landLine1Office = landLine1Office;
	}

	public String getLandLine2Office() {
		return landLine2Office;
	}

	public void setLandLine2Office(String landLine2Office) {
		this.landLine2Office = landLine2Office;
	}

	public String getMobileOffice() {
		return mobileOffice;
	}

	public void setMobileOffice(String mobileOffice) {
		this.mobileOffice = mobileOffice;
	}

	public String getEmailOffice() {
		return emailOffice;
	}

	public void setEmailOffice(String emailOffice) {
		this.emailOffice = emailOffice;
	}

	public String getProcessFLG() {
		return processFLG;
	}

	public void setProcessFLG(String processFLG) {
		this.processFLG = processFLG;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getMatchedId() {
		return matchedId;
	}

	public void setMatchedId(String matchedId) {
		this.matchedId = matchedId;
	}

	public String getMatchedPesontage() {
		return matchedPesontage;
	}

	public void setMatchedPesontage(String matchedPesontage) {
		this.matchedPesontage = matchedPesontage;
	}

	public String getNegativeMatchedId() {
		return negativeMatchedId;
	}

	public void setNegativeMatchedId(String negativeMatchedId) {
		this.negativeMatchedId = negativeMatchedId;
	}

	public String getSfMatchedId() {
		return sfMatchedId;
	}

	public void setSfMatchedId(String sfMatchedId) {
		this.sfMatchedId = sfMatchedId;
	}

	public String getSfMatchedPercentage() {
		return sfMatchedPercentage;
	}

	public void setSfMatchedPercentage(String sfMatchedPercentage) {
		this.sfMatchedPercentage = sfMatchedPercentage;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getLoanAppNo() {
		return loanAppNo;
	}

	public void setLoanAppNo(String loanAppNo) {
		this.loanAppNo = loanAppNo;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	public String getStayingSince() {
		return stayingSince;
	}

	public void setStayingSince(String stayingSince) {
		this.stayingSince = stayingSince;
	}

	public String getCityClassification() {
		return cityClassification;
	}

	public void setCityClassification(String cityClassification) {
		this.cityClassification = cityClassification;
	}

	public String getEmploymentBusiness() {
		return employmentBusiness;
	}

	public void setEmploymentBusiness(String employmentBusiness) {
		this.employmentBusiness = employmentBusiness;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getResidentType() {
		return residentType;
	}

	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}

	public String getCreditProgram() {
		return creditProgram;
	}

	public void setCreditProgram(String creditProgram) {
		this.creditProgram = creditProgram;
	}

	public String getAssetCategory() {
		return assetCategory;
	}

	public void setAssetCategory(String assetCategory) {
		this.assetCategory = assetCategory;
	}

	public String getRejMatchedId() {
		return rejMatchedId;
	}

	public void setRejMatchedId(String rejMatchedId) {
		this.rejMatchedId = rejMatchedId;
	}

	public String getRejMatchedPrcntg() {
		return rejMatchedPrcntg;
	}

	public void setRejMatchedPrcntg(String rejMatchedPrcntg) {
		this.rejMatchedPrcntg = rejMatchedPrcntg;
	}

	public String getFraudMatchedId() {
		return fraudMatchedId;
	}

	public void setFraudMatchedId(String fraudMatchedId) {
		this.fraudMatchedId = fraudMatchedId;
	}

	public String getFraudMatchedPrcntg() {
		return fraudMatchedPrcntg;
	}

	public void setFraudMatchedPrcntg(String fraudMatchedPrcntg) {
		this.fraudMatchedPrcntg = fraudMatchedPrcntg;
	}

	public String getTerrMatchedId() {
		return terrMatchedId;
	}

	public void setTerrMatchedId(String terrMatchedId) {
		this.terrMatchedId = terrMatchedId;
	}

	public String getRejReason() {
		return rejReason;
	}

	public void setRejReason(String rejReason) {
		this.rejReason = rejReason;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getOrg() {
		return org;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getApplnNo() {
		return applnNo;
	}

	public void setApplnNo(String applnNo) {
		this.applnNo = applnNo;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getCustSrNo() {
		return custSrNo;
	}

	public void setCustSrNo(String custSrNo) {
		this.custSrNo = custSrNo;
	}

	public String getApplicationType() {
		return applicationType;
	}

	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
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

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
	}

	public String getOffice_address() {
		return office_address;
	}

	public void setOffice_address(String office_address) {
		this.office_address = office_address;
	}

	public String getOffice_city() {
		return office_city;
	}

	public void setOffice_city(String office_city) {
		this.office_city = office_city;
	}

	public String getOffice_pin() {
		return office_pin;
	}

	public void setOffice_pin(String office_pin) {
		this.office_pin = office_pin;
	}

	public String getPermanent_address() {
		return permanent_address;
	}

	public void setPermanent_address(String permanent_address) {
		this.permanent_address = permanent_address;
	}

	public String getPermanent_city() {
		return permanent_city;
	}

	public void setPermanent_city(String permanent_city) {
		this.permanent_city = permanent_city;
	}

	public String getPermanent_pin() {
		return permanent_pin;
	}

	public void setPermanent_pin(String permanent_pin) {
		this.permanent_pin = permanent_pin;
	}

	public String getPresent_address() {
		return present_address;
	}

	public void setPresent_address(String present_address) {
		this.present_address = present_address;
	}

	public String getPresent_city() {
		return present_city;
	}

	public void setPresent_city(String present_city) {
		this.present_city = present_city;
	}

	public String getPresent_pin() {
		return present_pin;
	}

	public void setPresent_pin(String present_pin) {
		this.present_pin = present_pin;
	}

	public String getEmergency_address() {
		return emergency_address;
	}

	public void setEmergency_address(String emergency_address) {
		this.emergency_address = emergency_address;
	}

	public String getEmergency_city() {
		return emergency_city;
	}

	public void setEmergency_city(String emergency_city) {
		this.emergency_city = emergency_city;
	}

	public String getEmergency_pin() {
		return emergency_pin;
	}

	public void setEmergency_pin(String emergency_pin) {
		this.emergency_pin = emergency_pin;
	}

	public String getContact_address() {
		return contact_address;
	}

	public void setContact_address(String contact_address) {
		this.contact_address = contact_address;
	}

	public String getContact_city() {
		return contact_city;
	}

	public void setContact_city(String contact_city) {
		this.contact_city = contact_city;
	}

	public String getContact_pin() {
		return contact_pin;
	}

	public void setContact_pin(String contact_pin) {
		this.contact_pin = contact_pin;
	}

	public String getPostal_address() {
		return postal_address;
	}

	public void setPostal_address(String postal_address) {
		this.postal_address = postal_address;
	}

	public String getPostal_city() {
		return postal_city;
	}

	public void setPostal_city(String postal_city) {
		this.postal_city = postal_city;
	}

	public String getPostal_pin() {
		return postal_pin;
	}

	public void setPostal_pin(String postal_pin) {
		this.postal_pin = postal_pin;
	}

	public String getResidence_address() {
		return residence_address;
	}

	public void setResidence_address(String residence_address) {
		this.residence_address = residence_address;
	}

	public String getResidence_city() {
		return residence_city;
	}

	public void setResidence_city(String residence_city) {
		this.residence_city = residence_city;
	}

	public String getResidence_pin() {
		return residence_pin;
	}

	public void setResidence_pin(String residence_pin) {
		this.residence_pin = residence_pin;
	}

	public String getPreferred_address() {
		return preferred_address;
	}

	public void setPreferred_address(String preferred_address) {
		this.preferred_address = preferred_address;
	}

	public String getPreferred_city() {
		return preferred_city;
	}

	public void setPreferred_city(String preferred_city) {
		this.preferred_city = preferred_city;
	}

	public String getPreferred_pin() {
		return preferred_pin;
	}

	public void setPreferred_pin(String preferred_pin) {
		this.preferred_pin = preferred_pin;
	}

	public String getTemporary_address() {
		return temporary_address;
	}

	public void setTemporary_address(String temporary_address) {
		this.temporary_address = temporary_address;
	}

	public String getTemporary_city() {
		return temporary_city;
	}

	public void setTemporary_city(String temporary_city) {
		this.temporary_city = temporary_city;
	}

	public String getTemporary_pin() {
		return temporary_pin;
	}

	public void setTemporary_pin(String temporary_pin) {
		this.temporary_pin = temporary_pin;
	}

	public String getOffice_email() {
		return office_email;
	}

	public void setOffice_email(String office_email) {
		this.office_email = office_email;
	}

	public String getPermanent_email() {
		return permanent_email;
	}

	public void setPermanent_email(String permanent_email) {
		this.permanent_email = permanent_email;
	}

	public String getPresent_email() {
		return present_email;
	}

	public void setPresent_email(String present_email) {
		this.present_email = present_email;
	}

	public String getEmergency_email() {
		return emergency_email;
	}

	public void setEmergency_email(String emergency_email) {
		this.emergency_email = emergency_email;
	}

	public String getContact_email() {
		return contact_email;
	}

	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}

	public String getPostal_email() {
		return postal_email;
	}

	public void setPostal_email(String postal_email) {
		this.postal_email = postal_email;
	}

	public String getResidence_email() {
		return residence_email;
	}

	public void setResidence_email(String residence_email) {
		this.residence_email = residence_email;
	}

	public String getPreferred_email() {
		return preferred_email;
	}

	public void setPreferred_email(String preferred_email) {
		this.preferred_email = preferred_email;
	}

	public String getTemporary_email() {
		return temporary_email;
	}

	public void setTemporary_email(String temporary_email) {
		this.temporary_email = temporary_email;
	}

	public String getOffice_phone() {
		return office_phone;
	}

	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}

	public String getPermanent_phone() {
		return permanent_phone;
	}

	public void setPermanent_phone(String permanent_phone) {
		this.permanent_phone = permanent_phone;
	}

	public String getPresent_phone() {
		return present_phone;
	}

	public void setPresent_phone(String present_phone) {
		this.present_phone = present_phone;
	}

	public String getEmergency_phone() {
		return emergency_phone;
	}

	public void setEmergency_phone(String emergency_phone) {
		this.emergency_phone = emergency_phone;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getPostal_phone() {
		return postal_phone;
	}

	public void setPostal_phone(String postal_phone) {
		this.postal_phone = postal_phone;
	}

	public String getResidence_phone() {
		return residence_phone;
	}

	public void setResidence_phone(String residence_phone) {
		this.residence_phone = residence_phone;
	}

	public String getPreferred_phone() {
		return preferred_phone;
	}

	public void setPreferred_phone(String preferred_phone) {
		this.preferred_phone = preferred_phone;
	}

	public String getTemporary_phone() {
		return temporary_phone;
	}

	public void setTemporary_phone(String temporary_phone) {
		this.temporary_phone = temporary_phone;
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

	public String getCrnNo() {
		return crnNo;
	}

	public void setCrnNo(String crnNo) {
		this.crnNo = crnNo;
	}

	public String getCkyCno() {
		return ckyCno;
	}

	public void setCkyCno(String ckyCno) {
		this.ckyCno = ckyCno;
	}

	public Date getLchgTime() {
		return lchgTime;
	}

	public void setLchgTime(Date lchgTime) {
		this.lchgTime = lchgTime;
	}

	public String getOffice_area() {
		return office_area;
	}

	public void setOffice_area(String office_area) {
		this.office_area = office_area;
	}

	@Override
	public String toString() {
		return "CustomerRequestDetails [applnNo=" + applnNo + ", segment=" + segment + ", aadhaar=" + aadhaar
				+ ", gender=" + gender + ", custSrNo=" + custSrNo + ", applicationType=" + applicationType + ", name="
				+ name + ", lchgTime=" + lchgTime + ", motherName=" + motherName + ", firstName=" + firstName
				+ ", middleName=" + middleName + ", lastName=" + lastName + ", fatherName=" + fatherName
				+ ", spouseName=" + spouseName + ", employerName=" + employerName + ", dob1=" + dob1 + ", dob2=" + dob2
				+ ", dob3=" + dob3 + ", dob4=" + dob4 + ", pan=" + pan + ", drivingLicenseNumber="
				+ drivingLicenseNumber + ", accountNumber=" + accountNumber + ", voterId=" + voterId + ", cin=" + cin
				+ ", crnNo=" + crnNo + ", ckyCno=" + ckyCno + ", din=" + din + ", registrationNo=" + registrationNo
				+ ", creditCardNumber=" + creditCardNumber + ", customerNo=" + customerNo + ", lanNo=" + lanNo
				+ ", lanNo2=" + lanNo2 + ", customerType=" + customerType + ", dateOfInCorporation="
				+ dateOfInCorporation + ", tanNo=" + tanNo + ", product=" + product + ", passportNo=" + passportNo
				+ ", address1=" + address1 + ", address2=" + address2 + ", address3=" + address3 + ", area=" + area
				+ ", landMark=" + landMark + ", city=" + city + ", pin=" + pin + ", std=" + std + ", landLine1="
				+ landLine1 + ", landLine2=" + landLine2 + ", mobile=" + mobile + ", phone=" + phone + ", email="
				+ email + ", address=" + address + ", address1Office=" + address1Office + ", address2Office="
				+ address2Office + ", address3Office=" + address3Office + ", areaOffice=" + areaOffice
				+ ", landMarkOffice=" + landMarkOffice + ", cityOffice=" + cityOffice + ", pinOffice=" + pinOffice
				+ ", stdOffice=" + stdOffice + ", landLine1Office=" + landLine1Office + ", landLine2Office="
				+ landLine2Office + ", mobileOffice=" + mobileOffice + ", emailOffice=" + emailOffice + ", processFLG="
				+ processFLG + ", customerStatus=" + customerStatus + ", matchedId=" + matchedId + ", matchedPesontage="
				+ matchedPesontage + ", negativeMatchedId=" + negativeMatchedId + ", sfMatchedId=" + sfMatchedId
				+ ", sfMatchedPercentage=" + sfMatchedPercentage + ", batch=" + batch + ", loanAppNo=" + loanAppNo
				+ ", matchCount=" + matchCount + ", stayingSince=" + stayingSince + ", cityClassification="
				+ cityClassification + ", employmentBusiness=" + employmentBusiness + ", age=" + age + ", residentType="
				+ residentType + ", creditProgram=" + creditProgram + ", assetCategory=" + assetCategory
				+ ", rejMatchedId=" + rejMatchedId + ", rejMatchedPrcntg=" + rejMatchedPrcntg + ", fraudMatchedId="
				+ fraudMatchedId + ", fraudMatchedPrcntg=" + fraudMatchedPrcntg + ", terrMatchedId=" + terrMatchedId
				+ ", rejReason=" + rejReason + ", productCode=" + productCode + ", errorDesc=" + errorDesc + ", org="
				+ org + ", negativeMatchCriteria=" + negativeMatchCriteria + ", office_address=" + office_address
				+ ", office_area=" + office_area + ", office_city=" + office_city + ", office_pin=" + office_pin
				+ ", office_phone=" + office_phone + ", permanent_address=" + permanent_address + ", permanent_city="
				+ permanent_city + ", permanent_pin=" + permanent_pin + ", permanent_phone=" + permanent_phone
				+ ", present_address=" + present_address + ", present_city=" + present_city + ", present_pin="
				+ present_pin + ", present_phone=" + present_phone + ", emergency_address=" + emergency_address
				+ ", emergency_city=" + emergency_city + ", emergency_pin=" + emergency_pin + ", emergency_phone="
				+ emergency_phone + ", contact_address=" + contact_address + ", contact_city=" + contact_city
				+ ", contact_pin=" + contact_pin + ", contact_phone=" + contact_phone + ", postal_address="
				+ postal_address + ", postal_city=" + postal_city + ", postal_pin=" + postal_pin + ", postal_phone="
				+ postal_phone + ", residence_address=" + residence_address + ", residence_city=" + residence_city
				+ ", residence_pin=" + residence_pin + ", residence_phone=" + residence_phone + ", preferred_address="
				+ preferred_address + ", preferred_city=" + preferred_city + ", preferred_pin=" + preferred_pin
				+ ", preferred_phone=" + preferred_phone + ", temporary_address=" + temporary_address
				+ ", temporary_city=" + temporary_city + ", temporary_pin=" + temporary_pin + ", temporary_phone="
				+ temporary_phone + ", office_email=" + office_email + ", permanent_email=" + permanent_email
				+ ", present_email=" + present_email + ", emergency_email=" + emergency_email + ", contact_email="
				+ contact_email + ", postal_email=" + postal_email + ", residence_email=" + residence_email
				+ ", preferred_email=" + preferred_email + ", temporary_email=" + temporary_email + "]";
	}

}
