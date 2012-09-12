package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class FindUserByEmailBeginWithService extends BaseFindUserByEmailService {
	private String criteriaValue = "";
	private static final int MATCH_BEGIN_WITH = 1;
	
	public FindUserByEmailBeginWithService(EJBCAWebService service, String value) throws NullArgumentException {
		super(service);
		if(value == null) {
			throw new NullArgumentException("The search criteria value cannot be null");
		}
		this.criteriaValue = value;
	}

	@Override
	protected int getSearchMatchType() {
		return MATCH_BEGIN_WITH;
	}

	@Override
	protected String getCriteriaValue() {
		return this.criteriaValue;
	}

}
