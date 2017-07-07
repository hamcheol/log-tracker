package com.rp.seller.web;

import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.rp.seller.model.Product;
import com.rp.seller.model.Seller;
import com.rp.seller.service.ProductService;

@RestController
public class ProductController {
	private Logger logger = LoggerFactory.getLogger("log-tracker");
	
	@Autowired
	private ProductService productService;

	@RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
	public Product infoById(@PathVariable Long id) {
		Product param = new Product();
		param.setId(id);
		List<Product> products = productService.getProducts(param);

		if (products != null && products.size() == 1) {
			return products.get(0);
		} else {
			return null;
		}
	}

	@RequestMapping(path = "/product/seller/{id}", method = RequestMethod.GET)
	public List<Product> infoBySellerId(@PathVariable Long id) {
		Product param = new Product();
		param.setSeller(new Seller(id));
		List<Product> products = productService.getProducts(param);

		return products;

	}

	@RequestMapping(path = "/product/random", method = RequestMethod.GET)
	public List<Product> random() {
		List<Product> randomProducts = Lists.newArrayList();
		List<Product> products = productService.getProducts(null);
		int prdCnt = RandomUtils.nextInt(1, 6);
		logger.info("Products count : {}", prdCnt);
		for (int i = 0; i < prdCnt; i++) {
			int _idx = RandomUtils.nextInt(1, products.size());
			randomProducts.add(products.get(_idx));
		}
		return randomProducts;
	}
}
