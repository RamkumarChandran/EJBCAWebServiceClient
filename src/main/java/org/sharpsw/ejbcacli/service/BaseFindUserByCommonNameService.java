package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public abstract class BaseFindUserByCommonNameService extends BaseFindUserService {

	private static final int MATCH_WITH_COMMON_NAME = 101;
	public BaseFindUserByCommonNameService(EJBCAWebService service) throws NullArgumentException {
		super(service);
	}

	@Override
	protected int getSearchMatchType() {
		return MATCH_WITH_COMMON_NAME;
	}

	abstract protected int getSearchMatchInformation();
	
	abstract protected String getCriteriaValue();
}
