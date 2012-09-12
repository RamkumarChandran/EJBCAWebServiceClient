package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class FindUserByCertificateAuthorityBeginWithService extends BaseFindUserByCertificateAuthorityService {

	private String criteriaValue = "";
	private static final int MATCH_BEGINS_WITH = 1;
	
	public FindUserByCertificateAuthorityBeginWithService(EJBCAWebService service, String criteriaValue) throws NullArgumentException {
		super(service);
		if(criteriaValue == null) {
			throw new NullArgumentException("The search criteria argument cannot be null");
		}
		this.criteriaValue = criteriaValue;
	}

	@Override
	protected int getSearchMatchType() {
		return MATCH_BEGINS_WITH;
	}

	@Override
	protected String getCriteriaValue() {
		return this.criteriaValue;
	}

}
