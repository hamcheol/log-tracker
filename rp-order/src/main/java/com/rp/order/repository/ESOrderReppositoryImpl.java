package com.rp.order.repository;

import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class ESOrderReppositoryImpl implements ESOrderReppository {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void save(TransportClient client, XContentBuilder builder, String id) {
		IndexRequestBuilder indexRequestBuilder = client.prepareIndex("data-store", "order", id);
		IndexResponse response = indexRequestBuilder.setSource(builder).execute().actionGet();
		logger.info(response.getResult().name());
		client.close();
	}

}
