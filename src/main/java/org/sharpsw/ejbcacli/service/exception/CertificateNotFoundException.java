package org.sharpsw.ejbcacli.service.exception;

public class CertificateNotFoundException extends Exception {
	private static final long serialVersionUID = 3789815283790496115L;

	public CertificateNotFoundException() {
	}

	public CertificateNotFoundException(String message) {
		super(message);
	}

	public CertificateNotFoundException(Throwable cause) {
		super(cause);
	}

	public CertificateNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
