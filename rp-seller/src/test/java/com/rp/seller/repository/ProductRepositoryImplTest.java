package com.rp.seller.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;
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
		throw new RuntimeException("입력한 이름의 아이디를 찾을수 없습니다.");
	}

	private List<Sample> getDatas() {
		try {
			List<String> lines = Files.readAllLines(Paths.get("/Users/naver/git/log-tracker/rp-seller/sample_product.csv"));
			for (String line : lines) {
				String[] elements = line.split(",");
				Product product = new Product();
				product.setPrice(RandomUtils.nextInt(100, 300) * 100L);
				product.setName(elements[1].replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
				product.setInfo(elements[3].replaceAll("\"", "").replaceAll("“", "").replaceAll("”", ""));
				product.setSeller(getSeller(elements[2]));

				productRepository.insertProduct(product);
			}
		} catch (IOException e) {
			e.printStackTrace();
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
