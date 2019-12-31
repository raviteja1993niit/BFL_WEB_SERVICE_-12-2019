package com.posidex.ws.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
@JsonPropertyOrder({ "requestId" })
public class RequestObject {

	@JsonProperty("REQUEST_ID")
	private long requestId;

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	@Override
	public String toString() {
		return "RequestObject [requestId=" + requestId + "]";
	}

}
