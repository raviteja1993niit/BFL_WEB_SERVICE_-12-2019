package com.posidex.ws.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({ "customerNO","sourceSysNO"})
public class CustomerObject {

	@JsonProperty("CUSTOMER_NO")
	private String customerNO;

	@JsonProperty("SOURCE_SYS_ID")
	private String sourceSysNO;

	public String getCustomerNO() {
		return customerNO;
	}

	public void setCustomerNO(String customerNO) {
		this.customerNO = customerNO;
	}

	public String getSourceSysNO() {
		return sourceSysNO;
	}

	public void setSourceSysNO(String sourceSysNO) {
		this.sourceSysNO = sourceSysNO;
	}

	@Override
	public String toString() {
		return "CustomerObject [customerNO=" + customerNO + ", sourceSysNO="
				+ sourceSysNO + "]";
	}

}
