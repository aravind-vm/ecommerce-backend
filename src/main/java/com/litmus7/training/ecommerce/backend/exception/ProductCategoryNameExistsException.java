package com.litmus7.training.ecommerce.backend.exception;

public class ProductCategoryNameExistsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductCategoryNameExistsException() {
		super();

	}

	public ProductCategoryNameExistsException(String message, Throwable cause) {
		super(message, cause);

	}

	public ProductCategoryNameExistsException(String message) {
		super(message);
	}



}
