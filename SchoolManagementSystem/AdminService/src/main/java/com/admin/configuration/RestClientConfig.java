package com.admin.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {
	
	@Bean
	public RestClient restClient() {
		RestClient restClient = RestClient.builder()
				.baseUrl("http://staff-service-env.eba-ubcppjqw.ap-southeast-1.elasticbeanstalk.com")
				.build();
		
		return restClient;	
	}

}

