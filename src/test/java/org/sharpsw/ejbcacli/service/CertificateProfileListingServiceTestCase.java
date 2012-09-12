package org.sharpsw.ejbcacli.service;

import java.util.List;

import junit.framework.Assert;

import org.junit.Test;
import org.sharpsw.ejbcacli.data.CertificateProfileVO;
import org.sharpsw.ejbcacli.factory.certprofile.CertificateProfileVOFactoryDefaultImpl;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCertProfileEmptyListingMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCertProfileListingAuthDeniedMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCertProfileListingEjbcaExceptionMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCertProfileListingMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceMock;

public class CertificateProfileListingServiceTestCase {
	
	@Test
	public void testCertificateProfileListingOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateProfileListingService service = new CertificateProfileListingService(new EJBCAWebServiceCertProfileListingMock(), new CertificateProfileVOFactoryDefaultImpl());
		
		List<CertificateProfileVO> results = service.getCertificateProfiles(0);
		Assert.assertNotNull(results);
		Assert.assertEquals(2, results.size());
		
		CertificateProfileVO element = results.get(0);
		Assert.assertNotNull(element);
		Assert.assertEquals(1, element.getId());
		Assert.assertEquals("Certificate profile 1", element.getName());
		
		element = results.get(1);
		Assert.assertNotNull(element);
		Assert.assertEquals(2, element.getId());
		Assert.assertEquals("Certificate profile 2", element.getName());
	}
	
	@Test(expected = NullArgumentException.class)
	public void testCertificateProfileListingWebServiceNullArgFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateProfileListingService service = new CertificateProfileListingService(null);
		service.getCertificateProfiles(0);
	}
	
	@Test(expected = NullArgumentException.class)
	public void testCertificateProfileListingFactoryNullArgFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateProfileListingService service = new CertificateProfileListingService(new EJBCAWebServiceCertProfileListingMock(), null);
		service.getCertificateProfiles(0);
	}
	
	@Test
	public void testCertificateProfileListingNullResultOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateProfileListingService service = new CertificateProfileListingService(new EJBCAWebServiceMock(), new CertificateProfileVOFactoryDefaultImpl());
		List<CertificateProfileVO> results = service.getCertificateProfiles(0);
		Assert.assertNotNull(results);
		Assert.assertEquals(0, results.size());
	}
	
	@Test
	public void testCertificateProfileListingEmptyResultOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateProfileListingService service = new CertificateProfileListingService(new EJBCAWebServiceCertProfileEmptyListingMock(), new CertificateProfileVOFactoryDefaultImpl());
		List<CertificateProfileVO> results = service.getCertificateProfiles(0);
		Assert.assertNotNull(results);
		Assert.assertEquals(0, results.size());
	}
	
	@Test(expected = ServiceAuthDeniedException.class)
	public void testCertificateProfileListingAuthDeniedFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateProfileListingService service = new CertificateProfileListingService(new EJBCAWebServiceCertProfileListingAuthDeniedMock(), new CertificateProfileVOFactoryDefaultImpl());
		service.getCertificateProfiles(0);
	}
	
	@Test(expected = EJBCAException.class)
	public void testCertificateProfileListingEJBCAExceptionFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateProfileListingService service = new CertificateProfileListingService(new EJBCAWebServiceCertProfileListingEjbcaExceptionMock(), new CertificateProfileVOFactoryDefaultImpl());
		service.getCertificateProfiles(0);
	}
}
