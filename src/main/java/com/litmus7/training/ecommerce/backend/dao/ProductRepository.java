package com.litmus7.training.ecommerce.backend.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus7.training.ecommerce.backend.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Page<Product> findByCategoryIdAndActive(Long id, Boolean active, Pageable pageable);

	List<Product> findByActive(Boolean active);
	Page<Product> findByNameContainingAndActive(String name, Boolean active, Pageable pageable);
	// Pageable pageable = PageRequest.of(2, 20,Sort.by("id"));

}
