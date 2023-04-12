package com.sportyshooes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshooes.model.Cart;
import com.sportyshooes.repository.ICartRepository;

@Service(value = "cartService")
public class CartService implements ICartService {
	@Autowired
	private ICartRepository cartRepository;
	
	@Override
	public List<Cart> fetchCartList() {
		return cartRepository.findAll();
	}

	@Override
	public Cart saveCart(Cart cart) {
		return this.cartRepository.save(cart);
	}

	@Override
	public boolean deleteCart(Long cartId) {
		this.cartRepository.deleteById(cartId);
		return !this.cartRepository.existsById(cartId);
	}

	@Override
	public Cart getCart(Long cartId) {
		return this.cartRepository.findById(cartId).get();
	}

}
