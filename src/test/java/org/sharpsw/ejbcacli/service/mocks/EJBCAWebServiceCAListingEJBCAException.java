package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCAListingEJBCAException extends EJBCAWebServiceMock {
	public EjbcaWS getService() {
		EjbcaWSMockCAListingEJBCAException service = new EjbcaWSMockCAListingEJBCAException();
		return service;
	}
}
