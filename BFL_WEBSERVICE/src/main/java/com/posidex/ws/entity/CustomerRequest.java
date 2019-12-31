package com.posidex.ws.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "requestId", "bflRequestId", "dealId", "dataSource", "profileId", "requestIdFin", "matchProfile",
		"requestStatus", "requestType", "requestDetails" })
public class CustomerRequest implements Serializable {

	public String getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(String requestStatus) {
		this.requestStatus = requestStatus;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5174792378210192085L;

	// @JsonProperty("REQUEST_ID")
	@JsonIgnore
	private long requestId;

//	@JsonProperty("PROFILE_ID")
	@JsonIgnore
	private String profileId;

	@JsonProperty("REQUEST_ID_FIN")
	private String requestIdFin;

	@JsonProperty("MATCH_PROFILE")
	private String matchProfile;

	//@JsonProperty("REQUEST_STATUS")
	@JsonIgnore
	private String requestStatus;

	@JsonProperty("REQUEST_TYPE")
	private String requestType;

	@JsonProperty("REQUEST_ID_BFL")
	private String bflRequestId;

	@JsonProperty("DEAL_ID")
	private String dealId;
	@JsonProperty("DATASOURCE")
	private String dataSource;
	@JsonProperty("CUSTOMER_REQUEST_DETAILS")
	private CustomerRequestDetails requestDetails;

	public String getProfileId() {
		return profileId;
	}

	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	public String getRequestIdFin() {
		return requestIdFin;
	}

	public void setRequestIdFin(String requestIdFin) {
		this.requestIdFin = requestIdFin;
	}

	public String getMatchProfile() {
		return matchProfile;
	}

	public void setMatchProfile(String matchProfile) {
		this.matchProfile = matchProfile;
	}

	public CustomerRequestDetails getRequestDetails() {
		return requestDetails;
	}

	public void setRequestDetails(CustomerRequestDetails requestDetails) {
		this.requestDetails = requestDetails;
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public String getBflRequestId() {
		return bflRequestId;
	}

	public void setBflRequestId(String bflRequestId) {
		this.bflRequestId = bflRequestId;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String toString() {
		return "CustomerRequest [requestId=" + requestId + ", profileId=" + profileId + ", requestIdFin=" + requestIdFin
				+ ", matchProfile=" + matchProfile + ", requestStatus=" + requestStatus + ", requestType=" + requestType
				+ ", bflRequestId=" + bflRequestId + ", dealId=" + dealId + ", dataSource=" + dataSource
				+ ", requestDetails=" + requestDetails + "]";
	}

}
