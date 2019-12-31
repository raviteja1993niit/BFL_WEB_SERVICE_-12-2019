package com.posidex.service;

import java.util.Date;
import java.util.List;

import com.posidex.exception.DAOException;
import com.posidex.ws.entity.CustomerRequest;
import com.posidex.ws.entity.CustomerRequestDetails;
import com.posidex.ws.entity.DemoGraphicCustomerDetails;
import com.posidex.ws.entity.MatchedCustomerDetails;
import com.posidex.ws.entity.MatchedLoanDetails;

/***
 * 
 * @author raviteja
 *
 */

public interface PosidexRestServiceI {

	void saveCostomerRequest(CustomerRequest request, long reqPsxid, Date lchgtime) throws Exception;

	List<MatchedCustomerDetails> getmatchedCustomers(long requestId) throws Exception;

	String getProductProfileId(String product) throws Exception;

	String getProductOrg(String product) throws Exception;

	List<String> getPsxNspProfiles(String profileId) throws Exception;

	long appNextValProc() throws Exception;

	List<String> checkRequestStatus(long reqPsxid) throws Exception;

	MatchedLoanDetails getmatchedLoanDetails(int customerNo) throws Exception;
	 List<MatchedLoanDetails> getmatchedLoanDetailsList(int customerNo) throws Exception ;
	CustomerRequestDetails getCustomerRequestDetails(long requestId) throws Exception;

	List<String> getSourceSystem(long reqPsxid);

	List<String> getNegativeMatchIds(long reqPsxid);

	void saveCustomerRequestToNodeOneINTRADAY(CustomerRequest customerRequest, long reqPsxid, Date lchgtime)
			throws Exception;

	void saveCustomerRequestToNodeTwoINTRADAY(CustomerRequest customerRequest, long reqPsxid, Date lchgtime)
			throws Exception;

	List<String> getProfileIds();

	String getSourceSysId(String product);

	void updateRequestTable(String parameterString, long reqPsxid, int total_count, String reqStatus);

	List<MatchedLoanDetails> getmatchedLoanDetails(String customerNoString);

	List<MatchedLoanDetails> getmatchedLoanDetails(long reqPsxid);

	List<MatchedLoanDetails> getmatchedLoanDetails(List<Integer> customer_no);

	DemoGraphicCustomerDetails getDemographicCustomerDetails(String custUnqId,
			String property) throws Exception;

	void webserviceRequestProcessingTime(long request_id, long logCompletionTime,String match_profile,String request_status);

	List<String> getValidProfilesList() throws DAOException;

}
