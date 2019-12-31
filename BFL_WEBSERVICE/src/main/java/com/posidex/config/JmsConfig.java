package com.posidex.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableJms
@PropertySource("classpath:application.properties")
public class JmsConfig {

	@Autowired
	private Environment environment;
	@Bean
	public ActiveMQConnectionFactory aqconnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL(environment.getProperty("BROKER_URL"));
		return connectionFactory;
	}

	@Bean(name="pooledConnectionFactory")
	public PooledConnectionFactory pooledConnection(ActiveMQConnectionFactory activeMQConnectionFactory) {
		PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
		pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
		pooledConnectionFactory.setMaxConnections(200);
		pooledConnectionFactory.setMaximumActiveSessionPerConnection(200);
		return pooledConnectionFactory;
	}

	@Bean(name="activeMQ")
	public JmsTemplate jmsTemplate(PooledConnectionFactory pooledConnectionFactory) {
		JmsTemplate template = new JmsTemplate();
		template.setConnectionFactory(pooledConnectionFactory);
		template.setSessionAcknowledgeMode(2);
		template.setDeliveryMode(2);
		return template;
	}
	/*@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(PooledConnectionFactory pooledConnectionFactory,DefaultJmsListenerContainerFactoryConfigurer configurer,MessageConverter converter) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setMessageConverter(converter);
		configurer.configure(factory, pooledConnectionFactory);			
		return factory;		
	}*/
	@Bean
	public RestTemplate createRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	

	
}
