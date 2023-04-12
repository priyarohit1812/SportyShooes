package com.sportyshooes.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshooes.model.Product;
import com.sportyshooes.model.ProductCategory;
import com.sportyshooes.service.IProductCategoryService;
import com.sportyshooes.service.IProductService;

@Controller
public class ProductCategoryController {
	@Autowired
	private IProductCategoryService productCategoryService;
	@Autowired
	private IProductService productService;
	
	@GetMapping("/admin/productcategory/save")
	public String addProductCategory(Model model, @RequestParam(name="productCategoryId",required=false, defaultValue = "0") Long productCategoryId) {
		if (productCategoryId > 0) {
			model.addAttribute("category", productCategoryService.getProductCategoryById(productCategoryId));
		} else {
			model.addAttribute("category", new ProductCategory());
		}
		return "addproductcategory";
	}
	
	@GetMapping("/admin/productcategories")
	public String getProductCategoryList(Model model) {
		model.addAttribute("categories", productCategoryService.fetchProductCategoryList());
		return "productcategory";
	}
	
	@GetMapping("/admin/search/productcategories")
	public String searchProductCategory(Model model, @RequestParam(name = "key",defaultValue = "") String key) {
		if (key.isBlank()) {
			return "redirect:/admin/productcategories";
		} else {
			model.addAttribute("categories", productCategoryService.searchProductCategory(key));
			return "productcategory";
		}		
	}
	
	@GetMapping(path = "/admin/productcategory/delete")
	public String deleteCategory(@RequestParam(name = "productCategoryId",required = true) Long productCategoryId ) {
		productCategoryService.deleteProductCategory(productCategoryId);

		return "redirect:/admin/productcategories";

	}
	
	@GetMapping("/user/productcategory/search")
	public String searchProductCategoryUser(Model model, @RequestParam(name = "key",defaultValue = "") String key) {
		if (key.isBlank()) {
			return "redirect:/user/home";
		} else {
			model.addAttribute("categories", productCategoryService.searchProductCategory(key));
			return "home";
		}		
	}
	
	@GetMapping("/user/productcategory/searchByName")
	public String searchProductCategoryByName(Model model, @RequestParam(name = "key",required = true) String key) {
		if (key.isBlank()) {
			return "redirect:/user/home";
		} else {
			ProductCategory category = this .productCategoryService.searchProductCategoryByName(key);
			if (category != null) {
				model.addAttribute("products", category.getProducts());
			}
			
			return "home";
		}		
	}
	
	@GetMapping("/user/productcategory/searchByTargetGroup")
	public String searchProductCategoryByTargetGroup(Model model, @RequestParam(name = "key",required = true) String key) {
		if (key.isBlank()) {
			return "redirect:/user/home";
		} else {
			List<ProductCategory> categories = this .productCategoryService.searchProductCategoryByTargetGroup(key);
			Set<Product> products = new HashSet<Product>();
			for (ProductCategory cat : categories) {
				products.addAll(cat.getProducts());
			}
			
			model.addAttribute("products", products);
			
			return "home";
		}		
	}

	@PostMapping("/admin/productcategory/save")
	public String save(@ModelAttribute("category") ProductCategory productCategory) {
		if (productCategory == null) {
			return "redirect:/admin/productcategory/save";
		}
		ProductCategory savedProductCategory = this.productCategoryService.saveProductCategory(productCategory);
		if (savedProductCategory != null) {
			return "redirect:/admin/productcategories";
		} else {
			return "redirect:/admin/productcategory/save";
		}

	}

	@GetMapping("/productCategory/list")
	public ResponseEntity<List<ProductCategory>> getAllProductCategories() {
		List<ProductCategory> allProductCategories = this.productCategoryService.fetchProductCategoryList();
		return ResponseEntity.ok(allProductCategories);
	}

	@GetMapping("/productCategory/{productCategoryId}")
	public ResponseEntity<ProductCategory> getProductCategory(@PathVariable Long productCategoryId) {
		if (productCategoryId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		ProductCategory productCategory = this.productCategoryService.getProductCategoryById(productCategoryId);
		if (productCategory != null) {
			return ResponseEntity.ok(productCategory);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/productCategory/{productCategoryId}")
	public ResponseEntity<?> deleteproductCategory(@PathVariable Long productCategoryId) {
		if (productCategoryId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		ProductCategory category = this.productCategoryService.getProductCategoryById(productCategoryId);
		for(Product product: category.getProducts()) {
			product.setProductCategory(null);
			this.productService.saveProduct(product);
		}
		boolean isDeleted = this.productCategoryService.deleteProductCategory(productCategoryId);
		if (isDeleted) {
			return ResponseEntity.ok("Product Category deleted Succcessfully");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

}
