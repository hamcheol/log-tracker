package com.rp.order.component;

import java.util.List;

import com.rp.order.config.BizEnum;
import com.rp.order.model.Order;
import com.rp.order.model.Product;
import com.rp.order.utils.BizUtils;

public class RandomOrderBuilder {
	private String ordNo;
	private String mbrId;
	private Long ordTotAmt;
	private String ordStatCd;
	
	private List<Product> products;
	
	public RandomOrderBuilder() {
		this.ordNo = BizUtils.generateOrdNo();
		this.ordStatCd = BizEnum.ORDER_STAT.결제완료.getCode();
	}
	
	public RandomOrderBuilder mbrId(String mbrId) {
		this.mbrId = mbrId;
		return this;
	}
	
	public RandomOrderBuilder orderProds(List<Product> products) {
		this.products = products;
		return this;
	}
	
	public Order build() {
		Order order = new Order();
		order.setOrdNo(ordNo);
		order.setMbrId(mbrId);
		return order;
	}

}
