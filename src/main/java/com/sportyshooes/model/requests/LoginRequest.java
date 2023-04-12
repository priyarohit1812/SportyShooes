package com.sportyshooes.model.requests;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	@NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "Password is mandatory")
	private String password;
	private String userRole;
	
	public LoginRequest() {
		this(null,null,null);
	}
	
	

	public LoginRequest(String email, String password, String userRole) {
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
}
