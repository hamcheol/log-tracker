package com.rp.order.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.rp.order.model.Member;
import com.rp.order.model.Order;
import com.rp.order.model.Product;
import com.rp.order.service.OrderService;

/**
 * 주문 컨트롤
 * 
 * @author cheol.ham
 *
 */
@RestController
public class OrderController {
	private Logger trackingLogger = LoggerFactory.getLogger("log-tracker");
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/settle")
	public Order settle() {
		trackingLogger.info("Settlement begin....");
		Member member = restTemplate.getForObject("http://localhost:8080/member/random", Member.class);
		trackingLogger.info("Got member info.");
		ResponseEntity<List<Product>> responseEntity = restTemplate.exchange("http://localhost:8090/product/random",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Product>>() {
				});
		List<Product> products = responseEntity.getBody();
		trackingLogger.info("Got products info.");
		Order result = orderService.settle(member, products);
		trackingLogger.info(result.toString());
		return result;
	}

	@RequestMapping(value = "/deliver", method = RequestMethod.POST)
	public Order deliver(@RequestBody Order order) {
		Order result = orderService.deliver(order);
		return result;
	}

	@RequestMapping(value = "/confirm", method = RequestMethod.POST)
	public Order confirm(@RequestBody Order order) {
		Order result = orderService.confirm(order);
		return result;
	}

}
