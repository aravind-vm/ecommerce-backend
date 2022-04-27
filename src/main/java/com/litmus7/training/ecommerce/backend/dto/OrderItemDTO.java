package com.litmus7.training.ecommerce.backend.dto;

import java.math.BigDecimal;

public class OrderItemDTO {
	private Long skuId;
	private int quantity;
	private int stockStatus;
	private Integer fullfilledStockQty;
	private String name;
	private BigDecimal unitPrice;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStockStatus() {
		return stockStatus;
	}

	public void setStockStatus(int stockStatus) {
		this.stockStatus = stockStatus;
	}

	public Integer getFullfilledStockQty() {
		return fullfilledStockQty;
	}

	public void setFullfilledStockQty(Integer fullfilledStockQty) {
		this.fullfilledStockQty = fullfilledStockQty;
	}

	public Long getSkuId() {
		return skuId;
	}

	public void setSkuId(Long skuId) {
		this.skuId = skuId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public OrderItemDTO() {
		super();

	}

	@Override
	public String toString() {
		return "OrderItemDTO [skuId=" + skuId + ", quantity=" + quantity + ", stockStatus=" + stockStatus
				+ ", fullfilledStockQty=" + fullfilledStockQty + ", name=" + name + ", unitPrice=" + unitPrice + "]";
	}



}
