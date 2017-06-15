package com.rp.order.model;

public class OrderItem {
	private String ordNo;
	private Integer seq;
	private String itemNm;
	private Integer itemPrice;
	private Integer ordItemCnt;
	private String sellerId;

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getItemNm() {
		return itemNm;
	}

	public void setItemNm(String itemNm) {
		this.itemNm = itemNm;
	}

	public Integer getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(Integer itemPrice) {
		this.itemPrice = itemPrice;
	}

	public Integer getOrdItemCnt() {
		return ordItemCnt;
	}

	public void setOrdItemCnt(Integer ordItemCnt) {
		this.ordItemCnt = ordItemCnt;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

}
