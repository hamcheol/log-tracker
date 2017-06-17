package com.rp.order.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.order.model.OrderProd;

@Repository
public class OrderProdRepositoryImpl implements OrderProdRepository {
	private final String NAMESPACE = "com.easyshop.order.repository.OrderProdRepositoryImpl.";

	@Autowired
	private SqlSessionTemplate sqlSession;

	@Override
	public int insertOrderProd(OrderProd orderProd) {
		return sqlSession.insert(NAMESPACE + "insertOrderProd", orderProd);
	}

	@Override
	public int updateOrderProd(OrderProd orderProd) {
		return sqlSession.update(NAMESPACE + "updateOrderProd", orderProd);
	}

}
