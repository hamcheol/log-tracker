package com.rp.order.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rp.order.model.Order;
import com.rp.order.service.OrderService;

/**
 * 주문 컨트롤
 * @author cheol.ham
 *
 */
@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;

	@RequestMapping(value = "/settle", method = RequestMethod.POST)
	public Order settle(@RequestBody Order order) {
		Order result = orderService.settle(order);
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
