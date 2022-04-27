package com.litmus7.training.ecommerce.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.litmus7.training.ecommerce.backend.dto.OrderDTO;
import com.litmus7.training.ecommerce.backend.entity.Order;
import com.litmus7.training.ecommerce.backend.service.OrderService;

@CrossOrigin(origins = { "http://localhost:3000/", "http://localhost:3001/" })
@RestController
@RequestMapping("api/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("")
	public List<Order> getAllOrders() {
		return orderService.getAllOrders();

	}

	@GetMapping("/{id}")
	public OrderDTO getOrderByUserId(@PathVariable Long id) {
		return orderService.getOrderByUserActive(id);

	}

	@PostMapping("")
	public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {

		return orderService.createOrder(orderDTO);

	}

	@PutMapping("/update")
	public OrderDTO updateOrder(@RequestBody OrderDTO orderDTO) {
		return orderService.updateOrder(orderDTO);

	}

	@PutMapping("/checkout")
	public OrderDTO checkoutOrder(@RequestBody OrderDTO orderDTO) {
		return orderService.checkoutOrder(orderDTO);
	}

	@DeleteMapping("/cancel/{id}")
	public Boolean cancelOrder(@PathVariable Long id) {
		return orderService.cancelOrder(id);

	}

}
