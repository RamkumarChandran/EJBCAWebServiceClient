package org.sharpsw.ejbcacli.configuration;

public class InvalidConfigParameterException extends RuntimeException {

	private static final long serialVersionUID = 6152357585930753436L;

	public InvalidConfigParameterException() {
	}

	public InvalidConfigParameterException(String arg0) {
		super(arg0);
	}

	public InvalidConfigParameterException(Throwable arg0) {
		super(arg0);
	}

	public InvalidConfigParameterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
