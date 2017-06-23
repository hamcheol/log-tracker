package com.rp.order.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.order.model.Order;
import com.rp.order.model.OrderParam;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
	private final String NAMESPACE = "com.rp.order.repository.OrderRepositoryImpl.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insertOrder(Order order) {
		return sqlSessionTemplate.insert(NAMESPACE + "insertOrder", order);
	}

	@Override
	public int updateOrder(Order order) {
		return sqlSessionTemplate.update(NAMESPACE + "updateOrder", order);
	}

	@Override
	public List<Order> selectOrders(OrderParam param) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectOrders", param);
	}

	@Override
	public List<Order> selectDetailOrders(OrderParam param) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectDetailOrders", param);
	}
}
