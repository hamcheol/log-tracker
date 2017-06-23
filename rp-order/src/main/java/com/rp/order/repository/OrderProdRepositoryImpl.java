package com.rp.order.repository;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.order.model.OrderProd;

@Repository
public class OrderProdRepositoryImpl implements OrderProdRepository {
	private final String NAMESPACE = "com.rp.order.repository.OrderProdRepositoryImpl.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int insertOrderProd(OrderProd orderProd) {
		return sqlSessionTemplate.insert(NAMESPACE + "insertOrderProd", orderProd);
	}

	@Override
	public int updateOrderProd(OrderProd orderProd) {
		return sqlSessionTemplate.update(NAMESPACE + "updateOrderProd", orderProd);
	}

}
