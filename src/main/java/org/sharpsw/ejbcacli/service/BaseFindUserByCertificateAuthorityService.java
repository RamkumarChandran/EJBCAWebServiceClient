package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

/**
 * Base class for finding users given its certificate authority information.
 * @author andersonkmi
 *
 */
public abstract class BaseFindUserByCertificateAuthorityService extends BaseFindUserService {
	private static final int MATCH_WITH_CA = 5;
	
	/**
	 * Class constructor that receives an instance of the EJBCA web service wrapper.
	 * @param service EJBCA web service wrapper
	 * @throws NullArgumentException If the supplied argument is null.
	 */
	public BaseFindUserByCertificateAuthorityService(EJBCAWebService service) throws NullArgumentException {
		super(service);
	}

	abstract protected int getSearchMatchType();
	
	abstract protected String getCriteriaValue();
	
	@Override
	protected int getSearchMatchInformation() {
		return MATCH_WITH_CA;
	}

}
