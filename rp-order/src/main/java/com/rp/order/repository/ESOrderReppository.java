package com.rp.order.repository;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.xcontent.XContentBuilder;

public interface ESOrderReppository {

	void save(TransportClient client, XContentBuilder builder, String id);

}
