package org.sharpsw.ejbcacli.service.exception;

public class ApprovalException extends Exception {

	private static final long serialVersionUID = -8917743172000206560L;

	public ApprovalException() {
	}

	public ApprovalException(String message) {
		super(message);
	}

	public ApprovalException(Throwable cause) {
		super(cause);
	}

	public ApprovalException(String message, Throwable cause) {
		super(message, cause);
	}

}
