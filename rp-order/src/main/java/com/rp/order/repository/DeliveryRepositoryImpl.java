package com.rp.order.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.order.model.Delivery;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {
	private final String NAMESPACE = "com.rp.order.repository.DeliveryRepositoryImpl.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insertDelivery(Delivery delivery) {
		return sqlSessionTemplate.insert(NAMESPACE + "insertDelivery", delivery);
	}

	@Override
	public int updateDelivery(Delivery delivery) {
		return sqlSessionTemplate.update(NAMESPACE + "updateDelivery", delivery);
	}

}
