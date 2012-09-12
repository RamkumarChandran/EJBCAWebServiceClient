package org.sharpsw.ejbcacli.service;

public enum CertificateRevocationReason {
	NOT_REVOKED(-1),
	AFFILIATION_CHANGED(3),
	CA_COMPROMISE(2),
	CERTIFICATE_HOLD(6),
	CESSATION_OF_OPERATION(5),
	KEY_COMPROMISE(1),
	PRIVILEGES_WITHDRAWN(9),
	REMOVE_FROM_CRL(8),
	SUPERSEDED(4),
	UNSPECIFIED(0),
	AA_COMPROMISE(10);
	
	private int code;
	CertificateRevocationReason(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
