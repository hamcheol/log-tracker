package com.rp.order.component;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomUtils;

import com.rp.order.config.BizEnum;
import com.rp.order.model.Delivery;
import com.rp.order.model.Member;
import com.rp.order.model.Order;
import com.rp.order.model.OrderProd;
import com.rp.order.model.Product;
import com.rp.order.utils.BizUtils;

public class RandomOrderBuilder {
	private String ordNo;
	private Member member;
	private Long ordTotAmt;
	private String ordStatCd;

	private List<Product> products;

	public RandomOrderBuilder() {
		this.ordNo = BizUtils.generateOrdNo();
		this.ordStatCd = BizEnum.ORDER_STAT.결제완료.getCode();
	}

	public RandomOrderBuilder member(Member member) {
		this.member = member;
		return this;
	}

	public RandomOrderBuilder products(List<Product> products) {
		this.products = products;
		return this;
	}

	public Order build() {
		Order order = new Order();
		order.setOrdNo(ordNo);
		order.setMbrId(member.getId());
		order.setOrdStatCd(ordStatCd);
		int seq = 1;
		List<OrderProd> orderProds = new ArrayList<>();
		for (Product product : products) {
			OrderProd op = new OrderProd();
			op.setOrdNo(ordNo);
			op.setSeq(seq);
			op.setProdNm(product.getName());
			op.setProdNo(product.getId());
			op.setSellerId(product.getSeller().getId());
			int ordProdCnt = RandomUtils.nextInt(1, 5);
			op.setOrdProdCnt(ordProdCnt);
			ordTotAmt += ordProdCnt * product.getPrice();
			orderProds.add(op);
			seq++;
		}
		order.setOrdTotAmt(ordTotAmt);
		order.setOrderProds(orderProds);
		
		Delivery delivery = new Delivery();
		delivery.setOrdNo(ordNo);
		delivery.setAddr(member.getAddr());
		return order;
	}

}
