package org.sharpsw.ejbcacli.data;

public enum TokenType {
	JKS("JKS"),
	P12("P12"),
	PEM("PEM"),
	USER_GENERATED("USERGENERATED");
	
	private String type;
	TokenType(String type) {
		this.type = type;
	}
	
	public String getType() {
		return this.type;
	}
}
