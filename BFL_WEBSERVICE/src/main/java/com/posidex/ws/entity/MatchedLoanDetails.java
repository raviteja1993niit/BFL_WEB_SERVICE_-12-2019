package com.posidex.ws.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "batchId", "customerNo", "customerId", "sourceSysId", "dealId", "lanNo", "customerType", "applnNo",
		"productCode", "processType", "processFlag", "errorCode", "errorDesc", "psxBatchId", "psxId", "sourceSystem","custUnqId","segment" })
public class MatchedLoanDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8985523570604965555L;

	@JsonProperty("BATCHID")
	private String batchId;

	@JsonProperty("CUSTOMER_NO")
	private String customerNo;

	@JsonProperty("CUSTOMER_ID")
	private String customerId;

	@JsonProperty("SOURCE_SYS_ID")
	private String sourceSysId;

	@JsonProperty("DEAL_ID")
	private String dealId;

	@JsonProperty("LAN_NO")
	private String lanNo;

	@JsonProperty("CUSTOMER_TYPE")
	private String customerType;

	@JsonProperty("APPLN_NO")
	private String applnNo;

	@JsonProperty("PRODUCT_CODE")
	private String productCode;

	@JsonProperty("PROCESS_TYPE")
	private String processType;

	@JsonProperty("PROCESS_FLAG")
	private String processFlag;

	@JsonProperty("ERROR_CODE")
	private String errorCode;

	@JsonProperty("ERROR_DESC")
	private String errorDesc;

	@JsonProperty("PSX_BATCH_ID")
	private String psxBatchId;

	@JsonProperty("PSX_ID")
	private String psxId;

	@JsonProperty("SOURCE_SYSTEM")
	private String sourceSystem;
	
	@JsonProperty("CUST_UNQ_ID")
	private String custUnqId;
	
	@JsonProperty("SEGMENT")
	private String segment;
	
	
	
	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getCustUnqId() {
		return custUnqId;
	}

	public void setCustUnqId(String custUnqId) {
		this.custUnqId = custUnqId;
	}

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getCustomerNo() {
		return customerNo;
	}

	public void setCustomerNo(String customerNo) {
		this.customerNo = customerNo;
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

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getLanNo() {
		return lanNo;
	}

	public void setLanNo(String lanNo) {
		this.lanNo = lanNo;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getApplnNo() {
		return applnNo;
	}

	public void setApplnNo(String applnNo) {
		this.applnNo = applnNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProcessType() {
		return processType;
	}

	public void setProcessType(String processType) {
		this.processType = processType;
	}

	public String getProcessFlag() {
		return processFlag;
	}

	public void setProcessFlag(String processFlag) {
		this.processFlag = processFlag;
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

	public String getPsxBatchId() {
		return psxBatchId;
	}

	public void setPsxBatchId(String psxBatchId) {
		this.psxBatchId = psxBatchId;
	}

	public String getPsxId() {
		return psxId;
	}

	public void setPsxId(String psxId) {
		this.psxId = psxId;
	}

	public String getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	@Override
	public String toString() {
		return "MatchedLoanDetails [batchId=" + batchId + ", customerNo=" + customerNo + ", customerId=" + customerId
				+ ", sourceSysId=" + sourceSysId + ", dealId=" + dealId + ", lanNo=" + lanNo + ", customerType="
				+ customerType + ", applnNo=" + applnNo + ", productCode=" + productCode + ", processType="
				+ processType + ", processFlag=" + processFlag + ", errorCode=" + errorCode + ", errorDesc=" + errorDesc
				+ ", psxBatchId=" + psxBatchId + ", psxId=" + psxId + ", sourceSystem=" + sourceSystem + "]";
	}

}
