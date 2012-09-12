package org.sharpsw.ejbcacli.service.exception;

public class IllegalQueryException extends Exception {

	private static final long serialVersionUID = 2259218709655046827L;

	public IllegalQueryException() {
	}

	public IllegalQueryException(String message) {
		super(message);
	}

	public IllegalQueryException(Throwable cause) {
		super(cause);
	}

	public IllegalQueryException(String message, Throwable cause) {
		super(message, cause);
	}

}
