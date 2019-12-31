package com.posidex.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.posidex.dao.PosidexRestDAOI;
import com.posidex.exception.DAOException;
import com.posidex.service.PosidexRestServiceI;
import com.posidex.ws.entity.CustomerRequest;
import com.posidex.ws.entity.CustomerRequestDetails;
import com.posidex.ws.entity.CustomerResults;
import com.posidex.ws.entity.DemoGraphicCustomerDetails;
import com.posidex.ws.entity.MatchedCustomerDetails;
import com.posidex.ws.entity.MatchedLoanDetails;

/***
 * 
 * @author raviteja
 *
 */
@Service
public class PosidexRestServiceImpl implements PosidexRestServiceI {

	@Autowired
	private PosidexRestDAOI dao;



	@Override
	public void saveCostomerRequest(CustomerRequest customerRequest, long reqPsxid, Date lchgtime) throws Exception {
		dao.saveCostomerRequest(customerRequest, reqPsxid, lchgtime);
	}

	@Override
	public List<MatchedCustomerDetails> getmatchedCustomers(long requestId) throws Exception {

		List<MatchedCustomerDetails> matchedCustomerDetailsList = dao.getmatchedCustomers(requestId);
		return matchedCustomerDetailsList;

	}

	@Override
	public String getProductProfileId(String product) throws Exception {

		return dao.getProductProfileId(product);
	}

	@Override
	public String getProductOrg(String product) throws Exception {

		return dao.getProductOrg(product);
	}

	@Override
	public List<String> getPsxNspProfiles(String profileId) throws Exception {

		return dao.getPsxNspProfiles(profileId);
	}

	@Override
	public long appNextValProc() throws Exception {

		return dao.appNextValProc();

	}

	@Override
	public List<String> checkRequestStatus(long reqPsxid) throws Exception {

		return dao.checkRequestStatus(reqPsxid);
	}

	@Override
	public MatchedLoanDetails getmatchedLoanDetails(int customerNo) throws Exception {

		return dao.getmatchedLoanDetails(customerNo);
	}

	@Override
	public CustomerRequestDetails getCustomerRequestDetails(long requestId) throws Exception {

		return dao.getCustomerRequestDetails(requestId);
	}

	@Override
	public List<String> getSourceSystem(long reqPsxid) {

		return dao.getSourceSystem(reqPsxid);
	}

	@Override
	public List<String> getNegativeMatchIds(long reqPsxid) {

		return dao.getNegativeMatchIds(reqPsxid);
	}

	@Override
	public void saveCustomerRequestToNodeOneINTRADAY(CustomerRequest customerRequest, long reqPsxid, Date lchgtime)
			throws Exception {

		dao.saveCustomerRequestToNodeOneINTRADAY(customerRequest, reqPsxid, lchgtime);
	}

	@Override
	public void saveCustomerRequestToNodeTwoINTRADAY(CustomerRequest customerRequest, long reqPsxid, Date lchgtime)
			throws Exception {

		dao.saveCustomerRequestToNodeTwoINTRADAY(customerRequest, reqPsxid, lchgtime);
	}

	@Override
	public List<String> getProfileIds() {

		return dao.getProfileIds();
	}

	@Override
	public String getSourceSysId(String product) {

		return dao.getSourceSysId(product);
	}

	public void updateRequestTable(String parameterString, long reqPsxid,int total_count, String reqStatus) {

		dao.updateRequestTable(parameterString, reqPsxid, total_count,reqStatus);
	}

	@Override
	public List<MatchedLoanDetails> getmatchedLoanDetails(String customerNoString) {
		return  dao.getmatchedLoanDetails(customerNoString);
	}

	@Override
	public List<MatchedLoanDetails> getmatchedLoanDetails(long reqPsxid) {
		return  dao.getmatchedLoanDetails(reqPsxid);
	}

	@Override
	public List<MatchedLoanDetails> getmatchedLoanDetails(List<Integer> customer_no) {
		return  dao.getmatchedLoanDetails(customer_no);
	}

	@Override
	public DemoGraphicCustomerDetails getDemographicCustomerDetails(
			String custUnqId, String demoGraphicTables) throws Exception {
		return  dao.getDemographicCustomerDetails(custUnqId,demoGraphicTables);
	}

	@Override
	public void webserviceRequestProcessingTime(long request_id,
			long logCompletionTime,String match_profile,String request_status) {
		  dao.webserviceRequestProcessingTime(request_id,logCompletionTime, match_profile, request_status);
		
	}

	@Override
	public List<MatchedLoanDetails> getmatchedLoanDetailsList(int customerNo)
			throws Exception {
		// TODO Auto-generated method stub
		return  dao.getmatchedLoanDetailsList(customerNo);
	}

	@Override
	public List<String> getValidProfilesList() throws DAOException {
		// TODO Auto-generated method stub
		return dao.getValidProfilesList();
	}

}
