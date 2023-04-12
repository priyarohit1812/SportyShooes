package com.sportyshooes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshooes.model.Admin;
import com.sportyshooes.repository.IAdminRepository;
@Service(value = "adminService")
public class AdminService implements IAdminService {
	@Autowired
	private IAdminRepository adminRepository;
	
	@Override
	public List<Admin> fetchAdminList() {
		return this.adminRepository.findAll();
	}

	@Override
	public Admin saveAdmin(Admin admin) {
		return this.adminRepository.save(admin);
	}

	@Override
	public boolean deleteAdmin(Long adminId) {
		this.adminRepository.deleteById(adminId);
		return !this.adminRepository.existsById(adminId);
	}

	@Override
	public Admin getAdmin(String emailId, String password) {
		Optional<Admin> optAdmin = this.adminRepository.findByEmailAndPassword(emailId, password);
		if (optAdmin !=null && optAdmin.isPresent()) {
			return optAdmin.get();
		} else {
			return null;
		}
	}

	@Override
	public Admin getAdminById(Long adminId) {
		return this.adminRepository.findById(adminId).get();
	}

	@Override
	public List<Admin> searchAdmin(String key) {
		return this.adminRepository.findByFirstNameContainsOrLastNameContainsOrEmailContainsOrMobileContains(key, key, key, key);
	}

}
