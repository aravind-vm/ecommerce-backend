package com.litmus7.training.ecommerce.backend.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "product")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private ProductCategory category;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private Set<Sku> skus;

	@Column(name = "name")
	@NotEmpty(message = "Product name validation failed. Must contain atleast 3 letters for name")
	@Size(min = 3, message = "Product name validation failed. Must contain atleast 3 letters for name")
	private String name;

	@NotEmpty(message = "Product description validation failed. Must contain atleast 10 letters for description")
	@Size(min = 10, message = "Product description validation failed. Must contain atleast 10 letters for description")
	@Column(name = "description")
	private String description;

	@NotEmpty(message = "Image url validation failed. It cannot be empty")
	@Column(name = "image_url")
	private String imageUrl;

	@Column(name = "active")
	private boolean active;

	@Column(name = "date_created")
	@CreationTimestamp
	private Date dateCreated;

	@Column(name = "last_updated")
	@UpdateTimestamp
	private Date lastUpdated;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategory() {
		return category.getId();
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	public Set<Sku> getSkus() {
		return skus;
	}

	public void setSkus(Set<Sku> skus) {
		this.skus = skus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public Product() {
		super();

	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", skus=" + skus + ", name=" + name + ", description="
				+ description + ", imageUrl=" + imageUrl + ", active=" + active + ", dateCreated=" + dateCreated
				+ ", lastUpdated=" + lastUpdated + "]";
	}


}


