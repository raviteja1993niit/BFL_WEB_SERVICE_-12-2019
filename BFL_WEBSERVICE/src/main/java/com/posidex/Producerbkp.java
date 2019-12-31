package com.posidex;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.posidex.controller.BajajRestController;

@Component
public class Producerbkp {
	private static Logger logger = Logger.getLogger(Producerbkp.class.getName());
	@Autowired
	@Qualifier("activeMQ")
	JmsTemplate actjmsTemplate;
	
	@Autowired
	@Qualifier("oracleQueue")
	JmsTemplate orajmsTemplate;
	
	@Autowired
	Environment env;
	

	/*public void sendMessage(final String queueName, final String message,boolean javaConfig) throws JMSException{
		public void sendMessage(final String queueName, final String message) throws JMSException{
		//sendResponse(message, queueName,javaConfig);
			long time=System.currentTimeMillis();
		logger.debug("Sending Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
		jmsTemplate.send(queueName, new MessageCreator() {

			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = null;
				textMessage = session.createTextMessage(message);

				return textMessage;
			}
		});
		logger.debug("Sent Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
		logger.debug("Time Taken::"+(System.currentTimeMillis()-time));
	}*/

	public void sendMessage(final String queueName,final String textMsg,boolean isActiveMQ) throws JMSException {
		long time=System.currentTimeMillis();
		JmsTemplate template=isActiveMQ?actjmsTemplate:orajmsTemplate;//javaConfig?actjmsTemplate:orajmsTemplate;
		
		if(textMsg.contains("microBatchProcessing"))
		{
			System.out.println("MicroBatching ::: "+ textMsg);
			template.setExplicitQosEnabled(true);
			template.setPriority(9);
		}
		template.send(queueName, new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					
					TextMessage textMessage=session.createTextMessage(textMsg);
					if(textMsg.contains("microBatchProcessing"))
					{
						textMessage.setJMSPriority(9);
					}
					logger.debug("Sending Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
					return textMessage;
				}
			});
		logger.debug((System.currentTimeMillis()-time)+"Sent Message "+queueName+":"+new SimpleDateFormat("dd-MM-yyyy HH:mm:ss S").format(new Date(System.currentTimeMillis())));
	}

}
