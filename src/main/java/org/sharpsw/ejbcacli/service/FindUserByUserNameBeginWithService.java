package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class FindUserByUserNameBeginWithService extends BaseFindUserByUserNameService {
	private String criteriaValue = "";
	private static final int MATCH_TYPE_BEGIN_WITH = 1;
	
	public FindUserByUserNameBeginWithService(EJBCAWebService service, String value) throws NullArgumentException {
		super(service);
		if(value == null) {
			throw new NullArgumentException("The search criteria value cannot be null");
		}
		this.criteriaValue = value;
	}

	@Override
	protected int getSearchMatchType() {
		return MATCH_TYPE_BEGIN_WITH;
	}

	@Override
	protected String getCriteriaValue() {
		return this.criteriaValue;
	}

}
