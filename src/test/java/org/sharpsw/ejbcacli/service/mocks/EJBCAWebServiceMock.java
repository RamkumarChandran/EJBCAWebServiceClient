package org.sharpsw.ejbcacli.service.mocks;

import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.service.EJBCAWebService;


public class EJBCAWebServiceMock extends EJBCAWebService {

	public EJBCAWebServiceMock() {
		
	}

	public EjbcaWS getService() {
		EjbcaWSMock mock = new EjbcaWSMock();
		return mock;
	}
}
