package com.litmus7.training.ecommerce.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus7.training.ecommerce.backend.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
