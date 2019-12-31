package com.posidex.purgingprocess.EodPurge;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import posidex.core.prime360adapters.jdbcadapter.EventBean;
import posidex.core.prime360adapters.jdbcadapter.Prime360CallBack;
import posidex.core.prime360adapters.jdbcadapter.RequestBean;

public class EodProcessSuccessCallBack implements Prime360CallBack {
	// public class EodProcessSuccessCallBack {
	private static Log logger = LogFactory
			.getLog(EodProcessSuccessCallBack.class.getName());

	@Autowired
	private static Environment env;

	public static JmsTemplate jmsTemplate;

	static Properties props = new Properties();

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;

	}

	// public static void main(String[] args) {
	// try {
	// callBack();
	// } catch (Exception e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }

	public void callBack(DataSource dataSource, EventBean eventBean,
			RequestBean requestBean) throws Exception {

		// public static void callBack() throws Exception {
		InputStream inputStream = null;
		InputStream inputStream1 = null;
		String nodeOneQueue = "";
		String nodeTwoQueue = "";
		try {
			inputStream = new FileInputStream("./props/purging.properties");
			props.load(inputStream);
			nodeOneQueue = (String) props.get("nodeOneQueue");
			nodeTwoQueue = (String) props.get("nodeTwoQueue");
			logger.info("N1 & N2 " + nodeOneQueue + " " + nodeTwoQueue);
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"file:./props/incremental-spring-config-amq.xml");

			jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
			logger.info("jmsTemplate.........." + jmsTemplate);

		} catch (IOException ex) {
			ex.printStackTrace();
			logger.error("Exception occured in callBack()........."
					+ ex.getMessage());
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("Exception occured in callBack()........."
							+ e.getMessage());

				}
			}
		}

		sendPurgingMessageToEngine(nodeOneQueue, nodeTwoQueue);
	}

	public static void sendPurgingMessageToEngine(String queueName,
			String queue2) throws JSONException {

		JSONObject json = new JSONObject();

		json.put("psxBatchID", "100");
		json.put("processType", "PurgeData");
		json.put("sourceSystemName", "PRIME360");
		json.put("probingComponent", "BOTH");
		logger.info("Purging message::" + json.toString());
		final String message = json.toString();
		logger.info("MESSAGE :::" + message);
		jmsTemplate.send(queueName, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = null;
				textMessage = session.createTextMessage(message);
				return textMessage;
			}
		});

		jmsTemplate.send(queue2, new MessageCreator() {

			public Message createMessage(Session session) throws JMSException {
				TextMessage textMessage = null;
				textMessage = session.createTextMessage(message);
				return textMessage;
			}
		});

	}
}
