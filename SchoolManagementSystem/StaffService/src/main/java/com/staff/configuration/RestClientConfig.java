package com.staff.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;


@Configuration
public class RestClientConfig {
	
	@Bean
	public RestClient restClient() {
		RestClient restClient = RestClient.builder()
				.baseUrl("http://student-service-env-1.eba-hnfinae3.ap-southeast-1.elasticbeanstalk.com")
				.build();
		
		return restClient;	
	}

}
