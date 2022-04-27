package com.litmus7.training.ecommerce.backend.dto;

import java.math.BigDecimal;


public class SkuDTO {

	private Long id;
	private BigDecimal unitPrice;
	private String imageUrl;
	private String colour;
	private String ram;
	private String storage;
	private Long stock_quantity;
	private Long reserved_quantity;
	private Long available_quantity;
	private Long productId;
	private Boolean active;

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public Long getStock_quantity() {
		return stock_quantity;
	}

	public void setStock_quantity(Long stock_quantity) {
		this.stock_quantity = stock_quantity;
	}

	public Long getReserved_quantity() {
		return reserved_quantity;
	}

	public void setReserved_quantity(Long reserved_quantity) {
		this.reserved_quantity = reserved_quantity;
	}

	public Long getAvailable_quantity() {
		return available_quantity;
	}

	public void setAvailable_quantity(Long available_quantity) {
		this.available_quantity = available_quantity;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public SkuDTO() {
		super();
	}


}
