package org.sharpsw.ejbcacli.service.exception;

public class ServiceCreationException extends Exception {	
	private static final long serialVersionUID = -8047016955609219147L;

	public ServiceCreationException() {
	}

	public ServiceCreationException(String message) {
		super(message);
	}

	public ServiceCreationException(Throwable cause) {
		super(cause);
	}

	public ServiceCreationException(String message, Throwable cause) {
		super(message, cause);
	}	

}
