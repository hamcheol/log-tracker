package com.rp.log.tracker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class CorrelationIdRequestWrapper extends HttpServletRequestWrapper {
	private String correlationId;

	public CorrelationIdRequestWrapper(HttpServletRequest request, String correlationId) {
		super(request);
		this.correlationId = correlationId;
	}
	
	@Override
	public String getHeader(String name) {
		if(name != null && CorrelationId.CORRELATION_ID_NAME.equals(name)) {
			return correlationId;
		}
		return super.getHeader(name);
	}

}
