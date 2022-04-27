package com.litmus7.training.ecommerce.backend.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "SKU")
public class Sku {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "unit_price")
	private BigDecimal unitPrice;

	@Column(name = "image_url")
	private String imageUrl;

	private String colour;
	private String ram;
	private String storage;

	private Long stock_quantity;
	private Long reserved_quantity;
	private Long available_quantity;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;

	@Column(name = "active")
	private boolean active;


	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
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

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Long getProduct() {
		return product.getId();
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "Sku [id=" + id + ", unitPrice=" + unitPrice + ", imageUrl=" + imageUrl + ", colour=" + colour + ", ram="
				+ ram + ", storage=" + storage + ", stock_quantity=" + stock_quantity + ", reserved_quantity="
				+ reserved_quantity + ", available_quantity=" + available_quantity + ", dateCreated=" + dateCreated
				+ ", lastUpdated=" + lastUpdated + ", active=" + active + ", product=" + product + "]";
	}

}
