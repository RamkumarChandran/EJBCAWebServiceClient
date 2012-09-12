package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class FindUserByUserNameContainsService extends BaseFindUserByUserNameService {
	private String criteriaValue = "";
	private static final int MATCH_TYPE_CONTAINS = 2;
	
	public FindUserByUserNameContainsService(EJBCAWebService service, String value) throws NullArgumentException {
		super(service);
		if(value == null) {
			throw new NullArgumentException("The search criteria value cannot be null");
		}
		this.criteriaValue = value;
	}

	@Override
	protected int getSearchMatchType() {
		return MATCH_TYPE_CONTAINS;
	}

	@Override
	protected String getCriteriaValue() {
		return this.criteriaValue;
	}

}
