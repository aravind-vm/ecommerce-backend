package com.litmus7.training.ecommerce.backend.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "order_item")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;
	private BigDecimal unitPrice;
	private Integer quantity;
	private Long sku_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrder() {
		return order.getId();
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal bigDecimal) {
		this.unitPrice = bigDecimal;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getSku_id() {
		return sku_id;
	}

	public void setSku_id(Long sku_id) {
		this.sku_id = sku_id;
	}

	public OrderItem() {
		super();

	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", order=" + order.getId() + ", unitPrice=" + unitPrice + ", quantity="
				+ quantity + ", sku_id=" + sku_id + "]";
	}

}
