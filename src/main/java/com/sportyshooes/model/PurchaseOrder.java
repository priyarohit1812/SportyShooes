package com.sportyshooes.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="ss_purchaseorder")
public class PurchaseOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long purchaseOrderId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_date", nullable = false, updatable = false)
	@CreationTimestamp
	private Timestamp orderDate;
	
	@NotNull(message = "Cart reference is mandatory")
	@JsonIgnoreProperties("purchaseOrder")
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name = "cart_id_fk")
	private Cart cart;
	
	@NotNull(message = "Address reference is mandatory")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id_fk", nullable = false)
	private Address address;
	
	@NotNull(message = "Payment reference is mandatory")
	@JsonIgnoreProperties("purchaseOrder")
	@OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.DETACH})
	@JoinColumn(name = "payment_id_fk")
	private Payment payment;
	
	public Long getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Long purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}	
	
	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public PurchaseOrder(Long purchaseOrderId, Address address) {
		this.purchaseOrderId = purchaseOrderId;
		this.address = address;
	}

	public PurchaseOrder() {
		this((long) 0,null);
	}

	@Override
	public String toString() {
		return "PurchaseOrder [purchaseOrderId=" + purchaseOrderId + ", cart=" + cart + ", address=" + address
				+ ", payment=" + payment + "]";
	}		
}
