package com.posidex.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.jms.JMSException;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import posidex.core.prime360adapters.jdbcadapter.RequestBean;

import com.google.gson.Gson;
import com.posidex.Producer;
import com.posidex.exception.PosidexException;
import com.posidex.service.PosidexRestServiceI;
import com.posidex.service.impl.Prime360ResponseQueueListener;
import com.posidex.ws.entity.CustomerObject;
import com.posidex.ws.entity.CustomerRequest;
import com.posidex.ws.entity.CustomerRequestDetails;
import com.posidex.ws.entity.CustomerResults;
import com.posidex.ws.entity.DemoGraphicCustomerDetails;
import com.posidex.ws.entity.DemoGraphicsResults;
import com.posidex.ws.entity.MatchedCustomerDetails;
import com.posidex.ws.entity.MatchedLoanDetails;
import com.posidex.ws.entity.RequestObject;

/***
 * 
 * @author raviteja
 *
 */
@RestController
public class BajajRestController {
	private static Logger logger = Logger.getLogger(BajajRestController.class
			.getName());
	@Autowired
	private PosidexRestServiceI serviceI;
	@Autowired
	Producer producer;
	@Autowired
	private Environment env;
	@Autowired
	Prime360ResponseQueueListener listener;
	static final String sourceSystemName1 = "PRIME360";
	static final String sourceSystemName2 = "NEGATIVE";
	static final String recordType = "INPUT";
	String errorMessage = "";

	private static String getClientIp(ServletRequest request)
			throws IOException {
		String remoteAddr = "";

		if (request != null) {
			remoteAddr = ((HttpServletRequest) request)
					.getHeader("X-FORWARDED-FOR");
			if (remoteAddr == null || "".equals(remoteAddr)) {
				remoteAddr = request.getRemoteAddr();
			}
		}
		logger.info("LAN address ::: " + remoteAddr);
		return remoteAddr;
	}

	private static String getWanClientIp() throws IOException {
		String command = "curl ifconfig.co";
		Process process = Runtime.getRuntime().exec(command);
		InputStream value = process.getInputStream();
		String wanaddress = convertInputStreamToStringCommonIO(value);

		// String wanAddress = InetAddress.getLocalHost().getHostAddress();
		String noSlashes = wanaddress.replace("\n", "");
		logger.info("WAN address :::" + noSlashes);
		return noSlashes;
	}

	private static String convertInputStreamToStringCommonIO(
			InputStream inputStream) throws IOException {
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int length;
		while ((length = inputStream.read(buffer)) != -1) {
			result.write(buffer, 0, length);
		}

		return result.toString(StandardCharsets.UTF_8.name());

	}

	/***
	 * This method is used to post the request to PrimeMatch Engine and Report
	 * the Results to
	 * 
	 * 
	 * @param customerRequest
	 * @param request1
	 * @return
	 * @throws PosidexException
	 */
	@PostMapping(value = "findCustomer", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerResults findCustomer(
			@RequestBody CustomerRequest customerRequest,
			HttpServletRequest request1) {
		logger.info("Customer Request Details ::: Request_ID: "+customerRequest.getRequestId() +", Match_Profile: "+customerRequest.getMatchProfile()+", Product: "+customerRequest.getRequestDetails().getProduct());
		LinkedHashMap<String, String> performanceLogs = new LinkedHashMap<>();
		performanceLogs.put("Message", customerRequest.getBflRequestId());
		long methodStartTime = System.currentTimeMillis();
		String matching_rule = "";
		long processedTime = 0;
		String weightages = "";
		String scale_stringent = "";
		String residual_parameters = "";
		long reqPsxid = 0L;
		String rankingOrders = "";
		String profileId;
		String productOrg;
		String reqStatus = "";
		String node1ReqStatus = "";
		String node2ReqStatus = "";
		int node2_match_count = 0;
		int node1_match_count = 0;
		int total_count = 0;
		int counterValue = 0;
		CustomerResults response = new CustomerResults();
		response.setBflRequestId(customerRequest.getBflRequestId());
		response.setDealId(customerRequest.getDealId());
		response.setRequestIdFin(customerRequest.getRequestIdFin());
		response.setDataSource(customerRequest.getDataSource());
		try {
			// getClientWanIp();

			// String wanaddress=getWanClientIp();
			// System.out.println("WAN"+wanaddress);
			// String[] allowedOrigin1 = env.getProperty("wanaddress")
			// .split(",");
			// List<String> authorizedIp1 = Arrays.asList(allowedOrigin1);
			// System.out.println("List :::" + authorizedIp1);
			// for (String ip : authorizedIp1) {
			// logger.debug("authorized ip is" + ip);
			// }
			// if (authorizedIp1.contains(wanaddress)) {
			// logger.info("Authorized");
			// } else {
			// logger.info("UnAuthorized");
			// throw new PosidexException("Unauthorized Origin");
			// }
			//
			 String requestIP = getClientIp(request1); 
		//	String requestIP = request1.getLocalAddr();
			 logger.info("current ip is " + requestIP);
			// *****************************************************************************
			// String requestIP = getClientIp(request1); request.getRemoteAddr()
	//		String requestIP = request1.getRemoteAddr();
		//	logger.info("LAN address ::: " + requestIP);
			String[] allowedOrigin = env.getProperty("AllowedOrigin")
					.split(",");
			List<String> authorizedIp = Arrays.asList(allowedOrigin);
			logger.info("Authorized IP's ::: " + authorizedIp);
//			for (String ip : authorizedIp) {
//				logger.debug("authorized ip is" + ip);
//			}
			if (authorizedIp.contains(requestIP)) {
				logger.info("Authorized");
			} else {
				logger.info("UnAuthorized");
				throw new PosidexException("Unauthorized Origin");
			}

			customerRequest.setRequestStatus("P");

			if (("").equalsIgnoreCase(customerRequest.getDataSource())
					|| ("string").equalsIgnoreCase(customerRequest
							.getDataSource())
					|| customerRequest.getDataSource() == null) {
				errorMessage = " DataSource field should not be Empty ";
				throw new PosidexException(
						"DataSource field should not be Empty");
			}
			if (("").equalsIgnoreCase(customerRequest.getRequestDetails()
					.getProduct())
					|| ("string").equalsIgnoreCase(customerRequest
							.getRequestDetails().getProduct())
					|| customerRequest.getRequestDetails().getProduct() == null) {
				errorMessage = "";
				errorMessage += " Product field should not be Empty ";
				throw new PosidexException("Product field should not be Empty");
			}
			if (("").equalsIgnoreCase(customerRequest.getBflRequestId())
					|| ("string").equalsIgnoreCase(customerRequest
							.getBflRequestId())
					|| customerRequest.getBflRequestId() == null) {
				errorMessage = "";
				errorMessage += " BflRequestId field should not be Empty ";
				throw new PosidexException(
						"BflRequestId field should not be Empty");
			}
			/*
			 * if (("").equalsIgnoreCase(customerRequest.getRequestDetails().
			 * getCustomerNo()) ||
			 * ("string").equalsIgnoreCase(customerRequest.getRequestDetails
			 * ().getCustomerNo()) ||
			 * customerRequest.getRequestDetails().getCustomerNo() == null) {
			 * errorMessage = ""; errorMessage +=
			 * " CustomerNo field should not be Empty "; throw new
			 * PosidexException("CustomerNo field should not be Empty"); }
			 */
			long perfTime = System.currentTimeMillis();
			reqPsxid = serviceI.appNextValProc();
			performanceLogs.put("generatedRequestId", "" + reqPsxid);
			performanceLogs.put("requestIDGenerationTime",
					"" + (System.currentTimeMillis() - perfTime));
			perfTime = System.currentTimeMillis();
			logger.info("AppNextValProc() procedure " + " Time Taken "
					+ (System.currentTimeMillis() - perfTime));
			HashMap<String, Object> dg_row1 = buildHashMap(customerRequest);
			dg_row1.put("PSX_ID", Long.toString(reqPsxid));
			dg_row1.put("CUST_UNQ_ID", customerRequest.getBflRequestId());
			dg_row1.put("DUIFLAG", "I_OR_U");
			performanceLogs.put("dataRowGenerationTime",
					"" + (System.currentTimeMillis() - perfTime));
			perfTime = System.currentTimeMillis();
			logger.info("HasHMap:" + dg_row1);

			profileId = customerRequest.getMatchProfile();
			if ((("").equals(profileId)) || profileId == null
					|| ("string").equals(profileId)) {
				profileId = serviceI.getProductProfileId(customerRequest
						.getRequestDetails().getProduct());
				logger.info("Getting  ProfileID :: " + profileId
						+ " :: for Product "
						+ customerRequest.getRequestDetails().getProduct());// 2

			}

			productOrg = serviceI.getProductOrg(customerRequest
					.getRequestDetails().getProduct());
			customerRequest.getRequestDetails().setOrg(productOrg);
			logger.info("Sending ProfileID :: " + profileId);
			customerRequest.setProfileId(profileId);
			
			/** 
			 * Setting profile_id based on the given product
			 */
			
			customerRequest.setMatchProfile(profileId);

		//	List<String> validProfileId=serviceI.getValidProfilesList();
			
			ServletContext context = null;
			Map<String, List<String>> psxNspProfilesMap = null;
			List<String> psxNspProfiles = null;

			try {
//				context = request1.getServletContext();
//				psxNspProfilesMap = (Map<String, List<String>>) context
//						.getAttribute("psxNspProfiles");
//				logger.info("Profiles Map ::: " + psxNspProfilesMap
//						+ " Customer given Profile Id ::: "
//						+ customerRequest.getMatchProfile());
				psxNspProfiles = serviceI.getPsxNspProfiles(profileId);
			//	psxNspProfiles = psxNspProfilesMap.get(customerRequest.getProfileId());
				logger.info("Matching Rule ::: "+psxNspProfiles.get(0));
				matching_rule = psxNspProfiles.get(0);

				residual_parameters = psxNspProfiles.get(1) != null ? psxNspProfiles
						.get(1) : "";
				weightages = psxNspProfiles.get(2);
				scale_stringent = psxNspProfiles.get(3) != null ? psxNspProfiles
						.get(3) : "";
				rankingOrders = psxNspProfiles.get(4) != null ? psxNspProfiles
						.get(4) : "";

			} catch (Exception e) {
				e.printStackTrace();
				throw new PosidexException(
						"Given ProfileID is not a Valid ProfileID");
			}

			performanceLogs.put("timeForBuildingProfileInformation", ""
					+ (System.currentTimeMillis() - perfTime));
			perfTime = System.currentTimeMillis();
			String product_type = customerRequest.getRequestDetails()
					.getProduct();
			ArrayList<HashMap<String, Object>> dgAl = new ArrayList<HashMap<String, Object>>();
			dgAl.add(dg_row1);
			HashMap<String, ArrayList<HashMap<String, Object>>> entry = new HashMap<String, ArrayList<HashMap<String, Object>>>();
			entry.put("CDAP", dgAl);
			HashMap<String, String> paramMap = new HashMap<>();
			paramMap.put("requestID", Long.toString(reqPsxid));
			RequestBean request = new RequestBean(weightages, entry,
					matching_rule, scale_stringent, "matchingProcess",
					paramMap, "P", Long.toString(reqPsxid), sourceSystemName1,
					rankingOrders);
			if (request.getParamMap() == null) {
				request.setParamMap(new HashMap<String, String>());
			}
			// request.getParamMap().put("QUEUUE",
			// env.getProperty("PRIME_MATCH_RESPONSE_QUEUE"));
			Date lchgtime = new Date();
			request.getRequestInformation()
					.get("CDAP")
					.forEach(
							x -> {
								x.put("LCHGTIME", new SimpleDateFormat(
										"dd-MM-yyyy HH:mm:ss").format(lchgtime));
							});
			if (residual_parameters.length() > 0)
				request.setResidualParams(residual_parameters);
			String requestJson = new Gson().toJson(request, RequestBean.class);
			String custUnqId = "";
			try {
				custUnqId = customerRequest.getBflRequestId();
				serviceI.saveCostomerRequest(customerRequest, reqPsxid,
						lchgtime);
				// if (customerRequest.getRequestType().equalsIgnoreCase("QA"))
				// {
				if ("QA".equalsIgnoreCase(customerRequest.getRequestType())) {
					int asciiValue = 0;
					if (custUnqId != null && !custUnqId.isEmpty()) {
						for (int i = 0; i < custUnqId.length(); i++) {
							asciiValue += custUnqId.charAt(i);
						}
					}
					if ((asciiValue
							% Integer.parseInt(env.getProperty("NODES_COUNT")) == 0)) {
						serviceI.saveCustomerRequestToNodeOneINTRADAY(
								customerRequest, reqPsxid, lchgtime);
					}
					if ((asciiValue
							% Integer.parseInt(env.getProperty("NODES_COUNT")) == 1)) {
						serviceI.saveCustomerRequestToNodeTwoINTRADAY(
								customerRequest, reqPsxid, lchgtime);
					}
				}
			} catch (Exception e) {
				logger.error("Exception occured while inserting request details..... "
						+ e.getMessage());
				reqStatus = "E";
				e.printStackTrace();
			//	throw new PosidexException("Exception occured while inserting request details..... ");
			
			int index=	e.getMessage().indexOf("nested exception is");
			System.out.println("Index Value :: "+index); //
			
			System.out.println("Index Value Substring :: "+e.getMessage().substring(index+20));
				throw new PosidexException(e.getMessage().substring(index+20).replace("\"", "").replace("\n", ""));
			}
			performanceLogs.put("timeForRequestInsertion",
					"" + (System.currentTimeMillis() - perfTime));
			perfTime = System.currentTimeMillis();
			CountDownLatch countDownLatch = new CountDownLatch(
					Integer.parseInt(env.getProperty("NODES_COUNT")));
			int expectedResponsesCount = Integer.parseInt(env
					.getProperty("NODES_COUNT"));
			Object[] values = new Object[] { countDownLatch };
			values = Arrays.copyOf(values, expectedResponsesCount + 1);
			listener.addPendingRequest(request.getRequestID(), values);
			logger.info("Adding Pending Request::");
			if (!(env.getProperty("FRAUD_PROFILE_ID")
					.equalsIgnoreCase(customerRequest.getMatchProfile()))) {
				logger.info("request msg::: " + requestJson);
				perfTime = System.currentTimeMillis();
				producer.sendMessage(
						env.getProperty("NODE_1_PRIME_MATCH_QUEUE"),
						requestJson,
						Boolean.parseBoolean(env.getProperty("isActiveMQ")));
				// producer.sendMessage(env.getProperty("NODE_1_PRIME_MATCH_QUEUE"),
				// requestJson);
				performanceLogs.put(
						"timeForSendingJMSMessage(s)PRIME_MATCH_QUEUE-1 :", ""
								+ (System.currentTimeMillis() - perfTime));
				perfTime = System.currentTimeMillis();
				producer.sendMessage(
						env.getProperty("NODE_2_PRIME_MATCH_QUEUE"),
						requestJson,
						Boolean.parseBoolean(env.getProperty("isActiveMQ")));
				// producer.sendMessage(env.getProperty("NODE_2_PRIME_MATCH_QUEUE"),
				// requestJson);

				performanceLogs.put(
						"timeForSendingJMSMessage(s)PRIME_MATCH_QUEUE-2 :", ""
								+ (System.currentTimeMillis() - perfTime));
				perfTime = System.currentTimeMillis();
			} else {
				perfTime = System.currentTimeMillis();
				producer.sendMessage(
						env.getProperty("NODE1_FRAUD_PROFILE_QUEUE_NAME"),
						requestJson,
						Boolean.parseBoolean(env.getProperty("isActiveMQ")));
				// producer.sendMessage(env.getProperty("NODE1_FRAUD_PROFILE_QUEUE_NAME"),
				// requestJson);
				producer.sendMessage(
						env.getProperty("NODE2_FRAUD_PROFILE_QUEUE_NAME"),
						requestJson,
						Boolean.parseBoolean(env.getProperty("isActiveMQ")));
				// producer.sendMessage(env.getProperty("NODE2_FRAUD_PROFILE_QUEUE_NAME"),
				// requestJson);
				performanceLogs.put(
						"timeForSendingJMSMessage(s)_FRAUD_PROFILE_QUEUE", ""
								+ (System.currentTimeMillis() - perfTime));
				perfTime = System.currentTimeMillis();
			}

			if ("QA".equalsIgnoreCase(customerRequest.getRequestType())) {
				perfTime = System.currentTimeMillis();
				sendMessageToIntraDay(psxNspProfiles, reqPsxid,
						new HashMap<String, String>(), entry, product_type,
						custUnqId);
				performanceLogs.put("timeForSendingJMSMessage(s)_QA", ""
						+ (System.currentTimeMillis() - perfTime));
				perfTime = System.currentTimeMillis();
			}
			boolean normalTermination = false;
			while (listener.isStillProcessing(request.getRequestID())) { // Loop
																			// iterates
																			// only
																			// in
																			// spurious
																			// wakeup
																			// case.
				try {
					normalTermination = countDownLatch
							.await(Integer.parseInt(env
									.getProperty("countDownAwaitTimeInMillis")),
									TimeUnit.MILLISECONDS);
				} catch (Exception e) {
					throw new PosidexException(
							"Exception occured while performing CountDown Latch Operation ..... ");
				}
				if (!normalTermination) {
					break;
				}
			}
			logger.info(String
					.format("Count Down Latch Await Time completed for the request:%d and the count down latch counter value is:%d",
							reqPsxid, countDownLatch.getCount()));
			List<String> requestInfo = new ArrayList<>();
			String parameterString = "";
			long startTime = System.currentTimeMillis();
			List<MatchedCustomerDetails> matchedCustomers = new ArrayList<>();
			// What is the use of this variable-Sree
			// List<String> requestInfo1 = new ArrayList<>();
			long startWhile = System.currentTimeMillis();
			int hitCount = 0;
			while (counterValue <= Integer.parseInt(env
					.getProperty("NODES_COUNT"))) {
				hitCount++;
				requestInfo = serviceI.checkRequestStatus(reqPsxid);
				if (!(requestInfo.isEmpty())) {

					if (requestInfo.get(2) != null) {
						counterValue = Integer.parseInt(requestInfo.get(2));
					}
					if (requestInfo.get(3) != null) {
						node1ReqStatus = requestInfo.get(3);
					}
					if (requestInfo.get(4) != null) {
						node2ReqStatus = requestInfo.get(4);
					}
				}
				processedTime = System.currentTimeMillis() - startTime;
				if (processedTime > Integer.parseInt(env
						.getProperty("requestTimeoutInMillis"))) {
					logger.warn("Process Time Limit Exceeded ... " + reqPsxid);
					break;
				}

				if ("E".equalsIgnoreCase(node1ReqStatus)
						|| "E".equalsIgnoreCase(node2ReqStatus)) {
					reqStatus = "E";
					response.setRequestId(reqPsxid);
					response.setErrorCode("404");
					response.setStatus("E");
					response.setErrorDesc("Internal Exception");
					logger.info(" Updating Request Status in Request Table ::: "+reqPsxid+" Counter Value :::: "+counterValue+" Node1ReqStatus :::: "+requestInfo.get(3)+" Node2ReqStatus ::::: "+requestInfo.get(4) +" Request Status ::: "+reqStatus);
					serviceI.updateRequestTable(parameterString, reqPsxid,
							total_count, reqStatus);
					break;
				}

				if (counterValue == Integer.parseInt(env
						.getProperty("NODES_COUNT"))) {
					break;
				}

			}
			
			logger.info("Request_ID >>>> "+reqPsxid+" Counter Value :::: "+counterValue+" Node1ReqStatus :::: "+requestInfo.get(3)+" Node2ReqStatus ::::: "+requestInfo.get(4) );

			logger.debug(String.format(
					"Time Taken to reach Counter %d, for request Id %d ",
					(System.currentTimeMillis() - methodStartTime), reqPsxid));
			performanceLogs.put("pollingTime", ""
					+ (System.currentTimeMillis() - perfTime));
			performanceLogs.put("Number of Times Polled", hitCount + "");
			performanceLogs.put("countDownLatch Count",
					"" + countDownLatch.getCount());
			for (int i = 1; i < values.length; i++) {
				performanceLogs.put(
						"Response" + i + " Received At:",
						values[i] == null ? null : new SimpleDateFormat(
								"dd-MM-yyyy HH:mm:ss S").format(new Date(
								(Long) values[i])));
			}
			perfTime = System.currentTimeMillis();
			logger.info(String
					.format("RequestID: %d Polling time is %d and the number of hits are:%d",
							reqPsxid,
							(System.currentTimeMillis() - startWhile), hitCount));
			if (counterValue < Integer.parseInt(env.getProperty("NODES_COUNT"))) {
				
				
				if ("E".equalsIgnoreCase(node1ReqStatus)
						|| "E".equalsIgnoreCase(node2ReqStatus)) {
					reqStatus = "E";
					response.setRequestId(reqPsxid);
					response.setErrorCode("404");
					response.setStatus("E");
					response.setErrorDesc("Internal Exception");
				}
				
				if (node1ReqStatus.equalsIgnoreCase(("P"))
						&& node2ReqStatus.equalsIgnoreCase(("P"))) {
					reqStatus = "P";
					response.setRequestId(reqPsxid);
					response.setStatus("P");
					response.setErrorDesc("Pending Request ");
				} else if (node1ReqStatus.equalsIgnoreCase(("C"))
						&& node2ReqStatus.equalsIgnoreCase(("P"))) {

					reqStatus = "P";
					// reqStatus = "PARTIAL";
					response.setRequestId(reqPsxid);
					response.setStatus("PARTIAL");
					response.setErrorDesc("Pending Request");
					logger.info("Request Status in Controller 1U 2D::: "
							+ reqStatus);
				} else if (node1ReqStatus.equalsIgnoreCase(("P"))
						&& node2ReqStatus.equalsIgnoreCase(("C"))) {
					reqStatus = "P";
					response.setRequestId(reqPsxid);
					response.setStatus("PARTIAL");
					response.setErrorDesc("Pending Request");
				}
//				if (processedTime >= Integer.parseInt(env
//						.getProperty("requestTimeoutInMillis"))) {
//					reqStatus = "P";
//					response.setRequestId(reqPsxid);
//					response.setStatus("P");
//					response.setErrorCode("504");
//					response.setErrorDesc("Process Time Limit Exceeds..... ");
//				}
				

			}
			if (counterValue == Integer
					.parseInt(env.getProperty("NODES_COUNT"))) {
				if (processedTime >= Integer.parseInt(env
						.getProperty("requestTimeoutInMillis"))) {
					reqStatus = "P";
					response.setRequestId(reqPsxid);
					response.setStatus("P");
					response.setErrorCode("504");
					response.setErrorDesc("Process Time Limit Exceeds..... ");
				}
			}
			if (node1ReqStatus.equalsIgnoreCase(("C"))
					|| node2ReqStatus.equalsIgnoreCase(("C"))) {
				if (counterValue < Integer.parseInt(env
						.getProperty("NODES_COUNT"))) {
					reqStatus = "P";
					response.setRequestId(reqPsxid);
					response.setStatus("PARTIAL");
					response.setErrorDesc("Pending Request");
				}

			}
//			if (processedTime >= Integer.parseInt(env
//					.getProperty("requestTimeoutInMillis"))) {
//				reqStatus = "P";
//				response.setRequestId(reqPsxid);
//				response.setStatus("P");
//				response.setErrorCode("504");
//				response.setErrorDesc("Process Time Limit Exceeds..... ");
//			}

			if (Integer.parseInt(env.getProperty("NODES_COUNT")) == counterValue
					&& node1ReqStatus.equalsIgnoreCase(("C"))
					&& node2ReqStatus.equalsIgnoreCase(("C"))) {
				try {
					// requestInfo1 = serviceI.checkRequestStatus(reqPsxid);
					// reqStatus = requestInfo.get(0);
					reqStatus = "C";
					/*
					 * if (requestInfo.get(1) != null) { match_count =
					 * Integer.parseInt(requestInfo.get(1)); } else {
					 * match_count = 0; }
					 */
					if (requestInfo.get(0) != null) {
						node2_match_count = Integer
								.parseInt(requestInfo.get(0));
					}
					if (requestInfo.get(1) != null) {
						node1_match_count = Integer
								.parseInt(requestInfo.get(1));
					}
					/*
					 * if (requestInfo.get(5) != null) { negative_match_count =
					 * Integer.parseInt(requestInfo.get(5)); } else {
					 * negative_match_count = 0; }
					 */
					// total_count = node1_match_count + node2_match_count +
					// negative_match_count;
					total_count = node1_match_count + node2_match_count;
					long mc_start_time = System.currentTimeMillis();
					matchedCustomers = serviceI.getmatchedCustomers(reqPsxid);
					logger.info("Matched Customer Details List Size :::"
							+ matchedCustomers.size() + " Time Taken "
							+ (System.currentTimeMillis() - mc_start_time));
				} catch (Exception e) {
					logger.error("Exception has Caught inside MatchedCustomers Details");
					reqStatus = "E";
					throw new PosidexException(
							"Exception occured while inside MatchedCustomers Details..... ");
				}
				performanceLogs.put("timeForGettingMatchedCustomers", ""
						+ (System.currentTimeMillis() - perfTime));
				perfTime = System.currentTimeMillis();
				List<MatchedLoanDetails> matchedLoanDetailsList = new ArrayList<>();

				List<String> dataSource = new ArrayList<>();

				// Need to get these values from Properties File-Sree
				dataSource.add("CUSTOMER_CF");
				dataSource.add("CUSTOMER_AF");
				dataSource.add("PENNANT");
				dataSource.add("PENNANT_HFC");
				dataSource.add("WRF");

				// Removed unused variable-Sree
				// List<Integer> customer_no = new ArrayList<>();
				response.setNegativeMatchCriteria("NOMATCH");
				String joined1 = "";
				for (MatchedCustomerDetails matchedCustomer : matchedCustomers) {
					if (sourceSystemName2.equalsIgnoreCase(matchedCustomer
							.getPsxsourceSystem())) {
						parameterString = parameterString.concat(
								matchedCustomer.getArea_id()).concat(",");
						if (matchedCustomer.getMatchingRule() != null) {
							/*
							 * if (matchedCustomer.getMatchingRule()
							 * .contains(env
							 * .getProperty("NEGATIVE_MATCH_INDIRECT_RULE"))) {
							 * response.setNegativeMatchCriteria("INDIRECT"); }
							 * if (matchedCustomer.getMatchingRule()
							 * .contains(env
							 * .getProperty("NEGATIVE_MATCH_DIRECT_RULE"))) {
							 * response.setNegativeMatchCriteria("DIRECT"); }
							 */
							String[] indirectRuleNo = env.getProperty(
									"NEGATIVE_MATCH_INDIRECT_RULE").split(",");
							for (String drule : indirectRuleNo) {
								if (matchedCustomer.getMatchingRule().contains(
										drule)) {
									response.setNegativeMatchCriteria("INDIRECT");
								}
							}
							String[] directRuleNo = env.getProperty(
									"NEGATIVE_MATCH_DIRECT_RULE").split(",");
							for (String drule : directRuleNo) {
								if (matchedCustomer.getMatchingRule().contains(
										drule)) {
									response.setNegativeMatchCriteria("DIRECT");
								}
							}
						}
					} else {
						// if (matchedCustomer.getCustomerNo() != 0 &&
						// dataSource.contains(matchedCustomer.getSourceSystem())
						// &&
						// (sourceSystemName1).equalsIgnoreCase(matchedCustomer.getPsxsourceSystem()))
						// {
						if (matchedCustomer.getCust_unq_id() != null
								&& dataSource.contains(matchedCustomer
										.getSourceSystem())) {
							// MatchedLoanDetails
							// matchedLoanDetails;PSX_NSP_PM_RESP_QUEUE
							// matchedLoanDetails =
							// serviceI.getmatchedLoanDetails(matchedCustomer.getCustomerId());
							// matchedLoanDetailsList.add(matchedLoanDetails);
							// if (matchedCustomer.getCustomerNo() != 0 &&
							// dataSource.contains(matchedCustomer.getPsxsourceSystem()))
							// {
							// customer_no.add(matchedCustomer.getCustomerNo());

							// joined1 =
							// joined1.concat(matchedCustomer.getCustomerNo()+"").concat(",");

							joined1 = joined1.concat(
									matchedCustomer.getCust_unq_id()).concat(
									",");

						}

					}
				}
				performanceLogs.put("timeForBuildingNegativeIDsList", ""
						+ (System.currentTimeMillis() - perfTime));
				perfTime = System.currentTimeMillis();
				// logger.info("Joined Values " + joined1);
				// if(joined1.length()>1)
				logger.info("Cust_unq_ids for LOAN Details :::" + joined1);
				if (!(joined1.isEmpty())) {
					long ml_start_time = System.currentTimeMillis();
					matchedLoanDetailsList = serviceI
							.getmatchedLoanDetails(joined1);
					// matchedLoanDetailsList=serviceI.getmatchedLoanDetails(customer_no);

					logger.info("Matched Loan Details List Size ::::"
							+ matchedLoanDetailsList.size() + " Time Taken "
							+ (System.currentTimeMillis() - ml_start_time));
				}
				performanceLogs.put("timeForGettingMatchedLoan Details", ""
						+ (System.currentTimeMillis() - perfTime));
				perfTime = System.currentTimeMillis();

				if (parameterString.isEmpty()) {
					parameterString = "0";
				} else {
					parameterString = parameterString.substring(0,
							parameterString.length() - 1);
				}

				response.setRequestId(reqPsxid);
				response.setStatus(reqStatus);
				logger.info("Total Match Count....." + total_count);
				response.setMatchCount(total_count);
				response.setRequestIdFin(customerRequest.getRequestIdFin());
				response.setProduct(customerRequest.getRequestDetails()
						.getProduct());
				response.setDataSource(customerRequest.getDataSource());
				logger.info(" Updating Request Status in Request Table ::: "+reqPsxid+" Counter Value :::: "+counterValue+" Node1ReqStatus :::: "+requestInfo.get(3)+" Node2ReqStatus ::::: "+requestInfo.get(4) +" Request Status ::: "+reqStatus);

				serviceI.updateRequestTable(parameterString, reqPsxid,
						total_count, reqStatus);
				response.setNegativeMatchId(parameterString);
				response.setMatchedCustomerDetailsList(matchedCustomers);
				response.setMatchedLoanDetailsList(matchedLoanDetailsList);
				performanceLogs.put("timeForUpdatingTotalMatchCount", ""
						+ (System.currentTimeMillis() - perfTime));
				perfTime = System.currentTimeMillis();

			}

		} catch (Exception e) {
			/*
			 * if(e.getMessage().contains("")) {
			 * 
			 * }
			 */
			logger.error("Exception occured in findCustomer " + e.getMessage());
			logger.error(e, e);
			response.setRequestId(reqPsxid);
			response.setStatus("E");
			reqStatus="E";
			response.setErrorCode("100");
			response.setErrorDesc(e.getMessage());
			return response;
		} finally {
			long methodEndTime = System.currentTimeMillis();
			long logCompletionTime;
			if (customerRequest == null) {
				logger.info("customerRequest object is null");
			} else {
				logger.info(String
						.format("Request ID: %d ,Bajaj request id:%s Time Taken in millis:%d. Request StartTime is:%s. RequestEndTime is:%s",
								reqPsxid, customerRequest.getBflRequestId(),
								(methodEndTime - methodStartTime),
								new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S")
										.format(new Date(methodStartTime)),
								new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S")
										.format(new Date(methodEndTime))));
				performanceLogs.put("requestReceivedAt", new SimpleDateFormat(
						"dd-MM-yyyy HH:mm:ss S").format(new Date(
						methodStartTime)));
				performanceLogs.put("requestCompletedAt", new SimpleDateFormat(
						"dd-MM-yyyy HH:mm:ss S")
						.format(new Date(methodEndTime)));
				performanceLogs.put("totalTimeForRequestProcessing is:", ""
						+ (logCompletionTime=(methodEndTime - methodStartTime)));
				webserviceRequestProcessingTime(reqPsxid,logCompletionTime,customerRequest.getMatchProfile(),reqStatus);
				logger.info(performanceLogs.toString());
			}
		}
		return response;
	}

	/***
	 * This method is used to build Customer Request HashMap.
	 * 
	 * @param customerRequest
	 * @return HashMap<String, Object>
	 * @throws Exception
	 */

	private HashMap<String, Object> buildHashMap(CustomerRequest customerRequest)
			throws Exception {
		logger.info("Inside buildHashMap");
		String day = "";
		String month = "";
		String year = "";
		HashMap<String, Object> dg_row1 = new HashMap<>();
		try {
			DateFormat df1 = new SimpleDateFormat("dd/MMM/yyyy"); // for parsing
																	// input
			DateFormat df2 = new SimpleDateFormat("dd/MM/yyyy"); // for
																	// formatting
																	// output
			String dob1 = customerRequest.getRequestDetails().getDob1();
			String convertedDob1 = "";
			if (dob1 != null && dob1 != "") {
				Date d = df1.parse(dob1);
				convertedDob1 = df2.format(d);
				String[] dob1Split = convertedDob1.split("/");
				day = dob1Split[0];
				month = dob1Split[1];
				year = dob1Split[2];
			}
			String doi = customerRequest.getRequestDetails()
					.getDateOfInCorporation();
			String convertedDoi = "";
			if (doi != null && doi != "") {
				Date d1 = df1.parse(doi);
				convertedDoi = df2.format(d1);
			}
			String firstName;
			String middleName;
			String lastName;
			if (customerRequest.getRequestDetails().getFirstName() == null) {
				firstName = "";
			} else {
				firstName = customerRequest.getRequestDetails().getFirstName();
			}
			// if (customerRequest.getRequestDetails().getMiddleName()==null) {
			if (customerRequest.getRequestDetails().getMiddleName() == null) {
				middleName = "";
			} else {
				middleName = customerRequest.getRequestDetails()
						.getMiddleName();
			}
			if (customerRequest.getRequestDetails().getLastName() == null) {
				lastName = "";
			} else {
				lastName = customerRequest.getRequestDetails().getLastName();
			}

			System.out.println("While building Request "
					+ customerRequest.getRequestDetails().getEmployerName());
			customerRequest.getRequestDetails()
					.setName(
							customerRequest.getRequestDetails().getFirstName()
									+ " "
									+ customerRequest.getRequestDetails()
											.getMiddleName()
									+ " "
									+ customerRequest.getRequestDetails()
											.getLastName());
			if (customerRequest.getRequestDetails().getName() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getName())))
				dg_row1.put("NAME", customerRequest.getRequestDetails()
						.getName());
			if (customerRequest.getRequestDetails().getEmployerName() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getEmployerName())))
				dg_row1.put("EMPLOYER_NAME", customerRequest
						.getRequestDetails().getEmployerName());
			if (customerRequest.getRequestDetails().getFatherName() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getFatherName())))
				dg_row1.put("FATHERNAME", customerRequest.getRequestDetails()
						.getFatherName());
			if (customerRequest.getRequestDetails().getMotherName() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getMotherName())))
				dg_row1.put("MOTHERNAME", customerRequest.getRequestDetails()
						.getMotherName());
			if (customerRequest.getRequestDetails().getSpouseName() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getSpouseName())))
				dg_row1.put("SPOUSENAME", customerRequest.getRequestDetails()
						.getSpouseName());
			if (customerRequest.getRequestDetails().getDob1() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getDob1())))
				dg_row1.put("DOB1", convertedDob1);
			if (customerRequest.getRequestDetails().getDateOfInCorporation() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getDateOfInCorporation())))
				dg_row1.put("DOI", convertedDoi);
			if (customerRequest.getRequestDetails().getPan() != null
					&& !("".equals(customerRequest.getRequestDetails().getPan())))
				dg_row1.put("PAN", customerRequest.getRequestDetails().getPan());
			if (customerRequest.getRequestDetails().getCreditCardNumber() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getCreditCardNumber())))
				dg_row1.put("CREDIT_CARD_NUMBER", customerRequest
						.getRequestDetails().getCreditCardNumber());
			if (customerRequest.getRequestDetails().getDrivingLicenseNumber() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getDrivingLicenseNumber())))
				dg_row1.put("DRIVINGLIC", customerRequest.getRequestDetails()
						.getDrivingLicenseNumber());
			if (customerRequest.getRequestDetails().getTanNo() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getTanNo())))
				dg_row1.put("TAN_NO", customerRequest.getRequestDetails()
						.getTanNo());
			if (customerRequest.getRequestDetails().getCin() != null
					&& !("".equals(customerRequest.getRequestDetails().getCin())))
				dg_row1.put("CIN", customerRequest.getRequestDetails().getCin());
			if (customerRequest.getRequestDetails().getDin() != null
					&& !("".equals(customerRequest.getRequestDetails().getDin())))
				dg_row1.put("DIN", customerRequest.getRequestDetails().getDin());
			if (customerRequest.getRequestDetails().getRegistrationNo() != null
					&& !("".equals(customerRequest.getRequestDetails()
							.getRegistrationNo())))
				dg_row1.put("REGISTRATION_NO", customerRequest
						.getRequestDetails().getRegistrationNo());
			if (customerRequest.getRequestDetails().getPassportNo() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPassportNo()))
				dg_row1.put("PASSPORT", customerRequest.getRequestDetails()
						.getPassportNo());
			if (customerRequest.getRequestDetails().getVoterId() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getVoterId()))
				dg_row1.put("VOTERID", customerRequest.getRequestDetails()
						.getVoterId());
			if (customerRequest.getRequestDetails().getAadhaar() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getAadhaar()))
				dg_row1.put("AADHAAR", customerRequest.getRequestDetails()
						.getAadhaar());
			if (customerRequest.getRequestDetails().getAddress() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getAddress()))
				dg_row1.put("ADDRESS", customerRequest.getRequestDetails()
						.getAddress());
			if (customerRequest.getRequestDetails().getPhone() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPhone()))
				dg_row1.put("PHONE", customerRequest.getRequestDetails()
						.getPhone());
			if (customerRequest.getRequestDetails().getOrg() != null
					&& !"".equals(customerRequest.getRequestDetails().getOrg()))
				dg_row1.put("ORG", customerRequest.getRequestDetails().getOrg());
			if (customerRequest.getRequestDetails().getSegment() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getSegment()))
				dg_row1.put("SEGMENT", customerRequest.getRequestDetails()
						.getSegment());
			if (customerRequest.getRequestDetails().getArea() != null
					&& !"".equals(customerRequest.getRequestDetails().getArea()))
				dg_row1.put("AREA", customerRequest.getRequestDetails()
						.getArea());
			if (customerRequest.getRequestDetails().getCity() != null
					&& !"".equals(customerRequest.getRequestDetails().getCity()))
				dg_row1.put("CITY", customerRequest.getRequestDetails()
						.getCity());
			if (customerRequest.getRequestDetails().getPin() != null
					&& !"".equals(customerRequest.getRequestDetails().getPin()))
				dg_row1.put("PIN", customerRequest.getRequestDetails().getPin());
			/*
			 * if (customerRequest.getRequestDetails().getOffice_address() !=
			 * null &&
			 * !customerRequest.getRequestDetails().getOffice_address().equals
			 * ("")) dg_row1.put("OFFICE_ADDRESS",
			 * customerRequest.getRequestDetails().getOffice_address());
			 */
			if (customerRequest.getRequestDetails().getAddress1Office() + "  "
					+ customerRequest.getRequestDetails().getAddress2Office()
					+ "  "
					+ customerRequest.getRequestDetails().getAddress3Office() != null
					&& !("").equals(customerRequest.getRequestDetails()
							.getAddress1Office()
							+ "  "
							+ customerRequest.getRequestDetails()
									.getAddress2Office()
							+ "  "
							+ customerRequest.getRequestDetails()
									.getAddress3Office()))
				dg_row1.put("OFFICE_ADDRESS", customerRequest
						.getRequestDetails().getAddress1Office()
						+ "  "
						+ customerRequest.getRequestDetails()
								.getAddress2Office()
						+ "  "
						+ customerRequest.getRequestDetails()
								.getAddress3Office());
			/*
			 * if (customerRequest.getRequestDetails().getOffice_city() != null
			 * &&
			 * !customerRequest.getRequestDetails().getOffice_city().equals(""))
			 * dg_row1.put("OFFICE_CITY",
			 * customerRequest.getRequestDetails().getOffice_city());
			 */
			if (customerRequest.getRequestDetails().getCityOffice() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getCityOffice()))
				dg_row1.put("OFFICE_CITY", customerRequest.getRequestDetails()
						.getCityOffice());
			/*
			 * if (customerRequest.getRequestDetails().getOffice_pin() != null
			 * &&
			 * !customerRequest.getRequestDetails().getOffice_pin().equals(""))
			 * dg_row1.put("OFFICE_PIN",
			 * customerRequest.getRequestDetails().getOffice_pin());
			 */
			if (customerRequest.getRequestDetails().getPinOffice() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPinOffice()))
				dg_row1.put("OFFICE_PIN", customerRequest.getRequestDetails()
						.getPinOffice());
			/*
			 * if (customerRequest.getRequestDetails().getOffice_email() != null
			 * &&
			 * !customerRequest.getRequestDetails().getOffice_email().equals(""
			 * )) dg_row1.put("OFFICE_EMAIL",
			 * customerRequest.getRequestDetails().getOffice_email());
			 */
			if (customerRequest.getRequestDetails().getEmailOffice() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getEmailOffice()))
				dg_row1.put("OFFICE_EMAIL", customerRequest.getRequestDetails()
						.getEmailOffice());
			if (customerRequest.getRequestDetails().getPermanent_address() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPermanent_address()))
				dg_row1.put("PERMANENT_ADDRESS", customerRequest
						.getRequestDetails().getPermanent_address());
			if (customerRequest.getRequestDetails().getPermanent_city() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPermanent_city()))
				dg_row1.put("PERMANENT_CITY", customerRequest
						.getRequestDetails().getPermanent_city());
			if (customerRequest.getRequestDetails().getPermanent_pin() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPermanent_pin()))
				dg_row1.put("PERMANENT_PIN", customerRequest
						.getRequestDetails().getPermanent_pin());
			if (customerRequest.getRequestDetails().getPermanent_email() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPermanent_email()))
				dg_row1.put("PERMANENT_EMAIL", customerRequest
						.getRequestDetails().getPermanent_email());
			if (customerRequest.getRequestDetails().getPresent_address() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPresent_address()))
				dg_row1.put("PRESENT_ADDRESS", customerRequest
						.getRequestDetails().getPresent_address());
			if (customerRequest.getRequestDetails().getPresent_city() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPresent_city()))
				dg_row1.put("PRESENT_CITY", customerRequest.getRequestDetails()
						.getPresent_city());
			if (customerRequest.getRequestDetails().getPresent_pin() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPresent_pin()))
				dg_row1.put("PRESENT_PIN", customerRequest.getRequestDetails()
						.getPresent_pin());
			if (customerRequest.getRequestDetails().getPresent_email() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPresent_email()))
				dg_row1.put("PRESENT_EMAIL", customerRequest
						.getRequestDetails().getPresent_email());
			if (customerRequest.getRequestDetails().getEmergency_address() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getEmergency_address()))
				dg_row1.put("EMERGENCY_ADDRESS", customerRequest
						.getRequestDetails().getEmergency_address());
			if (customerRequest.getRequestDetails().getEmergency_city() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getEmergency_city()))
				dg_row1.put("EMERGENCY_CITY", customerRequest
						.getRequestDetails().getEmergency_city());
			if (customerRequest.getRequestDetails().getEmergency_pin() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getEmergency_pin()))
				dg_row1.put("EMERGENCY_PIN", customerRequest
						.getRequestDetails().getEmergency_pin());
			if (customerRequest.getRequestDetails().getEmergency_email() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getEmergency_email()))
				dg_row1.put("EMERGENCY_EMAIL", customerRequest
						.getRequestDetails().getEmergency_email());
			if (customerRequest.getRequestDetails().getContact_address() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getContact_address()))
				dg_row1.put("CONTACT_ADDRESS", customerRequest
						.getRequestDetails().getContact_address());
			if (customerRequest.getRequestDetails().getContact_city() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getContact_city()))
				dg_row1.put("CONTACT_CITY", customerRequest.getRequestDetails()
						.getContact_city());
			if (customerRequest.getRequestDetails().getContact_pin() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getContact_pin()))
				dg_row1.put("CONTACT_PIN", customerRequest.getRequestDetails()
						.getContact_pin());
			if (customerRequest.getRequestDetails().getContact_email() != null
					&& !customerRequest.getRequestDetails().getContact_email()
							.equals(""))
				dg_row1.put("CONTACT_EMAIL", customerRequest
						.getRequestDetails().getContact_email());
			if (customerRequest.getRequestDetails().getPostal_address() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPostal_address()))
				dg_row1.put("POSTAL_ADDRESS", customerRequest
						.getRequestDetails().getPostal_address());
			if (customerRequest.getRequestDetails().getPostal_city() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPostal_city()))
				dg_row1.put("POSTAL_CITY", customerRequest.getRequestDetails()
						.getPostal_city());
			if (customerRequest.getRequestDetails().getPostal_pin() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPostal_pin()))
				dg_row1.put("POSTAL_PIN", customerRequest.getRequestDetails()
						.getPostal_pin());
			if (customerRequest.getRequestDetails().getPostal_email() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPostal_email()))
				dg_row1.put("POSTAL_EMAIL", customerRequest.getRequestDetails()
						.getPostal_email());
			/*
			 * if (customerRequest.getRequestDetails().getResidence_address() !=
			 * null &&
			 * !customerRequest.getRequestDetails().getResidence_address(
			 * ).equals("")) dg_row1.put("RESIDENCE_ADDRESS",
			 * customerRequest.getRequestDetails().getResidence_address());
			 */
			if (customerRequest.getRequestDetails().getAddress1() + "  "
					+ customerRequest.getRequestDetails().getAddress2() + "  "
					+ customerRequest.getRequestDetails().getAddress3() != null
					&& !("").equals(customerRequest.getRequestDetails()
							.getAddress1()
							+ "  "
							+ customerRequest.getRequestDetails().getAddress2()
							+ "  "
							+ customerRequest.getRequestDetails().getAddress3()))
				dg_row1.put("RESIDENCE_ADDRESS", customerRequest
						.getRequestDetails().getAddress1()
						+ "  "
						+ customerRequest.getRequestDetails().getAddress2()
						+ "  "
						+ customerRequest.getRequestDetails().getAddress3());
			/*
			 * if (customerRequest.getRequestDetails().getPostal_city() != null
			 * &&
			 * !customerRequest.getRequestDetails().getResidence_city().equals
			 * ("")) dg_row1.put("RESIDENCE_CITY",
			 * customerRequest.getRequestDetails().getResidence_city());
			 */
			if (customerRequest.getRequestDetails().getCity() != null
					&& !"".equals(customerRequest.getRequestDetails().getCity()))
				dg_row1.put("RESIDENCE_CITY", customerRequest
						.getRequestDetails().getCity());
			/*
			 * if (customerRequest.getRequestDetails().getResidence_pin() !=
			 * null &&
			 * !customerRequest.getRequestDetails().getResidence_pin().equals
			 * ("")) dg_row1.put("RESIDENCE_PIN",
			 * customerRequest.getRequestDetails().getResidence_pin());
			 */
			if (customerRequest.getRequestDetails().getPin() != null
					&& !"".equals(customerRequest.getRequestDetails().getPin()))
				dg_row1.put("RESIDENCE_PIN", customerRequest
						.getRequestDetails().getPin());
			/*
			 * if (customerRequest.getRequestDetails().getResidence_email() !=
			 * null &&
			 * !customerRequest.getRequestDetails().getResidence_email().
			 * equals("")) dg_row1.put("RESIDENCE_EMAIL",
			 * customerRequest.getRequestDetails().getResidence_email());
			 */
			if (customerRequest.getRequestDetails().getEmail() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getEmail()))
				dg_row1.put("RESIDENCE_EMAIL", customerRequest
						.getRequestDetails().getEmail());
			if (customerRequest.getRequestDetails().getPreferred_address() != null
					&& !customerRequest.getRequestDetails()
							.getPreferred_address().equals(""))
				dg_row1.put("PREFERRED_ADDRESS", customerRequest
						.getRequestDetails().getPreferred_address());
			if (customerRequest.getRequestDetails().getPreferred_city() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPreferred_city()))
				dg_row1.put("PREFERRED_CITY", customerRequest
						.getRequestDetails().getPreferred_city());
			if (customerRequest.getRequestDetails().getPreferred_pin() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPreferred_pin()))
				dg_row1.put("PREFERRED_PIN", customerRequest
						.getRequestDetails().getPreferred_pin());
			if (customerRequest.getRequestDetails().getPreferred_email() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPreferred_email()))
				dg_row1.put("PREFERRED_EMAIL", customerRequest
						.getRequestDetails().getPreferred_email());
			if (customerRequest.getRequestDetails().getTemporary_address() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getTemporary_address()))
				dg_row1.put("TEMPORARY_ADDRESS", customerRequest
						.getRequestDetails().getTemporary_address());
			if (customerRequest.getRequestDetails().getTemporary_city() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getTemporary_city()))
				dg_row1.put("TEMPORARY__CITY", customerRequest
						.getRequestDetails().getTemporary_city());
			if (customerRequest.getRequestDetails().getTemporary_pin() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getTemporary_pin()))
				dg_row1.put("TEMPORARY__PIN", customerRequest
						.getRequestDetails().getTemporary_pin());
			if (customerRequest.getRequestDetails().getTemporary_email() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getTemporary_email()))
				dg_row1.put("TEMPORARY__EMAIL", customerRequest
						.getRequestDetails().getTemporary_email());
			/*
			 * if (customerRequest.getRequestDetails().getOffice_phone() != null
			 * &&
			 * !"".equals(customerRequest.getRequestDetails().getOffice_phone(
			 * ))) dg_row1.put("OFFICE_PHONE",
			 * customerRequest.getRequestDetails().getOffice_phone());
			 */
			if (customerRequest.getRequestDetails().getLandLine1Office() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getLandLine1Office()))
				dg_row1.put("OFFICE_PHONE", customerRequest.getRequestDetails()
						.getLandLine1Office());
			/*
			 * if (customerRequest.getRequestDetails().getPermanent_phone() !=
			 * null &&
			 * !"".equals(customerRequest.getRequestDetails().getPermanent_phone
			 * ())) dg_row1.put("PERMANENT_PHONE",
			 * customerRequest.getRequestDetails().getPermanent_phone());
			 */
			if (customerRequest.getRequestDetails().getLandLine2Office() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getLandLine2Office()))
				dg_row1.put("PERMANENT_PHONE", customerRequest
						.getRequestDetails().getLandLine2Office());
			if (customerRequest.getRequestDetails().getPresent_phone() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPresent_phone()))
				dg_row1.put("PRESENT_PHONE", customerRequest
						.getRequestDetails().getPresent_phone());
			if (customerRequest.getRequestDetails().getEmergency_phone() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getEmergency_phone()))
				dg_row1.put("EMERGENCY_PHONE", customerRequest
						.getRequestDetails().getEmergency_phone());
			if (customerRequest.getRequestDetails().getContact_phone() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getContact_phone()))
				dg_row1.put("CONTACT_PHONE", customerRequest
						.getRequestDetails().getContact_phone());
			/*
			 * if (customerRequest.getRequestDetails().getPostal_phone() != null
			 * &&
			 * !"".equals(customerRequest.getRequestDetails().getPostal_phone(
			 * ))) dg_row1.put("POSTAL_PHONE",
			 * customerRequest.getRequestDetails().getPostal_phone());
			 */
			if (customerRequest.getRequestDetails().getPhone() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getPhone()))
				dg_row1.put("POSTAL_PHONE", customerRequest.getRequestDetails()
						.getPhone());
			/*
			 * if (customerRequest.getRequestDetails().getResidence_phone() !=
			 * null &&
			 * !"".equals(customerRequest.getRequestDetails().getResidence_phone
			 * ())) dg_row1.put("RESIDENCE_PHONE",
			 * customerRequest.getRequestDetails().getResidence_phone());
			 */
			if (customerRequest.getRequestDetails().getLandLine1() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getLandLine1()))
				dg_row1.put("RESIDENCE_PHONE", customerRequest
						.getRequestDetails().getLandLine1());

			/*
			 * if (customerRequest.getRequestDetails().getPreferred_phone() !=
			 * null &&
			 * !"".equals(customerRequest.getRequestDetails().getPreferred_phone
			 * ())) dg_row1.put("PREFERRED_PHONE",
			 * customerRequest.getRequestDetails().getPreferred_phone());
			 */
			if (customerRequest.getRequestDetails().getMobileOffice() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getMobileOffice()))
				dg_row1.put("PREFERRED_PHONE", customerRequest
						.getRequestDetails().getMobileOffice());
			if (customerRequest.getRequestDetails().getMobile() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getMobile()))
				dg_row1.put("MOBILE", customerRequest.getRequestDetails()
						.getMobile());
			/*
			 * if (customerRequest.getRequestDetails().getTemporary_phone() !=
			 * null &&
			 * !"".equals(customerRequest.getRequestDetails().getTemporary_phone
			 * ())) dg_row1.put("TEMPORARY__PHONE",
			 * customerRequest.getRequestDetails().getTemporary_phone());
			 */
			if (customerRequest.getRequestDetails().getLandLine2() != null
					&& !"".equals(customerRequest.getRequestDetails()
							.getLandLine2()))
				dg_row1.put("TEMPORARY__PHONE", customerRequest
						.getRequestDetails().getLandLine2());
			dg_row1.put("LCHGTIME", new SimpleDateFormat("dd-MM-YYYY hh:mm:ss")
					.format(new Date()));
			dg_row1.put("DAY", day);
			dg_row1.put("MONTH", month);
			dg_row1.put("YEAR", year);

			/** Changes added as on 07th June 2019 */
			dg_row1.put("FIRSTNAME", customerRequest.getRequestDetails()
					.getFirstName());
			dg_row1.put("MIDDLENAME", customerRequest.getRequestDetails()
					.getMiddleName());
			dg_row1.put("LASTNAME", customerRequest.getRequestDetails()
					.getLastName());

		} catch (Exception e) {
			if (e.getMessage().contains("Unparseable date:")) {
				throw new PosidexException(
						"Exception occured while formatting Date ..... ");
			}
			logger.error("Exception while getting BuildHashMap:");
			logger.error(e, e);

			throw e;
		}
		return dg_row1;
	}

	/***
	 * This method is used to get the MatchedCustomer and MatchedLoan Details
	 * for the particular requestID.
	 * 
	 * @param requestId
	 * @return
	 */

	// @GetMapping(value = "getResults/{requestId}", produces =
	// MediaType.APPLICATION_JSON_VALUE)
	public CustomerResults getResults(@PathVariable long requestId) {
		CustomerResults response = new CustomerResults();
		List<String> requestInfo = null;
		try {
			requestInfo = serviceI.checkRequestStatus(requestId);
		} catch (Exception e) {
			logger.error("Exception has caught for checkRequestStatus inside getResults");
		}
		String reqStatus = requestInfo.get(0);
		int match_count = Integer.parseInt(requestInfo.get(1));
		String counterValue = requestInfo.get(3);
		MatchedLoanDetails matchedLoanDetails = null;
		List<MatchedLoanDetails> matchedLoanDetailsList = new ArrayList<>();
		String parameterString = "";
		String negativeMatchIds = "";
		List<String> area_id = null;
		List<String> source_systems = serviceI.getSourceSystem(requestId);
		logger.info("Source_system" + source_systems);
		for (String source_system : source_systems) {
			if (source_system.equalsIgnoreCase(sourceSystemName2)) {
				logger.info("Fetching AreaId");
				area_id = serviceI.getNegativeMatchIds(requestId);
				for (String id : area_id) {
					parameterString += appendWithDelimiter(negativeMatchIds,
							id, ";");
					logger.info("Parameter_String" + parameterString);
				}
			}
		}
		if (reqStatus.equalsIgnoreCase(("C"))
				&& env.getProperty("NODES_COUNT").equals(counterValue)) {
			logger.info("----------------------- Inside MatchedCustomers Details --------------------");
			List<MatchedCustomerDetails> matchedCustomers = null;
			try {
				matchedCustomers = serviceI.getmatchedCustomers(requestId);
			} catch (Exception e) {
				logger.error("Exception has Caught inside getmatchedCustomers");
			}
			logger.info(matchedCustomers);
			for (MatchedCustomerDetails matchedCustomer : matchedCustomers) {
				String customerNo = matchedCustomer.getCustomerId();
				String record_Type = matchedCustomer.getRecord_Type();
				logger.info("CustomerNo " + customerNo);
				logger.info("Record_Type " + record_Type);
				if (!(record_Type.equalsIgnoreCase(recordType))) {
					try {
						matchedLoanDetails = serviceI
								.getmatchedLoanDetails(customerNo == null ? 0
										: Integer.parseInt(customerNo));
						matchedLoanDetailsList.add(matchedLoanDetails);

					} catch (NumberFormatException e) {
						logger.error("NumberFormatException Exception has Caughted"
								+ e);
					} catch (Exception e) {
						logger.error("Exception has Caughted" + e);
					}
					logger.info(matchedLoanDetails);
				}
			}
			logger.info("RequestId" + requestId);
			response.setRequestId(requestId);
			response.setStatus(reqStatus);
			response.setMatchCount(match_count);
			response.setNegativeMatchCriteria("NO");
			response.setMatchedCustomerDetailsList(matchedCustomers);
			response.setMatchedLoanDetailsList(matchedLoanDetailsList);
		} else if (reqStatus.equalsIgnoreCase(("C"))) {
			if (Integer.parseInt(counterValue) < Integer.parseInt(env
					.getProperty("NODES_COUNT"))) {
				response.setRequestId(requestId);
				response.setStatus("PARTIAL");
			}
		}
		if (reqStatus.equalsIgnoreCase("E")) {
			response.setRequestId(requestId);
			response.setErrorCode("100");
			response.setErrorDesc(errorMessage);
		}
		logger.info("RESPONSE" + response);
		return response;

	}

	// @GetMapping(value = "getRequestInfo/{requestId}", produces =
	// MediaType.APPLICATION_JSON_VALUE)
	public CustomerRequest getRequestDetails(@PathVariable long requestId) {
		String reqStatus = null;
		List<String> requestInfo = null;
		try {
			requestInfo = serviceI.checkRequestStatus(requestId);
			reqStatus = requestInfo.get(0);
		} catch (Exception e) {
			logger.error("Exception has caught inside checkRequestStatus" + e);
		}
		CustomerRequestDetails details = null;
		try {
			details = serviceI.getCustomerRequestDetails(requestId);
		} catch (Exception e) {
			logger.error("Exception has caughted inside getCustomerRequestDetails");
		}
		CustomerRequest request = new CustomerRequest();
		request.setRequestId(requestId);
		request.setRequestStatus(reqStatus);
		request.setRequestDetails(details);
		return request;
	}

	/***
	 * This method will fetch the MatchedCustomer and MatchedLoanDetails for
	 * particular request_id by using HTTP post method.
	 * 
	 * @param requestObject
	 * @param request
	 * @return
	 */
	@PostMapping(value = "getResultObject", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public CustomerResults getResultObject(
			@RequestBody RequestObject requestObject, HttpServletRequest request) {

		String reqStatus = "";
		long requestId = requestObject.getRequestId();
		logger.info("Given Request_id ::: "+requestId);
		CustomerResults response = new CustomerResults();

		List<MatchedCustomerDetails> matchedCustomers = new ArrayList<>();
		List<String> requestInfo = null;
		String parameterString = "";
		String negativeMatchIds = "";
		List<String> area_id = new ArrayList<>();

		try {
			String requestIP = getClientIp(request);
			logger.info("current ip is " + requestIP);

			String[] allowedOrigin = env.getProperty("AllowedOrigin")
					.split(",");
			List<String> authorizedIp = Arrays.asList(allowedOrigin);
			System.out.println("List ::: " + authorizedIp);
			for (String ip : authorizedIp) {
				logger.debug("authorized ip is" + ip);
			}
			if (authorizedIp.contains(requestIP)) {
				logger.info("Authorized");
			} else {
				logger.info("UnAuthorized");
				throw new PosidexException("Unauthorized User");
			}
			List<String> source_systems = serviceI.getSourceSystem(requestId);
			logger.info("Source_system" + source_systems);
			for (String source_system : source_systems) {
				if (source_system.equalsIgnoreCase(sourceSystemName2)) {
					logger.info("Fetching AreaId");
					area_id = serviceI.getNegativeMatchIds(requestId);
					for (String id : area_id) {
						parameterString += appendWithDelimiter(
								negativeMatchIds, id, ";");
						logger.info("Parameter_String" + parameterString);
					}
				}
			}
			matchedCustomers = serviceI.getmatchedCustomers(requestId);
			logger.info("MatchedCustomers List Size:::"
					+ matchedCustomers.size());
			/*
			 * } catch (Exception e) {
			 * logger.error("Exception has Caught inside MatchedCustomers Details"
			 * ); reqStatus = "E"; }
			 */
			List<MatchedLoanDetails> matchedLoanDetailsList = new ArrayList<>();
			int match_count = 0;
			try {
				requestInfo = serviceI.checkRequestStatus(requestId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!(requestInfo.isEmpty())) {
				if (requestInfo.get(5) != null) {
					reqStatus = requestInfo.get(5);
				}
				if (requestInfo.get(6) != null) {
					match_count = Integer.parseInt(requestInfo.get(6));
				}
				if (requestInfo.get(7) != null) {
					response.setDealId(requestInfo.get(7));
				}
				if (requestInfo.get(8) != null) {
					response.setBflRequestId(requestInfo.get(8));
				}
				if (requestInfo.get(9) != null) {
					response.setRequestIdFin(requestInfo.get(9));
				}
				if (requestInfo.get(10) != null) {
					response.setDataSource(requestInfo.get(10));
				}
				if (requestInfo.get(11) != null) {
					response.setProduct(requestInfo.get(11));
				}
			}
			
			List<String> dataSource = new ArrayList<>();

			// Need to get these values from Properties File-Sree
			dataSource.add("CUSTOMER_CF");
			dataSource.add("CUSTOMER_AF");
			dataSource.add("PENNANT");
			dataSource.add("PENNANT_HFC");
			dataSource.add("WRF");

			response.setNegativeMatchCriteria("NOMATCH");
			String joined1 = "";
			for (MatchedCustomerDetails matchedCustomer : matchedCustomers) {
				if ("NEGATIVE".equalsIgnoreCase(matchedCustomer
						.getPsxsourceSystem())) {
					parameterString = parameterString.concat(
							matchedCustomer.getArea_id()).concat(",");
					if (matchedCustomer.getMatchingRule() != null) {

						String[] indirectRuleNo = env.getProperty(
								"NEGATIVE_MATCH_INDIRECT_RULE").split(",");
						for (String drule : indirectRuleNo) {
							if (matchedCustomer.getMatchingRule().contains(
									drule)) {
								response.setNegativeMatchCriteria("INDIRECT");
							}
						}
						String[] directRuleNo = env.getProperty(
								"NEGATIVE_MATCH_DIRECT_RULE").split(",");
						for (String drule : directRuleNo) {
							if (matchedCustomer.getMatchingRule().contains(
									drule)) {
								response.setNegativeMatchCriteria("DIRECT");
							}
						}
					}
				} else {

					if (matchedCustomer.getCust_unq_id() != null
							&& dataSource.contains(matchedCustomer
									.getSourceSystem())) {
						joined1 = joined1.concat(
								matchedCustomer.getCust_unq_id()).concat(",");

					}

				}
			}
			logger.info("Cust_unq_ids for LOAN Details :::" + joined1);
			if (!(joined1.isEmpty())) {
				long ml_start_time = System.currentTimeMillis();
				matchedLoanDetailsList = serviceI
						.getmatchedLoanDetails(joined1);

				logger.info("Matched Loan Details List Size ::::"
						+ matchedLoanDetailsList.size() + " Time Taken "
						+ (System.currentTimeMillis() - ml_start_time));
			}

			response.setRequestId(requestId);
			response.setStatus(reqStatus);
			logger.info("Match Count....." + match_count);
			response.setMatchCount(match_count);
			response.setNegativeMatchId(parameterString);
			response.setMatchedCustomerDetailsList(matchedCustomers);
			response.setMatchedLoanDetailsList(matchedLoanDetailsList);
			logger.info("RESPONSE" + response);
			return response;
		} catch (Exception e) {
			logger.error("Exception occured in findCustomer " + e.getMessage());
			logger.error(e, e);
			response.setRequestId(requestId);
			response.setStatus("E");
			response.setErrorCode("100");
			response.setErrorDesc(e.getMessage());
			return response;

		}
	}

	public String appendWithDelimiter(String original, String addition,
			String delimiter) {
		if (addition == null || addition.equals("")) {
			return "";
		} else {
			return original + addition + delimiter;
		}
	}

	/***
	 * This method is used to send Intraday Message to PrimeMatch Engine.
	 * 
	 * @param psxNspProfiles
	 * @param reqPsxid
	 * @param paramMap
	 * @param entry
	 * @param product_type
	 * @param custUnqId
	 * @throws JSONException
	 * @throws JMSException
	 */
	public void sendMessageToIntraDay(List<String> psxNspProfiles,
			long reqPsxid, HashMap<String, String> paramMap,
			HashMap<String, ArrayList<HashMap<String, Object>>> entry,
			String product_type, String custUnqId) throws JSONException,
			JMSException {
		String matching_rule = psxNspProfiles.get(0);
		String residual_parameters = psxNspProfiles.get(1) != null ? psxNspProfiles
				.get(1) : "";
		String weightages = psxNspProfiles.get(2);
		String scale_stringent = psxNspProfiles.get(3) != null ? psxNspProfiles
				.get(3) : "";
		String rankingOrders = psxNspProfiles.get(4) != null ? psxNspProfiles
				.get(4) : "";
		logger.info("IntraDay  RequestBean.....");
		JSONObject json = new JSONObject();
		json.put("requestID", reqPsxid);
		json.put("requestInformation", entry);
		// json.put("matchingRule", matching_rule);
		json.put("requestStatus", "P");
		json.put("sourceSystemName", sourceSystemName1);
		json.put("rankingOrders", rankingOrders);
		json.put("processType", "intraDayProcess");
		logger.info("Message is " + json.toString());
		String message = json.toString();

		int asciiValue = 0;
		if (custUnqId != null && !custUnqId.isEmpty()) {
			for (int i = 0; i < custUnqId.length(); i++) {
				asciiValue += custUnqId.charAt(i);
			}
		}

		// if (Integer.parseInt(custUnqId) % 2 == 0) {
		if ((asciiValue % Integer.parseInt(env.getProperty("NODES_COUNT")) == 0)) {
			// producer.sendMessage(env.getProperty("NODE_1_PRIME_MATCH_QUEUE"),
			// message);
			producer.sendMessage(env.getProperty("NODE_1_PRIME_MATCH_QUEUE"),
					message,
					Boolean.parseBoolean(env.getProperty("isActiveMQ")));
		}
		// if (Integer.parseInt(custUnqId) % 2 == 1) {
		if ((asciiValue % Integer.parseInt(env.getProperty("NODES_COUNT")) == 1)) {
			// producer.sendMessage(env.getProperty("NODE_2_PRIME_MATCH_QUEUE"),
			// message);
			producer.sendMessage(env.getProperty("NODE_2_PRIME_MATCH_QUEUE"),
					message,
					Boolean.parseBoolean(env.getProperty("isActiveMQ")));
		}

		// logger.info("Message sent succesffully to PMQueue");
		// producer.sendMessage(env.getProperty("NODE_1_PRIME_MATCH_QUEUE"),
		// message);
		// producer.sendMessage(env.getProperty("NODE_2_PRIME_MATCH_QUEUE"),
		// message);
	//	buildAndSendMicrobatchingMessageToEngine();
	}

	/***
	 * This method will send an Micro-Batching Message to PrimeMatch Engine.
	 * 
	 * @throws JSONException
	 * @throws JMSException
	 */
	public void buildAndSendMicrobatchingMessageToEngine()
			throws JSONException, JMSException {
		JSONObject json = new JSONObject();
		String processType = "microBatchProcessing";
		json.put("psxBatchID", 525);
		json.put("processType", processType);
		json.put("sourceSystemName", sourceSystemName1);

		logger.info("Microbatching message::" + json.toString());
		// producer.sendMessage(env.getProperty("NODE_1_EVENT_QUEUE"),json.toString(),Boolean.parseBoolean(env.getProperty("isActiveMQ")));
		// producer.sendMessage(env.getProperty("NODE_2_EVENT_QUEUE"),json.toString(),Boolean.parseBoolean(env.getProperty("isActiveMQ")));
		producer.sendMessage(env.getProperty("NODE_1_MB_QUEUE"),
				json.toString(),
				Boolean.parseBoolean(env.getProperty("isActiveMQ")));
		producer.sendMessage(env.getProperty("NODE_2_MB_QUEUE"),
				json.toString(),
				Boolean.parseBoolean(env.getProperty("isActiveMQ")));

		logger.info("Message has been sent succesfully to EventQueue:");
	}

	public static Date getFormattedDate(Long timeMillis) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss.SSS aa");//
		calendar.setTimeInMillis(timeMillis);
		String stringDate = dateFormat.format(calendar.getTime());
		Date d = new Date();
		try {
			d = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static Date getDateFormatFromString(Date date) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd-MM-yyyy HH:mm:ss.SSS aa");
		calendar.setTimeInMillis(date.getTime());
		String stringDate = dateFormat.format(calendar.getTime());
		Date d = new Date();
		try {

			d = dateFormat.parse(stringDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

	public static String getFormattedDate1(Long timeMillis) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd.MM.yyyy HH:mm:ss aa");
		calendar.setTimeInMillis(timeMillis);
		String stringDate = dateFormat.format(calendar.getTime());
		return stringDate;
	}
	
	
	public void webserviceRequestProcessingTime(long request_id,long logCompletionTime,String match_profile,String request_status)
	{
		serviceI.webserviceRequestProcessingTime(request_id,logCompletionTime,match_profile,request_status);
	}
	
//	@PostMapping(value = "getDemoGraphicResults", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public DemoGraphicsResults getDemoGraphicResults(@RequestBody CustomerObject customerObject, HttpServletRequest request) {
		DemoGraphicsResults demoGraphicsResults = new DemoGraphicsResults();
		DemoGraphicCustomerDetails demoGraphicCustomerDetails = new DemoGraphicCustomerDetails();
		List<MatchedLoanDetails> matchedLoanDetails = new ArrayList<>();
		String custUnqId = "";
		try {		
		if(customerObject.getCustomerNO() == null || customerObject.getSourceSysNO() == null)
		{
			
			errorMessage = "";
			errorMessage += " Missing Input Parameters Customer_NO "+customerObject.getCustomerNO()+"  Source_Sys_Id "+customerObject.getSourceSysNO();
			throw new PosidexException(errorMessage);
			
		}
		
		String customerNo = customerObject.getCustomerNO().trim();
		logger.info("Given customerId ::: "+customerNo);
		String sourceSysId = customerObject.getSourceSysNO().trim();
		logger.info("Given sourceSysId ::: "+sourceSysId);
		 custUnqId = customerNo+"||"+sourceSysId;
		logger.info("Given cust_unq_id ::: "+custUnqId);
		
		
	
		demoGraphicsResults.setCustomerId(customerNo);
		demoGraphicsResults.setSourceSysId(sourceSysId);
		demoGraphicsResults.setCustunqid(custUnqId);


		
			String requestIP = getClientIp(request);
			logger.info("current ip is " + requestIP);

			String[] allowedOrigin = env.getProperty("AllowedOrigin")
					.split(",");
			List<String> authorizedIp = Arrays.asList(allowedOrigin);
			logger.info("List ::: " + authorizedIp);
			for (String ip : authorizedIp) {
				logger.debug("authorized ip is" + ip);
			}
			if (authorizedIp.contains(requestIP)) {
				logger.info("Authorized");
			} else {
				logger.info("UnAuthorized");
				throw new PosidexException("Unauthorized User");
			}
		
			int asciiValue = 0;
			if (custUnqId != null && !custUnqId.isEmpty()) {
				for (int i = 0; i < custUnqId.length(); i++) {
					asciiValue += custUnqId.charAt(i);
				}
			}
			if ((asciiValue % Integer.parseInt(env.getProperty("NODES_COUNT")) == 0)) {
				logger.info("Fetching Demographic Customer Details from "+env.getProperty("DemoGraphicTable1"));
				demoGraphicCustomerDetails=serviceI.getDemographicCustomerDetails(custUnqId,env.getProperty("DemoGraphicTable1"));

			}
			if ((asciiValue % Integer.parseInt(env.getProperty("NODES_COUNT")) == 1)) {
				logger.info("Fetching Demographic Customer Details from "+env.getProperty("DemoGraphicTable2"));

				demoGraphicCustomerDetails=serviceI.getDemographicCustomerDetails(custUnqId,env.getProperty("DemoGraphicTable2"));

			}
			
			logger.info("Dedupe Cusomter Details : "+ demoGraphicCustomerDetails);
			if(demoGraphicCustomerDetails.getCust_unq_id()!= null){
			demoGraphicsResults.setDemoGraphicCustomerDetails(demoGraphicCustomerDetails);
			} 
			if(demoGraphicCustomerDetails.getCust_unq_id() == null){
				errorMessage = "";
				errorMessage += "Customer Information is Not Available for the given Customer_ID";
				throw new PosidexException(errorMessage);
			}
				
			matchedLoanDetails =serviceI.getmatchedLoanDetailsList(Integer.parseInt(customerNo));
			logger.info("Dedupe Cusomter's Loan Details Size: "+ matchedLoanDetails);
			if(!matchedLoanDetails.isEmpty()  && demoGraphicCustomerDetails.getCust_unq_id()!= null)
			{
				demoGraphicsResults.setMatchedLoanDetailsList(matchedLoanDetails);
			}
		
		return demoGraphicsResults;
	} catch (Exception e) {
		logger.error("Exception occured in getDemoGraphicResults " + e.getMessage());
		logger.error(e, e);
		demoGraphicsResults.setCustunqid(custUnqId);
		demoGraphicsResults.setErrorCode("100");
		if(e.getMessage().contains("ORA")){
		int index=	e.getMessage().indexOf("nested exception is");
		demoGraphicsResults.setErrorDesc(e.getMessage().substring(index+20).replace("\"", "").replace("\n", ""));
		}
		else
		{
			demoGraphicsResults.setErrorDesc(e.getMessage());
		}
		return demoGraphicsResults;

	}
	}
}
