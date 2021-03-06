package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public abstract class BaseFindUserByEndEntityProfileService extends BaseFindUserService {

	private static final int MATCH_WITH_END_ENTITY_PROFILE = 3;
	
	public BaseFindUserByEndEntityProfileService(EJBCAWebService service) throws NullArgumentException {
		super(service);
	}

	abstract protected int getSearchMatchType();
	
	@Override
	protected int getSearchMatchInformation() {
		return MATCH_WITH_END_ENTITY_PROFILE;
	}

	abstract protected String getCriteriaValue();
}
