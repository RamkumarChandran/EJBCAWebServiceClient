package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCAListingOKMock extends EJBCAWebServiceMock {
	public EjbcaWS getService() {
		EjbcaWSCAListingOKMock service = new EjbcaWSCAListingOKMock();
		return service;
	}
}
