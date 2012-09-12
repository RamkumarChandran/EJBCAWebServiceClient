package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class FindUserByEmailEqualsService extends BaseFindUserByEmailService {
	private String criteriaValue = "";
	private static final int MATCH_EQUALS = 0;
	
	public FindUserByEmailEqualsService(EJBCAWebService service, String value) throws NullArgumentException {
		super(service);
		if(value == null) {
			throw new NullArgumentException("The search criteria value cannot be null");
		}
		this.criteriaValue = value;
	}

	@Override
	protected int getSearchMatchType() {
		return MATCH_EQUALS;
	}

	@Override
	protected String getCriteriaValue() {
		return this.criteriaValue;
	}

}
