package org.sharpsw.ejbcacli.service.exception;

public class InvalidResponseTypeException extends Exception {
	private static final long serialVersionUID = 359565491307450847L;

	public InvalidResponseTypeException() {
	}

	public InvalidResponseTypeException(String message) {
		super(message);
	}

	public InvalidResponseTypeException(Throwable cause) {
		super(cause);
	}

	public InvalidResponseTypeException(String message, Throwable cause) {
		super(message, cause);
	}

}
