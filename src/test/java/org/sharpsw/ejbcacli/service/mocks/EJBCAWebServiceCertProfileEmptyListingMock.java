package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCertProfileEmptyListingMock extends
		EJBCAWebServiceMock {
	public EjbcaWS getService() {
		return new EjbcaWSCertProfileEmptyResultMock();
	}
}
