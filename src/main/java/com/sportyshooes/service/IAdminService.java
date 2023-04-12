package com.sportyshooes.service;

import java.util.List;

import com.sportyshooes.model.Admin;

public interface IAdminService {
	public List<Admin> fetchAdminList();
	public List<Admin> searchAdmin(String key);
	public Admin saveAdmin(Admin admin);
	public boolean deleteAdmin(Long adminId);
	public Admin getAdmin(String emailId, String password);
	public Admin getAdminById(Long adminId);
	
}
