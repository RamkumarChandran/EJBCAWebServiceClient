package org.sharpsw.ejbcacli.service.exception;


public class InvalidArgumentException extends RuntimeException {

	private static final long serialVersionUID = 1499358926662726884L;
	
	public InvalidArgumentException() {
	}

	public InvalidArgumentException(String message) {
		super(message);
	}

	public InvalidArgumentException(Throwable cause) {
		super(cause);
	}

	public InvalidArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}
