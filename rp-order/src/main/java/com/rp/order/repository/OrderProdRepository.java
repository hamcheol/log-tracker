package com.rp.order.repository;

import com.rp.order.model.OrderProd;

public interface OrderProdRepository {
	public int insertOrderProd(OrderProd orderProd);
	
	public int updateOrderProd(OrderProd orderProd);

}
