package com.posidex.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("com.posidex")
@EnableTransactionManagement

public class DataBaseConfig {

	@Autowired
	private Environment env;

	@Bean
	public JdbcTemplate jdbcTemplate(@Qualifier(value="dataSource2") DataSource dataSource) {
		return new JdbcTemplate( dataSource);
	}

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	// JNDI configuration for tomcat resource accessing
	@Bean
	public DataSource jndiDataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:/comp/env/jdbc/posidex");

		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();

		return (DataSource) bean.getObject();
	}

}