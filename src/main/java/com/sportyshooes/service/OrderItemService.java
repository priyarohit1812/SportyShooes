package com.sportyshooes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshooes.model.OrderItem;
import com.sportyshooes.repository.IOrderItemRepository;

@Service(value = "orderItemService")
public class OrderItemService implements IOrderItemService {
	@Autowired
	private IOrderItemRepository orderItemRepository;
	@Override
	public List<OrderItem> fetchOrderItemList() {
		return this.orderItemRepository.findAll();
	}

	@Override
	public OrderItem getOrderItemById(Long orderItemId) {
		return this.orderItemRepository.findById(orderItemId).get();
	}

	@Override
	public OrderItem saveOrderItem(OrderItem orderItem) {
		return this.orderItemRepository.save(orderItem);
	}

	@Override
	public boolean deleteOrderItem(Long orderItemId) {
		this.orderItemRepository.deleteById(orderItemId);
		return !this.orderItemRepository.existsById(orderItemId);
	}

	@Override
	public List<OrderItem> fetchOrderItemsByCart(Long cartId) {
		return this.orderItemRepository.findByCartId(cartId);
	}
}
