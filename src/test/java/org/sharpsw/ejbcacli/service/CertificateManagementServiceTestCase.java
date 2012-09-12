package org.sharpsw.ejbcacli.service;

import java.security.cert.CertificateException;

import org.junit.Before;
import org.junit.Test;
import org.sharpsw.ejbcacli.service.exception.ApprovalException;
import org.sharpsw.ejbcacli.service.exception.CertificateAlreadyRevokedException;
import org.sharpsw.ejbcacli.service.exception.CertificateNotFoundException;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.InvalidCertificateAuthorityException;
import org.sharpsw.ejbcacli.service.exception.InvalidResponseTypeException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.PendingApprovalException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;
import org.sharpsw.ejbcacli.service.exception.UserNotFoundException;
import org.sharpsw.ejbcacli.service.mocks.EJBCAWebServiceMock;

public class CertificateManagementServiceTestCase {

	private CertificateManagementService service;
	
	@Before
	public void setUp() {
		this.service = new CertificateManagementService(new EJBCAWebServiceMock());
	}

	@Test(expected = NullArgumentException.class)
	public void testRevokeCertNullIssuerFail() throws ServiceAuthDeniedException, EJBCAException, CertificateAlreadyRevokedException, ApprovalException, InvalidCertificateAuthorityException, CertificateNotFoundException, PendingApprovalException {
		this.service.revokeCertificate(null, "12345678", CertificateRevocationReason.UNSPECIFIED);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRevokeCertEmptyIssuerFail() throws ServiceAuthDeniedException, EJBCAException, CertificateAlreadyRevokedException, ApprovalException, InvalidCertificateAuthorityException, CertificateNotFoundException, PendingApprovalException {
		this.service.revokeCertificate("", "12345678", CertificateRevocationReason.UNSPECIFIED);
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRevokeCertNullSerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateAlreadyRevokedException, ApprovalException, InvalidCertificateAuthorityException, CertificateNotFoundException, PendingApprovalException {
		this.service.revokeCertificate("DN=teste", null, CertificateRevocationReason.UNSPECIFIED);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRevokeCertInvalidIssuerDNFail() throws ServiceAuthDeniedException, EJBCAException, CertificateAlreadyRevokedException, ApprovalException, InvalidCertificateAuthorityException, CertificateNotFoundException, PendingApprovalException {
		this.service.revokeCertificate("anderson", "1", CertificateRevocationReason.AA_COMPROMISE);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRevokeCertEmptySerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateAlreadyRevokedException, ApprovalException, InvalidCertificateAuthorityException, CertificateNotFoundException, PendingApprovalException {
		this.service.revokeCertificate("DN=teste", "", CertificateRevocationReason.UNSPECIFIED);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRevokeCertInvalidSerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateAlreadyRevokedException, ApprovalException, InvalidCertificateAuthorityException, CertificateNotFoundException, PendingApprovalException {
		this.service.revokeCertificate("DN=test", "T445", CertificateRevocationReason.UNSPECIFIED);
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRevokeCertNullRevocationReasonFail() throws ServiceAuthDeniedException, EJBCAException, CertificateAlreadyRevokedException, ApprovalException, InvalidCertificateAuthorityException, CertificateNotFoundException, PendingApprovalException {
		this.service.revokeCertificate("DN=Teste", "12345", null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRevokeCertInvalidReasonFail() throws ServiceAuthDeniedException, EJBCAException, CertificateAlreadyRevokedException, ApprovalException, InvalidCertificateAuthorityException, CertificateNotFoundException, PendingApprovalException {
		this.service.revokeCertificate("DN=test", "12345", CertificateRevocationReason.NOT_REVOKED);
	}
	
	@Test(expected = NullArgumentException.class)
	public void testFindCertificatesNullUserNameFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException {
		this.service.findCertificates(null, false);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testFindCertificatesEmptyUserNameFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException {
		this.service.findCertificates("", false);
	}
	
	@Test(expected = NullArgumentException.class)
	public void testFindCertificatesBase64NullUserNameFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException {
		this.service.findCertificatesBase64String(null, false);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testFindCertificatesBase64EmptyUserNameFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException {
		this.service.findCertificatesBase64String("", false);
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRequestCertificateX509NullUserNameFail() throws CertificateException, ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateX509Format(null, "test", "YW5kZXJzb24=");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificateX509EmptyUserNameFail() throws CertificateException, ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateX509Format("", "user", "YW5kZXJzb24=");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRequestCertificateX509NullPasswordFail() throws CertificateException, ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateX509Format("user", null, "YW5kZXJzb24=");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificateX509EmptyPasswordFail() throws CertificateException, ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateX509Format("user", "", "YW5kZXJzb24=");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRequestCertificateX509NullCSRFail() throws CertificateException, ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateX509Format("user", "password", null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificateX509EmptyCSRFail() throws CertificateException, ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateX509Format("user", "password", "");
	}

	@Test(expected = NullArgumentException.class)
	public void testRequestCertificatePKCS7NullUserNameFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificatePKCS7Format(null, "password", "YW5kZXJzb24=");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificatePKCS7EmptyUserFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificatePKCS7Format("", "password", "YW5kZXJzb24=");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRequestCertificatePKCS7NullPasswordFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificatePKCS7Format("user", null, "YW5kZXJzb24=");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificatePKCS7EmptyPasswordFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificatePKCS7Format("user", "", "YW5kZXJzb24=");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRequestCertificatePKCS7NullCSRFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificatePKCS7Format("user", "password", null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificatePKCS7EmptyCSRFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificatePKCS7Format("user", "password", "");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRequestCertificateWithChainPKCS7NullUserNameFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateWithChainPKCS7Format(null, "password", "YW5kZXJzb24=");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificateWithChainPKCS7EmptyUserFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateWithChainPKCS7Format("", "password", "YW5kZXJzb24=");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRequestCertificateWithChainPKCS7NullPasswordFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateWithChainPKCS7Format("user", null, "YW5kZXJzb24=");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificateWithChainPKCS7EmptyPasswordFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificatePKCS7Format("user", "", "YW5kZXJzb24=");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testRequestCertificateWithChainPKCS7NullCSRFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateWithChainPKCS7Format("user", "password", null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testRequestCertificateWithChainPKCS7EmptyCSRFail() throws ServiceAuthDeniedException, EJBCAException, InvalidResponseTypeException, InvalidCertificateAuthorityException, UserNotFoundException {
		this.service.requestCertificateWithChainPKCS7Format("user", "password", "");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testGetCertificateNullSerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificate(null, "DN=test");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testGetCertificateEmptySerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificate("", "DN=test");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testGetCertificateInvalidSerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificate("Z123", "DN=test");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testGetCertificateNullIssuerDNFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificate("123F", null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testGetCertificateEmptyIssuerDNFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificate("123F", "");
	}
	
	@Test(expected = InvalidArgumentException.class) 
	public void testGetCertificateInvalidIssuerDNFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificate("123", "test");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testGetCertificateBase64NullSerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificateBase64String(null, "DN=test");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testGetCertificateBase64StringEmptySerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificateBase64String("", "DN=test");
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testGetCertificateBase64StringInvalidSerialNumberFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificateBase64String("Z123", "DN=test");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testGetCertificateBase64StringNullIssuerDNFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificateBase64String("123F", null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testGetCertificateBase64StringEmptyIssuerDNFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificateBase64String("123F", "");
	}
	
	@Test(expected = InvalidArgumentException.class) 
	public void testGetCertificateBase64StringInvalidIssuerDNFail() throws ServiceAuthDeniedException, EJBCAException, CertificateException, InvalidCertificateAuthorityException {
		this.service.getCertificateBase64String("123", "test");
	}
	
	@Test(expected = NullArgumentException.class)
	public void testGetCertAuthorityChainNullFail() throws ServiceAuthDeniedException, EJBCAException, InvalidCertificateAuthorityException {
		this.service.getCertificateAuthorityChain(null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testGetCertAuthorityChainEmptyArgFail() throws ServiceAuthDeniedException, EJBCAException, InvalidCertificateAuthorityException {
		this.service.getCertificateAuthorityChain("");
	}
}
