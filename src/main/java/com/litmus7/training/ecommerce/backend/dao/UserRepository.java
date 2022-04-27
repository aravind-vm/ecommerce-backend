package com.litmus7.training.ecommerce.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus7.training.ecommerce.backend.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
	AppUser findByEmail(String email);

}
