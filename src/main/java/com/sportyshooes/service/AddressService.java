package com.sportyshooes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshooes.model.Address;
import com.sportyshooes.repository.IAddressRepository;

@Service(value = "addressService")
public class AddressService implements IAddressService {
	@Autowired
	private IAddressRepository addressRepository;
	
	@Override
	public List<Address> fetchAddressList() {
		return this.addressRepository.findAll();
	}

	@Override
	public Address getAddress(Long addressId) {
		return this.addressRepository.findById(addressId).get();
	}

	@Override
	public Address saveAddress(Address address) {
		return this.addressRepository.save(address);
	}

	@Override
	public boolean deleteAddress(Long addressId) {
		this.addressRepository.deleteById(addressId);
		return !this.addressRepository.existsById(addressId);
	}

	@Override
	public List<Address> fetchAddressListByUser(Long UserId) {
		return this.addressRepository.findByUserId(UserId);
	}

}
