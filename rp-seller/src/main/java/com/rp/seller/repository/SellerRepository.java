package com.rp.seller.repository;

import java.util.List;

import com.rp.seller.model.Seller;

public interface SellerRepository {
	public void insertSeller(Seller sqlParam);
	
	public List<Seller> selectSellers();

}
