package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class FindUserBySubjectDNContainsService extends BaseFindUserBySubjectDNService {

	private String criteriaValue = "";
	private static final int MATCH_CONTAINS = 2;
	
	public FindUserBySubjectDNContainsService(EJBCAWebService service, String value) throws NullArgumentException {
		super(service);
		if(value == null) {
			throw new NullArgumentException("The search criteria value cannot be null");
		}
		this.criteriaValue = value;
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
