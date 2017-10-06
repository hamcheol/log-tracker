package com.rp.pilot.address.api.service;

import java.util.List;
import com.rp.pilot.address.api.domain.Address;

public interface AddressService {
	
	public List<Address> getAddress();
	
	public Address getAddressById(Long id);

}
