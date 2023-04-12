package com.sportyshooes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshooes.model.Product;
import com.sportyshooes.repository.IProductRepository;

@Service(value = "productService")
public class ProductService implements IProductService {
	@Autowired
	private IProductRepository productRepository;
	
	@Override
	public List<Product> fetchProductList() {
		return this.productRepository.findAll();
	}

	@Override
	public Product getProductById(Long productId) {
		return this.productRepository.findById(productId).get();
	}

	@Override
	public Product saveProduct(Product product) {
		return this.productRepository.save(product);
	}

	@Override
	public boolean deleteProduct(Long productId) {
		this.productRepository.deleteById(productId);
		return !this.productRepository.existsById(productId);
	}

	@Override
	public List<Product> searchProduct(String key) {
		return this.productRepository.findByProductNameContainsOrBrandContains(key, key);
	}

	@Override
	public List<String> getBrands() {
		return this.productRepository.getDistictBrand();
	}

	@Override
	public List<Product> fetchProductListByBrand(String brand) {
		return this.productRepository.findByBrand(brand);
	}

	
	

	/*@Override
	public List<Product> getAllProductByCartId(Long cartId) {
		return this.productRepository.findAllProductBycartId(cartId);
	}

	@Override
	public List<Product> getAllProductByProductCategory(ProductCategory productCategoryId) {
		return this.productRepository.findAllProductByProductCategory(productCategoryId);
	}*/

}
