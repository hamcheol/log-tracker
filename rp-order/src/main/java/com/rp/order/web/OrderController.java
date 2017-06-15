package com.rp.order.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rp.order.model.Order;

@RestController
public class OrderController {
	@RequestMapping("")
	public Order info() {
		return null;
	}

}
