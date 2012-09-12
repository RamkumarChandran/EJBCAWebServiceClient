package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceEndEntityProfileListingMock extends
		EJBCAWebServiceMock {

	public EjbcaWS getService() {
		return new EjbcaWSEndEntityProfileListingMock();
	}
}
