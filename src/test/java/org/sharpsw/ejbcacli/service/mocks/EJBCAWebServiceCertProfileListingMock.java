package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCertProfileListingMock extends EJBCAWebServiceMock {
	public EjbcaWS getService() {
		return new EjbcaWSCertificateProfileListingMock();
	}
}
