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
	public String addAddress(Model model,
			@RequestParam(name = "addressId", required = false, defaultValue = "0") Long addressId,
			@RequestParam(name = "cartId", required = false, defaultValue = "0") Long cartId) {
		if (addressId > 0) {
			model.addAttribute("address", addressService.getAddress(addressId));
		} else {
			model.addAttribute("address", new Address());
		}
		model.addAttribute("cartId", cartId);

		return "addaddress";
	}

	@PostMapping("/address/save")
	public String save(@ModelAttribute("address") Address address, HttpServletRequest request,
			@RequestParam(name = "cartId", required = false, defaultValue = "0") Long cartId) {
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
		if (savedAddress != null) {
			if (cartId > 0) {
				return "redirect:/user/order/selectAddress?cartId=" + cartId;
			}
			return "redirect:/user/update?userId="+user.getUserId();
		} else {
			return "redirect:/address/save";
		}
	}

	@GetMapping("/user/address")
	public String getAddressByUserId(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		List<Address> addresses = this.addressService.fetchAddressListByUser(user.getUserId());
		model.addAttribute("addresses", addresses);
		model.addAttribute("selectable", false);
		return "addresses";
	}

	@GetMapping("/address/delete")
	public String deleteAddress(@RequestParam(name = "addressId", required = true) Long addressId, HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		this.addressService.deleteAddress(addressId);
		return "redirect:"+referer;
	}
}
