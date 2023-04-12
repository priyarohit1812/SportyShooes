package com.sportyshooes.model.requests;

import java.util.HashSet;
import java.util.Set;

public class AddToCartRequest {
	private Long cartId;
	private Long userId;
	private Set<CartItem> cartItems;
	
	public AddToCartRequest() {
		this(0L,0L,new HashSet<CartItem>());
	}	
	
	public AddToCartRequest(Long cartId, Long userId, Set<CartItem> cartItems) {
		this.cartId = cartId;
		this.userId = userId;
		this.cartItems = cartItems;
	}	

	public Long getCartId() {
		return cartId;
	}

	public void setCartId(Long cartId) {
		this.cartId = cartId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Set<CartItem> getCartItem() {
		return cartItems;
	}

	public void setCartItem(Set<CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public void addToCart(Long productId, int quantity) {
		this.cartItems.add(new CartItem(productId, quantity));
	}

	public class CartItem{
		private Long productId;
		private int quantity;
		
		public CartItem() {
			this(0L,0);
		}
		
		public CartItem(Long productId, int quantity) {
			this.productId = productId;
			this.quantity = quantity;
		}
		public Long getProductId() {
			return productId;
		}
		public void setProductId(Long productId) {
			this.productId = productId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
	}
}

