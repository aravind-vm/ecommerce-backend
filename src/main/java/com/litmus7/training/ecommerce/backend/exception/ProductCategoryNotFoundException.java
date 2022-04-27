package com.litmus7.training.ecommerce.backend.exception;

public class ProductCategoryNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductCategoryNotFoundException() {
		super();

	}

	public ProductCategoryNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public ProductCategoryNotFoundException(String message) {
		super(message);
	}


}
