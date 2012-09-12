package org.sharpsw.ejbcacli.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.sharpsw.ejbcacli.data.CertificateAuthorityVO;
import org.sharpsw.ejbcacli.factory.ca.CertificateAuthorityVOFactoryDefaultImpl;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCAByEndEntityAuthExceptionMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCAByEndEntityEjbcaExceptionMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCAByEndEntityProfileListingOKMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCAListingAuthDeniedException;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCAListingEJBCAException;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCAListingMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceCAListingOKMock;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceMock;

public class CertificateAuthorityListingServiceTestCase {
		
	@Test
	public void testCertAuthListingEmptyResponseOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceCAListingMock(), new CertificateAuthorityVOFactoryDefaultImpl());
		
		List<CertificateAuthorityVO> elements = service.getCertificateAuthorities();
		Assert.assertNotNull(elements);
		Assert.assertEquals(0, elements.size());
	}
	
	@Test
	public void testCertAuthListingEmptyResponseNullOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceMock(), new CertificateAuthorityVOFactoryDefaultImpl());

		List<CertificateAuthorityVO> elements = service.getCertificateAuthorities();
		Assert.assertNotNull(elements);
		Assert.assertEquals(0, elements.size());
	}
	
	@Test
	public void testCertAuthListingResponseOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceCAListingOKMock(), new CertificateAuthorityVOFactoryDefaultImpl());
		
		List<CertificateAuthorityVO> elements = service.getCertificateAuthorities();
		Assert.assertNotNull(elements);
		Assert.assertEquals(2, elements.size());
		
		Assert.assertEquals(1, elements.get(0).getId());
		Assert.assertEquals("Root CA Example", elements.get(0).getName());
		
		Assert.assertEquals(2, elements.get(1).getId());
		Assert.assertEquals("Intermediate CA Example", elements.get(1).getName());
	}
	
	@Test(expected = ServiceAuthDeniedException.class)
	public void testCertAuthListingAuthDeniedFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceCAListingAuthDeniedException(), new CertificateAuthorityVOFactoryDefaultImpl());
		
		service.getCertificateAuthorities();
	}
	
	@Test(expected = EJBCAException.class)
	public void testCertAuthListingEJBCAExceptionFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceCAListingEJBCAException(), new CertificateAuthorityVOFactoryDefaultImpl());
		
		service.getCertificateAuthorities();
	}
	
	@Test
	public void testCertAuthListingByEndEntityProfileEmptyResponseOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceCAListingMock(), new CertificateAuthorityVOFactoryDefaultImpl());
		
		List<CertificateAuthorityVO> elements = service.getCertificateAuthoritiesInProfile(0);
		Assert.assertNotNull(elements);
		Assert.assertEquals(0, elements.size());
	}
	
	@Test
	public void testCertAuthListingByEndEntityProfileNullResponseOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceMock(), new CertificateAuthorityVOFactoryDefaultImpl());
		List<CertificateAuthorityVO> elements = service.getCertificateAuthoritiesInProfile(0);
		Assert.assertNotNull(elements);
		Assert.assertEquals(0, elements.size());
	}
	
	@Test
	public void testCertAuthListingByEndEntityProfileResponseOK() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceCAByEndEntityProfileListingOKMock(), new CertificateAuthorityVOFactoryDefaultImpl());
		List<CertificateAuthorityVO> elements = service.getCertificateAuthoritiesInProfile(0);
		
		Assert.assertNotNull(elements);
		Assert.assertEquals(2, elements.size());
		
		CertificateAuthorityVO element = elements.get(0);
		Assert.assertEquals(1, element.getId());
		Assert.assertEquals("Root Certificate Authority", element.getName());
		
		element = elements.get(1);
		Assert.assertEquals(2, element.getId());
		Assert.assertEquals("Intermediate certificate authority", element.getName());
	}
	
	@Test(expected = ServiceAuthDeniedException.class)
	public void testCertAuthListingByEndEntityProfileAuthDeniedFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceCAByEndEntityAuthExceptionMock(), new CertificateAuthorityVOFactoryDefaultImpl());		
		service.getCertificateAuthoritiesInProfile(0);		
	}
	
	@Test(expected = EJBCAException.class)
	public void testCertAuthListingByEndEntityProfileEJBCAExceptionFail() throws NullArgumentException, ServiceAuthDeniedException, EJBCAException {
		CertificateAuthorityListingService service = new CertificateAuthorityListingService(new EJBCAWebServiceCAByEndEntityEjbcaExceptionMock(), new CertificateAuthorityVOFactoryDefaultImpl());
		service.getCertificateAuthoritiesInProfile(0);
	}
}
