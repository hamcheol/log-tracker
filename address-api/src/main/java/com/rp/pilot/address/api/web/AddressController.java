package com.rp.pilot.address.api.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rp.pilot.address.api.domain.Address;
import com.rp.pilot.address.api.service.AddressService;

@RestController
public class AddressController {

	@Autowired
	AddressService addressService;

	@RequestMapping(path = "/address/all", method = RequestMethod.GET)
	public List<Address> queryAll() {
		return addressService.getAddress();
	}

	@RequestMapping(path = "/address/{id}", method = RequestMethod.GET)
	public Address queryById(@PathVariable Long id) {
		return addressService.getAddressById(id);
	}

}
