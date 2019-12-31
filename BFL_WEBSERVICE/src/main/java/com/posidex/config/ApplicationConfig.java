package com.posidex.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import({ DataBaseConfig.class, ListenerConfig.class })
@PropertySource({ "classpath:application.properties", "classpath:sprin_jmsconfig.properties",
		"classpath:WSResources.properties", "classpath:Sequences.properties" })
public class ApplicationConfig {

}
