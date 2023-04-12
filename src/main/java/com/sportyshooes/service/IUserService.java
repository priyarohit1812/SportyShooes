package com.sportyshooes.service;

import java.util.List;

import com.sportyshooes.model.User;

public interface IUserService {
	public List<User> fetchUserList();
	public User saveUser(User user);
	public boolean deleteUser(Long userId);
	public User getUser(String emailId, String password);
	public User getUserById(Long userId);
	
}
