package com.sportyshooes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshooes.model.Address;
import com.sportyshooes.model.User;
import com.sportyshooes.service.AddressService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AddressController {
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/address/save")
	public String addAddress(Model model, @RequestParam(name="addressId",required=false, defaultValue = "0") Long addressId) {
		if (addressId > 0) {
			model.addAttribute("address", addressService.getAddress(addressId));
		} else {
			model.addAttribute("address", new Address());
		}
		return "addaddress";
	}
	
	@PostMapping("/address/save")
	public String save(@ModelAttribute("address") Address address,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		if (address == null) {
			return "redirect:/address/save";
		}
		address.setUser(user);
		Address savedAddress = this.addressService.saveAddress(address);
		if (savedAddress!=null) {
			return "redirect:/user/address";
		} else {
			return "redirect:/address/save";
		}
	}
		
	@GetMapping("/user/address")
	public String getAddressByUserId(Model model,HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		List<Address> addresses = this.addressService.fetchAddressListByUser(user.getUserId());
		model.addAttribute("addresses", addresses);
		return "address";
	}
	
	@GetMapping("/address/delete")
	public String deleteAddress(@RequestParam(name="addressId",required=true) Long addressId) {
		this.addressService.deleteAddress(addressId);
		return "redirect:/user/address";
	}
}
