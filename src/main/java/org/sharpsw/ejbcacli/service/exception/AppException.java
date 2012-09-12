package org.sharpsw.ejbcacli.service.exception;

public class AppException extends Exception {	
	private static final long serialVersionUID = 3766400588064812915L;
	

	public AppException() {
		
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(Throwable cause) {
		super(cause);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}	
}
