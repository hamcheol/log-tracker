package com.rp.member.config.spring;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rp.log.tracker.CorrelationIdFilter;

@Configuration
public class FilterConfig {
	@Bean
	public FilterRegistrationBean filter() {
		FilterRegistrationBean filterRegiter = new FilterRegistrationBean();
		filterRegiter.setFilter(new CorrelationIdFilter());
		return filterRegiter;
	}

}
