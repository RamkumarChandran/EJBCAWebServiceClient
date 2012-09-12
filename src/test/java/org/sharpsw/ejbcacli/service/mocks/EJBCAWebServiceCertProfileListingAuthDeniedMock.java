package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCertProfileListingAuthDeniedMock extends
		EJBCAWebServiceMock {
	
	public EjbcaWS getService() {
		return new EjbcaWSCertProfileListingAuthDeniedMock();
	}
}
