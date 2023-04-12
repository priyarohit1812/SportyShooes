package com.sportyshooes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshooes.model.Product;
import com.sportyshooes.service.ProductCategoryService;
import com.sportyshooes.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping("/admin/product/save")
	public String addProduct(Model model, @RequestParam(name="productId",required=false, defaultValue = "0") Long productId) {
		if (productId > 0) {
			model.addAttribute("product", productService.getProductById(productId));
		} else {
			model.addAttribute("product", new Product());
		}
		model.addAttribute("categories", productCategoryService.fetchProductCategoryList());
		return "addproduct";
	}
	
	@GetMapping("/product")
	public String getProduct(Model model, @RequestParam(name="productId",required=true) Long productId) {
		model.addAttribute("product", productService.getProductById(productId));
		return "productdetailpage";
	}
	
	@GetMapping("/admin/products")
	public String getProductList(Model model) {
		model.addAttribute("products", productService.fetchProductList());
		return "product";
	}
	
	@GetMapping("/admin/search/product")
	public String searchProduct(Model model, @RequestParam(name = "key",defaultValue = "") String key) {
		if (key.isBlank()) {
			return "redirect:/admin/products";
		} else {
			model.addAttribute("products", productService.searchProduct(key));
			return "product";
		}		
	}
	
	@GetMapping("/user/product/search")
	public String searchProductHome(Model model, @RequestParam(name = "key",defaultValue = "") String key) {
		if (key.isBlank()) {
			return "redirect:/user/home";
		} else {
			model.addAttribute("products", productService.searchProduct(key));
			return "home";
		}		
	}
	
	@GetMapping("/user/product/searchByBrand")
	public String searchProductByBrand(Model model, @RequestParam(name = "key",required = true) String key) {
		if (key.isBlank()) {
			return "redirect:/user/home";
		} else {
			model.addAttribute("products", productService.fetchProductListByBrand(key));
			return "home";
		}		
	}
	
	@GetMapping(path = "/admin/product/delete")
	public String deleteProduct(@RequestParam(name = "productId",required = true) Long productId ) {
		productService.deleteProduct(productId);

		return "redirect:/admin/products";

	}
	
	
	@PostMapping("/admin/product/save")
	public String save(@ModelAttribute("product") Product product) {
		if (product == null) {
			return "redirect:/admin/product/save";
		}
		Product savedProduct = this.productService.saveProduct(product);
		if (savedProduct!=null) {
			return "redirect:/admin/products";
		} else {
			return "redirect:/admin/product/save";
		}
	}
		
		@GetMapping("/product/list")
		public ResponseEntity<List<Product>> getAllProducts(){
			try {
				List<Product> allProducts = this.productService.fetchProductList();
				return ResponseEntity.ok(allProducts);
			} catch (Exception e) {
				return ResponseEntity.internalServerError().build();
			}
		}				
}
