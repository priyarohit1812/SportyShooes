package com.sportyshooes.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.sportyshooes.model.Cart;
import com.sportyshooes.model.Gender;
import com.sportyshooes.model.User;
import com.sportyshooes.service.AddressService;
import com.sportyshooes.service.CartService;
import com.sportyshooes.service.ProductCategoryService;
import com.sportyshooes.service.ProductService;
import com.sportyshooes.service.PurchaseOrderService;
import com.sportyshooes.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;
	@Autowired
	private CartService cartService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private PurchaseOrderService purchaseOrderService;

	private List<Gender> genderList;

	public UserController() {
		this.genderList = new ArrayList<>();
		this.genderList.add(new Gender());
		this.genderList.add(new Gender("Male", "Male"));
		this.genderList.add(new Gender("Female", "Female"));
	}

	@GetMapping("/user/home")
	public String home(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		model.addAttribute("products", productService.fetchProductList());
		session.setAttribute("targetGroups", this.productCategoryService.getTargetGroups());
		session.setAttribute("categoryNames", this.productCategoryService.getCategoryNames());
		session.setAttribute("brands", this.productService.getBrands());
		return "home";
	}

	@GetMapping("/")
	public String roothome() {
		return "redirect:/user/home";
	}

	@GetMapping("/user/login")
	public String userloginPage(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return "redirect:/user/home";
		}
		return "login";
	}

	@GetMapping("/user/update")
	public String updateUser(Model model, @RequestParam(name = "userId", required = true) Long userId) {
		User user = this.userService.getUserById(userId);
		model.addAttribute("user", user);
		model.addAttribute("addresses", this.addressService.fetchAddressListByUser(user.getUserId()));
		model.addAttribute("orders", this.purchaseOrderService.getAllPurchaseOrderByUserId(user.getUserId()));
		model.addAttribute("selectable", false);
		model.addAttribute("genderList", this.genderList);
		return "userprofile";
	}

	@GetMapping("/user/register")
	public String registerUser(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("genderList", this.genderList);
		return "register";
	}

	@PostMapping("/user/save")
	public String save(@ModelAttribute("user") User user, HttpServletRequest request) {
		if (user == null) {
			return "redirect:/user/home";
		}
		HttpSession session = request.getSession();
		boolean isUpdate = user.getUserId() > 0;

		User savedUser = this.userService.saveUser(user);
		if (savedUser != null) {
			Cart cart = savedUser.getCart();
			if (cart == null) {
				cart = new Cart();
				cart.setUser(savedUser);
				Cart savedCart = this.cartService.saveCart(cart);
				if (savedCart != null) {
					savedUser.setCart(savedCart);
					savedUser = this.userService.saveUser(savedUser);
				}
			}
			session.setAttribute("user", savedUser);
			if (isUpdate) {
				return "redirect:/user/update?userId="+savedUser.getUserId();
			}
			return "redirect:/user/home";
		} else {
			if (isUpdate) {
				return "redirect:/user/update?userId="+user.getUserId();
			}
			return "redirect:/user/register";
		}

	}

	@PostMapping("/user/login")
	public String userlogin(HttpServletRequest request) {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User loggedInUser = this.userService.getUser(email, password);
		if (loggedInUser != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", loggedInUser);
			return "redirect:/user/home";

		} else {
			return "redirect:/user/login";
		}
	}

	@GetMapping("/user/logout")
	public String logout(HttpServletRequest request) {

		HttpSession session = request.getSession();
		session.setAttribute("user", null);

		return "redirect:/user/home";

	}

	@GetMapping("/user/list")
	public ResponseEntity<List<User>> getAllUsers() {
		try {
			List<User> allUsers = this.userService.fetchUserList();
			return ResponseEntity.ok(allUsers);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUser(@PathVariable Long userId) {
		if (userId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		User user = this.userService.getUserById(userId);
		if (user != null) {
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
		if (userId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		boolean isDeleted = this.userService.deleteUser(userId);
		if (isDeleted) {
			return ResponseEntity.ok("User deleted Succcessfully");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
}
