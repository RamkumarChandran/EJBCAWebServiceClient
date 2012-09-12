package org.sharpsw.ejbcacli.service.exception;

public class CRLCreationException extends AppException {
	private static final long serialVersionUID = 1L;

	public CRLCreationException() {
	}

	public CRLCreationException(String message) {
		super(message);
	}

	public CRLCreationException(Throwable cause) {
		super(cause);
	}

	public CRLCreationException(String message, Throwable cause) {
		super(message, cause);
	}
}
