package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

/**
 * This class represents the user finding service based on the certificate authority information.
 * @author andersonkmi
 *
 */
public class FindUserByCertificateAuthorityEqualsService extends BaseFindUserByCertificateAuthorityService {
	private String criteriaValue = "";	
	private static final int MATCH_TYPE_EQUALS = 0;
	
	/**
	 * Constructor that receives the EJBCA web service wrapper and the search criteria value.
	 * @param service EJBCA web service wrapper.
	 * @param value Search criteria value.
	 * @throws NullArgumentException If one of the arguments is null.
	 */
	public FindUserByCertificateAuthorityEqualsService(EJBCAWebService service, String value) throws NullArgumentException {
		super(service);
		if(value == null) {
			throw new NullArgumentException("The search criteria value cannot be null");
		}
		this.criteriaValue = value;
	}

	protected int getSearchMatchType() {
		return MATCH_TYPE_EQUALS;
	}

	protected String getCriteriaValue() {
		return this.criteriaValue;
	}
}
