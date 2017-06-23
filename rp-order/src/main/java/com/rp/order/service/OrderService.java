package com.rp.order.service;

import java.util.List;

import com.rp.order.model.Member;
import com.rp.order.model.Order;
import com.rp.order.model.OrderParam;
import com.rp.order.model.Product;

public interface OrderService {
	/**
	 * 결제완료
	 * @param order
	 * @return
	 */
	public Order settle(Member member, List<Product> products);
	
	/**
	 * 배송중
	 * @param order
	 * @return
	 */
	public Order deliver(Order order);
	
	/**
	 * 배송완료
	 * @param order
	 * @return
	 */
	public Order completeDelivery(Order order);
	
	/**
	 * 구매확정
	 * @param order
	 * @return
	 */
	public Order confirm(Order order);
	
	
	public List<Order> getOrders(OrderParam param);
}
