package com.sportyshooes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.sportyshooes.enums.PaymentStatus;
import com.sportyshooes.enums.PaymentType;
import com.sportyshooes.model.Address;
import com.sportyshooes.model.Cart;
import com.sportyshooes.model.Payment;
import com.sportyshooes.model.PurchaseOrder;
import com.sportyshooes.model.User;
import com.sportyshooes.model.requests.PaymentRequest;
import com.sportyshooes.service.AddressService;
import com.sportyshooes.service.CartService;
import com.sportyshooes.service.OrderItemService;
import com.sportyshooes.service.PaymentService;
import com.sportyshooes.service.PurchaseOrderService;
import com.sportyshooes.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class PurchaseOrderController {
	@Autowired
	private PurchaseOrderService purchaseOrderService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderItemService orderItemService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private UserService userService;

	@GetMapping("/admin/purchaseorders")
	public String getPurchaseOrderList(Model model) {
		model.addAttribute("orders", purchaseOrderService.fetchPurchaseOrderList());
		return "purchaseorder";
	}

	@GetMapping("/user/order/selectAddress")
	public String showAddresses(Model model, @RequestParam(name = "cartId", required = true) Long cartId,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		model.addAttribute("addresses", this.addressService.fetchAddressListByUser(user.getUserId()));
		model.addAttribute("selectable", true);
		model.addAttribute("cartId", cartId);
		return "addresses";
	}

	@GetMapping("/user/order/payment")
	public String showPaymentPage(Model model, @RequestParam(name = "cartId", required = true) Long cartId,
			@RequestParam(name = "addressId", required = true) Long addressId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		model.addAttribute("paymentRequest", new PaymentRequest());
		model.addAttribute("cartId", cartId);
		model.addAttribute("addressId", addressId);
		return "paymentdetails";
	}

	@GetMapping("/user/order/details")
	public String showOrderDetails(Model model, @RequestParam(name = "orderId", required = true) Long orderId,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		PurchaseOrder order = this.purchaseOrderService.getPurchaseorder(orderId);
		model.addAttribute("order", order);
		model.addAttribute("orderItems", this.orderItemService.fetchOrderItemsByCart(order.getCart().getCartId()));
		return "orderdetails";
	}

	@PostMapping("/user/order/save")
	public String orderSave(Model model, @RequestParam(name = "cartId", required = true) Long cartId,
			@RequestParam(name = "addressId", required = true) Long addressId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		String referer = request.getHeader("Referer");
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "redirect:/user/login";
		}
		Cart cart = this.cartService.getCart(cartId);
		Address address = this.addressService.getAddress(addressId);
		PaymentRequest paymentRequest = (PaymentRequest) model.getAttribute("paymentRequest");
		Payment payment = new Payment();
		if (paymentRequest != null) {
			payment.setPaymentType(paymentRequest.getPaymentType());
		} else {
			payment.setPaymentType(PaymentType.CashOnDelivery);
		}
		payment.setPaymentStatus(PaymentStatus.Captured);
		if (payment.getPaymentType() == PaymentType.CashOnDelivery) {
			payment.setRemark("Customer opted for payment on delivery");
		} else {
			String last4Digits = "";
			if (paymentRequest.getCard() != null) {
				String cardNumber = paymentRequest.getCard().getCardNumber();
				if (cardNumber != null && !cardNumber.isBlank() && cardNumber.trim().length() > 4) {
					last4Digits = cardNumber.substring(cardNumber.length() - 4);
				}
			}

			if (!last4Digits.isBlank()) {
				payment.setRemark("Payment done by card ending with " + last4Digits);
			} else {
				payment.setRemark("Payment done by card");
			}
		}
		Payment savedPayment = paymentService.savePayment(payment);
		if (savedPayment != null) {
			PurchaseOrder order = new PurchaseOrder();
			order.setCart(cart);
			order.setAddress(address);
			order.setPayment(savedPayment);

			PurchaseOrder savedOrder = this.purchaseOrderService.savePurchaseOrder(order);
			if (savedOrder != null) {
				user.setCart(null);
				this.userService.saveUser(user);
				return "redirect:/user/order/details?orderId=" + savedOrder.getPurchaseOrderId();
			}
		}

		return "redirect:" + referer;
	}

	@PostMapping("/purchaseOrder/save")
	public ResponseEntity<PurchaseOrder> save(@Valid @RequestBody PurchaseOrder purchaseOrder) {
		if (purchaseOrder == null) {
			return ResponseEntity.badRequest().build();
		}
		PurchaseOrder savedPurchaseOrder = this.purchaseOrderService.savePurchaseOrder(purchaseOrder);
		if (savedPurchaseOrder != null) {
			return ResponseEntity.ok(savedPurchaseOrder);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/purchaseOrder/list")
	public ResponseEntity<List<PurchaseOrder>> getAllPurchaseOrders() {
		try {
			List<PurchaseOrder> allPurchaseOrders = this.purchaseOrderService.fetchPurchaseOrderList();
			return ResponseEntity.ok(allPurchaseOrders);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/purchaseOrder/{purchaseOrderId}")
	public ResponseEntity<PurchaseOrder> getPurchaseOrder(@PathVariable Long purchaseOrderId) {
		if (purchaseOrderId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		PurchaseOrder purchaseOrder = this.purchaseOrderService.getPurchaseorder(purchaseOrderId);
		if (purchaseOrder != null) {
			return ResponseEntity.ok(purchaseOrder);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("/purchaseOrder/{purchaseOrderId}")
	public ResponseEntity<?> deletePurchaseOrder(@PathVariable Long purchaseOrderId) {
		if (purchaseOrderId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		boolean isDeleted = this.purchaseOrderService.deletePurchaseOrder(purchaseOrderId);
		if (isDeleted) {
			return ResponseEntity.ok("PurchaseOrder deleted Succcessfully");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
}
