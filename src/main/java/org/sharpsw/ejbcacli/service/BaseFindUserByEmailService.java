package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public abstract class BaseFindUserByEmailService extends BaseFindUserService {

	private static final int MATCH_WITH_EMAIL = 1;
	
	public BaseFindUserByEmailService(EJBCAWebService service) throws NullArgumentException {
		super(service);
	}

	abstract protected int getSearchMatchType();
	
	@Override
	protected int getSearchMatchInformation() {
		return MATCH_WITH_EMAIL;
	}

	abstract protected String getCriteriaValue();
}
