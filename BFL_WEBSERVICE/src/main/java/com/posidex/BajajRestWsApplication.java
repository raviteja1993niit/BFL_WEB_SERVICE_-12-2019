package com.posidex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.posidex.config.OracleAQConfig;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@SpringBootApplication

@Import(OracleAQConfig.class)


public class BajajRestWsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {

		SpringApplication.run(BajajRestWsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(BajajRestWsApplication.class);
	}

}
