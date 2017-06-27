package com.rp.order.config.spring;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import com.rp.log.tracker.CorrelationIdRequestInterceptor;

@Configuration
public class BeanConfig {
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
		interceptors.add(new CorrelationIdRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

}
