package org.sharpsw.ejbcacli.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.sharpsw.ejbcacli.data.EndEntityProfileVO;
import org.sharpsw.ejbcacli.factory.entityprofile.EndEntityProfileVOFactoryDefaultImpl;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceEndEntityProfileEJBCAException;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceEndEntityProfileEmptyListingMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceEndEntityProfileListingAuthDenied;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceEndEntityProfileListingMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceMock;

public class EndEntityProfileListingServiceTestCase {

	@Test
	public void testEndEntityProfileListingOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		EndEntityProfileListingService service = new EndEntityProfileListingService(new EJBCAWebServiceEndEntityProfileListingMock(), new EndEntityProfileVOFactoryDefaultImpl());
		List<EndEntityProfileVO> results = service.getEndEntityProfiles();
		
		Assert.assertNotNull(results);
		Assert.assertEquals(3, results.size());
	}

	@Test
	public void testEndEntityProfileListingNullResultOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		EndEntityProfileListingService service = new EndEntityProfileListingService(new EJBCAWebServiceMock(), new EndEntityProfileVOFactoryDefaultImpl());
		List<EndEntityProfileVO> results = service.getEndEntityProfiles();
		
		Assert.assertNotNull(results);
		Assert.assertEquals(0, results.size());
	}
	
	@Test
	public void testEndEntityProfileListingEmptyResultOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		EndEntityProfileListingService service = new EndEntityProfileListingService(new EJBCAWebServiceEndEntityProfileEmptyListingMock(), new EndEntityProfileVOFactoryDefaultImpl());
		List<EndEntityProfileVO> results = service.getEndEntityProfiles();
		
		Assert.assertNotNull(results);
		Assert.assertEquals(0, results.size());
	}
	
	@Test(expected = NullArgumentException.class)
	public void testEndEntityProfileListingServiceNullWebServiceFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		EndEntityProfileListingService service = new EndEntityProfileListingService(null);
		service.getEndEntityProfiles();
	}
	
	@Test(expected = NullArgumentException.class)
	public void testEndEntityProfileListingServiceNullWebServiceAltCtorFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		EndEntityProfileListingService service = new EndEntityProfileListingService(null, new EndEntityProfileVOFactoryDefaultImpl());
		service.getEndEntityProfiles();
	}
	
	@Test(expected = NullArgumentException.class)
	public void testEndEntityProfileListingServiceNullFactoryArgFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		EndEntityProfileListingService service = new EndEntityProfileListingService(new EJBCAWebServiceMock(), null);
		service.getEndEntityProfiles();
	}
	
	@Test(expected = ServiceAuthDeniedException.class)
	public void testEndEntityProfileListingAuthDeniedFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		EndEntityProfileListingService service = new EndEntityProfileListingService(new EJBCAWebServiceEndEntityProfileListingAuthDenied(), new EndEntityProfileVOFactoryDefaultImpl());
		service.getEndEntityProfiles();
	}
	
	@Test(expected = EJBCAException.class)
	public void testEndEntityProfileListingEJBCAExceptionFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		EndEntityProfileListingService service = new EndEntityProfileListingService(new EJBCAWebServiceEndEntityProfileEJBCAException(), new EndEntityProfileVOFactoryDefaultImpl());
		service.getEndEntityProfiles();
	}
}
