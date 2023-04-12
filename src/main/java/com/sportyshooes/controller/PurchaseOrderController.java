package com.sportyshooes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.sportyshooes.model.PurchaseOrder;
import com.sportyshooes.service.PurchaseOrderService;

import jakarta.validation.Valid;

@Controller
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@GetMapping("/admin/purchaseorders")
	public String getPurchaseOrderList(Model model) {
		model.addAttribute("orders", purchaseOrderService.fetchPurchaseOrderList());
		return "purchaseorder";
	}
	
	@PostMapping("/purchaseOrder/save")
	public ResponseEntity<PurchaseOrder> save(@Valid @RequestBody PurchaseOrder purchaseOrder) {
		if (purchaseOrder == null) {
			return ResponseEntity.badRequest().build();
		}
		PurchaseOrder savedPurchaseOrder = this.purchaseOrderService.savePurchaseOrder(purchaseOrder);
		if (savedPurchaseOrder!=null) {
			return ResponseEntity.ok(savedPurchaseOrder);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/purchaseOrder/list")
	public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders(){
		try {
			List<PurchaseOrder> allPurchaseOrders = this.purchaseOrderService.fetchPurchaseOrderList();
			return ResponseEntity.ok(allPurchaseOrders);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@GetMapping("/purchaseOrder/{purchaseOrderId}")
	public ResponseEntity<PurchaseOrder> getPurchaseOrder(@PathVariable Long purchaseOrderId){
		if(purchaseOrderId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		PurchaseOrder purchaseOrder = this.purchaseOrderService.getPurchaseorder(purchaseOrderId);
		if (purchaseOrder!=null) {
			return ResponseEntity.ok(purchaseOrder);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/purchaseOrder/{purchaseOrderId}")
	public ResponseEntity<?> deletePurchaseOrder(@PathVariable Long purchaseOrderId) {
		if(purchaseOrderId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		boolean isDeleted = this.purchaseOrderService.deletePurchaseOrder(purchaseOrderId);
		if (isDeleted) {
			return ResponseEntity.ok("PurchaseOrder deleted Succcessfully");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
}
