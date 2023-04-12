package com.sportyshooes.service;

import java.util.List;

import com.sportyshooes.model.PurchaseOrder;

public interface IPurchaseOrderService {
	public List<PurchaseOrder> fetchPurchaseOrderList();
	public List<PurchaseOrder> getAllPurchaseOrderByAddressId(Long addressId);
	public PurchaseOrder getPurchaseorder(Long purchaseOrderId);
	public PurchaseOrder savePurchaseOrder(PurchaseOrder purchaseOrder);
	boolean deletePurchaseOrder(Long purchaseOrderId);
	
}
