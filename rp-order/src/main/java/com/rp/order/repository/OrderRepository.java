package com.rp.order.repository;

import java.util.List;

import com.rp.order.model.Order;
import com.rp.order.model.OrderParam;

public interface OrderRepository {
	public int insertOrder(Order order);
	
	public int updateOrder(Order order);
	
	public List<Order> selectOrders(OrderParam param);
	
	public List<Order> selectDetailOrders(OrderParam param);

}
