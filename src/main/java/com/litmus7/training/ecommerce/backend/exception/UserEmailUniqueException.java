package com.litmus7.training.ecommerce.backend.exception;

public class UserEmailUniqueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UserEmailUniqueException() {
		super();

	}

	public UserEmailUniqueException(String message, Throwable cause) {
		super(message, cause);

	}

	public UserEmailUniqueException(String message) {
		super(message);
}

}