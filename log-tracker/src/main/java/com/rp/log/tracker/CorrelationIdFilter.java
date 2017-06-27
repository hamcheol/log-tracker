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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CorrelationIdFilter implements Filter {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void destroy() {
		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		
		String correlationId = request.getHeader(CorrelationId.CORRELATION_ID_NAME);
		
		if(StringUtils.isEmpty(correlationId)) {
			correlationId = UUID.randomUUID().toString();
			logger.info("New correlationId is generated : {}", correlationId);
		} else {
			logger.info("Reuse existing correlationId exists : {}", correlationId);
		}
		
		CorrelationId.setId(correlationId);
		
		//HttpServletRequestWrapper의 getHeader를 override하는 방법은 아래와 같이 사용한다. 
		//chain.doFilter(new CorrelationIdRequestWrapper(request, correlationId), res);
		
		chain.doFilter(request, res);
	}

	public void init(FilterConfig config) throws ServletException {
		
	}

}
