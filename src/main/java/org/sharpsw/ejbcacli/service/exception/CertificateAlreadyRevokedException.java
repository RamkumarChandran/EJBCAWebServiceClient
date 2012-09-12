package org.sharpsw.ejbcacli.service.exception;

public class CertificateAlreadyRevokedException extends Exception {
	private static final long serialVersionUID = -281093671180410798L;

	public CertificateAlreadyRevokedException() {
	}

	public CertificateAlreadyRevokedException(String message) {
		super(message);
	}

	public CertificateAlreadyRevokedException(Throwable cause) {
		super(cause);
	}

	public CertificateAlreadyRevokedException(String message, Throwable cause) {
		super(message, cause);
	}

}
