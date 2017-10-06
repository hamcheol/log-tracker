package com.rp.pilot.address.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rp.pilot.address.api.domain.Address;

@SpringBootApplication
public class AddressApiApplication {
	public static List<Address> addressBook = new ArrayList<>();
	
	static {
		Address address1 = new Address();
		address1.setId(1L);
		address1.setServiceCode("Booking");
		address1.setMainAddress("서울시 서대문구 홍은동");
		address1.setDetailAddress("101-25");
		
		Address address2 = new Address();
		address2.setId(1L);
		address2.setServiceCode("Booking");
		address2.setMainAddress("서울시 동대문구 장안동");
		address2.setDetailAddress("1525-5");
		
		Address address3 = new Address();
		address3.setId(1L);
		address3.setServiceCode("Booking");
		address3.setMainAddress("경기도 영통구 이의동");
		address3.setDetailAddress("12-9");
		
		addressBook.add(address1);
		addressBook.add(address2);
		addressBook.add(address3);
	}

	public static void main(String[] args) {
		SpringApplication.run(AddressApiApplication.class, args);
	}
}
