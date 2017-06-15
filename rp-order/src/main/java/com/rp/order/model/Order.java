package com.rp.order.model;

public class Order {
	private String ordNo;
	private String mbrId;
	private Long ordTotAmt;
	private String ordStatCd;

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

}
