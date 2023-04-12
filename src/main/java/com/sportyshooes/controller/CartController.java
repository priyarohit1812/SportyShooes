package com.sportyshooes.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshooes.model.Cart;
import com.sportyshooes.model.OrderItem;
import com.sportyshooes.model.Product;
import com.sportyshooes.model.User;
import com.sportyshooes.service.CartService;
import com.sportyshooes.service.OrderItemService;
import com.sportyshooes.service.ProductService;
import com.sportyshooes.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {
	@Autowired
	private UserService userService;
	@Autowired
	private CartService cartService;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderItemService orderItemService;

	@GetMapping("/user/cart")
	public String cart(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/home";
		}
		Cart cart = user.getCart();
		List<OrderItem> orderItems = new ArrayList<>();
		if (cart != null) {
			orderItems = this.orderItemService.fetchOrderItemsByCart(cart.getCartId());
		}

		model.addAttribute("cart", cart);
		model.addAttribute("orderItems", orderItems);
		return "cart";
	}

	@GetMapping("/cart/add")
	public String addItem(HttpServletRequest request,
			@RequestParam(name = "productId", required = true) Long productId) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/home";
		}
		Cart cart = user.getCart();

		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);
			cart = this.cartService.saveCart(cart);
			if (cart != null) {
				user.setCart(cart);
				user = this.userService.saveUser(user);
			}
		}
			
		List<OrderItem> orderItems = this.orderItemService.fetchOrderItemsByCart(cart.getCartId());
		
		int totalQuantity = 0;
		double totalCartPrice = 0.0;
		Product product = this.productService.getProductById(productId);
		boolean existingOrderItem = false;
		for (OrderItem orderItem : orderItems) {
			if (orderItem.getProduct().getProductId() == productId) {
				int quantity = orderItem.getQuantity() + 1;
				double price = orderItem.getProduct().getPrice() * quantity;
				orderItem.setQuantity(quantity);
				orderItem.setTotalPrice(price);
				this.orderItemService.saveOrderItem(orderItem);
				existingOrderItem = true;
			}
			totalQuantity = totalQuantity + orderItem.getQuantity();
			totalCartPrice = totalCartPrice + orderItem.getTotalPrice();
		}

		if (!existingOrderItem) {
			OrderItem orderItem = new OrderItem(0L, 1, product.getPrice(), product);
			orderItem.setCart(cart);
			OrderItem savedOrderItem = this.orderItemService.saveOrderItem(orderItem);
			totalQuantity = totalQuantity + savedOrderItem.getQuantity();
			totalCartPrice = totalCartPrice + savedOrderItem.getTotalPrice();
		}

		cart.setNoOfItems(totalQuantity);
		cart.setTotalCartPrice(totalCartPrice);

		Cart savedCart = this.cartService.saveCart(cart);
		if (savedCart != null) {
			session.setAttribute("user", savedCart.getUser());
		}
		return "redirect:/product?productId=" + productId;
	}

	@GetMapping("/cart/remove")
	public String removeItem(HttpServletRequest request,
			@RequestParam(name = "productId", required = true) Long productId) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/home";
		}
		Cart cart = user.getCart();
		List<OrderItem> orderItems = this.orderItemService.fetchOrderItemsByCart(cart.getCartId());

		
		int totalQuantity = cart.getNoOfItems();
		double totalCartPrice = cart.getTotalCartPrice();
		OrderItem selectedItem = null;
		for (OrderItem orderItem : orderItems) {
			if (orderItem.getProduct().getProductId() == productId) {
				selectedItem = orderItem;
				totalQuantity = totalQuantity - orderItem.getQuantity();
				totalCartPrice = totalCartPrice - orderItem.getTotalPrice();
				break;
			}
		}
		boolean orderItemDeleted = false;
		if (selectedItem != null) {
			orderItemDeleted = this.orderItemService.deleteOrderItem(selectedItem.getOrderItemId());
		}

		cart.setNoOfItems(totalQuantity);
		cart.setTotalCartPrice(totalCartPrice);

		if (orderItemDeleted) {
			List<OrderItem> updatedOrderItems = this.orderItemService.fetchOrderItemsByCart(cart.getCartId());
			if (updatedOrderItems.isEmpty()) {
				user.setCart(null);
				User savedUser = this.userService.saveUser(user);
				if (savedUser != null) {
					this.cartService.deleteCart(cart.getCartId());	
					session.setAttribute("user", savedUser);
				}
				return "redirect:/user/home";
			} else {
				this.cartService.saveCart(cart);
				return "redirect:/user/cart";
			}
		}
		return "redirect:/user/cart";
	}

}
