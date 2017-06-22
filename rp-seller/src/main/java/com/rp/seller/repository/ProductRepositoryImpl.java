package com.rp.seller.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.seller.model.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
	private final String NAMESPACE = "com.rp.seller.repository.ProductRepositoryImpl.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insertProduct(Product sqlParam) {
		sqlSessionTemplate.insert(NAMESPACE + "insertProduct", sqlParam);
	}

	@Override
	public List<Product> selectProducts(Product sqlParam) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectProducts", sqlParam);
	}

}
