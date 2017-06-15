package com.rp.order.model;

public class Payment {
	private String ordNo;
	private String method;
	private Long payAmt;

	public String getOrdNo() {
		return ordNo;
	}

	public void setOrdNo(String ordNo) {
		this.ordNo = ordNo;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Long getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Long payAmt) {
		this.payAmt = payAmt;
	}

}
