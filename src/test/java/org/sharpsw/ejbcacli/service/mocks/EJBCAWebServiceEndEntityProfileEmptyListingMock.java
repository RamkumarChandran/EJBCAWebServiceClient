package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;

public class EJBCAWebServiceEndEntityProfileEmptyListingMock extends
		EJBCAWebServiceMock {

	public EjbcaWS getService() {
		return new EjbcaWSEndEntityProfileListingEmptyResult();
	}
}
