package com.sportyshooes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ss_order_item")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;
	@NotNull(message = "Quantity is mandatory")
	private int quantity;
	@NotNull(message = "Total Price is mandatory")
	private double totalPrice;
	
	@ManyToOne
	@JoinColumn(name = "product_id_fk")
	@NotNull(message = "Product Reference is mandatory")
	@JsonIgnoreProperties("orderItems")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "cart_id_fk")
	@NotNull(message = "Cart Reference is mandatory")
	@JsonIgnoreProperties("orderItems")
	private Cart cart;

	public OrderItem() {
		this(0L,0,0.0,null);
	}
	
	public OrderItem(Long orderItemId, @NotNull(message = "Quantity is mandatory") int quantity,
			@NotNull(message = "Total Price is mandatory") double totalPrice,
			@NotNull(message = "Product Reference is mandatory") Product product) {
		this.orderItemId = orderItemId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.product = product;
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}		
}
