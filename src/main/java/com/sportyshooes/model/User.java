package com.sportyshooes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ss_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "User_Role")
@DiscriminatorValue("Customer")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	@NotBlank(message = "Email is mandatory")
	private String email;
	@NotBlank(message = "First Name is mandatory")
	private String firstName;
	@NotBlank(message = "Last Name is mandatory")
	private String lastName;
	@NotBlank(message = "Mobile is mandatory")
	private String mobile;
	@NotBlank(message = "Password is mandatory")
	private String password;
	@NotBlank(message = "Gender is mandatory")
	private String gender;
	@Temporal(TemporalType.DATE)
	@NotBlank(message = "Date of Birth is mandatory")
	private String dateOfBirth;
		
	@OneToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "cartId_fk")
	@JsonIgnoreProperties("user")
	private Cart cart;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public User(Long userId, String email, String firstName, String lastName, String mobile, String password,
			String gender, String dateOfBirth) {
		super();
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.password = password;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public User() {
		this((long) 0,"","","","","","","");
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobile=" + mobile + ", password=" + password + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", cart=" + cart + "]";
	}
	
	
}	
	
	