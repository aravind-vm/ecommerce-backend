package com.litmus7.training.ecommerce.backend.service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litmus7.training.ecommerce.backend.dao.OrderItemRepository;
import com.litmus7.training.ecommerce.backend.dao.OrderRepository;
import com.litmus7.training.ecommerce.backend.dto.OrderDTO;
import com.litmus7.training.ecommerce.backend.dto.OrderItemDTO;
import com.litmus7.training.ecommerce.backend.entity.Order;
import com.litmus7.training.ecommerce.backend.entity.OrderItem;
import com.litmus7.training.ecommerce.backend.entity.Sku;

@Service

public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private SkuServiceImpl skuService;
	@Autowired
	private ProductService productService;
	@Autowired
	private PaymentServiceImpl paymentServiceImpl;
	@Autowired
	private AppUserService appUserService;
	@Override
	public List<Order> getAllOrders() {

		return orderRepository.findAll();
	}

	@Override
	public Order getOrder(Long id) {

		return orderRepository.getById(id);
	}

	@Override
	public OrderDTO getOrderByUserActive(Long id) {

		Order order = orderRepository.findByUserIdAndStatus(id, 1);
		if (order == null) {
			order = orderRepository.findByUserIdAndStatus(id, 2);
		}
		OrderDTO orderDTO = new OrderDTO();
		if (order != null) {
			orderDTO.setOrderStatus(order.getStatus());
			orderDTO.setUserId(id);
			orderDTO.setOrderId(order.getId());
			orderDTO.setTotalPrice(order.getTotalprice());
			Set<OrderItemDTO> itemsDTO = new HashSet<OrderItemDTO>();
			Set<OrderItem> items = order.getItems();
			for (OrderItem item : items) {
				OrderItemDTO itemDTO = new OrderItemDTO();
				itemDTO.setQuantity(item.getQuantity());
				itemDTO.setFullfilledStockQty(item.getQuantity());
				itemDTO.setSkuId(item.getSku_id());
				itemDTO.setStockStatus(1);
				Sku sku = skuService.getSkuById(item.getSku_id()).get();
				String name = productService.getProductById(sku.getProduct()).get().getName() + " " + sku.getColour()
						+ " " + sku.getRam() + "GB " + sku.getStorage() + "GB";
				BigDecimal unitPrice = skuService.getSkuById(itemDTO.getSkuId()).get().getUnitPrice();
				itemDTO.setName(name);
				itemDTO.setUnitPrice(unitPrice);
				itemsDTO.add(itemDTO);
			}
			orderDTO.setItems(itemsDTO);

		}
		return orderDTO;
	}

	@Override
	public OrderDTO createOrder(OrderDTO orderDTO) {

		OrderItemDTO orderItemDTO = skuService.updateSkuQuantityOnNewItem(orderDTO.getItem());
		if (orderItemDTO.getStockStatus() == 2) {
			orderDTO.setItem(orderItemDTO);
			return orderDTO;
		} else {

			Order order = new Order();
			OrderItem item = new OrderItem();
			item.setQuantity(orderItemDTO.getQuantity());
			item.setSku_id(orderItemDTO.getSkuId());
			item.setUnitPrice(skuService.getSkuById(orderItemDTO.getSkuId()).get().getUnitPrice());
			// Set<OrderItem> items = new HashSet<OrderItem>();
			order.add(item);
			// order.setItems(items);


			order.calculateTotalPrice();
			order.setUser(appUserService.getUser(orderDTO.getUserId()));
			order.setStatus(1);

			order = orderRepository.save(order);
			orderDTO.setOrderId(order.getId());
			orderDTO.setTotalPrice(order.getTotalprice());
			orderDTO.setItem(orderItemDTO);
			orderDTO.setOrderStatus(1);

			return orderDTO;

		}

	}

	@Override
	public OrderDTO updateOrder(OrderDTO orderDTO) {
		System.out.println(orderDTO);
		Order order=getOrder(orderDTO.getOrderId());
		OrderItemDTO orderItemDTO = orderDTO.getItem();
		Set<OrderItem> orderItems = order.getItems();
		Optional<OrderItem> orderItemO = orderItems.stream().filter(i -> i.getSku_id() == orderItemDTO.getSkuId())
				.findFirst();
		if (orderItemO.isPresent()) {
			// old item
			OrderItem orderItem = orderItemO.get();
			if (orderItemDTO.getQuantity() > orderItem.getQuantity()) {
				// increase quantity
				OrderItemDTO updtdOrderItemDTO = skuService.updateSkuQuantityOnOldItems(orderItemDTO);
				if (updtdOrderItemDTO.getStockStatus() == 1) {
					orderItems.forEach(item -> {
						if (item.getId() == orderItem.getId()) {
							item.setQuantity(orderItem.getQuantity() + 1);
						}
					});
					order.setItems(orderItems);
					order.calculateTotalPrice();
					orderRepository.save(order);
				}
				orderDTO.setItem(updtdOrderItemDTO);
				orderDTO.setTotalPrice(order.getTotalprice());
				return orderDTO;

			} else {
				// decrease quantity
				OrderItemDTO updtdOrderItemDTO = skuService.updateSkuQuantityOnOldItemsMinus(orderItemDTO);

				if (orderItem.getQuantity() - 1 == 0) {
					// remove item
					orderItems.remove(orderItem);
					order.setItems(orderItems);
					order.calculateTotalPrice();
					orderItemRepository.delete(orderItem);
					orderRepository.save(order);
					orderDTO.setItem(updtdOrderItemDTO);
					orderDTO.setTotalPrice(order.getTotalprice());
					return orderDTO;
				}
				orderItems.forEach(item -> {
					if (item.getId() == orderItem.getId()) {
						item.setQuantity(orderItem.getQuantity() - 1);
					}
				});
				order.setItems(orderItems);
				order.calculateTotalPrice();
				orderRepository.save(order);
				orderDTO.setTotalPrice(order.getTotalprice());
				orderDTO.setItem(updtdOrderItemDTO);
				return orderDTO;

			}

		} else {
			// new item
			OrderItemDTO newOrderItemDTO = skuService.updateSkuQuantityOnNewItem(orderItemDTO);
			if (newOrderItemDTO.getStockStatus() == 1) {
				
				OrderItem neworderItem = new OrderItem();

				neworderItem.setQuantity(1);
				neworderItem.setSku_id(newOrderItemDTO.getSkuId());
				neworderItem.setUnitPrice(skuService.getSkuById(newOrderItemDTO.getSkuId()).get().getUnitPrice());
				order.add(neworderItem);
				order.calculateTotalPrice();
				orderRepository.save(order);

				orderDTO.setTotalPrice(order.getTotalprice());
				orderDTO.setItem(newOrderItemDTO);
				return orderDTO;

			}
		}
		
		return null;
	}

	@Override
	@Transactional
	public OrderDTO checkoutOrder(OrderDTO orderDTO) {
		Order order = getOrder(orderDTO.getOrderId());
		order.setDelivery(orderDTO.getDeliveryAddressId());
		try {
			if (paymentServiceImpl.doPayement()) {
				order.setStatus(4);// success
				orderDTO.setOrderStatus(order.getStatus());
				skuService.skuCheckout(order.getItems());

			}
		} catch (Exception e) {
			order.setStatus(2);// failed
			orderDTO.setOrderStatus(order.getStatus());
		}
	return orderDTO;

	}

	@Override
	public Boolean cancelOrder(Long id) {
		Order order = getOrder(id);
		skuService.skuCheckoutCancel(order.getItems());
		order.setStatus(3);// cancelled
		orderRepository.save(order);
		return true;
	}



}
