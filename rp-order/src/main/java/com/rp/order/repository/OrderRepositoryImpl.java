package com.rp.order.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.order.model.Order;
import com.rp.order.model.OrderParam;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
	private final String NAMESPACE = "com.easyshop.order.repository.OrderRepositoryImpl.";

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertOrder(Order order) {
		return sqlSession.insert(NAMESPACE + "insertOrder", order);
	}

	@Override
	public int updateOrder(Order order) {
		return sqlSession.update(NAMESPACE + "updateOrder", order);
	}

	@Override
	public List<Order> selectOrders(OrderParam param) {
		return sqlSession.selectList(NAMESPACE + "selectOrders", param);
	}
}
