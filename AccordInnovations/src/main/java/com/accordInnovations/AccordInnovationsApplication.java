package com.accordInnovations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
@OpenAPIDefinition(
	    info = @Info(
	        title = "Spring Boot API",
	        version = "1.0",
	        description = "API documentation for the Spring Boot Application"
	    )
	)
@SpringBootApplication
public class AccordInnovationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccordInnovationsApplication.class, args);
	}
	
	 @Bean
	 public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }

}
