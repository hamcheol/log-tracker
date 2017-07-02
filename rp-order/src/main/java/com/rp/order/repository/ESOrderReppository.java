package com.rp.order.repository;

import org.elasticsearch.client.transport.TransportClient;

import com.rp.order.model.Order;

public interface ESOrderReppository {

	void save(TransportClient client, Order order);

}
