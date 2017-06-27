package com.rp.log.tracker;

import org.slf4j.MDC;

public class CorrelationId {
	public static final String CORRELATION_ID_NAME = "X-Correlation-ID";
	private static final ThreadLocal<String> XCorrelationIdThreadLocal = new ThreadLocal<String>();

	public static void setId(String id) {
		XCorrelationIdThreadLocal.set(id);
		MDC.put("X-Correlation-ID", id);
	}

	public static String getId() {
		return XCorrelationIdThreadLocal.get();
	}

}
