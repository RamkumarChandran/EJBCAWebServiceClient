package org.sharpsw.ejbcacli.service.exception;


public class EJBCAException extends AppException {
	private static final long serialVersionUID = 6575370269062147275L;

	public EJBCAException() {
	}

	public EJBCAException(String message) {
		super(message);
	}

	public EJBCAException(Throwable cause) {
		super(cause);
	}

	public EJBCAException(String message, Throwable cause) {
		super(message, cause);
	}	

}
