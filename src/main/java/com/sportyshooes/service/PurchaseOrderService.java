package com.sportyshooes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshooes.model.PurchaseOrder;
import com.sportyshooes.repository.IPurchaseOrderRepository;

@Service(value = "purchaseOrderService")
public class PurchaseOrderService implements IPurchaseOrderService {
	@Autowired
	private IPurchaseOrderRepository purchaseOrderRepository;
	
	@Override
	public List<PurchaseOrder> fetchPurchaseOrderList() {
		return this.purchaseOrderRepository.findAll();
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrderByAddressId(Long addressId) {
		return null;
	}

	@Override
	public PurchaseOrder getPurchaseorder(Long purchaseOrderId) {
		return this.purchaseOrderRepository.findById(purchaseOrderId).get();
	}

	@Override
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder) {
		return this.purchaseOrderRepository.save(purchaseOrder);
	}

	@Override
	public boolean deletePurchaseOrder(Long purchaseOrderId) {
		this.purchaseOrderRepository.deleteById(purchaseOrderId);
		return !this.purchaseOrderRepository.existsById(purchaseOrderId);
	}

	@Override
	public List<PurchaseOrder> getAllPurchaseOrderByUserId(Long userId) {
		return this.purchaseOrderRepository.getOrdersByUser(userId);
	}

}
