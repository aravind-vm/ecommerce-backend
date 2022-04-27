package com.litmus7.training.ecommerce.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus7.training.ecommerce.backend.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public Order findByUserIdAndStatus(Long id, int status);

}
