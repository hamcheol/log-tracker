package com.rp.order.service;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.order.model.Order;
import com.rp.order.repository.ESOrderReppository;

@Service
public class ESOrderServiceImpl implements ESOrderService {
	@Autowired
	private ESOrderReppository esOrderReppository;

	@Override
	public void save(Order order) {
		try {
			Settings settings = Settings.builder().put("cluster.name", "my-app").build();
			TransportClient client = new PreBuiltTransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
			esOrderReppository.save(client, order);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
