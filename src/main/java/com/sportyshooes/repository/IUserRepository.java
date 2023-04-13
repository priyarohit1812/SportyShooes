package com.sportyshooes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshooes.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmailAndPassword(String email, String password);

}
