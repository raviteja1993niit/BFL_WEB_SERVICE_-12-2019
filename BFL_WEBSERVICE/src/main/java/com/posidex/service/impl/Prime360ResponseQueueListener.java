package com.posidex.service.impl;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.posidex.controller.BajajRestController;

import posidex.core.prime360adapters.jdbcadapter.RequestBean;

/**
 *
 * @author jayantronald
 *
 */
@Component
public class Prime360ResponseQueueListener {
	
	
	private static Logger logger = Logger.getLogger(Prime360ResponseQueueListener.class.getName());
	
	
//	final String destQueue=env.getProperty("PRIME_MATCH_RESPONSE_QUEUE");
	private ConcurrentMap<String, Object[]> pendingResponses=new ConcurrentHashMap<>();
	
	//@JmsListener(destination = "PSX_NSP_PM_RESP_QUEUE_Q1",containerFactory = "jmsListenerContainerFactory")
	@JmsListener(destination = "PSX_NSP_PM_RESP_QUEUE",containerFactory = "jmsListenerContainerFactory")
	public  synchronized void onMessageInAQJMS(Message message){
		try {
			String messageContent=((TextMessage)message).getText();
			processMessage(messageContent);
		} catch (JMSException e) {
			logger.error(e,e);
		}
		
	}
	
	/*@JmsListener(destination = "PSX_NSP_PM_RESP_QUEUE_Q1",containerFactory = "jmsListenerContainerFactory")
	public  synchronized void onMessageInActiveMQ(Message message){
		try {
			String messageContent=((TextMessage)message).getText();
			processMessage(messageContent);
		} catch (JMSException e) {
			logger.error(e,e);
		}
		
	}*/
	private void processMessage(String messageContent){
		try {
			//System.out.println("messageContent"+messageContent);
			
			
			String requestId = messageContent.substring(messageContent.indexOf("requestID")+12, messageContent.indexOf("processType")-3);
			Object[] c=pendingResponses.get(requestId);
			if(c!=null) {
				if(!(messageContent.indexOf("intraDayProcess")>=0)){
					((CountDownLatch)c[0]).countDown();
					for(int i=1;i<c.length;i++){
						if(c[i]==null){
							c[i]=System.currentTimeMillis();
							break;
						}
					}
					if(logger.isDebugEnabled()){
						logger.debug(this+":"+"countDowned:"+ requestId);
					}
					if(((CountDownLatch)c[0]).getCount()==0){//bcz we have two nodes 
						pendingResponses.remove(requestId);
						if(logger.isDebugEnabled()){
							logger.debug(this+":"+"removed:"+ requestId);
						}
					}
				}
			}else{
				if(logger.isDebugEnabled()){
		//		logger.debug("There is no entry in the map with the request ID:"+requestId+" and the message is:"+messageContent);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error(e,e);
		}
	}
	public void addPendingRequest(String tokenName,Object[] values) {
		Object obj=	this.pendingResponses.put(tokenName, values);
		if(obj!=null){//To check duplicate request ids.
			logger.info(this+":"+values[0]+ " request aleardy exists.");
		}
	}
	public boolean isStillProcessing(String requestID) {
		return pendingResponses.get(requestID)!=null;
	}
}