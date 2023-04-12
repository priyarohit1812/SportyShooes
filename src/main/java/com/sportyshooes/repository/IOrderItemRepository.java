package com.sportyshooes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sportyshooes.model.OrderItem;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM ss_order_item WHERE cart_id_fk = :cartId")
	List<OrderItem> findByCartId(@Param("cartId") Long cartId);
}
