package com.rp.order.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderProd {
	private String ordNo;
	private Integer seq;
	private String prodNo;
	private String prodNm;
	private Long prodAmt;
	private Integer ordProdCnt;
	private String purchCfrmDt;
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

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public Long getProdAmt() {
		return prodAmt;
	}

	public void setProdAmt(Long prodAmt) {
		this.prodAmt = prodAmt;
	}

	public Integer getOrdProdCnt() {
		return ordProdCnt;
	}

	public void setOrdProdCnt(Integer ordProdCnt) {
		this.ordProdCnt = ordProdCnt;
	}

	public String getPurchCfrmDt() {
		return purchCfrmDt;
	}

	public void setPurchCfrmDt(String purchCfrmDt) {
		this.purchCfrmDt = purchCfrmDt;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getProdNo() {
		return prodNo;
	}

	public void setProdNo(String prodNo) {
		this.prodNo = prodNo;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
