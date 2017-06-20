package com.rp.order.repository;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rp.order.model.Order;
import com.rp.order.model.OrderParam;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryImplTest {
	@Autowired
	private OrderRepository orderRepository;
	
	@Test
	public void test() {
		List<Order> orders = orderRepository.selectDetailOrders(new OrderParam());
	}
	

}
