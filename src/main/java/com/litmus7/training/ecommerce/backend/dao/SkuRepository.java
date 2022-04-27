package com.litmus7.training.ecommerce.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus7.training.ecommerce.backend.entity.Sku;

public interface SkuRepository extends JpaRepository<Sku, Long> {

	List<Sku> findByProductIdAndActive(Long id, Boolean active);

	List<Sku> findByActive(Boolean active);
}
