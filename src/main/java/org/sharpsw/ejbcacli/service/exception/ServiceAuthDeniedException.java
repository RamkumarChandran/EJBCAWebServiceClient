package org.sharpsw.ejbcacli.service.exception;


public class ServiceAuthDeniedException extends AppException {
	
	private static final long serialVersionUID = 1434459790354505153L;

	public ServiceAuthDeniedException() {
	}

	public ServiceAuthDeniedException(String message) {
		super(message);
	}

	public ServiceAuthDeniedException(Throwable cause) {
		super(cause);
	}

	public ServiceAuthDeniedException(String message, Throwable cause) {
		super(message, cause);
	}
}
