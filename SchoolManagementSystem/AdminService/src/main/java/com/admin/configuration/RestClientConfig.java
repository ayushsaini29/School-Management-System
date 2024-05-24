package com.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {
	
	@Bean
	public RestClient restClient() {
		RestClient restClient = RestClient.builder()
				.baseUrl("http://localhost:9002")
				.build();
		
		return restClient;	
	}

}

