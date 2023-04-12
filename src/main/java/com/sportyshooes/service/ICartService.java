package com.sportyshooes.service;

import java.util.List;

import com.sportyshooes.model.Cart;

public interface ICartService {
	public List<Cart> fetchCartList(); 
	public Cart saveCart(Cart cart);
	boolean deleteCart(Long cartId);
	public Cart getCart(Long cartId);
}
