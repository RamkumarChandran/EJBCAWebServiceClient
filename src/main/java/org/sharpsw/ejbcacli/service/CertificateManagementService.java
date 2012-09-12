package org.sharpsw.ejbcacli.service;

import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.sharpsw.ejbcacli.AlreadyRevokedException_Exception;
import org.sharpsw.ejbcacli.ApprovalException_Exception;
import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.CADoesntExistsException_Exception;
import org.sharpsw.ejbcacli.CertificateResponse;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.NotFoundException_Exception;
import org.sharpsw.ejbcacli.WaitingForApprovalException_Exception;
import org.sharpsw.ejbcacli.service.exception.ApprovalException;
import org.sharpsw.ejbcacli.service.exception.CertificateAlreadyRevokedException;
import org.sharpsw.ejbcacli.service.exception.CertificateNotFoundException;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.InvalidCertificateAuthorityException;
import org.sharpsw.ejbcacli.service.exception.InvalidResponseTypeException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.PendingApprovalException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;
import org.sharpsw.ejbcacli.service.exception.UserNotFoundException;

/**
 * This class represents a service that is responsible for handling certificate requests,
 * certificate revocation and certificate chain.
 * @author andersonkmi
 *
 */
public class CertificateManagementService {
	private EJBCAWebService service;
	
	private static final String CERTIFICATE = "CERTIFICATE";
	private static final String PKCS7 = "PKCS7";
	private static final String PKCS7_WITH_CHAIN = "PKCS7WITHCHAIN";
	
	/**
	 * Class constructor that receives the EJBCA web service wrapper.
	 * @param service EJBCA web service wrapper instance.
	 * @throws NullArgumentException If the argument is null.
	 */
	public CertificateManagementService(EJBCAWebService service) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The web service instance cannot be null");
		}
		this.service = service;
	}
	
	public void revokeCertificate(String issuerDN, 
			                      String serialNumber, 
			                      CertificateRevocationReason reason) throws
	                                                                  CertificateAlreadyRevokedException, 
	                                                                  ApprovalException, 
	                                                                  InvalidCertificateAuthorityException,
	                                                                  ServiceAuthDeniedException, 
	                                                                  EJBCAException,
	                                                                  CertificateNotFoundException,
	                                                                  PendingApprovalException {
		
		try {
			EjbcaWS webService = this.service.getService();
			webService.revokeCert(issuerDN, serialNumber, reason.getCode());
		} catch (AlreadyRevokedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate is already revoked. Message: '").append(exception.getMessage()).append("'.");
			throw new CertificateAlreadyRevokedException(message.toString(), exception);
		} catch (ApprovalException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The revoke request is already in the approval queue. Message: '").append(exception.getMessage()).append("'.");
			throw new ApprovalException(message.toString(), exception);
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client is not authorized to perform such operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate authority is not valid. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server returned an error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} catch (NotFoundException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate was not found in the EJBCA server. Message: '").append(exception.getMessage()).append("'.");
			throw new CertificateNotFoundException(message.toString(), exception);
		} catch (WaitingForApprovalException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Waiting for approval. Message: '").append(exception.getMessage()).append("'.");
			throw new PendingApprovalException(message.toString(), exception);
		}
	}
	
	
	/**
	 * Returns a list of the certificates associated with the user informed.
	 * @param userName User in the EJBCA server.
	 * @param validOnly Flag indicating if only the valid certificates should be returned.
	 * @return List of the certificates associated with the user.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws EJBCAException Generic EJBCA server error.
	 * @throws CertificateException Certificate handling error.
	 */
	public List<Certificate> findCertificates(String userName, boolean validOnly) throws ServiceAuthDeniedException, EJBCAException, CertificateException {
		List<Certificate> certificates = new ArrayList<Certificate>();
		try {
			EjbcaWS service = this.service.getService();
			List<org.sharpsw.ejbcacli.Certificate> certs = service.findCerts(userName, validOnly);
			for(org.sharpsw.ejbcacli.Certificate item : certs) {
				byte[] certContents = Base64.decodeBase64(item.getCertificateData());
				CertificateFactory factory = CertificateFactory.getInstance("X.509");
				Certificate certificate = factory.generateCertificate(new ByteArrayInputStream(certContents));
				certificates.add(certificate);
			}
			return certificates;
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client is not authorized to perform this operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server returned a generic error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		}
	}
	
	
	/**
	 * Returns a list of the certificates associated with the user informed.
	 * @param userName User in the EJBCA server.
	 * @param validOnly Flag indicating if only the valid certificates should be returned.
	 * @return List of the certificates associated with the user.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws EJBCAException Generic EJBCA server error.
	 * @throws CertificateException Certificate handling error.
	 */
	public List<String> findCertificatesBase64String(String userName, boolean validOnly) throws ServiceAuthDeniedException, EJBCAException, CertificateException {
		List<String> certificates = new ArrayList<String>();
		List<Certificate> certs = this.findCertificates(userName, validOnly);
		for(Certificate cert : certs) {
			String encoded = Base64.encodeBase64String(cert.getEncoded());
			certificates.add(encoded);
		}
		return certificates;
	}

	
	/**
	 * Handles the CSR processing and returns a certificate in the X509 format.
	 * @param userName User requesting the certificate.
	 * @param password Password in the system.
	 * @param csr CSR (Certificate signing request)
	 * @return Base64-encoded string representing the certificate.
	 * @throws CertificateException Certificate processing error occurs.
	 * @throws InvalidResponseTypeException If the response from the EJBCA server does not match the expected response.
	 * @throws ServiceAuthDeniedException If the client does not have authorization to perform such operation.
	 * @throws InvalidCertificateAuthorityException If the CA is not valid.
	 * @throws UserNotFoundException User is not in the EJBCA server.
	 * @throws EJBCAException Generic EJBCA error returned.
	 */
	public String requestCertificateX509Format(String userName, String password, String csr) throws CertificateException, InvalidResponseTypeException, ServiceAuthDeniedException, InvalidCertificateAuthorityException, UserNotFoundException, EJBCAException {
		try {
			EjbcaWS service = this.service.getService();
			CertificateResponse response = service.pkcs10Request(userName, password, csr, null, CERTIFICATE);
			String responseType = response.getResponseType();
			if(responseType.equalsIgnoreCase(CERTIFICATE)) {
				byte[] contents = response.getData();
				CertificateFactory factory = CertificateFactory.getInstance("X.509");
				Certificate certificate = factory.generateCertificate(new ByteArrayInputStream(contents));
				String base64 = Base64.encodeBase64String(certificate.getEncoded());
				return base64;
			} else {
				StringBuffer message = new StringBuffer();
				message.append("Expected response type is 'CERTIFICATE', but received '").append(responseType).append("'.");
				throw new InvalidResponseTypeException(message.toString());
			}
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client is not authorized to perform such operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate authority does not exist. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server returned a generic error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} catch (NotFoundException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The user does not exist in the EJBCA server. Message: '").append(exception.getMessage()).append("'.");
			throw new UserNotFoundException(message.toString(), exception);
		}
	}
	
	
	/**
	 * Handles the CSR processing and returns a certificate in the PKCS7 format.
	 * @param userName User requesting the certificate.
	 * @param password Password in the system.
	 * @param csr CSR (Certificate signing request)
	 * @return Base64-encoded string representing the certificate.
	 * @throws CertificateException Certificate processing error occurs.
	 * @throws InvalidResponseTypeException If the response from the EJBCA server does not match the expected response.
	 * @throws ServiceAuthDeniedException If the client does not have authorization to perform such operation.
	 * @throws InvalidCertificateAuthorityException If the CA is not valid.
	 * @throws UserNotFoundException User is not in the EJBCA server.
	 * @throws EJBCAException Generic EJBCA error returned.
	 */
	public String requestCertificatePKCS7Format(String userName, String password, String csr) throws InvalidResponseTypeException, ServiceAuthDeniedException, InvalidCertificateAuthorityException, EJBCAException, UserNotFoundException {
		try {
			EjbcaWS service = this.service.getService();
			CertificateResponse response = service.pkcs10Request(userName, password, csr, null, PKCS7);
			if(response.getResponseType().equalsIgnoreCase(PKCS7)) {
				byte[] contents = response.getData();
				String base64 = Base64.encodeBase64String(contents);
				return base64;
			} else {
				StringBuffer message = new StringBuffer();
				message.append("Expected response type is 'PKCS7', but received '").append(response.getResponseType()).append("'.");
				throw new InvalidResponseTypeException(message.toString());
			}
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client does not have authorization to perform such operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate authority does not exist. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server returned a generic error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} catch (NotFoundException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The user does not exist. Message: '").append(exception.getMessage()).append("'.");
			throw new UserNotFoundException(message.toString(), exception);
		}
	}
	
	
	/**
	 * Handles the CSR processing and returns a certificate in the PKCS7 with the chain format.
	 * @param userName User requesting the certificate.
	 * @param password Password in the system.
	 * @param csr CSR (Certificate signing request)
	 * @return Base64-encoded string representing the certificate.
	 * @throws CertificateException Certificate processing error occurs.
	 * @throws InvalidResponseTypeException If the response from the EJBCA server does not match the expected response.
	 * @throws ServiceAuthDeniedException If the client does not have authorization to perform such operation.
	 * @throws InvalidCertificateAuthorityException If the CA is not valid.
	 * @throws UserNotFoundException User is not in the EJBCA server.
	 * @throws EJBCAException Generic EJBCA error returned.
	 */
	public String requestCertificateWithChainPKCS7Format(String userName, String password, String csr) throws InvalidResponseTypeException, ServiceAuthDeniedException, InvalidCertificateAuthorityException, EJBCAException, UserNotFoundException {
		try {
			EjbcaWS service = this.service.getService();
			CertificateResponse response = service.pkcs10Request(userName, password, csr, null, PKCS7_WITH_CHAIN);
			String responseType = response.getResponseType();
			if(responseType.equalsIgnoreCase(PKCS7_WITH_CHAIN)) {
				byte[] contents = response.getData();
				String base64 = Base64.encodeBase64String(contents);
				return base64;
			} else {
				StringBuffer message = new StringBuffer();
				message.append("Expected response type is 'PKCS7WITHCHAIN' but received '").append(responseType).append("'.");
				throw new InvalidResponseTypeException(message.toString());
			}
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client is not authorized to perform such operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate authority does not exist. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server return a generic error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} catch (NotFoundException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The user does not exist in the EJBCA server. Message: '").append(exception.getMessage()).append("'.");
			throw new UserNotFoundException(message.toString(), exception);
		}
	}
	
	
	/**
	 * Retrieves the certificate indicated by the serial number and the issuer.
	 * @param serialNumber Certificate serial number.
	 * @param issuer Certificate issuer information.
	 * @return Certificate in the base-64 encoded format.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws InvalidCertificateAuthorityException The CA is not valid.
	 * @throws EJBCAException Generic EJBCA error.
	 * @throws CertificateException Certificate processing error.
	 */
	public String getCertificateBase64String(String serialNumber, String issuer) throws ServiceAuthDeniedException, InvalidCertificateAuthorityException, EJBCAException, CertificateException {
		try {
			EjbcaWS ws = this.service.getService();
			org.sharpsw.ejbcacli.Certificate cert = ws.getCertificate(serialNumber, issuer);
			String result = new String(cert.getCertificateData());
			return result;
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client is not authorized to perform such operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate authority is not valid. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server returned a generic error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} 
	}
	
	
	/**
	 * Retrieves the certificate indicated by the serial number and the issuer.
	 * @param serialNumber Certificate serial number.
	 * @param issuer Certificate issuer information.
	 * @return Certificate in the base-64 encoded format.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws InvalidCertificateAuthorityException The CA is not valid.
	 * @throws EJBCAException Generic EJBCA error.
	 * @throws CertificateException Certificate processing error.
	 */
	public Certificate getCertificate(String serialNumber, String issuer) throws ServiceAuthDeniedException, InvalidCertificateAuthorityException, EJBCAException, CertificateException {
		try {
			EjbcaWS ws = this.service.getService();
			org.sharpsw.ejbcacli.Certificate cert = ws.getCertificate(serialNumber, issuer);
			String base64Cert = new String(cert.getCertificateData());
			CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
			Certificate certificate = certFactory.generateCertificate(new ByteArrayInputStream(Base64.decodeBase64(base64Cert.getBytes())));
			return certificate;
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client is not authorized to perform such operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate authority is not valid. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server returned a generic error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} 
	}
	
	
	/**
	 * Returns the certificate chain for the informed certificate authority.
	 * @param certificateAuthority Certificate authority name.
	 * @return List of the certificates that make up the certificate chain.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws InvalidCertificateAuthorityException The CA is not valid.
	 * @throws EJBCAException Generic EJBCA error.
	 */
	public List<String> getCertificateAuthorityChain(String certificateAuthority) throws ServiceAuthDeniedException, InvalidCertificateAuthorityException, EJBCAException {
		try {
			List<String> certificates = new ArrayList<String>();
			EjbcaWS service = this.service.getService();
			List<org.sharpsw.ejbcacli.Certificate> certs = service.getLastCAChain(certificateAuthority);
			for(org.sharpsw.ejbcacli.Certificate item : certs) {
				String base64 = Base64.encodeBase64String(item.getCertificateData());
				certificates.add(base64);
			}
			return certificates;
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client is not authorized to perform such operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate authority is not valid. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server returned a generic error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		}
	}
}
