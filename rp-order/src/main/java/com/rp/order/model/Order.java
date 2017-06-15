package com.rp.order.model;

import java.util.List;

public class Order {
	private String ordNo;
	private String mbrId;
	private Long ordTotAmt;
	private String ordStatCd;

	private List<OrderProd> orderProds;
	private Delivery delivery;

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public Long getOrdTotAmt() {
		return ordTotAmt;
	}

	public void setOrdTotAmt(Long ordTotAmt) {
		this.ordTotAmt = ordTotAmt;
	}

	public String getOrdStatCd() {
		return ordStatCd;
	}

	public void setOrdStatCd(String ordStatCd) {
		this.ordStatCd = ordStatCd;
	}

	public List<OrderProd> getOrderProds() {
		return orderProds;
	}

	public void setOrderProds(List<OrderProd> orderProds) {
		this.orderProds = orderProds;
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

}
