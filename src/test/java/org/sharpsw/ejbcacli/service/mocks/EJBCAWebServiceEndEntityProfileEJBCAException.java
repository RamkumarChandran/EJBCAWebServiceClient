package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceEndEntityProfileEJBCAException extends
		EJBCAWebServiceMock {

	public EjbcaWS getService() {
		return new EjbcaWSEndEntityProfileListingEJBCAException();
	}
}
