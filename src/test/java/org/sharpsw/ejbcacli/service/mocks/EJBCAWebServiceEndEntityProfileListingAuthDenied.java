package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceEndEntityProfileListingAuthDenied extends
		EJBCAWebServiceMock {

	public EjbcaWS getService() {
		return new EjbcaWSEndEntityProfileListingAuthDeniedMock();
	}
}
