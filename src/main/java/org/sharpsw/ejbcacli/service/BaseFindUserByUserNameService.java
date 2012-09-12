package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public abstract class BaseFindUserByUserNameService extends BaseFindUserService {

	private static final int MATCH_WITH_USER_NAME = 0;
	public BaseFindUserByUserNameService(EJBCAWebService service) throws NullArgumentException {
		super(service);
	}

	abstract protected int getSearchMatchType();
	
	@Override
	protected int getSearchMatchInformation() {
		return MATCH_WITH_USER_NAME;
	}

	abstract protected String getCriteriaValue();
}
