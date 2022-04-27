package com.litmus7.training.ecommerce.backend.exception;

public class ProductNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProductNotFoundException() {
		super();

	}

	public ProductNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public ProductNotFoundException(String message) {
		super(message);
	}


}
