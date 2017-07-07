package com.rp.order.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;

import org.apache.commons.lang3.time.DateUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.order.model.Order;
import com.rp.order.model.OrderProd;
import com.rp.order.repository.ESOrderReppository;

@Service
public class ESOrderServiceImpl implements ESOrderService {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ESOrderReppository esOrderReppository;

	@Override
	public void save(Order order) {
		try {
			Settings settings = Settings.builder().put("cluster.name", "my-app").build();
			TransportClient client = new PreBuiltTransportClient(settings);
			client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));

			XContentBuilder builder = getXContentBuilder(order);

			esOrderReppository.save(client, builder, order.getOrdNo());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private XContentBuilder getXContentBuilder(Order order) {
		try {
			XContentBuilder builder = XContentFactory.jsonBuilder().prettyPrint();
			builder.startObject()
			.field("ordNo", order.getOrdNo())
			.field("mbrId", order.getMbrId())
			.field("ordStatCd", order.getOrdStatCd())
			.field("ordYmdt", DateUtils.parseDate(order.getOrdYmdt(), "yyyyMMddHHmmss"))
			.field("ordTotAmt", order.getOrdTotAmt())
			.startArray("orderProds");
			for(OrderProd orderProd : order.getOrderProds()) {
				builder.startObject()
				.field("ordNo", orderProd.getOrdNo())
				.field("seq", orderProd.getSeq())
				.field("prodNo", orderProd.getProdNo())
				.field("prodNm", orderProd.getProdNm())
				.field("prodAmt", orderProd.getProdAmt())
				.field("sellerId", orderProd.getSellerId())
				//.field("purchCfrmDt", "-")
				.field("ordProdCnt", orderProd.getOrdProdCnt())
				.endObject();
			}
			builder.endArray();
			builder.startObject("delivery")
			.field("ordNo", order.getDelivery().getOrdNo())
			.field("addr", order.getDelivery().getAddr())
			.field("shpNo", order.getDelivery().getShpNo())
			.endObject()
			.endObject();
			return builder;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
