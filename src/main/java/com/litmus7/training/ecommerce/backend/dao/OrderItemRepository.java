package com.litmus7.training.ecommerce.backend.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.litmus7.training.ecommerce.backend.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
