package com.litmus7.training.ecommerce.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus7.training.ecommerce.backend.entity.UserCredentials;

public interface UserCredentialsRepository extends JpaRepository<UserCredentials, Long> {
	UserCredentials findByUsername(String username);

}