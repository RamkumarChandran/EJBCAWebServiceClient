package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceCAByEndEntityEjbcaExceptionMock extends
		EJBCAWebServiceMock {
	public EjbcaWS getService() {
		return new EjbcaWSCertificateAuthorityListingByEndEntityEJBCAException();
	}
}
