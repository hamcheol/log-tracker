package com.rp.seller.repository;

import java.util.List;

import com.rp.seller.model.Product;

public interface ProductRepository {
	public void insertProduct(Product sqlParam);
	
	public List<Product> selectProducts(Product sqlParam);

}
