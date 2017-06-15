package com.rp.order.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.order.model.Delivery;

@Repository
public class DeliveryRepositoryImpl implements DeliveryRepository {
	private final String NAMESPACE = "com.easyshop.order.repository.DeliveryRepositoryImpl.";

	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insertDelivery(Delivery delivery) {
		return sqlSession.insert(NAMESPACE + "insertDelivery", delivery);
	}

	@Override
	public int updateDelivery(Delivery delivery) {
		return sqlSession.update(NAMESPACE + "updateDelivery", delivery);
	}

}
