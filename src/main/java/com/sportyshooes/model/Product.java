package com.sportyshooes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ss_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;
	@NotBlank(message = "Product code is mandatory")
	private String productCode;
	@NotBlank(message = "Product name is mandatory")
	private String productName;
	@NotBlank(message = "Brand is mandatory")
	private String brand;
	@NotNull(message = "Size is mandatory")
	private int size;
	@NotBlank(message = "Image_url is mandatory")
	private String image_url;
	@NotNull(message = "Price is mandatory")
	private double price;
	
	@ManyToOne
	@JsonIgnoreProperties("products")
	@JoinColumn(name = "productCategory_id", nullable = true)
	private ProductCategory productCategory;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public Product(Long productId, String productCode, String productName, String brand, int size, String image_url,
			int price) {
		super();
		this.productId = productId;
		this.productCode = productCode;
		this.productName = productName;
		this.brand = brand;
		this.size = size;
		this.image_url = image_url;
		this.price = price;
	}

	public Product() {
		this((long) 0, "","","", 0, "", 0);
	}

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productCode=" + productCode + ", productName=" + productName
				+ ", brand=" + brand + ", size=" + size + ", image_url=" + image_url + ", price=" + price
				+ "]";
	}
	
	
}
