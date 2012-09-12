package org.sharpsw.ejbcacli.data;

/**
 * This enumeration represents the possible revocation values for the 
 * certificates in the EJBCA server.
 * @author andersonkmi
 *
 */
public enum RevocationReason {
	
	NOT_REVOKED("Not revoked", -1),
	AA_COMPROMISE("AA compromise", 10),
	AFFILIATION_CHANGED("Affiliation changed", 3),
	CA_COMPROMISE("CA compromise", 2),
	CERTIFICATE_HOLD("Certificate hold", 6),
	CESSATION_OF_OPERATION("Cessation of operation", 5),
	KEY_COMPROMISE("Key compromise", 1),
	PRIVILEGES_WITHDRAWN("Privileges withdrawn", 9),
	REMOVE_FROM_CRL("Remove from CRL", 8),
	SUPERSEDED("Superseded", 4),
	UNSPECIFIED("Unspecified", 0);
	
	private final String displayName;
	private final int code;
	
	RevocationReason(String name, int code) {
		this.displayName = name;
		this.code = code;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}
	
	public int getCode() {
		return this.code;
	}
}
