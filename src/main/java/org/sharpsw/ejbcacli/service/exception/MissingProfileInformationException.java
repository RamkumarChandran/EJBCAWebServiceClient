package org.sharpsw.ejbcacli.service.exception;

public class MissingProfileInformationException extends Exception {

	private static final long serialVersionUID = -6534436458532259403L;

	public MissingProfileInformationException() {
	}

	public MissingProfileInformationException(String message) {
		super(message);
	}

	public MissingProfileInformationException(Throwable cause) {
		super(cause);
	}

	public MissingProfileInformationException(String message, Throwable cause) {
		super(message, cause);
	}

}
