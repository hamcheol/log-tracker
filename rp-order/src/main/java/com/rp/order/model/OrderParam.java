package com.rp.order.model;

import com.rp.order.config.BizEnum;

public class OrderParam {
	private String ordNoLike;
	private String mbrId;
	private BizEnum.ORDER_STAT ordStat;

	public String getOrdNoLike() {
		return ordNoLike;
	}

	public void setOrdNoLike(String ordNoLike) {
		this.ordNoLike = ordNoLike;
	}

	public String getMbrId() {
		return mbrId;
	}

	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}

	public BizEnum.ORDER_STAT getOrdStat() {
		return ordStat;
	}

	public void setOrdStat(BizEnum.ORDER_STAT ordStat) {
		this.ordStat = ordStat;
	}

}
