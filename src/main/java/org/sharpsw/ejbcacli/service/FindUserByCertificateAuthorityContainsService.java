package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class FindUserByCertificateAuthorityContainsService extends BaseFindUserByCertificateAuthorityService {

	private String criteriaValue = "";
	private static final int MATCH_CONTAINS = 2;
	
	public FindUserByCertificateAuthorityContainsService(EJBCAWebService service, String criteriaValue) throws NullArgumentException {
		super(service);
		if(criteriaValue == null) {
			throw new NullArgumentException("The criteria search value cannot be null");
		}
		
		this.criteriaValue = criteriaValue;
	}

	@Override
	protected int getSearchMatchType() {
		return MATCH_CONTAINS;
	}

	@Override
	protected String getCriteriaValue() {
		return this.criteriaValue;
	}

}
