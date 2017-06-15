package com.rp.order.repository;

import com.rp.order.model.Delivery;

public interface DeliveryRepository {
	public int insertDelivery(Delivery delivery);

	public int updateDelivery(Delivery delivery);
}
