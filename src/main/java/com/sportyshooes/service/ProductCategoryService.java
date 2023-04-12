package com.sportyshooes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshooes.model.ProductCategory;
import com.sportyshooes.repository.IProductCategoryRepository;

@Service(value = "productCategoryService")
public class ProductCategoryService implements IProductCategoryService {
	@Autowired
	private IProductCategoryRepository productCategoryRepository;
	
	@Override
	public List<ProductCategory> fetchProductCategoryList() {
		return this.productCategoryRepository.findAll();
	}

	@Override
	public ProductCategory saveProductCategory(ProductCategory productCategory) {
		return this.productCategoryRepository.save(productCategory);
	}

	@Override
	public boolean deleteProductCategory(Long productCategoryId) {
		this.productCategoryRepository.deleteById(productCategoryId);
		return !this.productCategoryRepository.existsById(productCategoryId) ;
	}

	@Override
	public ProductCategory getProductCategoryById(Long productCategoryId) {
		return this.productCategoryRepository.findById(productCategoryId).get();
	}

	@Override
	public List<ProductCategory> searchProductCategory(String key) {
		return this.productCategoryRepository.findByCategoryNameContainsOrTargetGroupContains(key, key);
	}

	@Override
	public ProductCategory searchProductCategoryByName(String key) {
		Optional<ProductCategory> optPC = this.productCategoryRepository.findByCategoryName(key);
		if (optPC!=null && optPC.isPresent()) {
			return optPC.get();
		}
		return null;
	}

	@Override
	public List<ProductCategory> searchProductCategoryByTargetGroup(String key) {		
		return this.productCategoryRepository.findByTargetGroup(key);
	}

	@Override
	public List<String> getTargetGroups() {
		return this.productCategoryRepository.getDistictTargetGroups();
	}

	@Override
	public List<String> getCategoryNames() {
		return this.productCategoryRepository.getDistictCategoryName();
	}
}
