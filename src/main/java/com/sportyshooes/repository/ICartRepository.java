package com.sportyshooes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshooes.model.Cart;

public interface ICartRepository extends JpaRepository<Cart, Long> {
}
