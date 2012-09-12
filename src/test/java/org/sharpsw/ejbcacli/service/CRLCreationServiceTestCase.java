package org.sharpsw.ejbcacli.service;

import org.junit.Test;
import org.sharpsw.ejbcacli.service.exception.CRLCreationException;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceMock;


public class CRLCreationServiceTestCase {
	@Test
	public void testCRLCreationServiceOK() throws NullArgumentException, EJBCAException, CRLCreationException, InvalidArgumentException {
		CRLCreationService service = new CRLCreationService(new EJBCAWebServiceMock());
		service.createCRL("teste");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testCRLCreationServiceNullArgFail() throws NullArgumentException {
		@SuppressWarnings("unused")
		CRLCreationService service = new CRLCreationService(null);
	}
	
	@Test(expected = NullArgumentException.class)
	public void testCRLCreationNullArgFail() throws NullArgumentException, EJBCAException, CRLCreationException, InvalidArgumentException {
		CRLCreationService service = new CRLCreationService(new EJBCAWebServiceMock());
		service.createCRL(null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testCRLCreationEmptyArgFail() throws NullArgumentException, EJBCAException, CRLCreationException, InvalidArgumentException {
		CRLCreationService service = new CRLCreationService(new EJBCAWebServiceMock());
		service.createCRL("");
	}
}
