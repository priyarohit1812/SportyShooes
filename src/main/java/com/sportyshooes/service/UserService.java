package com.sportyshooes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportyshooes.model.User;
import com.sportyshooes.repository.IUserRepository;
@Service(value = "userService")
public class UserService implements IUserService {
	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public List<User> fetchUserList() {
		try {
			return this.userRepository.findAll();
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public User saveUser(User user) {
		return this.userRepository.save(user);
	}

	@Override
	public boolean deleteUser(Long userId) {
		this.userRepository.deleteById(userId);
		return !this.userRepository.existsById(userId);
		
	}

	@Override
	public User getUser(String email, String password) {
		Optional<User> optUser = this.userRepository.findByEmailAndPassword(email, password);
		if (optUser!= null && optUser.isPresent()) {
			return optUser.get();
		}

		return null;
	}

	@Override
	public User getUserById(Long userId) {
		return this.userRepository.findById(userId).get();
	}

}
