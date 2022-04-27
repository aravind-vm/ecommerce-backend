package com.litmus7.training.ecommerce.backend.exception;

public class SkuNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SkuNotFoundException() {
		super();

	}

	public SkuNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public SkuNotFoundException(String message) {
		super(message);
	}


}
