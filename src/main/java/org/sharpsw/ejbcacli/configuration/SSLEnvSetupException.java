package org.sharpsw.ejbcacli.configuration;

public class SSLEnvSetupException extends Exception {
	
	private static final long serialVersionUID = 8007883005001903557L;

	public SSLEnvSetupException() {
	}

	public SSLEnvSetupException(String message) {
		super(message);
	}

	public SSLEnvSetupException(Throwable cause) {
		super(cause);
	}

	public SSLEnvSetupException(String message, Throwable cause) {
		super(message, cause);
	}	
}
