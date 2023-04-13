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

import com.sportyshooes.model.Admin;
import com.sportyshooes.model.Gender;
import com.sportyshooes.service.AdminService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class AdminController {
	@Autowired
	private AdminService adminService;
	
	private List<Gender> genderList;
	
	public AdminController() {
		this.genderList = new ArrayList<>();
		this.genderList.add(new Gender());
		this.genderList.add(new Gender("Male", "Male"));
		this.genderList.add(new Gender("Female", "Female"));
	}
	
	@GetMapping("/admin")
	public String admin() {		
		return "redirect:/admin/home";
	}
	
	@GetMapping("/admin/home")
	public String adminhome(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin loggedInAdmin = (Admin) session.getAttribute("admin");
		if (loggedInAdmin == null) {
			return "redirect:/admin/login";
		}
		return "adminhome";
	}
	
	@GetMapping("/admin/login")
	public String adminloginform(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Admin loggedInAdmin = (Admin) session.getAttribute("admin");
		if (loggedInAdmin != null) {
			return "redirect:/admin/home";
		}
		return "adminlogin";
	}
	
	@GetMapping("/admin/logout")
	public String adminlogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("admin", null);
		return "redirect:/admin/home";
	}
	
	@GetMapping("/admin/save")
	public String addAdminUser(Model model, @RequestParam(name="userId",required=false, defaultValue = "0") Long userId) {
		if (userId > 0) {
			model.addAttribute("admin", adminService.getAdminById(userId));
		} else {
			model.addAttribute("admin", new Admin());
		}
		model.addAttribute("genderList", this.genderList);
		return "addadmin";
	}
	
	@GetMapping("/admins")
	public String getAdminList(Model model) {
		model.addAttribute("admins", adminService.fetchAdminList());
		return "admin";
	}
	
	@GetMapping("/admin/search")
	public String searchAdmin(Model model, @RequestParam(name = "key",defaultValue = "") String key) {
		if (key.isBlank()) {
			return "redirect:/admins";
		} else {
			model.addAttribute("admins", adminService.searchAdmin(key));
			return "admin";
		}		
	}
	
	@GetMapping(path = "/admin/delete")
	public String deleteAdmin(@RequestParam(name = "userId",required = true) Long userId ) {
		adminService.deleteAdmin(userId);

		return "redirect:/admins";

	}
	
	@PostMapping("/admin/save")
	public String save(@ModelAttribute("admin") Admin admin) {
		if (admin == null) {
			return "redirect:/admin/save";
		}
		Admin savedAdmin = this.adminService.saveAdmin(admin);
		if (savedAdmin != null) {
			return "redirect:/admins";
		} else {
			return "redirect:/admin/save";
		}

	}
	
	@PostMapping("/admin/login")
	public String adminlogin(HttpServletRequest request){
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Admin loggedInAdmin = this.adminService.getAdmin(email, password);
		if (loggedInAdmin!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("admin", loggedInAdmin);
			return "redirect:/admin/home";
		} else {
			return "redirect:/admin/login";
		}
	}
	
	@GetMapping("/admin/list")
	public ResponseEntity<List<Admin>> getAllAdmins(){
		List<Admin> allAdmins = this.adminService.fetchAdminList();
		return ResponseEntity.ok(allAdmins);
	}
	
	@GetMapping("/admin/{adminId}")
	public ResponseEntity<Admin> getAdmin(@PathVariable Long adminId){
		if(adminId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		Admin admin = this.adminService.getAdminById(adminId);
		if (admin!=null) {
			return ResponseEntity.ok(admin);
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@DeleteMapping("/admin/{adminId}")
	public ResponseEntity<?> deleteAdminById(@PathVariable Long adminId) {
		if(adminId <= 0) {
			return ResponseEntity.badRequest().build();
		}
		boolean isDeleted = this.adminService.deleteAdmin(adminId);
		if (isDeleted) {
			return ResponseEntity.ok("Admin deleted Succcessfully");
		} else {
			return ResponseEntity.internalServerError().build();
		}
	}
}
