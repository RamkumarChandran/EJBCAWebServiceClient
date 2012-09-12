package org.sharpsw.ejbcacli.data;

public enum UserStatus {
	NEW(10),
	KEY_RECOVERY(70),
	IN_PROCESS(30),
	INITIALIZED(20),
	HISTORICAL(60),
	GENERATED(40),
	REVOKED(50),
	FAILED(11);
	
	private int statusCode;
	UserStatus(int code) {
		this.statusCode = code;
	}
	
	public int getStatusCode() {
		return this.statusCode;
	}
}
