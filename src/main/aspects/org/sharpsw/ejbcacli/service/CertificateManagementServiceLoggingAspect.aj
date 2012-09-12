package org.sharpsw.ejbcacli.service;

import java.security.cert.Certificate;
import java.util.List;

import org.apache.log4j.Logger;

public aspect CertificateManagementServiceLoggingAspect {
	private static final Logger logger = Logger.getLogger(CertificateManagementService.class);
	
	pointcut revokeCertExec(String issuerDN, String serialNumber, CertificateRevocationReason reason) : execution(public void CertificateManagementService.revokeCertificate(String, String, CertificateRevocationReason)) && args(issuerDN, serialNumber, reason);
	before(String issuerDN, String serialNumber, CertificateRevocationReason reason) : revokeCertExec(issuerDN, serialNumber, reason) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.revokeCertificate() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Revoking a certificate using the following parameters -> issuer = '").append(issuerDN).append("'; serialNumber = '").append(serialNumber).append("'; reason = '").append(reason.name()).append("'.");
			logger.debug(log.toString());
		}
	}
		
	after(String issuerDN, String serialNumber, CertificateRevocationReason reason) : revokeCertExec(issuerDN, serialNumber, reason) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.revokeCertificate() method.");
		}
	}
	
	pointcut findCertificatesExec(String userName, boolean validOnly) : execution(public List<Certificate> CertificateManagementService.findCertificates(String, boolean)) && args(userName, validOnly);
	before(String userName, boolean validOnly) : findCertificatesExec(userName, validOnly) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.findCertificates() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Finding certificates for user '").append(userName).append("' and valid certificates -> '").append(validOnly).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String userName, boolean validOnly) : findCertificatesExec(userName, validOnly) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.findCertificates() method.");
		}
	}
	
	pointcut findCertificatesBase64StringExec(String userName, boolean validOnly) : execution(public List<String> CertificateManagementService.findCertificatesBase64String(String, boolean)) && args(userName, validOnly);
	before(String userName, boolean validOnly) : findCertificatesBase64StringExec(userName, validOnly) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.findCertificatesBase64String() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Finding certificates for user = '").append(userName).append("' and valid certificates = '").append(validOnly).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String userName, boolean validOnly) : findCertificatesBase64StringExec(userName, validOnly) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.findCertificatesBase64String() method.");
		}
	}
	
	pointcut requestCertificateX509FormatExec(String userName, String password, String csr) : execution(public String CertificateManagementService.requestCertificateX509Format(String, String, String)) && args(userName, password, csr);
	before(String userName, String password, String csr) : requestCertificateX509FormatExec(userName, password, csr) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.requestCertificateX509Format() method");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Requesting certificate (X509 format) for user = '").append(userName).append("'; csr = '").append(csr).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String userName, String password, String csr) : requestCertificateX509FormatExec(userName, password, csr) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.requestCertificateX509Format() method");
		}
	}
	
	pointcut requestCertificatePKCS7FormatExec(String userName, String password, String csr) : execution(public String CertificateManagementService.requestCertificatePKCS7Format(String, String, String)) && args(userName, password, csr);
	before(String userName, String password, String csr) : requestCertificatePKCS7FormatExec(userName, password, csr) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.requestCertificatePKCS7Format() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Requesting certificate (PKCS7 format) for user = '").append(userName).append("'; csr = '").append(csr).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String userName, String password, String csr) : requestCertificatePKCS7FormatExec(userName, password, csr) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.requestCertificatePKCS7Format() method.");
		}
	}
	
	pointcut requestCertificatePKCS7WithChainFormatExec(String user, String password, String csr) : execution(public String CertificateManagementService.requestCertificateWithChainPKCS7Format(String, String, String)) && args(user, password, csr);
	before(String user, String password, String csr) : requestCertificatePKCS7WithChainFormatExec(user, password, csr) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.requestCertificateWithChainPKCS7Format() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Requesting certificate and its chain (PKCS7 format) for user = '").append(user).append("'; csr = '").append(csr).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String user, String password, String csr) : requestCertificatePKCS7WithChainFormatExec(user, password, csr) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.requestCertificateWithChainPKCS7Format() method.");
		}
	}
	
	pointcut getCertificateExec(String user, String serialNumber) : execution(public Certificate CertificateManagementService.getCertificate(String, String)) && args(user, serialNumber);
	before(String user, String serialNumber) : getCertificateExec(user, serialNumber) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.getCertificate() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Obtaining the certificate for user = '").append(user).append("'; serial = '").append(serialNumber).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String user, String serialNumber) : getCertificateExec(user, serialNumber) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.getCertificate() method.");
		}
	}
	
	pointcut getCertificateBase64StringExec(String user, String serialNumber) : execution(public String CertificateManagementService.getCertificateBase64String(String, String)) && args(user, serialNumber);
	before(String user, String serialNumber) : getCertificateBase64StringExec(user, serialNumber) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.getCertificateBase64String() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Obtaining the certificate for user = '").append(user).append("'; serial = '").append(serialNumber).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String user, String serialNumber) : getCertificateBase64StringExec(user, serialNumber) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.getCertificateBase64String() method.");
		}
	}
	
	pointcut getCertificateAuthorityChainExec(String ca) : execution(public List<String> CertificateManagementService.getCertificateAuthorityChain(String)) && args(ca);
	before(String ca) : getCertificateAuthorityChainExec(ca) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateManagementService.getCertificateAuthorityChain() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Obtaining the certificate chain for CA = '").append(ca).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String ca) : getCertificateAuthorityChainExec(ca) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateManagementService.getCertificateAuthorityChain() method.");
		}
	}
}
