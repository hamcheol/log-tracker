package com.rp.seller.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rp.seller.model.Product;
import com.rp.seller.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void addProduct(Product product) {
		productRepository.insertProduct(product);
	}

	@Override
	public List<Product> getProducts(Product product) {
		return productRepository.selectProducts(product);
	}

}
