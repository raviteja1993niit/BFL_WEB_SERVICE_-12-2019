package com.posidex.config;

import java.util.Vector;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {

		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors
				.basePackage("com.posidex.controller"))
				.paths(PathSelectors.any()).build().apiInfo(metaData());
	}
	 private ApiInfo metaData() {
		 ApiInfo apiInfo = new ApiInfo(
               "REST API for Bajaj FinServ, Pune",
               "Developed by: RAVI TEJA",
               "PRIME360-1.1",
               "Terms of service",
               new Contact("Posidex Technologies", "https://www.posidex.com/", "info@posidex.com"),
               "Prime360 License Version 1.1",
               "https://www.posidex.com/", new Vector<>());
       return apiInfo;
	    }
}
