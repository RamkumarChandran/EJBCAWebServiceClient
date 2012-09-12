package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCAListingAuthDeniedException extends
		EJBCAWebServiceMock {
	public EjbcaWS getService() {
		EjbcaWSMockCAListingAuthDeniedException service = new EjbcaWSMockCAListingAuthDeniedException();
		return service;
	}
}
