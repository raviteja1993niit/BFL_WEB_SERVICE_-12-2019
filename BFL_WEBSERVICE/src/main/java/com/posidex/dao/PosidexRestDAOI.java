package com.posidex.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.posidex.exception.DAOException;
import com.posidex.ws.entity.CustomerRequest;
import com.posidex.ws.entity.CustomerRequestDetails;
import com.posidex.ws.entity.DemoGraphicCustomerDetails;
import com.posidex.ws.entity.MatchedCustomerDetails;
import com.posidex.ws.entity.MatchedLoanDetails;

public interface PosidexRestDAOI {

	public Map<String, String> getWebServiceErrorCodes() throws DAOException;
	public Map<String,ArrayList<String>> getActiveProfiles() throws Exception;

	public void saveCostomerRequest(CustomerRequest customerRequest, long reqPsxid, Date lchgtime) throws Exception;

	public List<MatchedCustomerDetails> getmatchedCustomers(long requestId) throws Exception;

	public String getProductProfileId(String product) throws Exception;

	public String getProductOrg(String product) throws Exception;

	public List<String> getPsxNspProfiles(String profileId) throws Exception;

	public long appNextValProc() throws Exception;

	public List<String> checkRequestStatus(long reqPsxid) throws Exception;

	MatchedLoanDetails getmatchedLoanDetails(int customerNo) throws Exception;

	public CustomerRequestDetails getCustomerRequestDetails(long requestId) throws Exception;

	public List<String> getSourceSystem(long reqPsxid);

	public List<String> getNegativeMatchIds(long reqPsxid);

	public void saveCustomerRequestToNodeOneINTRADAY(CustomerRequest customerRequest, long reqPsxid, Date lchgtime)
			throws Exception;

	public void saveCustomerRequestToNodeTwoINTRADAY(CustomerRequest customerRequest, long reqPsxid, Date lchgtime)
			throws Exception;

	public List<String> getProfileIds();

	public String getSourceSysId(String product);

	public void updateRequestTable(String parameterString, long reqPsxid,int total_count, String reqStatus);

	public List<MatchedLoanDetails> getmatchedLoanDetails(String customerNoString);

	public List<MatchedLoanDetails> getmatchedLoanDetails(long reqPsxid);

	public List<MatchedLoanDetails> getmatchedLoanDetails(List<Integer> customer_no);
	public DemoGraphicCustomerDetails getDemographicCustomerDetails(String custUnqId, String demoGraphicTables) throws Exception;
	public void webserviceRequestProcessingTime(long request_id,
			long logCompletionTime,String match_profile,String request_status);
	public List<MatchedLoanDetails> getmatchedLoanDetailsList(int customerNo) throws Exception;
	public List<String> getValidProfilesList() throws DAOException;

}
