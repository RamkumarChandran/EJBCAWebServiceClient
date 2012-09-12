package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCAListingMock extends EJBCAWebServiceMock {
	public EjbcaWS getService() {
		EjbcaWSMockCAListingEmpty service = new EjbcaWSMockCAListingEmpty();
		return service;
	}
}
