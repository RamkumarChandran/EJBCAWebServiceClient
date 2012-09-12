package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public abstract class BaseFindUserBySubjectDNService extends BaseFindUserService {

	private static final int MATCH_WITH_DN = 7;
	
	public BaseFindUserBySubjectDNService(EJBCAWebService service) throws NullArgumentException {
		super(service);
	}

	abstract protected int getSearchMatchType();

	@Override
	protected int getSearchMatchInformation() {
		return MATCH_WITH_DN;
	}

	abstract protected String getCriteriaValue();
}
