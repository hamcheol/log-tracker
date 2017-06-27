package com.rp.log.tracker;

import java.io.IOException;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

public class CorrelationIdRequestInterceptor implements ClientHttpRequestInterceptor {

	public ClientHttpResponse intercept(HttpRequest request, byte[] bytes, ClientHttpRequestExecution execution)
			throws IOException {

		request.getHeaders().add(CorrelationId.CORRELATION_ID_NAME, CorrelationId.getId());
		return execution.execute(request, bytes);
	}

}
