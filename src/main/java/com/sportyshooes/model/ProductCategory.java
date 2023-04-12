package com.sportyshooes.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ss_productcategory")
public class ProductCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productCategoryId;
	@NotBlank(message = "Category name is mandatory")
	private String categoryName;
	@NotBlank(message = "targetGroup is mandatory")
	private String targetGroup;
	
	
	@OneToMany(mappedBy = "productCategory", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JsonIgnoreProperties("productCategory")
	private Set<Product> products = new HashSet<>();

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getTargetGroup() {
		return targetGroup;
	}

	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public ProductCategory(Long productCategoryId, String categoryName, String targetGroup) {
		super();
		this.productCategoryId = productCategoryId;
		this.categoryName = categoryName;
		this.targetGroup = targetGroup;
	}

	public ProductCategory() {
		this((long) 0, "","");
	}

	@Override
	public String toString() {
		return "ProductCategory [productCategoryId=" + productCategoryId + ", categoryName=" + categoryName
				+ ", targetGroup=" + targetGroup + "]";
	}
	
	
}
