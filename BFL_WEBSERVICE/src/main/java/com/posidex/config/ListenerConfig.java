package com.posidex.config;

import javax.servlet.ServletContextListener;

import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Configuration;

import com.posidex.listeners.WSContextListener;

@Configuration
public class ListenerConfig {
	// @Bean
	public ServletListenerRegistrationBean<ServletContextListener> wsContextListenerRegistrationBean() {
		ServletListenerRegistrationBean<ServletContextListener> result = new ServletListenerRegistrationBean<>();
		result.setListener(new WSContextListener());
		// result.setName("wsContextListenerRegistrationBean");
		result.setEnabled(true);
		result.setOrder(1);
		// ...
		return result;
	}
}
