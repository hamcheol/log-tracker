package com.rp.pilot.address.api.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.rp.pilot.address.api.AddressApiApplication;
import com.rp.pilot.address.api.domain.Address;

@Service
public class AddressServiceImpl implements AddressService {

	@Override
	public List<Address> getAddress() {
		return AddressApiApplication.addressBook;
	}

	@Override
	public Address getAddressById(Long id) {
		for(Address address : AddressApiApplication.addressBook) {
			if(address.getId() == id) {
				return address;
			}
		}
		return null;
	}

}
