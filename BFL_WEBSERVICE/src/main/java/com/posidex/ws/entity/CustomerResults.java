package com.posidex.ws.entity;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "requestId", "bflRequestId", "dealId", "status", "errorCode", "matchCount", "requestIdFin",
		"product", "dataSource", "negativeMatchCriteria", "description", "matchedCustomerDetailsList",
		"matchedLoanDetailsList", "errorDesc", "negativeMatchId" })
public class CustomerResults implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7222095014963144264L;

	@JsonProperty("REQUEST_ID")
	private long requestId;

	@JsonProperty("REQUEST_ID_BFL")
	private String bflRequestId;

//	@JsonProperty("NODE1_REQUEST_STATUS")
	@JsonIgnore
	private String node1RequestStatus;

	
//	@JsonProperty("NODE2_REQUEST_STATUS")
	@JsonIgnore
	private String node2RequestStatus;
	
	@JsonProperty("STATUS")
	private String status;
	
	@JsonProperty("ERROR_CODE")
	private String errorCode;

	@JsonProperty("ERROR_DESC")
	private String errorDesc;
	@JsonProperty("MATCH_COUNT")
	private int matchCount;

	@JsonProperty("REQUEST_ID_FIN")
	private String requestIdFin;

	@JsonProperty("PRODUCT")
	private String product;

	@JsonProperty("DATASOURCE")
	private String dataSource;

	@JsonProperty("DEAL_ID")
	private String dealId;

	@JsonProperty("NEGATIVE_MATCH_CRITERIA")
	private String negativeMatchCriteria;

	@JsonProperty("NEGATIVE_MATCH_ID")
	private String negativeMatchId;

	@JsonProperty("MATCHED_CUSTOMER_DETAILS_LIST")
	private List<MatchedCustomerDetails> matchedCustomerDetailsList;

	@JsonProperty("MATCHED_LOAN_DETAILS_LIST")
	private List<MatchedLoanDetails> matchedLoanDetailsList;

	@JsonProperty("DESCRIPTION")
	private String description;

	public String getNegativeMatchCriteria() {
		return negativeMatchCriteria;
	}

	public void setNegativeMatchCriteria(String negativeMatchCriteria) {
		this.negativeMatchCriteria = negativeMatchCriteria;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getRequestIdFin() {
		return requestIdFin;
	}

	public void setRequestIdFin(String requestIdFin) {
		this.requestIdFin = requestIdFin;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public int getMatchCount() {
		return matchCount;
	}

	public void setMatchCount(int matchCount) {
		this.matchCount = matchCount;
	}

	public List<MatchedCustomerDetails> getMatchedCustomerDetailsList() {
		return matchedCustomerDetailsList;
	}

	public void setMatchedCustomerDetailsList(List<MatchedCustomerDetails> matchedCustomerDetailsList) {
		this.matchedCustomerDetailsList = matchedCustomerDetailsList;
	}

	public List<MatchedLoanDetails> getMatchedLoanDetailsList() {
		return matchedLoanDetailsList;
	}

	public void setMatchedLoanDetailsList(List<MatchedLoanDetails> matchedLoanDetailsList) {
		this.matchedLoanDetailsList = matchedLoanDetailsList;
	}

	public String getErrorDesc() {
		return errorDesc;
	}

	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}

	public String getNegativeMatchId() {
		return negativeMatchId;
	}

	public void setNegativeMatchId(String negativeMatchId) {
		this.negativeMatchId = negativeMatchId;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getBflRequestId() {
		return bflRequestId;
	}

	public void setBflRequestId(String string) {
		this.bflRequestId = string;
	}

	@Override
	public String toString() {
		return "CustomerResults [requestId=" + requestId + ", bflRequestId=" + bflRequestId + ", status=" + status
				+ ", errorCode=" + errorCode + ", errorDesc=" + errorDesc + ", matchCount=" + matchCount
				+ ", requestIdFin=" + requestIdFin + ", product=" + product + ", dataSource=" + dataSource + ", dealId="
				+ dealId + ", negativeMatchCriteria=" + negativeMatchCriteria + ", negativeMatchId=" + negativeMatchId
				+ ", matchedCustomerDetailsList=" + matchedCustomerDetailsList + ", matchedLoanDetailsList="
				+ matchedLoanDetailsList + ", description=" + description + "]";
	}

}
