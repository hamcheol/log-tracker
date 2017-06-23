package com.rp.seller.service;

import java.util.List;

import com.rp.seller.model.Product;

public interface ProductService {
	public void addProduct(Product product);
	
	public List<Product> getProducts(Product product);

}
