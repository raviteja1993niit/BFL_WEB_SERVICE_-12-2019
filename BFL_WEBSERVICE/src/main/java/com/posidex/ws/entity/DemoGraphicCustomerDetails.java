package com.posidex.ws.entity;

import java.io.Serializable;
import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "account_number", "aadhaar", "applicant_type", "area",
		"batchid", "ca_number", "cin", "ckycno", "contact_address",
		"contact_city", "contact_country", "contact_email", "contact_phone",
		"contact_pin", "contact_state", "contact_std_code", "corporatelicence",
		"country", "country_of_origin", "credit_card_number", "crnno",
		"cust_unq_id", "customer_contact_type", "customer_id", "customer_no",
		"date_of_incorporation", "din", "dob1", "dob2", "dob3", "dob4", "doi",
		"drivinglic", "emergency_address", "emergency_city", "emergency_email",
		"emergency_phone", "emergency_pin", "emergency_std_code",
		"employer_name", "eventtype", "father_name", "filler_date_1",
		"filler_date_2", "filler_number_1", "filler_number_2",
		"filler_number_3", "filler_number_4", "filler_number_5",
		"filler_string_1", "filler_string_2", "filler_string_3",
		"filler_string_4", "filler_string_5", "filler_string_6",
		"filler_string_7", "filler_string_8", "filler_string_9", "firstname",
		"gender", "gstin", "insert_time", "landmark", "lastname", "lchgtime",
		"middlename", "mobile", "mother_name", "name", "office_address",
		"office_city", "office_country", "office_email", "office_phone",
		"office_pin", "office_state", "office_std_code",
		"office_street_number", "old_psx_batch_id", "pan", "passport",
		"permanent_address", "permanent_city", "permanent_country",
		"permanent_email", "permanent_phone", "permanent_pin",
		"permanent_state", "permanent_std_code", "permanent_street_number",
		"policy_number", "postal_address", "postal_city", "postal_country",
		"postal_email", "postal_phone", "postal_pin", "postal_std_code",
		"preferred_address", "preferred_city", "preferred_country",
		"preferred_email", "preferred_phone", "preferred_pin",
		"preferred_state", "preferred_std_code", "present_address",
		"present_city", "present_country", "present_email", "present_phone",
		"present_pin", "present_state", "present_std_code", "process_flag",
		"process_type", "rationcard_no", "registration_no",
		"residence_address", "residence_city", "residence_country",
		"residence_email", "residence_phone", "residence_pin",
		"residence_state", "residence_std_code", "residence_street_number",
		"segment", "source_sys_id", "source_system", "spouse_name", "tan_no",
		"temp_street_number", "temporary_address", "temporary_city",
		"temporary_country", "temporary_email", "temporary_phone",
		"temporary_pin", "temporary_state", "temporary_std_code", "timestamp1",
		"timestamp2", "ucin_flag", "voterid", })
public class DemoGraphicCustomerDetails implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2508749414706545196L;
	
	@JsonProperty("ACCOUNT_NUMBER")
	private String account_number;
	@JsonProperty("AADHAAR")
	private String aadhaar;
	@JsonProperty("APPLICANT_TYPE")
	private String applicant_type;
	@JsonProperty("AREA")
	private String area;
	@JsonProperty("BATCHID")
	private String batchid;
	@JsonProperty("CA_NUMBER")
	private String ca_number;
	@JsonProperty("CIN")
	private String cin;
	@JsonProperty("CKYCNO")
	private String ckycno;
	@JsonProperty("CONTACT_ADDRESS")
	private String contact_address;
	@JsonProperty("CONTACT_CITY")
	private String contact_city;
	@JsonProperty("CONTACT_COUNTRY")
	private String contact_country;
	@JsonProperty("CONTACT_EMAIL")
	private String contact_email;
	@JsonProperty("CONTACT_PHONE")
	private String contact_phone;
	@JsonProperty("CONTACT_PIN")
	private String contact_pin;
	@JsonProperty("CONTACT_STATE")
	private String contact_state;
	@JsonProperty("CONTACT_STD_CODE")
	private String contact_std_code;
	@JsonProperty("CORPORATELICENCE")
	private String corporatelicence;
	@JsonProperty("COUNTRY")
	private String country;
	@JsonProperty("COUNTRY_OF_ORIGIN")
	private String country_of_origin;
	@JsonProperty("CREDIT_CARD_NUMBER")
	private String credit_card_number;
	@JsonProperty("CRNNO")
	private String crnno;
	@JsonProperty("CUST_UNQ_ID")
	private String cust_unq_id;
	@JsonProperty("CUSTOMER_CONTACT_TYPE")
	private String customer_contact_type;
	@JsonProperty("CUSTOMER_ID")
	private String customer_id;
	@JsonProperty("CUSTOMER_NO")
	private long customer_no;
	@JsonProperty("DATE_OF_INCORPORATION")
	private String date_of_incorporation;
	@JsonProperty("DIN")
	private String din;
	@JsonProperty("DOB1")
	private String dob1;
	@JsonProperty("DOB2")
	private String dob2;
	@JsonProperty("DOB3")
	private String dob3;
	@JsonProperty("DOB4")
	private String dob4;
	@JsonProperty("DOI")
	private String doi;
	@JsonProperty("DRIVINGLIC")
	private String drivinglic;
	@JsonProperty("EMERGENCY_ADDRESS")
	private String emergency_address;
	@JsonProperty("EMERGENCY_CITY")
	private String emergency_city;
	@JsonProperty("EMERGENCY_EMAIL")
	private String emergency_email;
	@JsonProperty("EMERGENCY_PHONE")
	private String emergency_phone;
	@JsonProperty("EMERGENCY_PIN")
	private String emergency_pin;
	@JsonProperty("EMERGENCY_STD_CODE")
	private String emergency_std_code;
	@JsonProperty("EMPLOYER_NAME")
	private String employer_name;
	@JsonProperty("EVENTTYPE")
	private String eventtype;
	@JsonProperty("FATHER_NAME")
	private String father_name;
	@JsonProperty("FILLER_DATE_1")
	private Date filler_date_1;
	@JsonProperty("FILLER_DATE_2")
	private Date filler_date_2;
	@JsonProperty("FILLER_NUMBER_1")
	private long filler_number_1;
	@JsonProperty("FILLER_NUMBER_2")
	private long filler_number_2;
	@JsonProperty("FILLER_NUMBER_3")
	private long filler_number_3;
	@JsonProperty("FILLER_NUMBER_4")
	private long filler_number_4;
	@JsonProperty("FILLER_NUMBER_5")
	private long filler_number_5;
	@JsonProperty("FILLER_STRING_1")
	private String filler_string_1;
	@JsonProperty("FILLER_STRING_2")
	private String filler_string_2;
	@JsonProperty("FILLER_STRING_3")
	private String filler_string_3;
	@JsonProperty("FILLER_STRING_4")
	private String filler_string_4;
	@JsonProperty("FILLER_STRING_5")
	private String filler_string_5;
	@JsonProperty("FILLER_STRING_6")
	private String filler_string_6;
	@JsonProperty("FILLER_STRING_7")
	private String filler_string_7;
	@JsonProperty("FILLER_STRING_8")
	private String filler_string_8;
	@JsonProperty("FILLER_STRING_9")
	private String filler_string_9;
	@JsonProperty("FIRSTNAME")
	private String firstname;
	@JsonProperty("GENDER")
	private String gender;
	@JsonProperty("GSTIN")
	private String gstin;
	@JsonProperty("INSERT_TIME")
	private String insert_time;
	@JsonProperty("LANDMARK")
	private String landmark;
	@JsonProperty("LASTNAME")
	private String lastname;
	@JsonProperty("LCHGTIME")
	private String lchgtime;
	@JsonProperty("MIDDLENAME")
	private String middlename;
	@JsonProperty("MOBILE")
	private String mobile;
	@JsonProperty("MOTHER_NAME")
	private String mother_name;
	@JsonProperty("NAME")
	private String name;
	@JsonProperty("OFFICE_ADDRESS")
	private String office_address;
	@JsonProperty("OFFICE_CITY")
	private String office_city;
	@JsonProperty("OFFICE_COUNTRY")
	private String office_country;
	@JsonProperty("OFFICE_EMAIL")
	private String office_email;
	@JsonProperty("OFFICE_PHONE")
	private String office_phone;
	@JsonProperty("OFFICE_PIN")
	private String office_pin;
	@JsonProperty("OFFICE_STATE")
	private String office_state;
	@JsonProperty("OFFICE_STD_CODE")
	private String office_std_code;
	@JsonProperty("OFFICE_STREET_NUMBER")
	private String office_street_number;
	@JsonProperty("OLD_PSX_BATCH_ID")
	private String old_psx_batch_id;
	@JsonProperty("PAN")
	private String pan;
	@JsonProperty("PASSPORT")
	private String passport;
	@JsonProperty("PERMANENT_ADDRESS")
	private String permanent_address;
	@JsonProperty("PERMANENT_CITY")
	private String permanent_city;
	@JsonProperty("PERMANENT_COUNTRY")
	private String permanent_country;
	@JsonProperty("PERMANENT_EMAIL")
	private String permanent_email;
	@JsonProperty("PERMANENT_PHONE")
	private String permanent_phone;
	@JsonProperty("PERMANENT_PIN")
	private String permanent_pin;
	@JsonProperty("PERMANENT_STATE")
	private String permanent_state;
	@JsonProperty("PERMANENT_STD_CODE")
	private String permanent_std_code;
	@JsonProperty("PERMANENT_STREET_NUMBER")
	private String permanent_street_number;
	@JsonProperty("POLICY_NUMBER")
	private String policy_number;
	@JsonProperty("POSTAL_ADDRESS")
	private String postal_address;
	@JsonProperty("POSTAL_CITY")
	private String postal_city;
	@JsonProperty("POSTAL_COUNTRY")
	private String postal_country;
	@JsonProperty("POSTAL_EMAIL")
	private String postal_email;
	@JsonProperty("POSTAL_PHONE")
	private String postal_phone;
	@JsonProperty("POSTAL_PIN")
	private String postal_pin;
	@JsonProperty("POSTAL_STD_CODE")
	private String postal_std_code;
	@JsonProperty("PREFERRED_ADDRESS")
	private String preferred_address;
	@JsonProperty("PREFERRED_CITY")
	private String preferred_city;
	@JsonProperty("PREFERRED_COUNTRY")
	private String preferred_country;
	@JsonProperty("PREFERRED_EMAIL")
	private String preferred_email;
	@JsonProperty("PREFERRED_PHONE")
	private String preferred_phone;
	@JsonProperty("PREFERRED_PIN")
	private String preferred_pin;
	@JsonProperty("PREFERRED_STATE")
	private String preferred_state;
	@JsonProperty("PREFERRED_STD_CODE")
	private String preferred_std_code;
	@JsonProperty("PRESENT_ADDRESS")
	private String present_address;
	@JsonProperty("PRESENT_CITY")
	private String present_city;
	@JsonProperty("PRESENT_COUNTRY")
	private String present_country;
	@JsonProperty("PRESENT_EMAIL")
	private String present_email;
	@JsonProperty("PRESENT_PHONE")
	private String present_phone;
	@JsonProperty("PRESENT_PIN")
	private String present_pin;
	@JsonProperty("PRESENT_STATE")
	private String present_state;
	@JsonProperty("PRESENT_STD_CODE")
	private String present_std_code;
	@JsonProperty("PROCESS_FLAG")
	private String process_flag;
	@JsonProperty("PROCESS_TYPE")
	private String process_type;
	@JsonProperty("RATIONCARD_NO")
	private String rationcard_no;
	@JsonProperty("REGISTRATION_NO")
	private String registration_no;
	@JsonProperty("RESIDENCE_ADDRESS")
	private String residence_address;
	@JsonProperty("RESIDENCE_CITY")
	private String residence_city;
	@JsonProperty("RESIDENCE_COUNTRY")
	private String residence_country;
	@JsonProperty("RESIDENCE_EMAIL")
	private String residence_email;
	@JsonProperty("RESIDENCE_PHONE")
	private String residence_phone;
	@JsonProperty("RESIDENCE_PIN")
	private String residence_pin;
	@JsonProperty("RESIDENCE_STATE")
	private String residence_state;
	@JsonProperty("RESIDENCE_STD_CODE")
	private String residence_std_code;
	@JsonProperty("RESIDENCE_STREET_NUMBER")
	private String residence_street_number;
	@JsonProperty("SEGMENT")
	private String segment;
	@JsonProperty("SOURCE_SYS_ID")
	private String source_sys_id;
	@JsonProperty("SOURCE_SYSTEM")
	private String source_system;
	@JsonProperty("SPOUSE_NAME")
	private String spouse_name;
	@JsonProperty("TAN_NO")
	private String tan_no;
	@JsonProperty("TEMP_STREET_NUMBER")
	private String temp_street_number;
	@JsonProperty("TEMPORARY_ADDRESS")
	private String temporary_address;
	@JsonProperty("TEMPORARY_CITY")
	private String temporary_city;
	@JsonProperty("TEMPORARY_COUNTRY")
	private String temporary_country;
	@JsonProperty("TEMPORARY_EMAIL")
	private String temporary_email;
	@JsonProperty("TEMPORARY_PHONE")
	private String temporary_phone;
	@JsonProperty("TEMPORARY_PIN")
	private String temporary_pin;
	@JsonProperty("TEMPORARY_STATE")
	private String temporary_state;
	@JsonProperty("TEMPORARY_STD_CODE")
	private String temporary_std_code;
	@JsonProperty("TIMESTAMP1")
	private String timestamp1;
	@JsonProperty("TIMESTAMP2")
	private String timestamp2;
	@JsonProperty("UCIN_FLAG")
	private String ucin_flag;
	@JsonProperty("VOTERID")
	private String voterid;
	public String getAccount_number() {
		return account_number;
	}
	public void setAccount_number(String account_number) {
		this.account_number = account_number;
	}
	public String getAadhaar() {
		return aadhaar;
	}
	public void setAadhaar(String aadhaar) {
		this.aadhaar = aadhaar;
	}
	public String getApplicant_type() {
		return applicant_type;
	}
	public void setApplicant_type(String applicant_type) {
		this.applicant_type = applicant_type;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getBatchid() {
		return batchid;
	}
	public void setBatchid(String batchid) {
		this.batchid = batchid;
	}
	public String getCa_number() {
		return ca_number;
	}
	public void setCa_number(String ca_number) {
		this.ca_number = ca_number;
	}
	public String getCin() {
		return cin;
	}
	public void setCin(String cin) {
		this.cin = cin;
	}
	public String getCkycno() {
		return ckycno;
	}
	public void setCkycno(String ckycno) {
		this.ckycno = ckycno;
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
	public String getContact_country() {
		return contact_country;
	}
	public void setContact_country(String contact_country) {
		this.contact_country = contact_country;
	}
	public String getContact_email() {
		return contact_email;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public String getContact_phone() {
		return contact_phone;
	}
	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}
	public String getContact_pin() {
		return contact_pin;
	}
	public void setContact_pin(String contact_pin) {
		this.contact_pin = contact_pin;
	}
	public String getContact_state() {
		return contact_state;
	}
	public void setContact_state(String contact_state) {
		this.contact_state = contact_state;
	}
	public String getContact_std_code() {
		return contact_std_code;
	}
	public void setContact_std_code(String contact_std_code) {
		this.contact_std_code = contact_std_code;
	}
	public String getCorporatelicence() {
		return corporatelicence;
	}
	public void setCorporatelicence(String corporatelicence) {
		this.corporatelicence = corporatelicence;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountry_of_origin() {
		return country_of_origin;
	}
	public void setCountry_of_origin(String country_of_origin) {
		this.country_of_origin = country_of_origin;
	}
	public String getCredit_card_number() {
		return credit_card_number;
	}
	public void setCredit_card_number(String credit_card_number) {
		this.credit_card_number = credit_card_number;
	}
	public String getCrnno() {
		return crnno;
	}
	public void setCrnno(String crnno) {
		this.crnno = crnno;
	}
	public String getCust_unq_id() {
		return cust_unq_id;
	}
	public void setCust_unq_id(String cust_unq_id) {
		this.cust_unq_id = cust_unq_id;
	}
	public String getCustomer_contact_type() {
		return customer_contact_type;
	}
	public void setCustomer_contact_type(String customer_contact_type) {
		this.customer_contact_type = customer_contact_type;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public long getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(long customer_no) {
		this.customer_no = customer_no;
	}
	public String getDate_of_incorporation() {
		return date_of_incorporation;
	}
	public void setDate_of_incorporation(String date_of_incorporation) {
		this.date_of_incorporation = date_of_incorporation;
	}
	public String getDin() {
		return din;
	}
	public void setDin(String din) {
		this.din = din;
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
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getDrivinglic() {
		return drivinglic;
	}
	public void setDrivinglic(String drivinglic) {
		this.drivinglic = drivinglic;
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
	public String getEmergency_email() {
		return emergency_email;
	}
	public void setEmergency_email(String emergency_email) {
		this.emergency_email = emergency_email;
	}
	public String getEmergency_phone() {
		return emergency_phone;
	}
	public void setEmergency_phone(String emergency_phone) {
		this.emergency_phone = emergency_phone;
	}
	public String getEmergency_pin() {
		return emergency_pin;
	}
	public void setEmergency_pin(String emergency_pin) {
		this.emergency_pin = emergency_pin;
	}
	public String getEmergency_std_code() {
		return emergency_std_code;
	}
	public void setEmergency_std_code(String emergency_std_code) {
		this.emergency_std_code = emergency_std_code;
	}
	public String getEmployer_name() {
		return employer_name;
	}
	public void setEmployer_name(String employer_name) {
		this.employer_name = employer_name;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public String getFather_name() {
		return father_name;
	}
	public void setFather_name(String father_name) {
		this.father_name = father_name;
	}
	public Date getFiller_date_1() {
		return filler_date_1;
	}
	public void setFiller_date_1(Date filler_date_1) {
		this.filler_date_1 = filler_date_1;
	}
	public Date getFiller_date_2() {
		return filler_date_2;
	}
	public void setFiller_date_2(Date filler_date_2) {
		this.filler_date_2 = filler_date_2;
	}
	public long getFiller_number_1() {
		return filler_number_1;
	}
	public void setFiller_number_1(long i) {
		this.filler_number_1 = i;
	}
	public long getFiller_number_2() {
		return filler_number_2;
	}
	public void setFiller_number_2(long filler_number_2) {
		this.filler_number_2 = filler_number_2;
	}
	public long getFiller_number_3() {
		return filler_number_3;
	}
	public void setFiller_number_3(long filler_number_3) {
		this.filler_number_3 = filler_number_3;
	}
	public long getFiller_number_4() {
		return filler_number_4;
	}
	public void setFiller_number_4(long filler_number_4) {
		this.filler_number_4 = filler_number_4;
	}
	public long getFiller_number_5() {
		return filler_number_5;
	}
	public void setFiller_number_5(long filler_number_5) {
		this.filler_number_5 = filler_number_5;
	}
	public String getFiller_string_1() {
		return filler_string_1;
	}
	public void setFiller_string_1(String filler_string_1) {
		this.filler_string_1 = filler_string_1;
	}
	public String getFiller_string_2() {
		return filler_string_2;
	}
	public void setFiller_string_2(String filler_string_2) {
		this.filler_string_2 = filler_string_2;
	}
	public String getFiller_string_3() {
		return filler_string_3;
	}
	public void setFiller_string_3(String filler_string_3) {
		this.filler_string_3 = filler_string_3;
	}
	public String getFiller_string_4() {
		return filler_string_4;
	}
	public void setFiller_string_4(String filler_string_4) {
		this.filler_string_4 = filler_string_4;
	}
	public String getFiller_string_5() {
		return filler_string_5;
	}
	public void setFiller_string_5(String filler_string_5) {
		this.filler_string_5 = filler_string_5;
	}
	public String getFiller_string_6() {
		return filler_string_6;
	}
	public void setFiller_string_6(String filler_string_6) {
		this.filler_string_6 = filler_string_6;
	}
	public String getFiller_string_7() {
		return filler_string_7;
	}
	public void setFiller_string_7(String filler_string_7) {
		this.filler_string_7 = filler_string_7;
	}
	public String getFiller_string_8() {
		return filler_string_8;
	}
	public void setFiller_string_8(String filler_string_8) {
		this.filler_string_8 = filler_string_8;
	}
	public String getFiller_string_9() {
		return filler_string_9;
	}
	public void setFiller_string_9(String filler_string_9) {
		this.filler_string_9 = filler_string_9;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getGstin() {
		return gstin;
	}
	public void setGstin(String gstin) {
		this.gstin = gstin;
	}
	public String getInsert_time() {
		return insert_time;
	}
	public void setInsert_time(String insert_time) {
		this.insert_time = insert_time;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLchgtime() {
		return lchgtime;
	}
	public void setLchgtime(String lchgtime) {
		this.lchgtime = lchgtime;
	}
	public String getMiddlename() {
		return middlename;
	}
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getMother_name() {
		return mother_name;
	}
	public void setMother_name(String mother_name) {
		this.mother_name = mother_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getOffice_country() {
		return office_country;
	}
	public void setOffice_country(String office_country) {
		this.office_country = office_country;
	}
	public String getOffice_email() {
		return office_email;
	}
	public void setOffice_email(String office_email) {
		this.office_email = office_email;
	}
	public String getOffice_phone() {
		return office_phone;
	}
	public void setOffice_phone(String office_phone) {
		this.office_phone = office_phone;
	}
	public String getOffice_pin() {
		return office_pin;
	}
	public void setOffice_pin(String office_pin) {
		this.office_pin = office_pin;
	}
	public String getOffice_state() {
		return office_state;
	}
	public void setOffice_state(String office_state) {
		this.office_state = office_state;
	}
	public String getOffice_std_code() {
		return office_std_code;
	}
	public void setOffice_std_code(String office_std_code) {
		this.office_std_code = office_std_code;
	}
	public String getOffice_street_number() {
		return office_street_number;
	}
	public void setOffice_street_number(String office_street_number) {
		this.office_street_number = office_street_number;
	}
	public String getOld_psx_batch_id() {
		return old_psx_batch_id;
	}
	public void setOld_psx_batch_id(String old_psx_batch_id) {
		this.old_psx_batch_id = old_psx_batch_id;
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
	public String getPermanent_country() {
		return permanent_country;
	}
	public void setPermanent_country(String permanent_country) {
		this.permanent_country = permanent_country;
	}
	public String getPermanent_email() {
		return permanent_email;
	}
	public void setPermanent_email(String permanent_email) {
		this.permanent_email = permanent_email;
	}
	public String getPermanent_phone() {
		return permanent_phone;
	}
	public void setPermanent_phone(String permanent_phone) {
		this.permanent_phone = permanent_phone;
	}
	public String getPermanent_pin() {
		return permanent_pin;
	}
	public void setPermanent_pin(String permanent_pin) {
		this.permanent_pin = permanent_pin;
	}
	public String getPermanent_state() {
		return permanent_state;
	}
	public void setPermanent_state(String permanent_state) {
		this.permanent_state = permanent_state;
	}
	public String getPermanent_std_code() {
		return permanent_std_code;
	}
	public void setPermanent_std_code(String permanent_std_code) {
		this.permanent_std_code = permanent_std_code;
	}
	public String getPermanent_street_number() {
		return permanent_street_number;
	}
	public void setPermanent_street_number(String permanent_street_number) {
		this.permanent_street_number = permanent_street_number;
	}
	public String getPolicy_number() {
		return policy_number;
	}
	public void setPolicy_number(String policy_number) {
		this.policy_number = policy_number;
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
	public String getPostal_country() {
		return postal_country;
	}
	public void setPostal_country(String postal_country) {
		this.postal_country = postal_country;
	}
	public String getPostal_email() {
		return postal_email;
	}
	public void setPostal_email(String postal_email) {
		this.postal_email = postal_email;
	}
	public String getPostal_phone() {
		return postal_phone;
	}
	public void setPostal_phone(String postal_phone) {
		this.postal_phone = postal_phone;
	}
	public String getPostal_pin() {
		return postal_pin;
	}
	public void setPostal_pin(String postal_pin) {
		this.postal_pin = postal_pin;
	}
	public String getPostal_std_code() {
		return postal_std_code;
	}
	public void setPostal_std_code(String postal_std_code) {
		this.postal_std_code = postal_std_code;
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
	public String getPreferred_country() {
		return preferred_country;
	}
	public void setPreferred_country(String preferred_country) {
		this.preferred_country = preferred_country;
	}
	public String getPreferred_email() {
		return preferred_email;
	}
	public void setPreferred_email(String preferred_email) {
		this.preferred_email = preferred_email;
	}
	public String getPreferred_phone() {
		return preferred_phone;
	}
	public void setPreferred_phone(String preferred_phone) {
		this.preferred_phone = preferred_phone;
	}
	public String getPreferred_pin() {
		return preferred_pin;
	}
	public void setPreferred_pin(String preferred_pin) {
		this.preferred_pin = preferred_pin;
	}
	public String getPreferred_state() {
		return preferred_state;
	}
	public void setPreferred_state(String preferred_state) {
		this.preferred_state = preferred_state;
	}
	public String getPreferred_std_code() {
		return preferred_std_code;
	}
	public void setPreferred_std_code(String preferred_std_code) {
		this.preferred_std_code = preferred_std_code;
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
	public String getPresent_country() {
		return present_country;
	}
	public void setPresent_country(String present_country) {
		this.present_country = present_country;
	}
	public String getPresent_email() {
		return present_email;
	}
	public void setPresent_email(String present_email) {
		this.present_email = present_email;
	}
	public String getPresent_phone() {
		return present_phone;
	}
	public void setPresent_phone(String present_phone) {
		this.present_phone = present_phone;
	}
	public String getPresent_pin() {
		return present_pin;
	}
	public void setPresent_pin(String present_pin) {
		this.present_pin = present_pin;
	}
	public String getPresent_state() {
		return present_state;
	}
	public void setPresent_state(String present_state) {
		this.present_state = present_state;
	}
	public String getPresent_std_code() {
		return present_std_code;
	}
	public void setPresent_std_code(String present_std_code) {
		this.present_std_code = present_std_code;
	}
	public String getProcess_flag() {
		return process_flag;
	}
	public void setProcess_flag(String process_flag) {
		this.process_flag = process_flag;
	}
	public String getProcess_type() {
		return process_type;
	}
	public void setProcess_type(String process_type) {
		this.process_type = process_type;
	}
	public String getRationcard_no() {
		return rationcard_no;
	}
	public void setRationcard_no(String rationcard_no) {
		this.rationcard_no = rationcard_no;
	}
	public String getRegistration_no() {
		return registration_no;
	}
	public void setRegistration_no(String registration_no) {
		this.registration_no = registration_no;
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
	public String getResidence_country() {
		return residence_country;
	}
	public void setResidence_country(String residence_country) {
		this.residence_country = residence_country;
	}
	public String getResidence_email() {
		return residence_email;
	}
	public void setResidence_email(String residence_email) {
		this.residence_email = residence_email;
	}
	public String getResidence_phone() {
		return residence_phone;
	}
	public void setResidence_phone(String residence_phone) {
		this.residence_phone = residence_phone;
	}
	public String getResidence_pin() {
		return residence_pin;
	}
	public void setResidence_pin(String residence_pin) {
		this.residence_pin = residence_pin;
	}
	public String getResidence_state() {
		return residence_state;
	}
	public void setResidence_state(String residence_state) {
		this.residence_state = residence_state;
	}
	public String getResidence_std_code() {
		return residence_std_code;
	}
	public void setResidence_std_code(String residence_std_code) {
		this.residence_std_code = residence_std_code;
	}
	public String getResidence_street_number() {
		return residence_street_number;
	}
	public void setResidence_street_number(String residence_street_number) {
		this.residence_street_number = residence_street_number;
	}
	public String getSegment() {
		return segment;
	}
	public void setSegment(String segment) {
		this.segment = segment;
	}
	public String getSource_sys_id() {
		return source_sys_id;
	}
	public void setSource_sys_id(String source_sys_id) {
		this.source_sys_id = source_sys_id;
	}
	public String getSource_system() {
		return source_system;
	}
	public void setSource_system(String source_system) {
		this.source_system = source_system;
	}
	public String getSpouse_name() {
		return spouse_name;
	}
	public void setSpouse_name(String spouse_name) {
		this.spouse_name = spouse_name;
	}
	public String getTan_no() {
		return tan_no;
	}
	public void setTan_no(String tan_no) {
		this.tan_no = tan_no;
	}
	public String getTemp_street_number() {
		return temp_street_number;
	}
	public void setTemp_street_number(String temp_street_number) {
		this.temp_street_number = temp_street_number;
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
	public String getTemporary_country() {
		return temporary_country;
	}
	public void setTemporary_country(String temporary_country) {
		this.temporary_country = temporary_country;
	}
	public String getTemporary_email() {
		return temporary_email;
	}
	public void setTemporary_email(String temporary_email) {
		this.temporary_email = temporary_email;
	}
	public String getTemporary_phone() {
		return temporary_phone;
	}
	public void setTemporary_phone(String temporary_phone) {
		this.temporary_phone = temporary_phone;
	}
	public String getTemporary_pin() {
		return temporary_pin;
	}
	public void setTemporary_pin(String temporary_pin) {
		this.temporary_pin = temporary_pin;
	}
	public String getTemporary_state() {
		return temporary_state;
	}
	public void setTemporary_state(String temporary_state) {
		this.temporary_state = temporary_state;
	}
	public String getTemporary_std_code() {
		return temporary_std_code;
	}
	public void setTemporary_std_code(String temporary_std_code) {
		this.temporary_std_code = temporary_std_code;
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
	public String getUcin_flag() {
		return ucin_flag;
	}
	public void setUcin_flag(String ucin_flag) {
		this.ucin_flag = ucin_flag;
	}
	public String getVoterid() {
		return voterid;
	}
	public void setVoterid(String voterid) {
		this.voterid = voterid;
	}
	@Override
	public String toString() {
		return "DemoGraphicCustomerDetails [account_number=" + account_number
				+ ", aadhaar=" + aadhaar + ", applicant_type=" + applicant_type
				+ ", area=" + area + ", batchid=" + batchid + ", ca_number="
				+ ca_number + ", cin=" + cin + ", ckycno=" + ckycno
				+ ", contact_address=" + contact_address + ", contact_city="
				+ contact_city + ", contact_country=" + contact_country
				+ ", contact_email=" + contact_email + ", contact_phone="
				+ contact_phone + ", contact_pin=" + contact_pin
				+ ", contact_state=" + contact_state + ", contact_std_code="
				+ contact_std_code + ", corporatelicence=" + corporatelicence
				+ ", country=" + country + ", country_of_origin="
				+ country_of_origin + ", credit_card_number="
				+ credit_card_number + ", crnno=" + crnno + ", cust_unq_id="
				+ cust_unq_id + ", customer_contact_type="
				+ customer_contact_type + ", customer_id=" + customer_id
				+ ", customer_no=" + customer_no + ", date_of_incorporation="
				+ date_of_incorporation + ", din=" + din + ", dob1=" + dob1
				+ ", dob2=" + dob2 + ", dob3=" + dob3 + ", dob4=" + dob4
				+ ", doi=" + doi + ", drivinglic=" + drivinglic
				+ ", emergency_address=" + emergency_address
				+ ", emergency_city=" + emergency_city + ", emergency_email="
				+ emergency_email + ", emergency_phone=" + emergency_phone
				+ ", emergency_pin=" + emergency_pin + ", emergency_std_code="
				+ emergency_std_code + ", employer_name=" + employer_name
				+ ", eventtype=" + eventtype + ", father_name=" + father_name
				+ ", filler_date_1=" + filler_date_1 + ", filler_date_2="
				+ filler_date_2 + ", filler_number_1=" + filler_number_1
				+ ", filler_number_2=" + filler_number_2 + ", filler_number_3="
				+ filler_number_3 + ", filler_number_4=" + filler_number_4
				+ ", filler_number_5=" + filler_number_5 + ", filler_string_1="
				+ filler_string_1 + ", filler_string_2=" + filler_string_2
				+ ", filler_string_3=" + filler_string_3 + ", filler_string_4="
				+ filler_string_4 + ", filler_string_5=" + filler_string_5
				+ ", filler_string_6=" + filler_string_6 + ", filler_string_7="
				+ filler_string_7 + ", filler_string_8=" + filler_string_8
				+ ", filler_string_9=" + filler_string_9 + ", firstname="
				+ firstname + ", gender=" + gender + ", gstin=" + gstin
				+ ", insert_time=" + insert_time + ", landmark=" + landmark
				+ ", lastname=" + lastname + ", lchgtime=" + lchgtime
				+ ", middlename=" + middlename + ", mobile=" + mobile
				+ ", mother_name=" + mother_name + ", name=" + name
				+ ", office_address=" + office_address + ", office_city="
				+ office_city + ", office_country=" + office_country
				+ ", office_email=" + office_email + ", office_phone="
				+ office_phone + ", office_pin=" + office_pin
				+ ", office_state=" + office_state + ", office_std_code="
				+ office_std_code + ", office_street_number="
				+ office_street_number + ", old_psx_batch_id="
				+ old_psx_batch_id + ", pan=" + pan + ", passport=" + passport
				+ ", permanent_address=" + permanent_address
				+ ", permanent_city=" + permanent_city + ", permanent_country="
				+ permanent_country + ", permanent_email=" + permanent_email
				+ ", permanent_phone=" + permanent_phone + ", permanent_pin="
				+ permanent_pin + ", permanent_state=" + permanent_state
				+ ", permanent_std_code=" + permanent_std_code
				+ ", permanent_street_number=" + permanent_street_number
				+ ", policy_number=" + policy_number + ", postal_address="
				+ postal_address + ", postal_city=" + postal_city
				+ ", postal_country=" + postal_country + ", postal_email="
				+ postal_email + ", postal_phone=" + postal_phone
				+ ", postal_pin=" + postal_pin + ", postal_std_code="
				+ postal_std_code + ", preferred_address=" + preferred_address
				+ ", preferred_city=" + preferred_city + ", preferred_country="
				+ preferred_country + ", preferred_email=" + preferred_email
				+ ", preferred_phone=" + preferred_phone + ", preferred_pin="
				+ preferred_pin + ", preferred_state=" + preferred_state
				+ ", preferred_std_code=" + preferred_std_code
				+ ", present_address=" + present_address + ", present_city="
				+ present_city + ", present_country=" + present_country
				+ ", present_email=" + present_email + ", present_phone="
				+ present_phone + ", present_pin=" + present_pin
				+ ", present_state=" + present_state + ", present_std_code="
				+ present_std_code + ", process_flag=" + process_flag
				+ ", process_type=" + process_type + ", rationcard_no="
				+ rationcard_no + ", registration_no=" + registration_no
				+ ", residence_address=" + residence_address
				+ ", residence_city=" + residence_city + ", residence_country="
				+ residence_country + ", residence_email=" + residence_email
				+ ", residence_phone=" + residence_phone + ", residence_pin="
				+ residence_pin + ", residence_state=" + residence_state
				+ ", residence_std_code=" + residence_std_code
				+ ", residence_street_number=" + residence_street_number
				+ ", segment=" + segment + ", source_sys_id=" + source_sys_id
				+ ", source_system=" + source_system + ", spouse_name="
				+ spouse_name + ", tan_no=" + tan_no + ", temp_street_number="
				+ temp_street_number + ", temporary_address="
				+ temporary_address + ", temporary_city=" + temporary_city
				+ ", temporary_country=" + temporary_country
				+ ", temporary_email=" + temporary_email + ", temporary_phone="
				+ temporary_phone + ", temporary_pin=" + temporary_pin
				+ ", temporary_state=" + temporary_state
				+ ", temporary_std_code=" + temporary_std_code
				+ ", timestamp1=" + timestamp1 + ", timestamp2=" + timestamp2
				+ ", ucin_flag=" + ucin_flag + ", voterid=" + voterid + "]";
	}
	
	
}
