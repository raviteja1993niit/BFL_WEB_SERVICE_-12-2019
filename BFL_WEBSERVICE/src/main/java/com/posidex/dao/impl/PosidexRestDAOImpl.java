package com.posidex.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import com.posidex.dao.PosidexRestDAOI;
import com.posidex.exception.DAOException;
import com.posidex.ws.entity.CustomerRequest;
import com.posidex.ws.entity.CustomerRequestDetails;
import com.posidex.ws.entity.DemoGraphicCustomerDetails;
import com.posidex.ws.entity.MatchedCustomerDetails;
import com.posidex.ws.entity.MatchedLoanDetails;

@Repository
public class PosidexRestDAOImpl implements PosidexRestDAOI {
	private static Logger logger = Logger.getLogger(PosidexRestDAOImpl.class
			.getName());
	String source_system_name = "";
	String sourceSysId = "";
	@Autowired
	private JdbcTemplate jdbctemplate;

	@Autowired
	private Environment env;

	@Override
	/***
	 * This method is used to get Error Codes From Database table.
	 */
	public Map<String, String> getWebServiceErrorCodes() throws DAOException {
		logger.info("Inside WebServiceErrorCodes");
		List<String> errorcodeslist = null;
		Map<String, String> map = null;
		map = new HashMap<String, String>();
		try {
			errorcodeslist = jdbctemplate.query(
					"select ERROR_CODE from PSX_WEBSERVICE_ERROR_CODES_T", (rs,
							rownum) -> {
						return rs.getString(1);
					});
			for (String errorcode : errorcodeslist) {
				System.out.println("error codes " + errorcode);
				if ("100".equals(errorcode.trim())) {
					map.put("VALIDATIONFAILED", errorcode.trim().trim());
				}
				if ("200".equals(errorcode.trim().trim())) {
					map.put("INVALIDDATA", errorcode.trim().trim());
				}
				if ("300".equals(errorcode.trim().trim())) {
					map.put("INTERNALEXCEPTION", errorcode.trim().trim());
				}
				if ("0".equals(errorcode.trim().trim())) {
					map.put("PENDINGORPROCESSCOMPLETED", errorcode.trim()
							.trim());
				}
			}
			logger.debug("Leaving getWebServiceErrorCodes()");
		} catch (Exception ex) {
			logger.error("Exception while getting getWebServiceErrorCodes: " + ex.getMessage());//$NON-NLS-1$
			throw new DAOException("errors.internalError");//$NON-NLS-1$
		}
		return map;
	}

	/***
	 * This method is used to insert Customer Request Data into the Request
	 * Table.
	 */
	@Override
	public void saveCostomerRequest(CustomerRequest customerRequest,
			long reqPsxid, Date lchgtime) throws DAOException {
		logger.info("Inside Saving Request Record into Request Table for RequestId "
				+ reqPsxid);
		String day = "";
		String month = "";
		String year = "";
		String doi = customerRequest.getRequestDetails()
				.getDateOfInCorporation();
		if (doi == null) {
			doi = "";
		}
		String dob1 = customerRequest.getRequestDetails().getDob1();
		if (dob1 == null) {
			dob1 = "";
		}
		if (dob1 != null && dob1.length() > 1) {
			String[] dob1Split = dob1.split("/");
			day = dob1Split[0];
			month = dob1Split[1];
			year = dob1Split[2];
		}
		String dob2 = customerRequest.getRequestDetails().getDob2();
		if (dob2 == null) {
			dob2 = "";
		}
		String dob3 = customerRequest.getRequestDetails().getDob3();
		if (dob3 == null) {
			dob3 = "";
		}
		String dob4 = customerRequest.getRequestDetails().getDob4();
		if (dob4 == null) {
			dob4 = "";
		}
		if (customerRequest.getRequestDetails().getFirstName() == null) {
			customerRequest.getRequestDetails().setFirstName("");
		}
		if (customerRequest.getRequestDetails().getMiddleName() == null) {
			customerRequest.getRequestDetails().setMiddleName("");
		}
		if (customerRequest.getRequestDetails().getLastName() == null) {
			customerRequest.getRequestDetails().setLastName("");
		}
		try {
			String sql = "insert into PSX_NSP_REQUEST"
					+ "(ADDRESS,PSX_BATCH_ID,PSX_ID,REQUEST_ID,REQUEST_STATUS,NAME,MOTHER_NAME,FATHER_NAME,SPOUSE_NAME,DATE_OF_INCORPORATION,DOB1,DOB2,DOB3,DOB4,"
					+ "VOTERID,CRNNO,ACCOUNT_NUMBER,CIN,CKYCNO,CONTACT_ADDRESS,CONTACT_CITY,CONTACT_EMAIL,CONTACT_PHONE,CONTACT_PIN,"
					+ "DIN,EMERGENCY_ADDRESS,EMERGENCY_CITY,EMERGENCY_EMAIL,EMERGENCY_PHONE,EMERGENCY_PIN,EMPLOYER_NAME,ERROR_DESC,"
					+ "PAN,PASSPORT,DRIVINGLIC,AADHAAR,GENDER,OFFICE_ADDRESS,OFFICE_CITY,OFFICE_PIN,OFFICE_EMAIL,OFFICE_PHONE,"
					+ "PERMANENT_ADDRESS,PERMANENT_CITY,PERMANENT_PIN,PERMANENT_EMAIL,PERMANENT_PHONE,PHONE,TAN_NO,AREA,LANDMARK,PRESENT_ADDRESS,PRESENT_CITY,PRESENT_PIN,PRESENT_EMAIL,PRESENT_PHONE,"
					+ "POSTAL_ADDRESS,POSTAL_CITY,POSTAL_EMAIL,POSTAL_PHONE,POSTAL_PIN,PREFERRED_ADDRESS,PREFERRED_CITY,PREFERRED_EMAIL,PREFERRED_PHONE,PREFERRED_PIN,"
					+ "PROCESS_FLAG,REGISTRATION_NO,RESIDENCE_ADDRESS,RESIDENCE_CITY,RESIDENCE_EMAIL,RESIDENCE_PHONE,RESIDENCE_PIN,SEGMENT,"
					+ "TEMPORARY_ADDRESS,TEMPORARY_CITY,TEMPORARY_EMAIL,TEMPORARY_PHONE,TEMPORARY_PIN,"
					+ "MOBILE,CUSTOMER_NO,CREDIT_CARD_NUMBER,APPLICANT_TYPE,INSERT_TIME,"
					+ "CUSTOMER_CONTACT_TYPE,LCHGTIME,REQUEST_TYPE,REQUEST_ID_BFL,DAY,MONTH,YEAR,OFFICE_AREA,CUST_UNQ_ID,AGE,APPLICATION_NO,ASSET_CATEGORY,BATCHID,CITY_CLASSIFICATION,CREDIT_PROGRAM,CUSTOMER_STATUS,"
					+ "CUSTOMER_TYPE,CUST_SR_NO,EMPLOYMENT_BUSINESS,LANDMARK_OFFICE,LAN_2,LAN_NO,LOAN_APP_NO,MOBILE_OFFICE,ORG,PRODUCT,PRODUCT_CODE,RESIDENT_TYPE,STAYING_SINCE,STD,STD_OFFICE,MATCH_PROFILE,SOURCE_SYSTEM,NODE1_REQUEST_STATUS,NODE2_REQUEST_STATUS,DEAL_ID,DATA_SOURCE,REQUEST_ID_FIN,SYSTIME_INSERT) "
					+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,systimestamp)";

			jdbctemplate
					.update(sql,
							new Object[] {
									customerRequest.getRequestDetails()
											.getAddress(),
									reqPsxid,
									reqPsxid,
									reqPsxid,
									customerRequest.getRequestStatus(),
									customerRequest.getRequestDetails()
											.getFirstName()
											+ " "
											+ customerRequest
													.getRequestDetails()
													.getMiddleName()
											+ " "
											+ customerRequest
													.getRequestDetails()
													.getLastName(),
									customerRequest.getRequestDetails()
											.getMotherName(),
									customerRequest.getRequestDetails()
											.getFatherName(),
									customerRequest.getRequestDetails()
											.getSpouseName(),
									doi,
									dob1,
									dob2,
									dob3,
									dob4,
									customerRequest.getRequestDetails()
											.getVoterId(),
									customerRequest.getRequestDetails()
											.getCrnNo(),
									customerRequest.getRequestDetails()
											.getAccountNumber(),
									customerRequest.getRequestDetails()
											.getCin(),
									customerRequest.getRequestDetails()
											.getCkyCno(),
									customerRequest.getRequestDetails()
											.getContact_address(),
									customerRequest.getRequestDetails()
											.getContact_city(),
									customerRequest.getRequestDetails()
											.getContact_email(),
									customerRequest.getRequestDetails()
											.getContact_phone(),
									customerRequest.getRequestDetails()
											.getContact_pin(),
									customerRequest.getRequestDetails()
											.getDin(),
									customerRequest.getRequestDetails()
											.getEmergency_address(),
									customerRequest.getRequestDetails()
											.getEmergency_city(),
									customerRequest.getRequestDetails()
											.getEmergency_email(),
									customerRequest.getRequestDetails()
											.getEmergency_phone(),
									customerRequest.getRequestDetails()
											.getEmergency_pin(),
									customerRequest.getRequestDetails()
											.getEmployerName(),
									customerRequest.getRequestDetails()
											.getErrorDesc(),
									customerRequest.getRequestDetails()
											.getPan(),
									customerRequest.getRequestDetails()
											.getPassportNo(),
									customerRequest.getRequestDetails()
											.getDrivingLicenseNumber(),
									customerRequest.getRequestDetails()
											.getAadhaar(),
									customerRequest.getRequestDetails()
											.getGender(),
									// customerRequest.getRequestDetails().getOffice_address(),
									customerRequest.getRequestDetails()
											.getAddress1Office()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress2Office()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress3Office(),
									// customerRequest.getRequestDetails().getOffice_city(),
									customerRequest.getRequestDetails()
											.getCityOffice(),
									// customerRequest.getRequestDetails().getOffice_pin(),
									customerRequest.getRequestDetails()
											.getPinOffice(),
									// customerRequest.getRequestDetails().getOffice_email(),
									customerRequest.getRequestDetails()
											.getEmailOffice(),
									// customerRequest.getRequestDetails().getOffice_phone(),
									customerRequest.getRequestDetails()
											.getLandLine1Office(),
									customerRequest.getRequestDetails()
											.getPermanent_address(),
									customerRequest.getRequestDetails()
											.getPermanent_city(),
									customerRequest.getRequestDetails()
											.getPermanent_pin(),
									customerRequest.getRequestDetails()
											.getPermanent_email(),
									// customerRequest.getRequestDetails().getPermanent_phone(),
									customerRequest.getRequestDetails()
											.getLandLine2Office(),
									customerRequest.getRequestDetails()
											.getPhone(),
									customerRequest.getRequestDetails()
											.getTanNo(),
									customerRequest.getRequestDetails()
											.getArea(),
									customerRequest.getRequestDetails()
											.getLandMark(),
									customerRequest.getRequestDetails()
											.getPresent_address(),
									customerRequest.getRequestDetails()
											.getPresent_city(),
									customerRequest.getRequestDetails()
											.getPresent_pin(),
									customerRequest.getRequestDetails()
											.getPresent_email(),
									customerRequest.getRequestDetails()
											.getPresent_phone(),
									customerRequest.getRequestDetails()
											.getPostal_address(),
									customerRequest.getRequestDetails()
											.getPostal_city(),
									customerRequest.getRequestDetails()
											.getPostal_email(),
									// customerRequest.getRequestDetails().getPostal_phone(),
									customerRequest.getRequestDetails()
											.getPhone(),
									customerRequest.getRequestDetails()
											.getPostal_pin(),
									customerRequest.getRequestDetails()
											.getPreferred_address(),
									customerRequest.getRequestDetails()
											.getPreferred_city(),
									customerRequest.getRequestDetails()
											.getPreferred_email(),
									// customerRequest.getRequestDetails().getPreferred_phone(),
									customerRequest.getRequestDetails()
											.getMobileOffice(),
									customerRequest.getRequestDetails()
											.getPreferred_pin(),
									customerRequest.getRequestDetails()
											.getProcessFLG(),
									customerRequest.getRequestDetails()
											.getRegistrationNo(),
									// customerRequest.getRequestDetails().getResidence_address(),
									customerRequest.getRequestDetails()
											.getAddress1()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress2()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress3(),
									// customerRequest.getRequestDetails().getResidence_city(),
									customerRequest.getRequestDetails()
											.getCity(),
									// customerRequest.getRequestDetails().getResidence_email(),
									customerRequest.getRequestDetails()
											.getEmail(),
									// customerRequest.getRequestDetails().getResidence_phone(),
									customerRequest.getRequestDetails()
											.getLandLine1(),
									// customerRequest.getRequestDetails().getResidence_pin(),
									customerRequest.getRequestDetails()
											.getPin(),
									customerRequest.getRequestDetails()
											.getSegment(),
									customerRequest.getRequestDetails()
											.getTemporary_address(),
									customerRequest.getRequestDetails()
											.getTemporary_city(),
									customerRequest.getRequestDetails()
											.getTemporary_email(),
									// customerRequest.getRequestDetails().getTemporary_phone(),
									customerRequest.getRequestDetails()
											.getLandLine2(),
									customerRequest.getRequestDetails()
											.getTemporary_pin(),
									customerRequest.getRequestDetails()
											.getMobile(),
									customerRequest.getRequestDetails()
											.getCustomerNo(),
									customerRequest.getRequestDetails()
											.getCreditCardNumber(),
									customerRequest.getRequestDetails()
											.getApplicationType(),
									lchgtime,
									customerRequest.getRequestDetails()
											.getCustomerType(),
									lchgtime,
									customerRequest.getRequestType(),
									customerRequest.getBflRequestId(),
									day,
									month,
									year,
									customerRequest.getRequestDetails()
											.getAreaOffice(),
									customerRequest.getBflRequestId(),
									customerRequest.getRequestDetails()
											.getAge(),
									customerRequest.getRequestDetails()
											.getApplnNo(),
									customerRequest.getRequestDetails()
											.getAssetCategory(),
									customerRequest.getRequestDetails()
											.getBatch(),
									customerRequest.getRequestDetails()
											.getCityClassification(),
									customerRequest.getRequestDetails()
											.getCreditProgram(),
									customerRequest.getRequestDetails()
											.getCustomerStatus(),
									customerRequest.getRequestDetails()
											.getCustomerType(),
									customerRequest.getRequestDetails()
											.getCustSrNo(),
									customerRequest.getRequestDetails()
											.getEmploymentBusiness(),
									customerRequest.getRequestDetails()
											.getLandMarkOffice(),
									customerRequest.getRequestDetails()
											.getLanNo2(),
									customerRequest.getRequestDetails()
											.getLanNo(),
									customerRequest.getRequestDetails()
											.getLoanAppNo(),
									customerRequest.getRequestDetails()
											.getMobileOffice(),
									customerRequest.getRequestDetails()
											.getOrg(),
									customerRequest.getRequestDetails()
											.getProduct(),
									customerRequest.getRequestDetails()
											.getProductCode(),
									customerRequest.getRequestDetails()
											.getResidentType(),
									customerRequest.getRequestDetails()
											.getStayingSince(),
									customerRequest.getRequestDetails()
											.getStd(),
									customerRequest.getRequestDetails()
											.getStdOffice(),
									customerRequest.getMatchProfile(),
									customerRequest.getDataSource(), "P", "P",
									customerRequest.getDealId(),
									customerRequest.getDataSource(),
									customerRequest.getRequestIdFin() });
			logger.info("Record Inserted into PSX_NSP_REQUEST");
		} catch (Exception ex) {
			logger.error("Exception while getting saveCostomerRequest: " + ex.getMessage());//$NON-NLS-1$
			// throw (new
			// DAOException("Exception encountered while inserting Customer_Request_Record"));
			throw (new DAOException(ex.getMessage()));
		}
	}

	/***
	 * This method is used to save Intraday Request Information into Intraday
	 * tables.
	 */
	@Override
	public void saveCustomerRequestToNodeOneINTRADAY(
			CustomerRequest customerRequest, long reqPsxid, Date lchgtime)
			throws DAOException, Exception {
		logger.info("Inside Saving Request Record into NodeOneIntraday Table for RequestId "
				+ reqPsxid);
		String flag = "I_OR_U";
		String eventType = "INTRADAY";
		String doi = customerRequest.getRequestDetails()
				.getDateOfInCorporation();
		if (doi == null) {
			doi = "";
		}
		String dob1 = customerRequest.getRequestDetails().getDob1();
		if (dob1 == null) {
			dob1 = "";
		}
		String dob2 = customerRequest.getRequestDetails().getDob2();
		if (dob2 == null) {
			dob2 = "";
		}
		String dob3 = customerRequest.getRequestDetails().getDob3();
		if (dob3 == null) {
			dob3 = "";
		}
		String dob4 = customerRequest.getRequestDetails().getDob4();
		if (dob4 == null) {
			dob4 = "";
		}
		if (customerRequest.getRequestDetails().getFirstName() == null) {
			customerRequest.getRequestDetails().setFirstName("");
		}
		if (customerRequest.getRequestDetails().getMiddleName() == null) {
			customerRequest.getRequestDetails().setMiddleName("");
		}
		if (customerRequest.getRequestDetails().getLastName() == null) {
			customerRequest.getRequestDetails().setLastName("");
		}

		String dob = customerRequest.getRequestDetails().getDob1();

		/** Changes added as on 07th June 2019 */
		String convertedDob1 = "";
		String[] dob1Split = new String[3];
		DateFormat df1 = new SimpleDateFormat("dd/MMM/yyyy"); // for parsing
																// input
		DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); // for formatting
																// output
		if (dob != null && dob != "") {
			Date d = df1.parse(dob1);
			convertedDob1 = df2.format(d);
			dob1Split = convertedDob1.split("/");
		}

		try {
			/**
			 * Changes added as on 07th June 2019 -- Added
			 * FIRSTNAME,MIDDLENAME,LASTNAME,DAY,MONTH,YEAR and place holders
			 */
			String sql = "insert into PSX_NSP_INTRADAY_1"
					+ "(ADDRESS,PSX_ID,PSX_BATCH_ID,AREA_UNQ_ID,NAME,MOTHER_NAME,FATHER_NAME,SPOUSE_NAME,DOI,DOB1,DOB2,DOB3,DOB4,"
					+ "VOTERID,CRNNO,ACCOUNT_NUMBER,CIN,CKYCNO,CONTACT_ADDRESS,CONTACT_CITY,CONTACT_EMAIL,CONTACT_PHONE,CONTACT_PIN,"
					+ "DIN,EMERGENCY_ADDRESS,EMERGENCY_CITY,EMERGENCY_EMAIL,EMERGENCY_PHONE,EMERGENCY_PIN,EMPLOYER_NAME,ERROR_DESC,"
					+ "PAN,PASSPORT,DRIVINGLIC,AADHAAR,GENDER,OFFICE_ADDRESS,OFFICE_CITY,OFFICE_PIN,OFFICE_EMAIL,OFFICE_PHONE,"
					+ "PERMANENT_ADDRESS,PERMANENT_CITY,PERMANENT_PIN,PERMANENT_EMAIL,PERMANENT_PHONE,PHONE,TAN_NO,AREA,LANDMARK,PRESENT_ADDRESS,PRESENT_CITY,PRESENT_PIN,PRESENT_EMAIL,PRESENT_PHONE,"
					+ "POSTAL_ADDRESS,POSTAL_CITY,POSTAL_EMAIL,POSTAL_PHONE,POSTAL_PIN,PREFERRED_ADDRESS,PREFERRED_CITY,PREFERRED_EMAIL,PREFERRED_PHONE,PREFERRED_PIN,"
					+ "PROCESS_FLAG,REGISTRATION_NO,RESIDENCE_ADDRESS,RESIDENCE_CITY,RESIDENCE_EMAIL,RESIDENCE_PHONE,RESIDENCE_PIN,SEGMENT,"
					+ "TEMPORARY_ADDRESS,TEMPORARY_CITY,TEMPORARY_EMAIL,TEMPORARY_PHONE,TEMPORARY_PIN,MOBILE,CUSTOMER_NO,CREDIT_CARD_NUMBER,APPLICANT_TYPE,INSERT_TIME,"
					+ "CUSTOMER_CONTACT_TYPE,CUST_UNQ_ID,DUIFLAG,EVENTTYPE,LCHGTIME,CUSTOMER_ID,SOURCE_SYSTEM,SOURCE_SYS_ID,AGE,"
					+ "APPLICATION_NO,ASSET_CATEGORY,BATCHID,CITY_CLASSIFICATION,CREDIT_PROGRAM,CUSTOMER_STATUS,CUSTOMER_TYPE,CUST_SR_NO,"
					+ "EMPLOYMENT_BUSINESS,LANDMARK_OFFICE,LAN_2,LAN_NO,LOAN_APP_NO,MOBILE_OFFICE,ORG,PRODUCT,PRODUCT_CODE,RESIDENT_TYPE,"
					+ "STAYING_SINCE,STD,STD_OFFICE,MATCH_PROFILE,FIRSTNAME,MIDDLENAME,LASTNAME,DAY,MONTH,YEAR,DEAL_ID,DATASOURCE,REQUEST_ID_FIN) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			jdbctemplate
					.update(sql,
							new Object[] {
									customerRequest.getRequestDetails()
											.getAddress(),
									reqPsxid,
									reqPsxid,
									reqPsxid,
									customerRequest.getRequestDetails()
											.getFirstName()
											+ " "
											+ customerRequest
													.getRequestDetails()
													.getMiddleName()
											+ " "
											+ customerRequest
													.getRequestDetails()
													.getLastName(),
									customerRequest.getRequestDetails()
											.getMotherName(),
									customerRequest.getRequestDetails()
											.getFatherName(),
									customerRequest.getRequestDetails()
											.getSpouseName(),
									doi,
									dob1,
									dob2,
									dob3,
									dob4,
									customerRequest.getRequestDetails()
											.getVoterId(),
									customerRequest.getRequestDetails()
											.getCrnNo(),
									customerRequest.getRequestDetails()
											.getAccountNumber(),
									customerRequest.getRequestDetails()
											.getCin(),
									customerRequest.getRequestDetails()
											.getCkyCno(),
									customerRequest.getRequestDetails()
											.getContact_address(),
									customerRequest.getRequestDetails()
											.getContact_city(),
									customerRequest.getRequestDetails()
											.getContact_email(),
									customerRequest.getRequestDetails()
											.getContact_phone(),
									customerRequest.getRequestDetails()
											.getContact_pin(),
									customerRequest.getRequestDetails()
											.getDin(),
									customerRequest.getRequestDetails()
											.getEmergency_address(),
									customerRequest.getRequestDetails()
											.getEmergency_city(),
									customerRequest.getRequestDetails()
											.getEmergency_email(),
									customerRequest.getRequestDetails()
											.getEmergency_phone(),
									customerRequest.getRequestDetails()
											.getEmergency_pin(),
									customerRequest.getRequestDetails()
											.getEmployerName(),
									customerRequest.getRequestDetails()
											.getErrorDesc(),
									customerRequest.getRequestDetails()
											.getPan(),
									customerRequest.getRequestDetails()
											.getPassportNo(),
									customerRequest.getRequestDetails()
											.getDrivingLicenseNumber(),
									customerRequest.getRequestDetails()
											.getAadhaar(),
									customerRequest.getRequestDetails()
											.getGender(),
									// customerRequest.getRequestDetails().getOffice_address(),
									customerRequest.getRequestDetails()
											.getAddress1Office()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress2Office()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress3Office(),
									// customerRequest.getRequestDetails().getOffice_city(),
									customerRequest.getRequestDetails()
											.getCityOffice(),
									// customerRequest.getRequestDetails().getOffice_pin(),
									customerRequest.getRequestDetails()
											.getPinOffice(),
									// customerRequest.getRequestDetails().getOffice_email(),
									customerRequest.getRequestDetails()
											.getEmailOffice(),
									// customerRequest.getRequestDetails().getOffice_phone(),
									customerRequest.getRequestDetails()
											.getLandLine1Office(),
									customerRequest.getRequestDetails()
											.getPermanent_address(),
									customerRequest.getRequestDetails()
											.getPermanent_city(),
									customerRequest.getRequestDetails()
											.getPermanent_pin(),
									customerRequest.getRequestDetails()
											.getPermanent_email(),
									// customerRequest.getRequestDetails().getPermanent_phone(),
									customerRequest.getRequestDetails()
											.getLandLine2Office(),
									customerRequest.getRequestDetails()
											.getPhone(),
									customerRequest.getRequestDetails()
											.getTanNo(),
									customerRequest.getRequestDetails()
											.getArea(),
									customerRequest.getRequestDetails()
											.getLandMark(),
									customerRequest.getRequestDetails()
											.getPresent_address(),
									customerRequest.getRequestDetails()
											.getPresent_city(),
									customerRequest.getRequestDetails()
											.getPresent_pin(),
									customerRequest.getRequestDetails()
											.getPresent_email(),
									customerRequest.getRequestDetails()
											.getPresent_phone(),
									customerRequest.getRequestDetails()
											.getPostal_address(),
									customerRequest.getRequestDetails()
											.getPostal_city(),
									customerRequest.getRequestDetails()
											.getPostal_email(),
									// customerRequest.getRequestDetails().getPostal_phone(),
									customerRequest.getRequestDetails()
											.getPhone(),
									customerRequest.getRequestDetails()
											.getPostal_pin(),
									customerRequest.getRequestDetails()
											.getPreferred_address(),
									customerRequest.getRequestDetails()
											.getPreferred_city(),
									customerRequest.getRequestDetails()
											.getPreferred_email(),
									// customerRequest.getRequestDetails().getPreferred_phone(),
									customerRequest.getRequestDetails()
											.getMobileOffice(),
									customerRequest.getRequestDetails()
											.getPreferred_pin(),
									customerRequest.getRequestDetails()
											.getProcessFLG(),
									customerRequest.getRequestDetails()
											.getRegistrationNo(),
									// customerRequest.getRequestDetails().getResidence_address(),
									customerRequest.getRequestDetails()
											.getAddress1()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress2()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress3(),
									// customerRequest.getRequestDetails().getResidence_city(),
									customerRequest.getRequestDetails()
											.getCity(),
									// customerRequest.getRequestDetails().getResidence_email(),
									customerRequest.getRequestDetails()
											.getEmail(),
									// customerRequest.getRequestDetails().getResidence_phone(),
									customerRequest.getRequestDetails()
											.getLandLine1(),
									// customerRequest.getRequestDetails().getResidence_pin(),
									customerRequest.getRequestDetails()
											.getPin(),
									customerRequest.getRequestDetails()
											.getSegment(),
									customerRequest.getRequestDetails()
											.getTemporary_address(),
									customerRequest.getRequestDetails()
											.getTemporary_city(),
									customerRequest.getRequestDetails()
											.getTemporary_email(),
									// customerRequest.getRequestDetails().getTemporary_phone(),
									customerRequest.getRequestDetails()
											.getLandLine2(),
									customerRequest.getRequestDetails()
											.getTemporary_pin(),
									customerRequest.getRequestDetails()
											.getMobile(),
									customerRequest.getRequestDetails()
											.getCustomerNo(),
									customerRequest.getRequestDetails()
											.getCreditCardNumber(),
									customerRequest.getRequestDetails()
											.getApplicationType(),
									lchgtime,
									// customerRequest.getRequestDetails().getCustomerType(),
									// reqPsxid, flag,
									customerRequest.getRequestDetails()
											.getCustomerType(),
									customerRequest.getBflRequestId(),
									flag,
									eventType,
									lchgtime,
									customerRequest.getRequestDetails()
											.getCustomerNo(),
									"INTRADAY",
									"99",
									customerRequest.getRequestDetails()
											.getAge(),
									customerRequest.getRequestDetails()
											.getApplnNo(),
									customerRequest.getRequestDetails()
											.getAssetCategory(),
									customerRequest.getRequestDetails()
											.getBatch(),
									customerRequest.getRequestDetails()
											.getCityClassification(),
									customerRequest.getRequestDetails()
											.getCreditProgram(),
									customerRequest.getRequestDetails()
											.getCustomerStatus(),
									customerRequest.getRequestDetails()
											.getCustomerType(),
									customerRequest.getRequestDetails()
											.getCustSrNo(),
									customerRequest.getRequestDetails()
											.getEmploymentBusiness(),
									customerRequest.getRequestDetails()
											.getLandMarkOffice(),
									customerRequest.getRequestDetails()
											.getLanNo2(),
									customerRequest.getRequestDetails()
											.getLanNo(),
									customerRequest.getRequestDetails()
											.getLoanAppNo(),
									customerRequest.getRequestDetails()
											.getMobileOffice(),
									customerRequest.getRequestDetails()
											.getOrg(),
									customerRequest.getRequestDetails()
											.getProduct(),
									customerRequest.getRequestDetails()
											.getProductCode(),
									customerRequest.getRequestDetails()
											.getResidentType(),
									customerRequest.getRequestDetails()
											.getStayingSince(),
									customerRequest.getRequestDetails()
											.getStd(),
									customerRequest.getRequestDetails()
											.getStdOffice(),
									customerRequest.getMatchProfile(),

									// Changes added as on 07th June 2019
									// FIRSTNAME,MIDDLENAME,LASTNAME,DAY,MONTH,YEAR
									customerRequest.getRequestDetails()
											.getFirstName(),
									customerRequest.getRequestDetails()
											.getMiddleName(),
									customerRequest.getRequestDetails()
											.getLastName(), dob1Split[0],
									dob1Split[1], dob1Split[2],
									customerRequest.getDealId(),
									customerRequest.getDataSource(),
									customerRequest.getRequestIdFin()

							});
			logger.info("Record Inserted into NodeOneIntraday");
		} catch (Exception ex) {
			logger.error("Exception while getting saveCustomerRequestToNodeOneINTRADAY: " + ex.getMessage());//$NON-NLS-1$
			// throw (new
			// DAOException("Exception encountered while inserting Node1_Intraday_Record"));
			throw (new DAOException(ex.getMessage()));
		}
	}

	/***
	 * This method is used to get Matched Customer Details for that particular
	 * RequestID
	 */
	@Override
	public List<MatchedCustomerDetails> getmatchedCustomers(long requestId)
			throws DAOException {
		try {
			String query = "select /* + parallel(PSX_NSP_REQUEST_RESULTS,+"
					+ env.getProperty("process.threads")
					// +
					// ") */ ACCOUNT_NUMBER,APPLICANT_TYPE,PROCESS_TYPE,TAN_NO,PERMANENT_PIN,PERMANENT_CITY,GSTIN,CRNNO,CKYCNO,PREFERRED_PHONE,POSTAL_PHONE,EMERGENCY_PHONE,PRESENT_PHONE,RESIDENCE_PHONE,OFFICE_PHONE,POSTAL_EMAIL,RESIDENCE_EMAIL,EMPLOYER_NAME,TEMPORARY_EMAIL,PRESENT_PHONE,EMERGENCY_PHONE,CONTACT_PHONE,OFFICE_PHONE,PREFERRED_PHONE,PERMANENT_PHONE,TEMPORARY_PHONE,NAME,MOTHER_NAME,FATHER_NAME,SPOUSE_NAME,DOI,DOB1,DOB2,DOB3,DOB4,PAN,PASSPORT,VOTERID,DRIVINGLIC,AADHAAR,GENDER,CRNNO,CKYCNO,OFFICE_STATE,PERMANENT_STATE,RESIDENCE_STATE,TEMPORARY_STATE,PRESENT_STATE,CONTACT_STATE,PREFERRED_STATE,GSTIN,CORPORATELICENCE,OFFICE_ADDRESS,OFFICE_CITY,OFFICE_PIN,PERMANENT_ADDRESS,PERMANENT_CITY,PERMANENT_PIN,PRESENT_ADDRESS,PRESENT_CITY,PRESENT_PIN,EMERGENCY_ADDRESS,EMERGENCY_CITY,EMERGENCY_PIN,CONTACT_ADDRESS,CONTACT_CITY,CONTACT_PIN,POSTAL_ADDRESS,POSTAL_CITY,POSTAL_PIN,RESIDENCE_ADDRESS,RESIDENCE_CITY,RESIDENCE_PIN,PREFERRED_ADDRESS,PREFERRED_CITY,PREFERRED_PIN,TEMPORARY_ADDRESS,TEMPORARY_CITY,TEMPORARY_PIN,PRESENT_EMAIL,EMERGENCY_EMAIL,CONTACT_EMAIL,OFFICE_EMAIL,PREFERRED_EMAIL,PERMANENT_EMAIL,RESIDENCE_EMAIL,POSTAL_EMAIL,TEMPORARY_EMAIL,OFFICE_PHONE,PERMANENT_PHONE,RESIDENCE_PHONE,PRESENT_PHONE,EMERGENCY_PHONE,POSTAL_PHONE,PREFERRED_PHONE,CONTACT_PHONE,TEMPORARY_PHONE,OFFICE_STREET_NUMBER,COUNTRY,PERMANENT_STREET_NUMBER,RESIDENCE_STREET_NUMBER,TEMP_STREET_NUMBER,EVENTTYPE,OLD_PSX_BATCH_ID,RATIONCARD_NO,PRESENT_COUNTRY,PREFERRED_COUNTRY,CONTACT_COUNTRY,POSTAL_COUNTRY,OFFICE_COUNTRY,PERMANENT_COUNTRY,TEMPORARY_COUNTRY,RESIDENCE_COUNTRY,TIMESTAMP1,TIMESTAMP2,AREA,POLICY_NUMBER,COUNTRY_OF_ORIGIN,LANDMARK,MOBILE,DATE_OF_INCORPORATION,TAN_NO,PROCESS_TYPE,APPLICANT_TYPE,EMPLOYER_NAME,ACCOUNT_NUMBER,CREDIT_CARD_NUMBER,PROCESS_FLAG,PSX_SOURCE_SYSTEM_NAME,UCIN_FLAG,CA_NUMBER,CIN,DIN,REGISTRATION_NO,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,SEGMENT,FILLER_STRING_1,FILLER_STRING_2,FILLER_STRING_3,FILLER_STRING_4,FILLER_STRING_5,FILLER_DATE_1,FILLER_DATE_2,FILLER_NUMBER_1,FILLER_NUMBER_2,FILLER_NUMBER_3,FILLER_NUMBER_4,FILLER_NUMBER_5,FILLER_STRING_6,FILLER_STRING_7,FILLER_STRING_8,FILLER_STRING_9,BATCHID,RECORD_TYPE,MATCHING_RULE,SOURCE_SYS_ID,AREA_ID,SOURCE_SYSTEM,CUST_UNQ_ID,SEGMENT,WEIGHTAGES,MATCH_TYPE,INSERT_TIME,LCHGTIME,CUSTOMER_CONTACT_TYPE,PERMANENT_STD_CODE,PREFERRED_STD_CODE,PRESENT_STD_CODE,EMERGENCY_STD_CODE,TEMPORARY_STD_CODE,CONTACT_STD_CODE,RESIDENCE_STD_CODE,OFFICE_STD_CODE,POSTAL_STD_CODE,DEAL_ID,PRODUCT,MATCH_PROFILE,DATASOURCE,REQUEST_ID,REQUEST_ID_BFL,REQUEST_ID_FIN,FIRSTNAME,MIDDLENAME,LASTNAME,APPLICATION_NO,LOAN_APP_NO,CUSTOMER_TYPE from PSX_NSP_REQUEST_RESULTS WHERE REQUEST_ID like ?";
					+ ") */ ACCOUNT_NUMBER,APPLICANT_TYPE,PROCESS_TYPE,TAN_NO,PERMANENT_PIN,PERMANENT_CITY,GSTIN,CRNNO,CKYCNO,PREFERRED_PHONE,POSTAL_PHONE,EMERGENCY_PHONE,PRESENT_PHONE,RESIDENCE_PHONE,OFFICE_PHONE,POSTAL_EMAIL,RESIDENCE_EMAIL,EMPLOYER_NAME,TEMPORARY_EMAIL,PRESENT_PHONE,EMERGENCY_PHONE,CONTACT_PHONE,OFFICE_PHONE,PREFERRED_PHONE,PERMANENT_PHONE,TEMPORARY_PHONE,NAME,MOTHER_NAME,FATHER_NAME,SPOUSE_NAME,DOI,DOB1,DOB2,DOB3,DOB4,PAN,PASSPORT,VOTERID,DRIVINGLIC,AADHAAR,GENDER,CRNNO,CKYCNO,OFFICE_STATE,PERMANENT_STATE,RESIDENCE_STATE,TEMPORARY_STATE,PRESENT_STATE,CONTACT_STATE,PREFERRED_STATE,GSTIN,CORPORATELICENCE,OFFICE_ADDRESS,OFFICE_CITY,OFFICE_PIN,PERMANENT_ADDRESS,PERMANENT_CITY,PERMANENT_PIN,PRESENT_ADDRESS,PRESENT_CITY,PRESENT_PIN,EMERGENCY_ADDRESS,EMERGENCY_CITY,EMERGENCY_PIN,CONTACT_ADDRESS,CONTACT_CITY,CONTACT_PIN,POSTAL_ADDRESS,POSTAL_CITY,POSTAL_PIN,RESIDENCE_ADDRESS,RESIDENCE_CITY,RESIDENCE_PIN,PREFERRED_ADDRESS,PREFERRED_CITY,PREFERRED_PIN,TEMPORARY_ADDRESS,TEMPORARY_CITY,TEMPORARY_PIN,PRESENT_EMAIL,EMERGENCY_EMAIL,CONTACT_EMAIL,OFFICE_EMAIL,PREFERRED_EMAIL,PERMANENT_EMAIL,RESIDENCE_EMAIL,POSTAL_EMAIL,TEMPORARY_EMAIL,OFFICE_PHONE,PERMANENT_PHONE,RESIDENCE_PHONE,PRESENT_PHONE,EMERGENCY_PHONE,POSTAL_PHONE,PREFERRED_PHONE,CONTACT_PHONE,TEMPORARY_PHONE,OFFICE_STREET_NUMBER,COUNTRY,PERMANENT_STREET_NUMBER,RESIDENCE_STREET_NUMBER,TEMP_STREET_NUMBER,EVENTTYPE,OLD_PSX_BATCH_ID,RATIONCARD_NO,PRESENT_COUNTRY,PREFERRED_COUNTRY,CONTACT_COUNTRY,POSTAL_COUNTRY,OFFICE_COUNTRY,PERMANENT_COUNTRY,TEMPORARY_COUNTRY,RESIDENCE_COUNTRY,TIMESTAMP1,TIMESTAMP2,AREA,POLICY_NUMBER,COUNTRY_OF_ORIGIN,LANDMARK,MOBILE,DATE_OF_INCORPORATION,TAN_NO,PROCESS_TYPE,APPLICANT_TYPE,EMPLOYER_NAME,ACCOUNT_NUMBER,CREDIT_CARD_NUMBER,PROCESS_FLAG,PSX_SOURCE_SYSTEM_NAME,UCIN_FLAG,CA_NUMBER,CIN,DIN,REGISTRATION_NO,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,SEGMENT,FILLER_STRING_1,FILLER_STRING_2,FILLER_STRING_3,FILLER_STRING_4,FILLER_STRING_5,FILLER_DATE_1,FILLER_DATE_2,FILLER_NUMBER_1,FILLER_NUMBER_2,FILLER_NUMBER_3,FILLER_NUMBER_4,FILLER_NUMBER_5,FILLER_STRING_6,FILLER_STRING_7,FILLER_STRING_8,FILLER_STRING_9,BATCHID,RECORD_TYPE,MATCHING_RULE,SOURCE_SYS_ID,AREA_ID,SOURCE_SYSTEM,CUST_UNQ_ID,SEGMENT,WEIGHTAGES,MATCH_TYPE,INSERT_TIME,LCHGTIME,CUSTOMER_CONTACT_TYPE,PERMANENT_STD_CODE,PREFERRED_STD_CODE,PRESENT_STD_CODE,EMERGENCY_STD_CODE,TEMPORARY_STD_CODE,CONTACT_STD_CODE,RESIDENCE_STD_CODE,OFFICE_STD_CODE,POSTAL_STD_CODE,DEAL_ID,PRODUCT,MATCH_PROFILE,DATASOURCE,INTRADAY_REQUEST_ID,REQUEST_ID,REQUEST_ID_BFL,REQUEST_ID_FIN,FIRSTNAME,MIDDLENAME,LASTNAME,APPLICATION_NO,LOAN_APP_NO,CUSTOMER_TYPE,FILLER_STRING_1,FILLER_STRING_2,FILLER_STRING_3,FILLER_STRING_4,FILLER_STRING_5,FILLER_STRING_6,FILLER_STRING_7,FILLER_STRING_8,FILLER_STRING_9,FILLER_DATE_1,FILLER_DATE_2,FILLER_NUMBER_1,FILLER_NUMBER_2,FILLER_NUMBER_3,FILLER_NUMBER_4,FILLER_NUMBER_5 from PSX_NSP_REQUEST_RESULTS WHERE REQUEST_ID like ?";
			jdbctemplate.setFetchSize(1000);
			return jdbctemplate
					.query(query,
							(ResultSet rs, int rowNumber) -> {
								MatchedCustomerDetails details1 = new MatchedCustomerDetails();
								details1.setName(rs.getString("NAME"));
								details1.setFatherName(rs
										.getString("FATHER_NAME"));
								details1.setMotherName(rs
										.getString("MOTHER_NAME"));
								details1.setSpouseName(rs
										.getString("SPOUSE_NAME"));
								details1.setDateOfInCorporation(rs
										.getString("DOI"));
								details1.setDoi(rs.getString("DOI"));
								details1.setDob1(rs.getString("DOB1"));
								details1.setDob2(rs.getString("DOB2"));
								details1.setDob3(rs.getString("DOB3"));
								details1.setDob4(rs.getString("DOB4"));
								details1.setPan(rs.getString("PAN"));
								details1.setPassport(rs.getString("PASSPORT"));
								details1.setVoterID(rs.getString("VOTERID"));
								details1.setDrivingLIC(rs
										.getString("DRIVINGLIC"));
								details1.setAadhaar(rs.getString("AADHAAR"));
								details1.setGender(rs.getString("GENDER"));
								details1.setCrnNo(rs.getString("CRNNO"));
								details1.setCkycNo(rs.getString("CKYCNO"));
								details1.setGstin(rs.getString("GSTIN"));
								details1.setTanNo(rs.getString("TAN_NO"));
								details1.setProcessType(rs
										.getString("PROCESS_TYPE"));
								details1.setApplicatType(rs
										.getString("APPLICANT_TYPE"));
								details1.setAccountNumber(rs
										.getString("ACCOUNT_NUMBER"));
								details1.setOfficeState(rs
										.getString("OFFICE_STATE"));
								details1.setPermanentState(rs
										.getString("PERMANENT_STATE"));
								details1.setResidenceState(rs
										.getString("RESIDENCE_STATE"));
								details1.setTemporaryState(rs
										.getString("TEMPORARY_STATE"));
								details1.setPresentState((rs
										.getString("PRESENT_STATE")));
								details1.setContactState(rs
										.getString("CONTACT_STATE"));
								details1.setPreferredState(rs
										.getString("PREFERRED_STATE"));
								details1.setCorporatelicence(rs
										.getString("CORPORATELICENCE"));
								details1.setOfficeAddress(rs
										.getString("OFFICE_ADDRESS"));
								details1.setOfficeCity(rs
										.getString("OFFICE_CITY"));
								details1.setOfficePin(rs
										.getString("OFFICE_PIN"));
								details1.setPresentAddress(rs
										.getString("PRESENT_ADDRESS"));
								details1.setPresentCity(rs
										.getString("PRESENT_CITY"));
								details1.setPresentPin(rs
										.getString("PRESENT_PIN"));
								details1.setPermanentCity(rs
										.getString("PERMANENT_CITY"));
								details1.setPermanentPin(rs
										.getString("PERMANENT_PIN"));
								details1.setPermanentAddress(rs
										.getString("PERMANENT_ADDRESS"));
								details1.setEmergencyAddress(rs
										.getString("EMERGENCY_ADDRESS"));
								details1.setEmergencyCity(rs
										.getString("EMERGENCY_CITY"));
								details1.setEmergencyPin(rs
										.getString("EMERGENCY_PIN"));
								details1.setContactAddress(rs
										.getString("CONTACT_ADDRESS"));
								details1.setContactCity(rs
										.getString("CONTACT_CITY"));
								details1.setContactPin(rs
										.getString("CONTACT_PIN"));
								details1.setPostalAddress(rs
										.getString("POSTAL_ADDRESS"));
								details1.setPostalCity(rs
										.getString("POSTAL_CITY"));
								details1.setPostalPin(rs
										.getString("POSTAL_PIN"));
								details1.setResindenceAddress(rs
										.getString("RESIDENCE_ADDRESS"));
								details1.setResidenceCity(rs
										.getString("RESIDENCE_CITY"));
								details1.setResidencePin(rs
										.getString("RESIDENCE_PIN"));
								details1.setPreferredAddress(rs
										.getString("PREFERRED_ADDRESS"));
								details1.setPreferredCity(rs
										.getString("PREFERRED_CITY"));
								details1.setPreferredPin(rs
										.getString("PREFERRED_PIN"));
								details1.setTemporaryAddress(rs
										.getString("TEMPORARY_ADDRESS"));
								details1.setTemporaryCity(rs
										.getString("TEMPORARY_CITY"));
								details1.setTemporaryPin(rs
										.getString("TEMPORARY_PIN"));
								details1.setPresentEmail(rs
										.getString("PRESENT_EMAIL"));
								details1.setEmaergencyEmail(rs
										.getString("EMERGENCY_EMAIL"));
								details1.setContactEmail(rs
										.getString("CONTACT_EMAIL"));
								details1.setOfficeEmail(rs
										.getString("OFFICE_EMAIL"));
								details1.setPreferredEmail(rs
										.getString("PREFERRED_EMAIL"));
								details1.setPermanentEmail(rs
										.getString("PERMANENT_EMAIL"));
								details1.setTemporaryEmail(rs
										.getString("TEMPORARY_EMAIL"));
								details1.setPresentPhone(rs
										.getString("PRESENT_PHONE"));
								details1.setOfficePhone(rs
										.getString("OFFICE_PHONE"));
								details1.setEmergencyPhone(rs
										.getString("EMERGENCY_PHONE"));
								details1.setContactPhone(rs
										.getString("CONTACT_PHONE"));
								details1.setPreferredPhone(rs
										.getString("PREFERRED_PHONE"));
								details1.setPermanentPhone(rs
										.getString("PERMANENT_PHONE"));
								details1.setTemporaryPhone(rs
										.getString("TEMPORARY_PHONE"));
								details1.setResidencePhone(rs
										.getString("RESIDENCE_PHONE"));
								details1.setPostalPhone(rs
										.getString("POSTAL_PHONE"));
								details1.setPreferredPhone(rs
										.getString("PREFERRED_PHONE"));
								details1.setBatchId(rs.getString("BATCHID"));
								details1.setEmployerName(rs
										.getString("EMPLOYER_NAME"));
								details1.setResidenceEmail(rs
										.getString("RESIDENCE_EMAIL"));
								details1.setPostalEmail(rs
										.getString("POSTAL_EMAIL"));
								// details1.setCustomerNo(rs.getInt("CUSTOMER_NO"));
								details1.setCustomerNo(rs
										.getLong("CUSTOMER_NO"));
								details1.setCustomerId(rs
										.getString("CUSTOMER_ID"));
								details1.setRecord_Type(rs
										.getString("RECORD_TYPE"));// CUSTOMER_NO,CUSTOMER_ID
								details1.setMatchingRule(rs
										.getString("MATCHING_RULE"));
								details1.setSourceSysId(rs
										.getString("SOURCE_SYS_ID"));
								details1.setPsxsourceSystem(rs
										.getString("PSX_SOURCE_SYSTEM_NAME"));
								details1.setSourceSystem(rs
										.getString("SOURCE_SYSTEM"));
								if (rs.getString("SOURCE_SYS_ID") != null) {
									try {
										details1.setSourceSystem(getSourceSystemName(rs
												.getInt("SOURCE_SYS_ID")));
									} catch (Exception e) {
										logger.error("Exception while getting SourceSystemName: "
												+ e.getMessage());
									}
								} else {
									details1.setSourceSystem(null);
								}
								details1.setArea_id(rs.getString("AREA_ID"));
								details1.setCust_unq_id(rs
										.getString("CUST_UNQ_ID"));
								details1.setSegment(rs.getString("SEGMENT"));
								details1.setMathcedId(rs
										.getString("CUST_UNQ_ID"));
								details1.setMathcedPercentage(rs
										.getString("WEIGHTAGES"));
								details1.setMatchType(rs
										.getString("MATCH_TYPE"));
								details1.setInsert_time(rs
										.getString("INSERT_TIME")); // rs.getString("")
								details1.setLchgtime(rs.getString("LCHGTIME"));
								details1.setCustomer_contact_type(rs
										.getString("CUSTOMER_CONTACT_TYPE"));
								details1.setPermanent_std_code(rs
										.getString("PERMANENT_STD_CODE"));
								details1.setPreferred_std_code(rs
										.getString("PREFERRED_STD_CODE"));
								details1.setPresent_std_code(rs
										.getString("PRESENT_STD_CODE"));
								details1.setEmergency_std_code(rs
										.getString("EMERGENCY_STD_CODE"));
								details1.setTemporary_std_code(rs
										.getString("TEMPORARY_STD_CODE"));
								details1.setContact_std_code(rs
										.getString("CONTACT_STD_CODE"));
								details1.setResidence_std_code(rs
										.getString("RESIDENCE_STD_CODE"));
								details1.setOffical_std_code(rs
										.getString("OFFICE_STD_CODE"));
								details1.setPostal_std_code(rs
										.getString("POSTAL_STD_CODE"));
								details1.setDealId(rs.getString("DEAL_ID"));
								details1.setProduct(rs.getString("PRODUCT"));
								details1.setMatchProfile(rs
										.getString("MATCH_PROFILE"));
								details1.setDataSource(rs
										.getString("DATASOURCE"));
								// As per the client requirement setting the
								// Request_id
								if ("ONLINE".equalsIgnoreCase(rs
										.getString("RECORD_TYPE"))) {
									details1.setRequest_id(rs
											.getString("INTRADAY_REQUEST_ID"));
								} else {
									details1.setRequest_id(rs
											.getString("REQUEST_ID"));
								}
								details1.setRequest_id_bfl(rs
										.getString("REQUEST_ID_BFL"));
								details1.setRequest_id_fin(rs
										.getString("REQUEST_ID_FIN"));
								details1.setFirstName(rs.getString("FIRSTNAME"));
								details1.setMiddleName(rs
										.getString("MIDDLENAME"));
								details1.setLastName(rs.getString(("LASTNAME")));
								details1.setApplNO(rs
										.getString("APPLICATION_NO"));
								details1.setLoanApplNO(rs
										.getString("LOAN_APP_NO"));
								details1.setCustomerType(rs
										.getString("CUSTOMER_TYPE"));

								details1.setFilterString1(rs
										.getString("FILLER_STRING_1"));
								details1.setFilterStrig2(rs
										.getString("FILLER_STRING_2"));
								details1.setFilterString3(rs
										.getString("FILLER_STRING_3"));
								details1.setFilterString4(rs
										.getString("FILLER_STRING_4"));
								details1.setFilterString5(rs
										.getString("FILLER_STRING_5"));
								details1.setFilterString6(rs
										.getString("FILLER_STRING_6"));
								details1.setFilterString7(rs
										.getString("FILLER_STRING_7"));
								details1.setFilterString8(rs
										.getString("FILLER_STRING_8"));
								details1.setFilterString9(rs
										.getString("FILLER_STRING_9"));
								details1.setFilterDate1(rs.getDate("FILLER_DATE_1"));
								details1.setFilterDate1(rs.getDate("FILLER_DATE_2"));
								details1.setFilterNumber1(rs.getLong("FILLER_NUMBER_1"));
								details1.setFilterNumber2(rs.getLong("FILLER_NUMBER_2"));
								details1.setFilterNumber3(rs.getLong("FILLER_NUMBER_3"));
								details1.setFilterNumber4(rs.getLong("FILLER_NUMBER_4"));
								details1.setFilterNumber5(rs.getLong("FILLER_NUMBER_5"));
								details1.setCin(rs.getString("CIN"));
								details1.setDin(rs.getString("DIN"));
								details1.setCreditCardNumber(rs.getString("CREDIT_CARD_NUMBER"));
								details1.setCaNumber(rs.getString("CA_NUMBER"));
								details1.setRegistrationNo(rs.getString("REGISTRATION_NO"));
								return details1;
							}, requestId);
		} catch (Exception e) {
			logger.error("Exception while getting getmatchedCustomers: "
					+ e.getMessage());
			return null;

		}
	}

	/***
	 * This method is used to get Source System Name for that particular
	 * SourceSysID
	 */
	protected String getSourceSystemName(int sourceSysId2) throws DAOException {

		List<String> strLst = new ArrayList<String>();
		String sql = "SELECT SOURCE_NAME FROM PSX_DATASOURCE_MST_T WHERE SOURCE_SYS_ID=?";
		try {
			// sourceSystemName = (String) jdbctemplate.query(sql, new Object[]
			// {
			// sourceSysId2 }, String.class);

			return jdbctemplate.query(sql, (ResultSet rs) -> {
				String sourceSystemName = "";
				while (rs.next()) {
					strLst.add(rs.getString("SOURCE_NAME"));
				}
				if (strLst.isEmpty()) {
					sourceSystemName = "";
				} else if (strLst.size() == 1) { // list contains exactly 1
													// element
					sourceSystemName = strLst.get(0);
				} else { // list contains more than 1 elements
					// your wish, you can either throw the exception or
					// return 1st element.
					sourceSystemName = "";
				}
				return sourceSystemName;

			}, sourceSysId2);
		} catch (Exception e) {
			logger.error("Exception while getting getSourceSystemName: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while fetching SourceSystem Name"));
		}
	}

	/***
	 * This method is used to get Matched ProfileId for that given Product
	 */
	@Override
	public String getProductProfileId(String product) throws Exception {
		logger.info("Inside getProductProfileId " + product);
		String profileId;
		String sql = "SELECT PROFILE_ID FROM PRODUCT_PM_PROFILE_ID WHERE PRODUCT_TYPE=?";
		try {
			profileId = (String) jdbctemplate.queryForObject(sql,
					new Object[] { product }, String.class);

		} catch (Exception e) {
			logger.error("Exception while getting getProductProfileId: "
					+ e.getMessage());
			throw (new DAOException(
					"There is no Valid ProfileID for the given Product"));
		}
		return profileId;
	}

	/***
	 * This method is used to get Matched Org for that given Product
	 */
	@Override
	public String getProductOrg(String product) throws Exception {
		logger.info("Inside getProductOrg for Product " + product);
		String productOrg = null;
		String sql = "SELECT ORG FROM product_ORG_MAPPING_T WHERE PRODUCT_TYPE=?";
		try {
			productOrg = (String) jdbctemplate.queryForObject(sql,
					new Object[] { product }, String.class);

		} catch (Exception e) {
			logger.error("Exception while getting getProductOrg: "
					+ e.getMessage());
			throw (new DAOException(
					"There is no Valid Org for the given Product"));
		}
		return productOrg;
	}

	/***
	 * This method is used to get PsxNspProfiles information for that given
	 * Product
	 */
	public List<String> getPsxNspProfiles(String profileid) throws Exception {
		logger.info("Inside getPsxNspProfiles for ProfileID ::" + profileid
				+ "::"); // 2
		String sql = "SELECT matching_rule_csv,residual_parameters, weightages_csv, scale_stringent_csv,ranking_csv FROM psx_nsp_profiles WHERE active='Y' AND profile_id=?";
		List<String> ls = new ArrayList<String>();
		try {
			return jdbctemplate.query(sql, (ResultSet rs) -> {
				while (rs.next()) {
					ls.add(rs.getString("MATCHING_RULE_CSV"));
					ls.add(rs.getString("RESIDUAL_PARAMETERS"));
					ls.add(rs.getString("WEIGHTAGES_CSV"));
					ls.add(rs.getString("SCALE_STRINGENT_CSV"));
					ls.add(rs.getString("RANKING_CSV"));
				}
				return ls;
			}, profileid);
		} catch (Exception e) {
			logger.error("Exception while getting getPsxNspProfiles: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while fetching Matching Rules"));
		}
	}

	@SuppressWarnings("null")
	public Map<String, ArrayList<String>> getActiveProfiles() throws Exception {
		logger.info("Inside getActiveProfiles::" + "::"); // 2
		Map<String, ArrayList<String>> psxNspProfiles = new HashMap();
		ArrayList<String> ls = new ArrayList<String>();
		String sql = "SELECT profile_id,matching_rule_csv,residual_parameters, weightages_csv, scale_stringent_csv,ranking_csv FROM psx_nsp_profiles WHERE active='Y'";

		try {
			return jdbctemplate.query(sql,
					new ResultSetExtractor<Map<String, ArrayList<String>>>() {

						@Override
						public Map<String, ArrayList<String>> extractData(
								ResultSet rs) throws SQLException,
								DataAccessException {
							while (rs.next()) {
								ArrayList<String> ls = new ArrayList<String>();
								ls.add(rs.getString("MATCHING_RULE_CSV"));
								ls.add(rs.getString("RESIDUAL_PARAMETERS"));
								ls.add(rs.getString("WEIGHTAGES_CSV"));
								ls.add(rs.getString("SCALE_STRINGENT_CSV"));
								ls.add(rs.getString("RANKING_CSV"));

								psxNspProfiles.put(rs.getString("PROFILE_ID"),
										ls);
							}
							return psxNspProfiles;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while getting getPsxNspProfiles: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while fetching Matching Rules"));
		}
	}

	/***
	 * This method is used to generate a Sequence Number for RequestID
	 */
	@Override
	public long appNextValProc() throws Exception {
		String sql = "select appnextval('crms_req_psx_id') from dual";
		try {
			return jdbctemplate.query(sql, (ResultSet rs) -> {
				Long reqPsxid = 0L;
				while (rs.next()) {
					reqPsxid = rs.getLong(1);
				}
				return reqPsxid;
			});
		} catch (Exception e) {
			logger.error("Exception while getting appNextValProc: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while generating  Request_Id"));
		}
	}

	/***
	 * This method is used to check request status information for particular
	 * request_id
	 */
	@Override
	public List<String> checkRequestStatus(long reqPsxid) throws Exception {
		// String sql =
		// "SELECT REQUEST_STATUS,MATCH_COUNT,NODE2_MATCH_COUNT,COUNTER,NODE1_MATCH_COUNT,NEGATIVE_MATCH_COUNT,NODE1_REQUEST_STATUS,NODE2_REQUEST_STATUS FROM PSX_NSP_REQUEST WHERE REQUEST_ID=?";
		String sql = "SELECT NODE1_MATCH_COUNT,NODE2_MATCH_COUNT,COUNTER,NODE1_REQUEST_STATUS,NODE2_REQUEST_STATUS,REQUEST_STATUS,MATCH_COUNT,DEAL_ID,REQUEST_ID_BFL,REQUEST_ID_FIN,DATA_SOURCE,PRODUCT FROM PSX_NSP_REQUEST WHERE REQUEST_ID=?";
		List<String> ls = new ArrayList<String>();
		try {
			return jdbctemplate.query(sql, (ResultSet rs) -> {
				while (rs.next()) {
					// ls.add(rs.getString("REQUEST_STATUS"));
					ls.add(rs.getString("NODE1_MATCH_COUNT"));
					ls.add(rs.getString("NODE2_MATCH_COUNT"));
					ls.add(rs.getString("COUNTER"));
					// ls.add(rs.getString("NEGATIVE_MATCH_COUNT"));
					ls.add(rs.getString("NODE1_REQUEST_STATUS"));
					ls.add(rs.getString("NODE2_REQUEST_STATUS"));
					ls.add(rs.getString("REQUEST_STATUS"));
					ls.add(rs.getString("MATCH_COUNT"));
					if (rs.getString("DEAL_ID") != null) {
						ls.add(rs.getString("DEAL_ID"));
					} else {
						ls.add("");
					}
					ls.add(rs.getString("REQUEST_ID_BFL"));
					if (rs.getString("REQUEST_ID_FIN") != null) {
						ls.add(rs.getString("REQUEST_ID_FIN"));
					} else {
						ls.add("");
					}
					if (rs.getString("DATA_SOURCE") != null) {
						ls.add(rs.getString("DATA_SOURCE"));
					} else {
						ls.add("");
					}
					if (rs.getString("PRODUCT") != null) {
						ls.add(rs.getString("PRODUCT"));
					} else {
						ls.add("");
					}
				}
				return ls;
			}, reqPsxid + "");
		} catch (Exception e) {
			logger.error("Exception while getting checkRequestStatus: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while getting RequestStatus and MatchCount"));
		}
	}

	public MatchedLoanDetails getmatchedLoanDetails(int customerNo)
			throws Exception {
		String sql = "select BATCHID,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,DEAL_ID,LAN_NO,CUSTOMER_TYPE,APPLN_NO,PRODUCT_CODE,PROCESS_TYPE,PROCESS_FLAG,ERROR_CODE,ERROR_DESC,PSX_BATCH_ID,PSX_ID,SOURCE_SYSTEM,CUST_UNQ_ID,SEGMENT from PSX_DEDUP_EOD_CUST_LOAN_DTL WHERE CUSTOMER_NO=";
		try {
			MatchedLoanDetails loandetails = new MatchedLoanDetails();
			return jdbctemplate.query(sql, (ResultSet rs) -> {
				loandetails.setApplnNo(rs.getString("APPLN_NO"));
				loandetails.setBatchId(rs.getString("BATCHID"));
				loandetails.setCustomerId(rs.getString("CUSTOMER_ID"));
				loandetails.setCustomerNo(rs.getString("CUSTOMER_NO"));
				loandetails.setCustomerType(rs.getString("CUSTOMER_TYPE"));
				loandetails.setDealId(rs.getString("DEAL_ID"));
				loandetails.setErrorCode(rs.getString("ERROR_CODE"));
				loandetails.setErrorDesc(rs.getString("ERROR_DESC"));
				loandetails.setLanNo(rs.getString("LAN_NO"));
				loandetails.setProcessFlag(rs.getString("PROCESS_FLAG"));
				loandetails.setProcessType(rs.getString("PROCESS_TYPE"));
				loandetails.setProductCode(rs.getString("PRODUCT_CODE"));
				loandetails.setPsxBatchId(rs.getString("PSX_BATCH_ID"));
				loandetails.setPsxId(rs.getString("PSX_ID"));
				loandetails.setSourceSysId(rs.getString("SOURCE_SYS_ID"));
				loandetails.setSourceSystem(rs.getString("SOURCE_SYSTEM"));
				//CUST_UNQ_ID,SEGMENT
				loandetails.setCustUnqId(rs.getString("CUST_UNQ_ID"));
				loandetails.setSegment(rs.getString("SEGMENT"));
				return loandetails;
			}, customerNo);
		} catch (Exception e) {
			logger.error("Exception while getting getmatchedLoanDetails: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while getting MatchedLoanDetails"));
		}
	}

	
	public List<MatchedLoanDetails> getmatchedLoanDetailsList(int customerNo)
			throws Exception {
		System.out.println("Getting the Loan Details for the given Customer No::: "+customerNo);
		List<MatchedLoanDetails> matchedLoanDetailsList =  new ArrayList<>();;
		String sql = "select BATCHID,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,DEAL_ID,LAN_NO,CUSTOMER_TYPE,APPLN_NO,PRODUCT_CODE,PROCESS_TYPE,PROCESS_FLAG,ERROR_CODE,ERROR_DESC,PSX_BATCH_ID,PSX_ID,SOURCE_SYSTEM,CUST_UNQ_ID,SEGMENT from PSX_DEDUP_EOD_CUST_LOAN_DTL WHERE CUSTOMER_NO=?";
		try {
			return jdbctemplate.query(sql, (ResultSet rs) -> {
				while (rs.next()) {
					MatchedLoanDetails loandetails = new MatchedLoanDetails();

					loandetails.setApplnNo(rs.getString("APPLN_NO"));
					loandetails.setBatchId(rs.getString("BATCHID"));
					loandetails.setCustomerId(rs.getString("CUSTOMER_ID"));
					loandetails.setCustomerNo(rs.getString("CUSTOMER_NO"));
					loandetails.setCustomerType(rs.getString("CUSTOMER_TYPE"));
					loandetails.setDealId(rs.getString("DEAL_ID"));
					loandetails.setErrorCode(rs.getString("ERROR_CODE"));
					loandetails.setErrorDesc(rs.getString("ERROR_DESC"));
					loandetails.setLanNo(rs.getString("LAN_NO"));
					loandetails.setProcessFlag(rs.getString("PROCESS_FLAG"));
					loandetails.setProcessType(rs.getString("PROCESS_TYPE"));
					loandetails.setProductCode(rs.getString("PRODUCT_CODE"));
					loandetails.setPsxBatchId(rs.getString("PSX_BATCH_ID"));
					loandetails.setPsxId(rs.getString("PSX_ID"));
					loandetails.setSourceSysId(rs.getString("SOURCE_SYS_ID"));
					loandetails.setSourceSystem(rs.getString("SOURCE_SYSTEM"));
					loandetails.setCustUnqId(rs.getString("CUST_UNQ_ID"));
					loandetails.setSegment(rs.getString("SEGMENT"));
					matchedLoanDetailsList.add(loandetails);
				}
				return matchedLoanDetailsList;
			}, customerNo);
		} catch (Exception e) {
			logger.error("Exception while getting getmatchedLoanDetails: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while getting MatchedLoanDetails"));
		}
	}

	@Override
	public CustomerRequestDetails getCustomerRequestDetails(long requestId)
			throws Exception {
		logger.info("Inside getCustomerRequestDetails " + requestId);
		String sql = "select PSX_BATCH_ID,NAME,MOTHER_NAME,FATHER_NAME,SPOUSE_NAME,DOI,PAN,PASSPORT,DRIVINGLIC,AADHAAR,GENDER,MATCH_COUNT,OFFICE_ADDRESS,OFFICE_CITY,OFFICE_PIN,OFFICE_EMAIL,OFFICE_PHONE,TAN_NO,AREA,LANDMARK,PRESENT_CITY,MOBILE,PRESENT_PIN,PRESENT_EMAIL,PRESENT_PHONE,CUSTOMER_NO,EMPLOYER_NAME,CREDIT_CARD_NUMBER,PROCESS_FLAG,APPLICANT_TYPE,CUSTOMER_CONTACT_TYPE from PSX_NSP_REQUEST where REQUEST_ID=?";
		try {
			CustomerRequestDetails requestdetails = new CustomerRequestDetails();
			return jdbctemplate
					.query(sql,
							(ResultSet rs) -> {
								requestdetails.setAadhaar(rs
										.getString("AADHAAR"));
								requestdetails.setName(rs.getString("NAME"));
								requestdetails.setBatch(rs
										.getString("PSX_BATCH_ID"));
								requestdetails.setMotherName(rs
										.getString("MOTHER_NAME"));
								requestdetails.setFatherName(rs
										.getString("FATHER_NAME"));
								requestdetails.setSpouseName(rs
										.getString("SPOUSE_NAME"));
								requestdetails.setDateOfInCorporation(rs
										.getString("DOI"));
								requestdetails.setPan(rs.getString("PAN"));
								requestdetails.setPassportNo(rs
										.getString("PASSPORT"));
								requestdetails.setDrivingLicenseNumber(rs
										.getString("DRIVINGLIC"));
								requestdetails.setGender(rs.getString("GENDER"));
								requestdetails.setMatchCount(rs
										.getInt("MATCH_COUNT"));
								requestdetails.setAddress1Office(rs
										.getString("OFFICE_ADDRESS"));
								requestdetails.setPinOffice(rs
										.getString("OFFICE_PIN"));
								requestdetails.setEmailOffice(rs
										.getString("OFFICE_EMAIL"));
								requestdetails.setLandLine1Office(rs
										.getString("OFFICE_PHONE"));
								requestdetails.setTanNo(rs.getString("TAN_NO"));
								requestdetails.setArea(rs.getString("AREA"));
								requestdetails.setLandMark(rs
										.getString("LANDMARK"));
								requestdetails.setCity(rs
										.getString("PRESENT_CITY"));
								requestdetails.setMobile(rs.getString("MOBILE"));
								requestdetails.setPin(rs
										.getString("PRESENT_PIN"));
								requestdetails.setCustomerNo(rs
										.getString("CUSTOMER_NO"));
								requestdetails.setEmployerName(rs
										.getString("EMPLOYER_NAME"));
								requestdetails.setProcessFLG(rs
										.getString("PROCESS_FLAG"));
								requestdetails.setCreditCardNumber(rs
										.getString("CREDIT_CARD_NUMBER"));
								requestdetails.setApplicationType(rs
										.getString("APPLICANT_TYPE"));
								requestdetails.setCustomerType(rs
										.getString("CUSTOMER_CONTACT_TYPE"));
								return requestdetails;
							}, requestId);
		} catch (Exception e) {
			logger.error("Exception while getting getCustomerRequestDetails: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while getting CustomerRequestDetails"));
		}
	}

	@Override
	public List<String> getSourceSystem(long reqPsxid) {
		logger.info("Inside getSourceSystem for Request_id " + reqPsxid);
		String sql = "SELECT PSX_SOURCE_SYSTEM_NAME FROM PSX_NSP_REQUEST_RESULTS WHERE REQUEST_ID=? and PSX_SOURCE_SYSTEM_NAME is not null";
		List<String> ls = new ArrayList<String>();
		try {
			return jdbctemplate.query(sql, (ResultSet rs) -> {
				while (rs.next()) {
					ls.add(rs.getString("PSX_SOURCE_SYSTEM_NAME"));
				}
				return ls;
			}, reqPsxid);
		} catch (Exception e) {
			logger.error("Exception while getting checkRequestStatus: "
					+ e.getMessage());
			throw (e);
		}
	}

	@Override
	public List<String> getNegativeMatchIds(long reqPsxid) {
		logger.info("Inside checkRequestStatus " + reqPsxid);
		String sql = "SELECT DISTINCT AREA_ID FROM PSX_NSP_REQUEST_RESULTS WHERE REQUEST_ID=? and AREA_ID is not null";
		List<String> ls = new ArrayList<String>();
		try {
			return jdbctemplate.query(sql,
					new ResultSetExtractor<List<String>>() {
						@Override
						public List<String> extractData(ResultSet rs)
								throws SQLException, DataAccessException {
							while (rs.next()) {
								ls.add(rs.getString("AREA_ID"));
							}
							return ls;
						}
					}, reqPsxid);
		} catch (Exception e) {
			logger.error("Exception while getting checkRequestStatus: "
					+ e.getMessage());
			throw (e);
		}
	}

	@Override
	public void saveCustomerRequestToNodeTwoINTRADAY(
			CustomerRequest customerRequest, long reqPsxid, Date lchgTime)
			throws Exception {
		logger.info("Inside Saving Request Record into NodeTwoIntraday Table for RequestId "
				+ reqPsxid);
		String flag = "I_OR_U";
		String eventType = "INTRADAY";
		String doi = customerRequest.getRequestDetails()
				.getDateOfInCorporation();
		if (doi == null) {
			doi = "";
		}
		String dob1 = customerRequest.getRequestDetails().getDob1();
		if (dob1 == null) {
			dob1 = "";
		}
		String dob2 = customerRequest.getRequestDetails().getDob2();
		if (dob2 == null) {
			dob2 = "";
		}
		String dob3 = customerRequest.getRequestDetails().getDob3();
		if (dob3 == null) {
			dob3 = "";
		}
		String dob4 = customerRequest.getRequestDetails().getDob4();
		if (dob4 == null) {
			dob4 = "";
		}
		if (customerRequest.getRequestDetails().getFirstName().equals("null")) {
			customerRequest.getRequestDetails().setFirstName("");
		}
		if (customerRequest.getRequestDetails().getMiddleName().equals("null")) {
			customerRequest.getRequestDetails().setMiddleName("");
		}
		if (customerRequest.getRequestDetails().getLastName().equals("null")) {
			customerRequest.getRequestDetails().setLastName("");
		}
		String dob = customerRequest.getRequestDetails().getDob1();
		String convertedDob1 = "";
		String[] dob1Split = new String[3];
		DateFormat df1 = new SimpleDateFormat("dd/MMM/yyyy"); // for parsing
																// input
		DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); // for formatting
																// output
		if (dob != null && dob != "") {
			Date d = df1.parse(dob1);
			convertedDob1 = df2.format(d);
			dob1Split = convertedDob1.split("/");
		}
		System.out.println("In NODE 2 "
				+ customerRequest.getRequestDetails().getEmployerName());

		try {
			/**
			 * Changes added as on 07th June 2019 -- Added
			 * FIRSTNAME,MIDDLENAME,LASTNAME,DAY,MONTH,YEAR and place holders
			 */
			String sql = "insert into PSX_NSP_INTRADAY_2"
					+ "(ADDRESS,PSX_ID,PSX_BATCH_ID,AREA_UNQ_ID,NAME,MOTHER_NAME,FATHER_NAME,SPOUSE_NAME,DOI,DOB1,DOB2,DOB3,DOB4,"
					+ "VOTERID,CRNNO,ACCOUNT_NUMBER,CIN,CKYCNO,CONTACT_ADDRESS,CONTACT_CITY,CONTACT_EMAIL,CONTACT_PHONE,CONTACT_PIN,"
					+ "DIN,EMERGENCY_ADDRESS,EMERGENCY_CITY,EMERGENCY_EMAIL,EMERGENCY_PHONE,EMERGENCY_PIN,EMPLOYER_NAME,ERROR_DESC,"
					+ "PAN,PASSPORT,DRIVINGLIC,AADHAAR,GENDER,OFFICE_ADDRESS,OFFICE_CITY,OFFICE_PIN,OFFICE_EMAIL,OFFICE_PHONE,"
					+ "PERMANENT_ADDRESS,PERMANENT_CITY,PERMANENT_PIN,PERMANENT_EMAIL,PERMANENT_PHONE,PHONE,TAN_NO,AREA,LANDMARK,PRESENT_ADDRESS,PRESENT_CITY,PRESENT_PIN,PRESENT_EMAIL,PRESENT_PHONE,"
					+ "POSTAL_ADDRESS,POSTAL_CITY,POSTAL_EMAIL,POSTAL_PHONE,POSTAL_PIN,PREFERRED_ADDRESS,PREFERRED_CITY,PREFERRED_EMAIL,PREFERRED_PHONE,PREFERRED_PIN,"
					+ "PROCESS_FLAG,REGISTRATION_NO,RESIDENCE_ADDRESS,RESIDENCE_CITY,RESIDENCE_EMAIL,RESIDENCE_PHONE,RESIDENCE_PIN,SEGMENT,"
					+ "TEMPORARY_ADDRESS,TEMPORARY_CITY,TEMPORARY_EMAIL,TEMPORARY_PHONE,TEMPORARY_PIN,MOBILE,CUSTOMER_NO,CREDIT_CARD_NUMBER,APPLICANT_TYPE,INSERT_TIME,"
					+ "CUSTOMER_CONTACT_TYPE,CUST_UNQ_ID,DUIFLAG,EVENTTYPE,LCHGTIME,CUSTOMER_ID,SOURCE_SYSTEM,SOURCE_SYS_ID,AGE,APPLICATION_NO,ASSET_CATEGORY,BATCHID,CITY_CLASSIFICATION,"
					+ "CREDIT_PROGRAM,CUSTOMER_STATUS,CUSTOMER_TYPE,CUST_SR_NO,EMPLOYMENT_BUSINESS,LANDMARK_OFFICE,LAN_2,LAN_NO,LOAN_APP_NO,MOBILE_OFFICE,ORG,PRODUCT,PRODUCT_CODE,RESIDENT_TYPE,"
					+ "STAYING_SINCE,STD,STD_OFFICE,MATCH_PROFILE,FIRSTNAME,MIDDLENAME,LASTNAME,DAY,MONTH,YEAR,DEAL_ID,DATASOURCE,REQUEST_ID_FIN) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			jdbctemplate
					.update(sql,
							new Object[] {
									customerRequest.getRequestDetails()
											.getAddress(),
									reqPsxid,
									reqPsxid,
									reqPsxid,
									customerRequest.getRequestDetails()
											.getFirstName()
											+ " "
											+ customerRequest
													.getRequestDetails()
													.getMiddleName()
											+ " "
											+ customerRequest
													.getRequestDetails()
													.getLastName(),
									customerRequest.getRequestDetails()
											.getMotherName(),
									customerRequest.getRequestDetails()
											.getFatherName(),
									customerRequest.getRequestDetails()
											.getSpouseName(),
									doi,
									dob1,
									dob2,
									dob3,
									dob4,
									customerRequest.getRequestDetails()
											.getVoterId(),
									customerRequest.getRequestDetails()
											.getCrnNo(),
									customerRequest.getRequestDetails()
											.getAccountNumber(),
									customerRequest.getRequestDetails()
											.getCin(),
									customerRequest.getRequestDetails()
											.getCkyCno(),
									customerRequest.getRequestDetails()
											.getContact_address(),
									customerRequest.getRequestDetails()
											.getContact_city(),
									customerRequest.getRequestDetails()
											.getContact_email(),
									customerRequest.getRequestDetails()
											.getContact_phone(),
									customerRequest.getRequestDetails()
											.getContact_pin(),
									customerRequest.getRequestDetails()
											.getDin(),
									customerRequest.getRequestDetails()
											.getEmergency_address(),
									customerRequest.getRequestDetails()
											.getEmergency_city(),
									customerRequest.getRequestDetails()
											.getEmergency_email(),
									customerRequest.getRequestDetails()
											.getEmergency_phone(),
									customerRequest.getRequestDetails()
											.getEmergency_pin(),
									customerRequest.getRequestDetails()
											.getEmployerName(),
									customerRequest.getRequestDetails()
											.getErrorDesc(),
									customerRequest.getRequestDetails()
											.getPan(),
									customerRequest.getRequestDetails()
											.getPassportNo(),
									customerRequest.getRequestDetails()
											.getDrivingLicenseNumber(),
									customerRequest.getRequestDetails()
											.getAadhaar(),
									customerRequest.getRequestDetails()
											.getGender(),
									// customerRequest.getRequestDetails().getOffice_address(),
									customerRequest.getRequestDetails()
											.getAddress1Office()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress2Office()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress3Office(),
									// customerRequest.getRequestDetails().getOffice_city(),
									customerRequest.getRequestDetails()
											.getCityOffice(),
									// customerRequest.getRequestDetails().getOffice_pin(),
									customerRequest.getRequestDetails()
											.getPinOffice(),
									// customerRequest.getRequestDetails().getOffice_email(),
									customerRequest.getRequestDetails()
											.getEmailOffice(),
									// customerRequest.getRequestDetails().getOffice_phone(),
									customerRequest.getRequestDetails()
											.getLandLine1Office(),
									customerRequest.getRequestDetails()
											.getPermanent_address(),
									customerRequest.getRequestDetails()
											.getPermanent_city(),
									customerRequest.getRequestDetails()
											.getPermanent_pin(),
									customerRequest.getRequestDetails()
											.getPermanent_email(),
									// customerRequest.getRequestDetails().getPermanent_phone(),
									customerRequest.getRequestDetails()
											.getLandLine2Office(),
									customerRequest.getRequestDetails()
											.getPhone(),
									customerRequest.getRequestDetails()
											.getTanNo(),
									customerRequest.getRequestDetails()
											.getArea(),
									customerRequest.getRequestDetails()
											.getLandMark(),
									customerRequest.getRequestDetails()
											.getPresent_address(),
									customerRequest.getRequestDetails()
											.getPresent_city(),
									customerRequest.getRequestDetails()
											.getPresent_pin(),
									customerRequest.getRequestDetails()
											.getPresent_email(),
									customerRequest.getRequestDetails()
											.getPresent_phone(),
									customerRequest.getRequestDetails()
											.getPostal_address(),
									customerRequest.getRequestDetails()
											.getPostal_city(),
									customerRequest.getRequestDetails()
											.getPostal_email(),
									// customerRequest.getRequestDetails().getPostal_phone(),
									customerRequest.getRequestDetails()
											.getPhone(),
									customerRequest.getRequestDetails()
											.getPostal_pin(),
									customerRequest.getRequestDetails()
											.getPreferred_address(),
									customerRequest.getRequestDetails()
											.getPreferred_city(),
									customerRequest.getRequestDetails()
											.getPreferred_email(),
									// customerRequest.getRequestDetails().getPreferred_phone(),
									customerRequest.getRequestDetails()
											.getMobileOffice(),
									customerRequest.getRequestDetails()
											.getPreferred_pin(),
									customerRequest.getRequestDetails()
											.getProcessFLG(),
									customerRequest.getRequestDetails()
											.getRegistrationNo(),
									// customerRequest.getRequestDetails().getResidence_address(),
									customerRequest.getRequestDetails()
											.getAddress1()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress2()
											+ "  "
											+ customerRequest
													.getRequestDetails()
													.getAddress3(),
									// customerRequest.getRequestDetails().getResidence_city(),
									customerRequest.getRequestDetails()
											.getCity(),
									// customerRequest.getRequestDetails().getResidence_email(),
									customerRequest.getRequestDetails()
											.getEmail(),
									// customerRequest.getRequestDetails().getResidence_phone(),
									customerRequest.getRequestDetails()
											.getLandLine1(),
									// customerRequest.getRequestDetails().getResidence_pin(),
									customerRequest.getRequestDetails()
											.getPin(),
									customerRequest.getRequestDetails()
											.getSegment(),
									customerRequest.getRequestDetails()
											.getTemporary_address(),
									customerRequest.getRequestDetails()
											.getTemporary_city(),
									customerRequest.getRequestDetails()
											.getTemporary_email(),
									// customerRequest.getRequestDetails().getTemporary_phone(),
									customerRequest.getRequestDetails()
											.getLandLine2(),
									customerRequest.getRequestDetails()
											.getTemporary_pin(),
									customerRequest.getRequestDetails()
											.getMobile(),
									customerRequest.getRequestDetails()
											.getCustomerNo(),
									customerRequest.getRequestDetails()
											.getCreditCardNumber(),
									customerRequest.getRequestDetails()
											.getApplicationType(),
									lchgTime,
									// customerRequest.getRequestDetails().getCustomerType(),
									// reqPsxid, flag,
									customerRequest.getRequestDetails()
											.getCustomerType(),
									customerRequest.getBflRequestId(),
									flag,
									eventType,
									lchgTime,
									customerRequest.getRequestDetails()
											.getCustomerNo(),
									"INTRADAY",
									"99",

									customerRequest.getRequestDetails()
											.getAge(),
									customerRequest.getRequestDetails()
											.getApplnNo(),
									customerRequest.getRequestDetails()
											.getAssetCategory(),
									customerRequest.getRequestDetails()
											.getBatch(),
									customerRequest.getRequestDetails()
											.getCityClassification(),
									customerRequest.getRequestDetails()
											.getCreditProgram(),
									customerRequest.getRequestDetails()
											.getCustomerStatus(),
									customerRequest.getRequestDetails()
											.getCustomerType(),
									customerRequest.getRequestDetails()
											.getCustSrNo(),
									customerRequest.getRequestDetails()
											.getEmploymentBusiness(),
									customerRequest.getRequestDetails()
											.getLandMarkOffice(),
									customerRequest.getRequestDetails()
											.getLanNo2(),
									customerRequest.getRequestDetails()
											.getLanNo(),
									customerRequest.getRequestDetails()
											.getLoanAppNo(),
									customerRequest.getRequestDetails()
											.getMobileOffice(),
									customerRequest.getRequestDetails()
											.getOrg(),
									customerRequest.getRequestDetails()
											.getProduct(),
									customerRequest.getRequestDetails()
											.getProductCode(),
									customerRequest.getRequestDetails()
											.getResidentType(),
									customerRequest.getRequestDetails()
											.getStayingSince(),
									customerRequest.getRequestDetails()
											.getStd(),
									customerRequest.getRequestDetails()
											.getStdOffice(),
									customerRequest.getMatchProfile(),

									// Changes added as on 07th June 2019
									// FIRSTNAME,MIDDLENAME,LASTNAME,DAY,MONTH,YEAR
									customerRequest.getRequestDetails()
											.getFirstName(),
									customerRequest.getRequestDetails()
											.getMiddleName(),
									customerRequest.getRequestDetails()
											.getLastName(), dob1Split[0],
									dob1Split[1], dob1Split[2],
									customerRequest.getDealId(),
									customerRequest.getDataSource(),
									customerRequest.getRequestIdFin()

							});
			logger.info("Record Inserted into  NodeTwoIntraday");
		} catch (Exception ex) {
			logger.error("Exception while getting saveCustomerRequestToNodeTwoINTRADAY: "
					+ ex.getMessage());// $NON-for
			// throw (new
			// DAOException("Exception encountered while inserting Node2_Intraday_Record"));
			throw (new DAOException(ex.getMessage()));
		}
	}

	@Override
	public List<String> getProfileIds() {
		logger.info("Inside getProfileIds ");

		String sql = "SELECT PROFILE_ID FROM PSX_NSP_PROFILES WHERE ACTIVE='Y'";
		List<String> ls = new ArrayList<String>();
		try {
			return jdbctemplate.query(sql,
					new ResultSetExtractor<List<String>>() {

						@Override
						public List<String> extractData(ResultSet rs)
								throws SQLException, DataAccessException {

							while (rs.next()) {
								ls.add(rs.getString("PROFILE_ID"));
							}
							return ls;
						}
					});
		} catch (Exception e) {
			logger.error("Exception while getting getProfileIds: "
					+ e.getMessage());
			throw (e);
		}
	}

	@Override
	public String getSourceSysId(String product) {
		logger.info("Inside getSourceSysId " + product);
		String sourceSysId = "";
		String sql = "SELECT SOURCE_SYS_ID FROM PSX_DATASOURCE_MST_T WHERE PRODUCT_TYPE=?";
		try {
			sourceSysId = (String) jdbctemplate.queryForObject(sql,
					new Object[] { product }, String.class);

		} catch (Exception e) {
			logger.error("Exception while getting getSourceSysId: "
					+ e.getMessage());
			throw (e);
		}
		return sourceSysId;
	}

	public void updateRequestTable(String parameterString, long reqPsxid,
			int total_count, String reqStatus) {
		// logger.info("Inside updateRequestTable Negative AreaID " +
		// parameterString + " for Request_Id " + reqPsxid);
		String sql = "UPDATE PSX_NSP_REQUEST SET NEGATIVE_MATCHED_ID=?, MATCH_COUNT=?, REQUEST_STATUS=? WHERE REQUEST_ID = to_char(?)";
		// String sql =
		// "UPDATE PSX_NSP_REQUEST SET NEGATIVE_MATCHED_ID=? WHERE REQUEST_ID=?";
		try {
			logger.info("Request Status in DAO::: " + reqStatus);
			Object[] params = { parameterString, total_count, reqStatus,
					reqPsxid };
			// Object[] params = { parameterString, reqPsxid };
			int[] types = { Types.VARCHAR, Types.BIGINT, Types.VARCHAR,
					Types.INTEGER };
			// int[] types = { Types.VARCHAR,Types.INTEGER };
			int rows = jdbctemplate.update(sql, params, types);

			logger.info(rows + " row(s) updated.");
		} catch (Exception e) {
			logger.error("Exception while getting getSourceSysId: "
					+ e.getMessage());
			throw (e);
		}
	}

	@Override
	public List<MatchedLoanDetails> getmatchedLoanDetails(
			String customerNoString) {
		List<MatchedLoanDetails> matchedLoanDetails = new ArrayList<>();

		// String sql =
		// "select BATCHID,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,DEAL_ID,LAN_NO,CUSTOMER_TYPE,APPLN_NO,PRODUCT_CODE,PROCESS_TYPE,PROCESS_FLAG,ERROR_CODE,ERROR_DESC,PSX_BATCH_ID,PSX_ID,SOURCE_SYSTEM from PSX_DEDUP_EOD_CUST_LOAN_DTL WHERE CUSTOMER_ID=?";
		// String sql =
		// "select /* + parallel(PSX_DEDUP_EOD_CUST_LOAN_DTL,+"+env.getProperty("process.threads")+") */ BATCHID,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,DEAL_ID,LAN_NO,CUSTOMER_TYPE,APPLN_NO,PRODUCT_CODE,PROCESS_TYPE,PROCESS_FLAG,ERROR_CODE,ERROR_DESC,PSX_BATCH_ID,PSX_ID,SOURCE_SYSTEM,CUST_UNQ_ID,SEGMENT from PSX_DEDUP_EOD_CUST_LOAN_DTL WHERE CUSTOMER_NO = ?";
		String sql = "select /* + parallel(PSX_DEDUP_EOD_CUST_LOAN_DTL,"
				+ env.getProperty("process.threads")
				+ ") */ BATCHID,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,DEAL_ID,LAN_NO,CUSTOMER_TYPE,APPLN_NO,PRODUCT_CODE,PROCESS_TYPE,PROCESS_FLAG,ERROR_CODE,ERROR_DESC,PSX_BATCH_ID,PSX_ID,SOURCE_SYSTEM,CUST_UNQ_ID,SEGMENT  from PSX_DEDUP_EOD_CUST_LOAN_DTL WHERE CUST_UNQ_ID = ?";
		// String sql =
		// "select  BATCHID,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,DEAL_ID,LAN_NO,CUSTOMER_TYPE,APPLN_NO,PRODUCT_CODE,PROCESS_TYPE,PROCESS_FLAG,ERROR_CODE,ERROR_DESC,PSX_BATCH_ID,PSX_ID,SOURCE_SYSTEM,CUST_UNQ_ID,SEGMENT  from PSX_DEDUP_EOD_CUST_LOAN_DTL WHERE CUST_UNQ_ID = ?";
		logger.info("Customer_no" + customerNoString);
		try {
			String[] arrSplit = {};
			if (customerNoString != null && !(customerNoString.isEmpty())) {
				arrSplit = customerNoString.split(",");
			}
			// logger.info("Customer_No List inside getmatchedLoanDetails " +
			// arrSplit.length +arrSplit[0]);

			jdbctemplate.setFetchSize(1000);
			// for (String s : arrSplit) {

			/*
			 * jdbctemplate.query(sql, (ResultSet rs) -> {
			 * logger.info("Outside"+ rs.getRow()); boolean status=rs.next();
			 * logger.info("RS "+status); while ( rs.next()) {
			 * logger.info("Inside"+ rs.getRow()); MatchedLoanDetails
			 * loandetails = new MatchedLoanDetails();
			 * loandetails.setApplnNo(rs.getString("APPLN_NO"));
			 * loandetails.setBatchId(rs.getString("BATCHID"));
			 * loandetails.setCustomerId(rs.getString("CUSTOMER_ID"));
			 * loandetails.setCustomerNo(rs.getString("CUSTOMER_NO"));
			 * loandetails.setCustomerType(rs.getString("CUSTOMER_TYPE"));
			 * loandetails.setDealId(rs.getString("DEAL_ID"));
			 * loandetails.setErrorCode(rs.getString("ERROR_CODE"));
			 * loandetails.setErrorDesc(rs.getString("ERROR_DESC"));
			 * loandetails.setLanNo(rs.getString("LAN_NO"));
			 * loandetails.setProcessFlag(rs.getString("PROCESS_FLAG"));
			 * loandetails.setProcessType(rs.getString("PROCESS_TYPE"));
			 * loandetails.setProductCode(rs.getString("PRODUCT_CODE"));
			 * loandetails.setPsxBatchId(rs.getString("PSX_BATCH_ID"));
			 * loandetails.setPsxId(rs.getString("PSX_ID"));
			 * loandetails.setSourceSysId(rs.getString("SOURCE_SYS_ID"));
			 * loandetails.setSourceSystem(rs.getString("SOURCE_SYSTEM"));
			 * loandetails.setCustUnqId(rs.getString("CUST_UNQ_ID"));
			 * loandetails.setSegment(rs.getString("SEGMENT"));
			 * matchedLoanDetails.add(loandetails); } }, s);
			 */
			/*
			 * for (String s : arrSplit) { matchedLoanDetails=
			 * jdbctemplate.query(sql,new RowMapper<MatchedLoanDetails>(){
			 * 
			 * @Override public MatchedLoanDetails mapRow(ResultSet rs, int
			 * rownumber) throws SQLException { MatchedLoanDetails
			 * loandetails=new MatchedLoanDetails();
			 * loandetails.setApplnNo(rs.getString("APPLN_NO"));
			 * loandetails.setBatchId(rs.getString("BATCHID"));
			 * loandetails.setCustomerId(rs.getString("CUSTOMER_ID"));
			 * loandetails.setCustomerNo(rs.getString("CUSTOMER_NO"));
			 * loandetails.setCustomerType(rs.getString("CUSTOMER_TYPE"));
			 * loandetails.setDealId(rs.getString("DEAL_ID"));
			 * loandetails.setErrorCode(rs.getString("ERROR_CODE"));
			 * loandetails.setErrorDesc(rs.getString("ERROR_DESC"));
			 * loandetails.setLanNo(rs.getString("LAN_NO"));
			 * loandetails.setProcessFlag(rs.getString("PROCESS_FLAG"));
			 * loandetails.setProcessType(rs.getString("PROCESS_TYPE"));
			 * loandetails.setProductCode(rs.getString("PRODUCT_CODE"));
			 * loandetails.setPsxBatchId(rs.getString("PSX_BATCH_ID"));
			 * loandetails.setPsxId(rs.getString("PSX_ID"));
			 * loandetails.setSourceSysId(rs.getString("SOURCE_SYS_ID"));
			 * loandetails.setSourceSystem(rs.getString("SOURCE_SYSTEM"));
			 * loandetails.setCustUnqId(rs.getString("CUST_UNQ_ID"));
			 * loandetails.setSegment(rs.getString("SEGMENT")); //
			 * matchedLoanDetails.add(loandetails); return loandetails; } },s);
			 * }
			 */

			// List<MatchedLoanDetails> list=new
			// ArrayList<MatchedLoanDetails>();
			for (String s : arrSplit) {
				jdbctemplate.query(sql,
						new ResultSetExtractor<List<MatchedLoanDetails>>() {
							@Override
							public List<MatchedLoanDetails> extractData(
									ResultSet rs) throws SQLException,
									DataAccessException {

								while (rs.next()) {
									MatchedLoanDetails loandetails = new MatchedLoanDetails();
									loandetails.setApplnNo(rs
											.getString("APPLN_NO"));
									loandetails.setBatchId(rs
											.getString("BATCHID"));
									loandetails.setCustomerId(rs
											.getString("CUSTOMER_ID"));
									loandetails.setCustomerNo(rs
											.getString("CUSTOMER_NO"));
									loandetails.setCustomerType(rs
											.getString("CUSTOMER_TYPE"));
									loandetails.setDealId(rs
											.getString("DEAL_ID"));
									loandetails.setErrorCode(rs
											.getString("ERROR_CODE"));
									loandetails.setErrorDesc(rs
											.getString("ERROR_DESC"));
									loandetails.setLanNo(rs.getString("LAN_NO"));
									loandetails.setProcessFlag(rs
											.getString("PROCESS_FLAG"));
									loandetails.setProcessType(rs
											.getString("PROCESS_TYPE"));
									loandetails.setProductCode(rs
											.getString("PRODUCT_CODE"));
									loandetails.setPsxBatchId(rs
											.getString("PSX_BATCH_ID"));
									loandetails.setPsxId(rs.getString("PSX_ID"));
									loandetails.setSourceSysId(rs
											.getString("SOURCE_SYS_ID"));
									loandetails.setSourceSystem(rs
											.getString("SOURCE_SYSTEM"));
									loandetails.setCustUnqId(rs
											.getString("CUST_UNQ_ID"));
									loandetails.setSegment(rs
											.getString("SEGMENT"));
									matchedLoanDetails.add(loandetails);
								}
								return matchedLoanDetails;
							}
						}, s);
			}

		} catch (Exception e) {
			logger.error("Exception while getting getmatchedLoanDetails: "
					+ e.getMessage());
			throw (e);
		}

		return matchedLoanDetails;
	}

	@Override
	public List<MatchedLoanDetails> getmatchedLoanDetails(long reqPsxid) {
		logger.info("Inside getmatchedLoanDetails " + reqPsxid);
		List<String> dataSource = new ArrayList<>();
		dataSource.add("CUSTOMER_CF");
		dataSource.add("CUSTOMER_AF");
		dataSource.add("PENNANT");
		dataSource.add("PENNANT_HFC");
		dataSource.add("WRF");
		List<MatchedLoanDetails> matchedLoanDetails = new ArrayList<>();
		MatchedLoanDetails loandetails = new MatchedLoanDetails();
		String sql = "select loan.BATCHID,loan.CUSTOMER_NO,loan.CUSTOMER_ID,loan.SOURCE_SYS_ID,loan.DEAL_ID,loan.LAN_NO,loan.CUSTOMER_TYPE,loan.APPLN_NO,loan.PRODUCT_CODE,loan.PROCESS_TYPE,loan.PROCESS_FLAG,loan.ERROR_CODE,loan.ERROR_DESC,loan.PSX_BATCH_ID,loan.PSX_ID,loan.SOURCE_SYSTEM from PSX_NSP_REQUEST_RESULTS res, PSX_DEDUP_EOD_CUST_LOAN_DTL loan where res.customer_id=loan.customer_id and res.source_sys_id=loan.source_sys_id and res.request_id=?";
		try {
			return jdbctemplate
					.query(sql,
							(ResultSet rs) -> {
								while (rs.next()) {
									if (dataSource.contains(rs
											.getString("SOURCE_SYSTEM"))) {
										loandetails.setApplnNo(rs
												.getString("APPLN_NO"));
										loandetails.setBatchId(rs
												.getString("BATCHID"));
										loandetails.setCustomerId(rs
												.getString("CUSTOMER_ID"));
										loandetails.setCustomerNo(rs
												.getString("CUSTOMER_NO"));
										loandetails.setCustomerType(rs
												.getString("CUSTOMER_TYPE"));
										loandetails.setDealId(rs
												.getString("DEAL_ID"));
										loandetails.setErrorCode(rs
												.getString("ERROR_CODE"));
										loandetails.setErrorDesc(rs
												.getString("ERROR_DESC"));
										loandetails.setLanNo(rs
												.getString("LAN_NO"));
										loandetails.setProcessFlag(rs
												.getString("PROCESS_FLAG"));
										loandetails.setProcessType(rs
												.getString("PROCESS_TYPE"));
										loandetails.setProductCode(rs
												.getString("PRODUCT_CODE"));
										loandetails.setPsxBatchId(rs
												.getString("PSX_BATCH_ID"));
										loandetails.setPsxId(rs
												.getString("PSX_ID"));
										loandetails.setSourceSysId(rs
												.getString("SOURCE_SYS_ID"));
										loandetails.setSourceSystem(rs
												.getString("SOURCE_SYSTEM"));
										matchedLoanDetails.add(loandetails);
									}
								}
								return matchedLoanDetails;
							}, reqPsxid);

		} catch (Exception e) {
			logger.error("Exception while getting getmatchedLoanDetails: "
					+ e.getMessage());
			throw (e);
		}
	}

	@Override
	public List<MatchedLoanDetails> getmatchedLoanDetails(
			List<Integer> customer_no) {
		List<MatchedLoanDetails> matchedLoanDetails = new ArrayList<>();
		logger.info("Customer_no Integer::: " + customer_no);
		String sql = "select /* + parallel(PSX_DEDUP_EOD_CUST_LOAN_DTL,+"
				+ env.getProperty("process.threads")
				+ ") */ BATCHID,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,DEAL_ID,LAN_NO,CUSTOMER_TYPE,APPLN_NO,PRODUCT_CODE,PROCESS_TYPE,PROCESS_FLAG,ERROR_CODE,ERROR_DESC,PSX_BATCH_ID,PSX_ID,SOURCE_SYSTEM from PSX_DEDUP_EOD_CUST_LOAN_DTL WHERE CUSTOMER_NO = ?";
		try {
			if (customer_no.size() > 0) {
				customer_no
						.forEach(cust_no -> {
							MatchedLoanDetails loandetails = new MatchedLoanDetails();
							jdbctemplate.query(sql,
									(ResultSet rs) -> {
										loandetails.setApplnNo(rs
												.getString("APPLN_NO"));
										loandetails.setBatchId(rs
												.getString("BATCHID"));
										loandetails.setCustomerId(rs
												.getString("CUSTOMER_ID"));
										loandetails.setCustomerNo(rs
												.getString("CUSTOMER_NO"));
										loandetails.setCustomerType(rs
												.getString("CUSTOMER_TYPE"));
										loandetails.setDealId(rs
												.getString("DEAL_ID"));
										loandetails.setErrorCode(rs
												.getString("ERROR_CODE"));
										loandetails.setErrorDesc(rs
												.getString("ERROR_DESC"));
										loandetails.setLanNo(rs
												.getString("LAN_NO"));
										loandetails.setProcessFlag(rs
												.getString("PROCESS_FLAG"));
										loandetails.setProcessType(rs
												.getString("PROCESS_TYPE"));
										loandetails.setProductCode(rs
												.getString("PRODUCT_CODE"));
										loandetails.setPsxBatchId(rs
												.getString("PSX_BATCH_ID"));
										loandetails.setPsxId(rs
												.getString("PSX_ID"));
										loandetails.setSourceSysId(rs
												.getString("SOURCE_SYS_ID"));
										loandetails.setSourceSystem(rs
												.getString("SOURCE_SYSTEM"));
										matchedLoanDetails.add(loandetails);
									}, cust_no);
						});
			}
			return matchedLoanDetails;
		} catch (Exception e) {
			logger.error("Exception while getting getmatchedLoanDetails: "
					+ e.getMessage());
			throw (e);
		}
	}

	@Override
	public DemoGraphicCustomerDetails getDemographicCustomerDetails(
			String custUnqId, String demoGraphicTables) throws Exception{

		DemoGraphicCustomerDetails details1 = new DemoGraphicCustomerDetails();
		try {

			String query = "select /* + parallel("+demoGraphicTables+",+"
					+ env.getProperty("process.threads")
					// +
					// ") */ ACCOUNT_NUMBER,APPLICANT_TYPE,PROCESS_TYPE,TAN_NO,PERMANENT_PIN,PERMANENT_CITY,GSTIN,CRNNO,CKYCNO,PREFERRED_PHONE,POSTAL_PHONE,EMERGENCY_PHONE,PRESENT_PHONE,RESIDENCE_PHONE,OFFICE_PHONE,POSTAL_EMAIL,RESIDENCE_EMAIL,EMPLOYER_NAME,TEMPORARY_EMAIL,PRESENT_PHONE,EMERGENCY_PHONE,CONTACT_PHONE,OFFICE_PHONE,PREFERRED_PHONE,PERMANENT_PHONE,TEMPORARY_PHONE,NAME,MOTHER_NAME,FATHER_NAME,SPOUSE_NAME,DOI,DOB1,DOB2,DOB3,DOB4,PAN,PASSPORT,VOTERID,DRIVINGLIC,AADHAAR,GENDER,CRNNO,CKYCNO,OFFICE_STATE,PERMANENT_STATE,RESIDENCE_STATE,TEMPORARY_STATE,PRESENT_STATE,CONTACT_STATE,PREFERRED_STATE,GSTIN,CORPORATELICENCE,OFFICE_ADDRESS,OFFICE_CITY,OFFICE_PIN,PERMANENT_ADDRESS,PERMANENT_CITY,PERMANENT_PIN,PRESENT_ADDRESS,PRESENT_CITY,PRESENT_PIN,EMERGENCY_ADDRESS,EMERGENCY_CITY,EMERGENCY_PIN,CONTACT_ADDRESS,CONTACT_CITY,CONTACT_PIN,POSTAL_ADDRESS,POSTAL_CITY,POSTAL_PIN,RESIDENCE_ADDRESS,RESIDENCE_CITY,RESIDENCE_PIN,PREFERRED_ADDRESS,PREFERRED_CITY,PREFERRED_PIN,TEMPORARY_ADDRESS,TEMPORARY_CITY,TEMPORARY_PIN,PRESENT_EMAIL,EMERGENCY_EMAIL,CONTACT_EMAIL,OFFICE_EMAIL,PREFERRED_EMAIL,PERMANENT_EMAIL,RESIDENCE_EMAIL,POSTAL_EMAIL,TEMPORARY_EMAIL,OFFICE_PHONE,PERMANENT_PHONE,RESIDENCE_PHONE,PRESENT_PHONE,EMERGENCY_PHONE,POSTAL_PHONE,PREFERRED_PHONE,CONTACT_PHONE,TEMPORARY_PHONE,OFFICE_STREET_NUMBER,COUNTRY,PERMANENT_STREET_NUMBER,RESIDENCE_STREET_NUMBER,TEMP_STREET_NUMBER,EVENTTYPE,OLD_PSX_BATCH_ID,RATIONCARD_NO,PRESENT_COUNTRY,PREFERRED_COUNTRY,CONTACT_COUNTRY,POSTAL_COUNTRY,OFFICE_COUNTRY,PERMANENT_COUNTRY,TEMPORARY_COUNTRY,RESIDENCE_COUNTRY,TIMESTAMP1,TIMESTAMP2,AREA,POLICY_NUMBER,COUNTRY_OF_ORIGIN,LANDMARK,MOBILE,DATE_OF_INCORPORATION,TAN_NO,PROCESS_TYPE,APPLICANT_TYPE,EMPLOYER_NAME,ACCOUNT_NUMBER,CREDIT_CARD_NUMBER,PROCESS_FLAG,PSX_SOURCE_SYSTEM_NAME,UCIN_FLAG,CA_NUMBER,CIN,DIN,REGISTRATION_NO,CUSTOMER_NO,CUSTOMER_ID,SOURCE_SYS_ID,SEGMENT,FILLER_STRING_1,FILLER_STRING_2,FILLER_STRING_3,FILLER_STRING_4,FILLER_STRING_5,FILLER_DATE_1,FILLER_DATE_2,FILLER_NUMBER_1,FILLER_NUMBER_2,FILLER_NUMBER_3,FILLER_NUMBER_4,FILLER_NUMBER_5,FILLER_STRING_6,FILLER_STRING_7,FILLER_STRING_8,FILLER_STRING_9,BATCHID,RECORD_TYPE,MATCHING_RULE,SOURCE_SYS_ID,AREA_ID,SOURCE_SYSTEM,CUST_UNQ_ID,SEGMENT,WEIGHTAGES,MATCH_TYPE,INSERT_TIME,LCHGTIME,CUSTOMER_CONTACT_TYPE,PERMANENT_STD_CODE,PREFERRED_STD_CODE,PRESENT_STD_CODE,EMERGENCY_STD_CODE,TEMPORARY_STD_CODE,CONTACT_STD_CODE,RESIDENCE_STD_CODE,OFFICE_STD_CODE,POSTAL_STD_CODE,DEAL_ID,PRODUCT,MATCH_PROFILE,DATASOURCE,REQUEST_ID,REQUEST_ID_BFL,REQUEST_ID_FIN,FIRSTNAME,MIDDLENAME,LASTNAME,APPLICATION_NO,LOAN_APP_NO,CUSTOMER_TYPE from PSX_NSP_REQUEST_RESULTS WHERE REQUEST_ID like ?";
					+ ") */ ACCOUNT_NUMBER,AADHAAR,APPLICANT_TYPE,AREA,BATCHID,CA_NUMBER,CIN,CKYCNO,CONTACT_ADDRESS,CONTACT_CITY,CONTACT_COUNTRY,CONTACT_EMAIL,CONTACT_PHONE,CONTACT_PIN,CONTACT_STATE,CONTACT_STD_CODE,CORPORATELICENCE,COUNTRY,COUNTRY_OF_ORIGIN,CREDIT_CARD_NUMBER,CRNNO,CUST_UNQ_ID,CUSTOMER_CONTACT_TYPE,CUSTOMER_ID,CUSTOMER_NO,DATE_OF_INCORPORATION,DIN,DOB1,DOB2,DOB3,DOB4,DOI,DRIVINGLIC,EMERGENCY_ADDRESS,EMERGENCY_CITY,EMERGENCY_EMAIL,EMERGENCY_PHONE,EMERGENCY_PIN,EMERGENCY_STD_CODE,EMPLOYER_NAME,EVENTTYPE,FATHER_NAME,FILLER_DATE_1,FILLER_DATE_2,FILLER_NUMBER_1,FILLER_NUMBER_2,FILLER_NUMBER_3,FILLER_NUMBER_4,FILLER_NUMBER_5,FILLER_STRING_1,FILLER_STRING_2,FILLER_STRING_3,FILLER_STRING_4,FILLER_STRING_5,FILLER_STRING_6,FILLER_STRING_7,FILLER_STRING_8,FILLER_STRING_9,FIRSTNAME,GENDER,GSTIN,INSERT_TIME,LANDMARK,LASTNAME,LCHGTIME,MIDDLENAME,MOBILE,MOTHER_NAME,NAME,OFFICE_ADDRESS,OFFICE_CITY,OFFICE_COUNTRY,OFFICE_EMAIL,OFFICE_PHONE,OFFICE_PIN,OFFICE_STATE,OFFICE_STD_CODE,OFFICE_STREET_NUMBER,OLD_PSX_BATCH_ID,PAN,PASSPORT,PERMANENT_ADDRESS,PERMANENT_CITY,PERMANENT_COUNTRY,PERMANENT_EMAIL,PERMANENT_PHONE,PERMANENT_PIN,PERMANENT_STATE,PERMANENT_STD_CODE,PERMANENT_STREET_NUMBER,POLICY_NUMBER,POSTAL_ADDRESS,POSTAL_CITY,POSTAL_COUNTRY,POSTAL_EMAIL,POSTAL_PHONE,POSTAL_PIN,POSTAL_STD_CODE,PREFERRED_ADDRESS,PREFERRED_CITY,PREFERRED_COUNTRY,PREFERRED_EMAIL,PREFERRED_PHONE,PREFERRED_PIN,PREFERRED_STATE,PREFERRED_STD_CODE,PRESENT_ADDRESS,PRESENT_CITY,PRESENT_COUNTRY,PRESENT_EMAIL,PRESENT_PHONE,PRESENT_PIN,PRESENT_STATE,PRESENT_STD_CODE,PROCESS_FLAG,PROCESS_TYPE,RATIONCARD_NO,REGISTRATION_NO,RESIDENCE_ADDRESS,RESIDENCE_CITY,RESIDENCE_COUNTRY,RESIDENCE_EMAIL,RESIDENCE_PHONE,RESIDENCE_PIN,RESIDENCE_STATE,RESIDENCE_STD_CODE,RESIDENCE_STREET_NUMBER,SEGMENT,SOURCE_SYS_ID,SOURCE_SYSTEM,SPOUSE_NAME,TAN_NO,TEMP_STREET_NUMBER,TEMPORARY_ADDRESS,TEMPORARY_CITY,TEMPORARY_COUNTRY,TEMPORARY_EMAIL,TEMPORARY_PHONE,TEMPORARY_PIN,TEMPORARY_STATE,TEMPORARY_STD_CODE,TIMESTAMP1,TIMESTAMP2,UCIN_FLAG,VOTERID from "
					+ demoGraphicTables + " WHERE CUST_UNQ_ID = ?";
			jdbctemplate.setFetchSize(1000);
			jdbctemplate.query(query,
					new ResultSetExtractor<DemoGraphicCustomerDetails>() {
						@Override
						public DemoGraphicCustomerDetails extractData(
								ResultSet rs) throws SQLException,
								DataAccessException {

							while (rs.next()) {

								details1.setName(rs.getString("NAME"));
								details1.setFather_name(rs
										.getString("FATHER_NAME"));
								details1.setMother_name(rs
										.getString("MOTHER_NAME"));
								details1.setSpouse_name(rs
										.getString("SPOUSE_NAME"));
								details1.setDate_of_incorporation(rs
										.getString("DOI"));
								details1.setDoi(rs.getString("DOI"));
								details1.setDob1(rs.getString("DOB1"));
								details1.setDob2(rs.getString("DOB2"));
								details1.setDob3(rs.getString("DOB3"));
								details1.setDob4(rs.getString("DOB4"));
								details1.setPan(rs.getString("PAN"));
								details1.setPassport(rs.getString("PASSPORT"));
								details1.setVoterid(rs.getString("VOTERID"));
								details1.setDrivinglic(rs
										.getString("DRIVINGLIC"));
								details1.setAadhaar(rs.getString("AADHAAR"));
								details1.setGender(rs.getString("GENDER"));
								details1.setCrnno(rs.getString("CRNNO"));
								details1.setCkycno(rs.getString("CKYCNO"));
								details1.setGstin(rs.getString("GSTIN"));
								details1.setTan_no(rs.getString("TAN_NO"));
								details1.setProcess_type(rs
										.getString("PROCESS_TYPE"));
								details1.setApplicant_type(rs
										.getString("APPLICANT_TYPE"));
								details1.setAccount_number(rs
										.getString("ACCOUNT_NUMBER"));
								details1.setOffice_state(rs
										.getString("OFFICE_STATE"));
								details1.setPermanent_state(rs
										.getString("PERMANENT_STATE"));
								details1.setResidence_state(rs
										.getString("RESIDENCE_STATE"));
								details1.setTemporary_state(rs
										.getString("TEMPORARY_STATE"));
								details1.setPresent_state((rs
										.getString("PRESENT_STATE")));
								details1.setContact_state(rs
										.getString("CONTACT_STATE"));
								details1.setPreferred_state(rs
										.getString("PREFERRED_STATE"));
								details1.setCorporatelicence(rs
										.getString("CORPORATELICENCE"));
								details1.setOffice_address(rs
										.getString("OFFICE_ADDRESS"));
								details1.setOffice_city(rs
										.getString("OFFICE_CITY"));
								details1.setOffice_pin(rs
										.getString("OFFICE_PIN"));
								details1.setPresent_address(rs
										.getString("PRESENT_ADDRESS"));
								details1.setPresent_city(rs
										.getString("PRESENT_CITY"));
								details1.setPresent_pin(rs
										.getString("PRESENT_PIN"));
								details1.setPermanent_city(rs
										.getString("PERMANENT_CITY"));
								details1.setPermanent_pin(rs
										.getString("PERMANENT_PIN"));
								details1.setPermanent_address(rs
										.getString("PERMANENT_ADDRESS"));
								details1.setEmergency_address(rs
										.getString("EMERGENCY_ADDRESS"));
								details1.setEmergency_city(rs
										.getString("EMERGENCY_CITY"));
								details1.setEmergency_pin(rs
										.getString("EMERGENCY_PIN"));
								details1.setContact_address(rs
										.getString("CONTACT_ADDRESS"));
								details1.setContact_city(rs
										.getString("CONTACT_CITY"));
								details1.setContact_pin(rs
										.getString("CONTACT_PIN"));
								details1.setPostal_address(rs
										.getString("POSTAL_ADDRESS"));
								details1.setPostal_city(rs
										.getString("POSTAL_CITY"));
								details1.setPostal_pin(rs
										.getString("POSTAL_PIN"));
								details1.setResidence_address(rs
										.getString("RESIDENCE_ADDRESS"));
								details1.setResidence_city(rs
										.getString("RESIDENCE_CITY"));
								details1.setResidence_pin(rs
										.getString("RESIDENCE_PIN"));
								details1.setPreferred_address(rs
										.getString("PREFERRED_ADDRESS"));
								details1.setPreferred_city(rs
										.getString("PREFERRED_CITY"));
								details1.setPreferred_pin(rs
										.getString("PREFERRED_PIN"));
								details1.setTemporary_address(rs
										.getString("TEMPORARY_ADDRESS"));
								details1.setTemporary_city(rs
										.getString("TEMPORARY_CITY"));
								details1.setTemporary_pin(rs
										.getString("TEMPORARY_PIN"));
								details1.setPresent_email(rs
										.getString("PRESENT_EMAIL"));
								details1.setEmergency_email(rs
										.getString("EMERGENCY_EMAIL"));
								details1.setContact_email(rs
										.getString("CONTACT_EMAIL"));
								details1.setOffice_email(rs
										.getString("OFFICE_EMAIL"));
								details1.setPreferred_email(rs
										.getString("PREFERRED_EMAIL"));
								details1.setPermanent_email(rs
										.getString("PERMANENT_EMAIL"));
								details1.setTemporary_email(rs
										.getString("TEMPORARY_EMAIL"));
								details1.setPresent_phone(rs
										.getString("PRESENT_PHONE"));
								details1.setOffice_phone(rs
										.getString("OFFICE_PHONE"));
								details1.setEmergency_phone(rs
										.getString("EMERGENCY_PHONE"));
								details1.setContact_phone(rs
										.getString("CONTACT_PHONE"));
								details1.setPreferred_phone(rs
										.getString("PREFERRED_PHONE"));
								details1.setPermanent_phone(rs
										.getString("PERMANENT_PHONE"));
								details1.setTemporary_phone(rs
										.getString("TEMPORARY_PHONE"));
								details1.setResidence_phone(rs
										.getString("RESIDENCE_PHONE"));
								details1.setPostal_phone(rs
										.getString("POSTAL_PHONE"));
								details1.setPreferred_phone(rs
										.getString("PREFERRED_PHONE"));
								details1.setBatchid(rs.getString("BATCHID"));
								details1.setEmployer_name(rs
										.getString("EMPLOYER_NAME"));
								details1.setResidence_email(rs
										.getString("RESIDENCE_EMAIL"));
								details1.setPostal_email(rs
										.getString("POSTAL_EMAIL"));
								// details1.setCustomerNo(rs.getInt("CUSTOMER_NO"));
								details1.setCustomer_no(rs
										.getLong("CUSTOMER_NO"));
								details1.setCustomer_id(rs
										.getString("CUSTOMER_ID"));

								details1.setSource_sys_id(rs
										.getString("SOURCE_SYS_ID"));
								// details1.setPsxsourceSystem(rs
								// .getString("PSX_SOURCE_SYSTEM_NAME"));
								details1.setSource_system(rs
										.getString("SOURCE_SYSTEM"));
//								if (rs.getString("SOURCE_SYS_ID") != null) {
//									try {
//										details1.setSource_system(getSourceSystemName(rs
//												.getInt("SOURCE_SYS_ID")));
//									} catch (Exception e) {
//										logger.error("Exception while getting SourceSystemName: "
//												+ e.getMessage());
//									}
//								} else {
//									details1.setSource_system(null);
//								}

								details1.setCust_unq_id(rs
										.getString("CUST_UNQ_ID"));
								details1.setSegment(rs.getString("SEGMENT"));
								// details1.setMathcedId(rs.getString("CUST_UNQ_ID"));
								details1.setInsert_time(rs
										.getString("INSERT_TIME")); // rs.getString("")
								details1.setLchgtime(rs.getString("LCHGTIME"));
								details1.setCustomer_contact_type(rs
										.getString("CUSTOMER_CONTACT_TYPE"));
								details1.setPermanent_std_code(rs
										.getString("PERMANENT_STD_CODE"));
								details1.setPreferred_std_code(rs
										.getString("PREFERRED_STD_CODE"));
								details1.setPresent_std_code(rs
										.getString("PRESENT_STD_CODE"));
								details1.setEmergency_std_code(rs
										.getString("EMERGENCY_STD_CODE"));
								details1.setTemporary_std_code(rs
										.getString("TEMPORARY_STD_CODE"));
								details1.setContact_std_code(rs
										.getString("CONTACT_STD_CODE"));
								details1.setResidence_std_code(rs
										.getString("RESIDENCE_STD_CODE"));
								details1.setOffice_std_code(rs
										.getString("OFFICE_STD_CODE"));
								details1.setPostal_std_code(rs
										.getString("POSTAL_STD_CODE"));
								details1.setFirstname(rs
										.getString("FIRSTNAME"));
								details1.setMiddlename(rs
										.getString("MIDDLENAME"));
								details1.setLastname(rs.getString(("LASTNAME")));

								details1.setFiller_string_1(rs
										.getString("FILLER_STRING_1"));
								details1.setFiller_string_2(rs
										.getString("FILLER_STRING_2"));
								details1.setFiller_string_3(rs
										.getString("FILLER_STRING_3"));
								details1.setFiller_string_4(rs
										.getString("FILLER_STRING_4"));
								details1.setFiller_string_5(rs
										.getString("FILLER_STRING_5"));
								details1.setFiller_string_6(rs
										.getString("FILLER_STRING_6"));
								details1.setFiller_string_7(rs
										.getString("FILLER_STRING_7"));
								details1.setFiller_string_8(rs
										.getString("FILLER_STRING_8"));
								details1.setFiller_string_9(rs
										.getString("FILLER_STRING_9"));
								details1.setFiller_date_1(rs.getDate("FILLER_DATE_1"));
								details1.setFiller_date_2(rs.getDate("FILLER_DATE_2"));
								details1.setFiller_number_1(rs.getLong("FILLER_NUMBER_1"));
								details1.setFiller_number_2(rs.getLong("FILLER_NUMBER_2"));
								details1.setFiller_number_3(rs.getLong("FILLER_NUMBER_3"));
								details1.setFiller_number_4(rs.getLong("FILLER_NUMBER_4"));
								details1.setFiller_number_5(rs.getLong("FILLER_NUMBER_5"));
								details1.setCredit_card_number(rs.getString("CREDIT_CARD_NUMBER"));
								details1.setCin(rs.getString("CIN"));
								details1.setDin(rs.getString("DIN"));
								details1.setPreferred_country(rs.getString("PREFERRED_COUNTRY"));
								details1.setPresent_country(rs.getString("PRESENT_COUNTRY"));
								details1.setContact_country(rs.getString("CONTACT_COUNTRY"));
								details1.setPostal_country(rs.getString("POSTAL_COUNTRY"));
								details1.setOffice_country(rs.getString("OFFICE_COUNTRY"));
								details1.setPermanent_country(rs.getString("PERMANENT_COUNTRY"));
								details1.setTemporary_country(rs.getString("TEMPORARY_COUNTRY"));
								details1.setResidence_country(rs.getString("RESIDENCE_COUNTRY"));
								details1.setMobile(rs.getString("MOBILE"));
								details1.setRegistration_no(rs.getString("REGISTRATION_NO"));
								details1.setTemp_street_number(rs.getString("TEMP_STREET_NUMBER"));
								details1.setOffice_street_number(rs.getString("OFFICE_STREET_NUMBER"));
								details1.setPermanent_street_number(rs.getString("PERMANENT_STREET_NUMBER"));
								details1.setResidence_street_number(rs.getString("RESIDENCE_STREET_NUMBER"));
							}
							System.out.println("Details :::: "+details1);
							return details1;
						}
					}, custUnqId);
		} catch (Exception e) {
			logger.error("Exception while getting getDemographicCustomerDetails: "
					+ e.getMessage());
			throw (e);
			//return null;

		}
		return details1;
	}

	@Override
	public void webserviceRequestProcessingTime(long request_id,
			long logCompletionTime,String match_profile,String request_status) {
		try {
			String sql = "insert into WS_REQ_PROCESSING_TIME"
					+ "(REQUEST_ID,REQUEST_COMPLETION_TIME,INSERT_TIME,MATCH_PROFILE,REQUEST_STATUS)"
					+ " values(?,?,systimestamp,?,?)";
			jdbctemplate.update(sql, new Object[] { request_id,
					logCompletionTime,match_profile, request_status});
		} catch (Exception e) {
			logger.error("Exception while getting webserviceRequestProcessingTime: "
					+ e.getMessage());
		}
	}

	@Override
	public List<String> getValidProfilesList() throws DAOException {

		String sql = "SELECT profile_id FROM psx_nsp_profiles WHERE APPROVED='Y'";

		try {
			List<String> ls = new ArrayList<String>();
			return jdbctemplate.query(sql,
					new ResultSetExtractor<List<String>>() {

						@Override
						public List<String> extractData(
								ResultSet rs) throws SQLException,
								DataAccessException {
							while (rs.next()) {
								
								ls.add(rs.getString("profile_id"));
							}
							return ls;
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception while getting getPsxNspProfiles: "
					+ e.getMessage());
			throw (new DAOException(
					"Exception encountered while fetching Matching Rules"));
		}
	}
}
