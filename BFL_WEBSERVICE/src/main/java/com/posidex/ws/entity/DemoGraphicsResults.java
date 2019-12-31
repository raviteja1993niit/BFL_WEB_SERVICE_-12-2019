package com.posidex.ws.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "customerId","sourceSysId","custunqid","errorCode","errorDesc","demoGraphicCustomerDetails","matchedLoanDetailsList"})
public class DemoGraphicsResults implements Serializable {
	
	private static final long serialVersionUID = -570953632416842539L;

	@JsonProperty("CUSTOMER_ID")
	private String customerId;

	@JsonProperty("SOURCE_SYS_ID")
	private String sourceSysId;

	@JsonProperty("CUST_UNQ_ID")
	private String custunqid;
	

	
	
	@JsonProperty("ERROR_CODE")
	private String errorCode;

	@JsonProperty("ERROR_DESC")
	private String errorDesc;
	
	@JsonProperty("DEMOGRAPHIC_DETAILS")
	private DemoGraphicCustomerDetails  demoGraphicCustomerDetails;

	@JsonProperty("MATCHED_LOAN_DETAILS_LIST")
	private List<MatchedLoanDetails> matchedLoanDetailsList;

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

	public List<MatchedLoanDetails> getMatchedLoanDetailsList() {
		return matchedLoanDetailsList;
	}

	public void setMatchedLoanDetailsList(
			List<MatchedLoanDetails> matchedLoanDetailsList) {
		this.matchedLoanDetailsList = matchedLoanDetailsList;
	}

	public String getCustunqid() {
		return custunqid;
	}

	public void setCustunqid(String custunqid) {
		this.custunqid = custunqid;
	}

	public DemoGraphicCustomerDetails getDemoGraphicCustomerDetails() {
		return demoGraphicCustomerDetails;
	}

	public void setDemoGraphicCustomerDetails(
			DemoGraphicCustomerDetails demoGraphicCustomerDetails) {
		this.demoGraphicCustomerDetails = demoGraphicCustomerDetails;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	@Override
	public String toString() {
		return "DemoGraphicsResults [customerId=" + customerId
				+ ", sourceSysId=" + sourceSysId + ", custunqid=" + custunqid
				+ ", errorCode=" + errorCode + ", errorDesc=" + errorDesc
				+ ", demoGraphicCustomerDetails=" + demoGraphicCustomerDetails
				+ ", matchedLoanDetailsList=" + matchedLoanDetailsList + "]";
	}



	
	

}
