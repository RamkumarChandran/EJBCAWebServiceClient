package org.sharpsw.ejbcacli.service.exception;

/**
 * This class represents an exception that is thrown when the revocation status
 * service indicates when the certificate authority does not exist.
 * @author andersonkmi
 *
 */
public class InvalidCertificateAuthorityException extends Exception {
	private static final long serialVersionUID = -5816852314799829508L;

	public InvalidCertificateAuthorityException() {
	}

	public InvalidCertificateAuthorityException(String message) {
		super(message);
	}

	public InvalidCertificateAuthorityException(Throwable cause) {
		super(cause);
	}

	public InvalidCertificateAuthorityException(String message, Throwable cause) {
		super(message, cause);
	}

}
