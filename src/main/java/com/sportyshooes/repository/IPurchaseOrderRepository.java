package com.sportyshooes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshooes.model.PurchaseOrder;

public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

}
