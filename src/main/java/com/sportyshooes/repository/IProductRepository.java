package com.sportyshooes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sportyshooes.model.Product;

public interface IProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByProductNameContainsOrBrandContains(String productName, String brand);
	List<Product> findByBrand(String brand);
	
	@Query("SELECT DISTINCT(p.brand) FROM Product p")
	List<String> getDistictBrand();
}
