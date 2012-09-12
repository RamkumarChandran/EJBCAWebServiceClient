package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCAByEndEntityAuthExceptionMock extends EJBCAWebServiceMock {
	public EjbcaWS getService() {
		return new EjbcaWSCAListingByEndEntityAuthDeniedException();
	}
}
