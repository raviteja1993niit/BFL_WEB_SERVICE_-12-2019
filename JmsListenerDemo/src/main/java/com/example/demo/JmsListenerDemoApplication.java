package com.example.demo;

import javax.jms.JMSException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;



@SpringBootApplication
@Import(OracleAQConfig.class)
public class JmsListenerDemoApplication {

	public static void main(String[] args) {
		Prime360ResponseQueueListener producer=new Prime360ResponseQueueListener();
		SpringApplication.run(JmsListenerDemoApplication.class, args);
		try {
			producer.sendMessage("PSX_NSP_PM_QUEUE","Hi...");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
	}

}
