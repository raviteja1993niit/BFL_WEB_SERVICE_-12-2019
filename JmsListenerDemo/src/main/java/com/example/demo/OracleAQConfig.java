package com.example.demo;

import java.sql.SQLException;
import java.util.Properties;

import javax.jms.QueueConnectionFactory;
import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;
import oracle.jms.AQjmsFactory;

import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@EnableJms
@PropertySource("classpath:application.properties")
public class OracleAQConfig {
	private static Logger logger = Logger.getLogger(JmsListenerDemoApplication.class.getName());
	@Autowired
	private Environment env;
	
	
	//@Primary
	@Bean
	OracleDataSource dataSource() throws SQLException {
		OracleDataSource oradataSource = new OracleDataSource();

		oradataSource.setUser(env.getProperty("jdbc.username"));
		oradataSource.setPassword(env.getProperty("jdbc.password"));
		oradataSource.setURL(env.getProperty("jdbc.url"));
		Properties props1=new Properties();
    	props1.put("MinLimit",Integer.parseInt(env.getProperty("min.active")));
    	props1.put("MaxLimit",Integer.parseInt(env.getProperty("max.active")));
    	props1.put("InitialLimit",Integer.parseInt(env.getProperty("initiallimit")));
    	props1.put("InactivityTimeout",Integer.parseInt(env.getProperty("inactivityTimeOut"))); 
    	props1.put("MaxActiveSessionPerConnection",Integer.parseInt(env.getProperty("maxActiveSession")));
		oradataSource.setConnectionCachingEnabled(true);
		oradataSource.setConnectionCacheProperties(props1);
		oradataSource.setConnectionCacheName(env.getProperty("cacheName"));
		return oradataSource;
	}
	@Bean(name="aqjmsConnectionFactory")
	public QueueConnectionFactory connectionFactory(OracleDataSource oracleDatasource) throws Exception {
		return AQjmsFactory.getQueueConnectionFactory(oracleDatasource);
	}

	@Bean
	public JmsTemplate jmsTemplate(@Autowired
			@Qualifier("aqjmsConnectionFactory")
			QueueConnectionFactory qcf) throws Exception {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(qcf);
		jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
		return jmsTemplate;
	}
	
	//pooledConnectionFactory
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerFactory(@Autowired
			@Qualifier("aqjmsConnectionFactory")
			QueueConnectionFactory queueConnectionFactory, DefaultJmsListenerContainerFactoryConfigurer configurer) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setMessageConverter(jacksonJmsMessageConverter());
	
		configurer.configure(factory, queueConnectionFactory);		
		
		return factory;
	}

	
	@Bean(name="dataSource2")
	public DataSource getDataSource() {
		BasicDataSource dataSource=null;
		try{
		dataSource = new BasicDataSource();
	//	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.username"));
		dataSource.setPassword(env.getProperty("jdbc.password"));
		dataSource.setMaxIdle(Integer.parseInt(env.getProperty("max.idle.connections")));
		dataSource.setMinIdle(Integer.parseInt(env.getProperty("min.idle.connections")));
	//	dataSource.setMaxOpenPreparedStatements(Integer.parseInt(env.getProperty("max.open.prepstmt")));
    //  dataSource.setMaxActive(Integer.parseInt(env.getProperty("max.active")));
	//	dataSource.setMaxWait(Integer.parseInt(env.getProperty("max.wait")));
		dataSource.setMaxTotal(Integer.parseInt(env.getProperty("max.active")));
	//	dataSource.setMaxWaitMillis(Long.parseLong(env.getProperty("max.wait.millis")));
		dataSource.setInitialSize(Integer.parseInt(env.getProperty("initial.pool.size")));
	//	dataSource.setMaxConnLifetimeMillis(Integer.parseInt(env.getProperty("max.conn.life.time")));
	//	dataSource.setCacheState(true);
		return dataSource;
		}
		catch(Exception e)
		{
			logger.error("Exception while getting DataSource... "+e.getMessage());
			throw(e);
		}
	}
	
	
	@Bean
	public MessageConverter jacksonJmsMessageConverter() {
		MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);

		return converter;
	}

}