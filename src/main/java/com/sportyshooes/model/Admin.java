package com.sportyshooes.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ss_user")
@DiscriminatorValue("Admin")
public class Admin extends User {
	public Admin() {
		super();
	}
}
