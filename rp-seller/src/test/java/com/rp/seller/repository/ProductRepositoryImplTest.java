package com.rp.seller.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rp.seller.model.Product;
import com.rp.seller.model.Seller;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryImplTest {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private SellerRepository sellerRepository;

	@Test
	public void testAddProduct() {
		getDatas();

	}

	private Seller getSeller(String name) {
		Seller param = new Seller();
		param.setName(name);
		List<Seller> sellers = sellerRepository.selectSellers(param);

		if (sellers != null && sellers.size() == 1) {
			return sellers.get(0);
		}
		logger.info("입력한 이름({})의 아이디를 찾을수 없습니다.", name);
		return null;
	}

	private List<Sample> getDatas() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("/Users/naver/git/log-tracker/rp-seller/sample_product.csv"));
			logger.info("line number:" + lines.size());
			for (String line : lines) {
				line = line.replaceAll("\"", "").replaceAll("“", "").replaceAll("”", "");
				Product product = new Product();
				String[] elements = line.split(",");
				if(elements != null && elements.length >= 4) {
					//logger.info("{}:{}:{}:{}",elements[0],elements[1],elements[2],elements[3]);
					product.setPrice(RandomUtils.nextInt(100, 300) * 100L);
					product.setName(elements[1]);
					Seller seller = getSeller(elements[2]);
					if(seller == null) continue;
					product.setSeller(seller);
					product.setInfo(elements[3]);
				} else {
					product.setPrice(RandomUtils.nextInt(100, 300) * 100L);
					product.setName(elements[1]);
					Seller seller = getSeller(elements[2]);
					if(seller == null) continue;
					product.setSeller(seller);
					product.setInfo("....");
				}
				productRepository.insertProduct(product);
			}
		} catch (Exception e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		}

		return null;
	}

	public class Sample {
		private int no;
		private String name;
		private String seller;
		private String desc;

		public int getNo() {
			return no;
		}

		public void setNo(int no) {
			this.no = no;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSeller() {
			return seller;
		}

		public void setSeller(String seller) {
			this.seller = seller;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

	}

}
