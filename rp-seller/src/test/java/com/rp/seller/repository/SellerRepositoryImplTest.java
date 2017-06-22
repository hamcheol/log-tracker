package com.rp.seller.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rp.seller.model.Seller;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SellerRepositoryImplTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private SellerRepository repository;

	private List<Seller> defaultSellers = new ArrayList<Seller>();
	
	private Set<String> sellerNameSet = new HashSet<>();

	@Before
	public void setDefaultSeller() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("/Users/naver/git/log-tracker/rp-seller/sample_product.csv"));
			for(String line : lines) {
				String[] elements = line.split(",");
				sellerNameSet.add(elements[2]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String name : sellerNameSet) {
			Seller seller = new Seller();
			seller.setName(name);
			defaultSellers.add(seller);
		}
	}
	
	@Test
	public void addSellerTest() {
		logger.info("seller count:" + defaultSellers.size());
		for(Seller seller : defaultSellers) {
			repository.insertSeller(seller);
		}
	}

}
