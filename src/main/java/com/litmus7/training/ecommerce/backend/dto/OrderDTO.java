package com.litmus7.training.ecommerce.backend.dto;

import java.math.BigDecimal;
import java.util.Set;

public class OrderDTO {
	private Long orderId;
	private OrderItemDTO item;
	private Set<OrderItemDTO> items;
	private BigDecimal totalPrice;
	private int orderStatus;
	private Long userId;
	private Long deliveryAddressId;

	public Set<OrderItemDTO> getItems() {
		return items;
	}

	public void setItems(Set<OrderItemDTO> items) {
		this.items = items;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public OrderItemDTO getItem() {
		return item;
	}

	public void setItem(OrderItemDTO item) {
		this.item = item;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getDeliveryAddressId() {
		return deliveryAddressId;
	}

	public void setDeliveryAddressId(Long deliveryAddressId) {
		this.deliveryAddressId = deliveryAddressId;
	}

	public OrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "OrderDTO [orderId=" + orderId + ", item=" + item + ", items=" + items + ", totalPrice=" + totalPrice
				+ ", orderStatus=" + orderStatus + ", userId=" + userId + ", deliveryAddressId=" + deliveryAddressId
				+ "]";
	}


}
