package com.sportyshooes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshooes.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	List<User> findByEmailAndPassword(String email, String password);

}
