package org.sharpsw.ejbcacli.service.exception;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 6307302831676144653L;

	public UserNotFoundException() {
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
