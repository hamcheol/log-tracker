package com.rp.seller.repository;

import java.util.ArrayList;
import java.util.List;

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

	private final String DEFAULT_NAMES_STR = "채희향,표혜인,차여진,장지선,유주엽,우인수,차아담,왕선재,왕성혜,오율";
	private final String[] DEFAULT_NAMES = DEFAULT_NAMES_STR.split(",");

	private List<Seller> defaultSellers = new ArrayList<Seller>();

	@Before
	public void setDefaultSeller() {
		for (int i = 0; i < 10; i++) {
			Seller seller = new Seller();
			seller.setId(i);
			seller.setName(DEFAULT_NAMES[i]);
			defaultSellers.add(seller);
		}
	}
	
	@Test
	public void addSellerTest() {
		for(Seller seller : defaultSellers) {
			repository.insertSeller(seller);
		}
	}

}
