package org.sharpsw.ejbcacli.service.exception;

public class PendingApprovalException extends Exception {
	private static final long serialVersionUID = 2400852742699263425L;

	public PendingApprovalException() {
	}

	public PendingApprovalException(String message) {
		super(message);
	}

	public PendingApprovalException(Throwable cause) {
		super(cause);
	}

	public PendingApprovalException(String message, Throwable cause) {
		super(message, cause);
	}

}
