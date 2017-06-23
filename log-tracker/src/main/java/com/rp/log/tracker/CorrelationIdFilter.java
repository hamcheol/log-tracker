package com.rp.log.tracker;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorrelationIdFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		String correlationId = request.getHeader("X-Request-ID");
		
		if(StringUtils.isEmpty(correlationId)) {
			correlationId = UUID.randomUUID().toString();
			response.setHeader("X-Request-ID", correlationId);
		}
		
		logger.info("X-Request-ID:{}", correlationId);
		
		chain.doFilter(req, res);
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}

}
