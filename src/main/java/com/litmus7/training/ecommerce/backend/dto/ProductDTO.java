package com.litmus7.training.ecommerce.backend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class ProductDTO {

	@NotEmpty(message = "Product name validation failed. Must contain atleast 3 letters for name")
	@Size(min = 3, message = "Product name validation failed. Must contain atleast 3 letters for name")
	private String name;
	@NotEmpty(message = "Product description validation failed. Must contain atleast 10 letters for description")
	@Size(min = 10, message = "Product description validation failed. Must contain atleast 10 letters for description")
	private String description;
	@NotEmpty(message = "Image url validation failed. It cannot be empty")
	private String imageUrl;
	@Positive(message = "categoryid cannot be empty")
	private Long categoryId;

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

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public ProductDTO() {
		super();
	}





}


