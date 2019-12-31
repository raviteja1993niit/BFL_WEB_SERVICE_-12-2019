package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 *
 * @author jayantronald
 *
 */
@Component
public class Prime360ResponseQueueListener {
	
	@Autowired
	JmsTemplate orajmsTemplate;
	
	private static Logger logger = Logger.getLogger(Prime360ResponseQueueListener.class.getName());
	

	private ConcurrentMap<String, Object[]> pendingResponses=new ConcurrentHashMap<>();
	
	@JmsListener(destination = "PSX_NSP_PM_QUEUE",containerFactory = "jmsListenerContainerFactory")
	public  synchronized void onMessageInAQJMS(Message message){
		try {
			boolean messageContent=((CLOB)message).isConvertibleTo(String.class);
			System.out.println("Message ... "+ messageContent);
			
		} catch (Exception e) {
			logger.error(e,e);
		}
		
	}
	
	public void sendMessage(final String queueName,final String textMsg) throws JMSException {
		long time=System.currentTimeMillis();
		
		orajmsTemplate.send(queueName, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					TextMessage textMessage=session.createTextMessage(textMsg);
					logger.debug("Sending Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
					return textMessage;
				}
			});
		logger.debug((System.currentTimeMillis()-time)+"Sent Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
	}

}