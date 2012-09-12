package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCertProfileListingEjbcaExceptionMock extends
		EJBCAWebServiceMock {

	public EjbcaWS getService() {
		return new EjbcaWSCertProfileListingEjbcaExceptionMock();
	}
}
