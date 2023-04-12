package com.sportyshooes.service;

import java.util.List;

import com.sportyshooes.model.Product;

public interface IProductService {
	public List<Product> fetchProductList();
	public List<Product> fetchProductListByBrand(String brand);
	public List<Product> searchProduct(String key);
	public Product getProductById(Long productId);
	public Product saveProduct(Product product);
	public boolean deleteProduct(Long productId);
	public List<String> getBrands();
}
