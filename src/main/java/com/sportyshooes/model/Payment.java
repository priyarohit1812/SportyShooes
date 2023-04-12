package com.sportyshooes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sportyshooes.enums.PaymentStatus;
import com.sportyshooes.enums.PaymentType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ss_payment")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;
	
	@NotNull(message = "Payment Type is mandatory")
	private PaymentType paymentType;
	
	@NotNull(message = "Payment Status is mandatory")
	private PaymentStatus paymentStatus;
	
	@OneToOne
	@JsonIgnoreProperties({"payment"})
	@NotNull(message = "Purchase Order reference is mandatory")
	@JoinColumn(name = "purchase_order_fk")
	private PurchaseOrder purchaseOrder;
	
	public Payment() {
	}	

	public Long getPaymentId() {
		return paymentId;
	}
	
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public PurchaseOrder getPurchaseOrder() {
		return purchaseOrder;
	}

	public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentType=" + paymentType + ", paymentStatus=" + paymentStatus
				+ ", purchaseOrder=" + purchaseOrder + "]";
	}		
}
