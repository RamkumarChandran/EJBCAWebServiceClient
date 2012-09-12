package org.sharpsw.ejbcacli.service.exception;

public class UserAlreadyRevokedException extends Exception {
	private static final long serialVersionUID = -8144419863808089589L;

	public UserAlreadyRevokedException() {
	}

	public UserAlreadyRevokedException(String message) {
		super(message);
	}

	public UserAlreadyRevokedException(Throwable cause) {
		super(cause);
	}

	public UserAlreadyRevokedException(String message, Throwable cause) {
		super(message, cause);
	}

}
