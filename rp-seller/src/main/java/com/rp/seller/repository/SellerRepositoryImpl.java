package com.rp.seller.repository;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rp.seller.model.Seller;

@Repository
public class SellerRepositoryImpl implements SellerRepository {
	private final String NAMESPACE = "com.rp.seller.repository.SellerRepositoryImpl.";

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public void insertSeller(Seller sqlParam) {
		sqlSessionTemplate.insert(NAMESPACE + "insertSeller", sqlParam);
	}

	@Override
	public List<Seller> selectSellers(Seller sqlParam) {
		return sqlSessionTemplate.selectList(NAMESPACE + "selectSellers", sqlParam);
	}
}