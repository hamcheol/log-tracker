package com.rp.order.repository;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rp.order.model.Order;

@Repository
public class ESOrderReppositoryImpl implements ESOrderReppository {

	@Override
	public void save(TransportClient client, Order order) {
		// TODO Auto-generated method stub
		try {
			ObjectMapper mapper = new ObjectMapper();
			byte[] json = mapper.writeValueAsBytes(order);

			IndexResponse response = client.prepareIndex("data-store", "order", order.getOrdNo())
					.setSource(json)
			    .get();
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
