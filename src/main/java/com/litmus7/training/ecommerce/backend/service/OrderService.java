package com.litmus7.training.ecommerce.backend.service;

import java.util.List;

import com.litmus7.training.ecommerce.backend.dto.OrderDTO;
import com.litmus7.training.ecommerce.backend.entity.Order;

public interface OrderService {
	public List<Order> getAllOrders();

	public OrderDTO createOrder(OrderDTO orderDTO);

	public OrderDTO updateOrder(OrderDTO orderDTO);

	public OrderDTO checkoutOrder(OrderDTO orderDTO);

	public Order getOrder(Long id);

	public Boolean cancelOrder(Long id);

	public OrderDTO getOrderByUserActive(Long id);

}
