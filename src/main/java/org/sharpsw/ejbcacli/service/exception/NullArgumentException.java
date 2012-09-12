package org.sharpsw.ejbcacli.service.exception;


public class NullArgumentException extends RuntimeException {
	
	private static final long serialVersionUID = -5893488243946977875L;

	public NullArgumentException() {
	}

	public NullArgumentException(String message) {
		super(message);
	}

	public NullArgumentException(Throwable cause) {
		super(cause);
	}

	public NullArgumentException(String message, Throwable cause) {
		super(message, cause);
	}
}
