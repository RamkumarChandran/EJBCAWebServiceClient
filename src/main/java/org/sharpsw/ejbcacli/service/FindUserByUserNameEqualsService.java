package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class FindUserByUserNameEqualsService extends BaseFindUserByUserNameService {
	private String criteriaValue = "";
	private static final int MATCH_TYPE_EQUALS = 0;
	
	public FindUserByUserNameEqualsService(EJBCAWebService service, String value) throws NullArgumentException {
		super(service);
		if(value == null) {
			throw new NullArgumentException("The search criteria value cannot be null");
		}
		this.criteriaValue = value;
	}

	@Override
	protected int getSearchMatchType() {
		return MATCH_TYPE_EQUALS;
	}

	@Override
	protected String getCriteriaValue() {
		return this.criteriaValue;
	}

}
