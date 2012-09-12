package org.sharpsw.ejbcacli.service;

import java.security.cert.Certificate;
import java.util.List;
import java.util.regex.Pattern;

import javax.naming.InvalidNameException;
import javax.naming.ldap.Rdn;

import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;



public aspect CertificateManagementServiceValidationAspect {
	pointcut revokeCertificateExecution(String issuer, String serialNumber, CertificateRevocationReason reason) : execution(public void CertificateManagementService.revokeCertificate(String, String, CertificateRevocationReason)) && args(issuer, serialNumber, reason);
	before(String issuer, String serialNumber, CertificateRevocationReason reason) throws NullArgumentException, InvalidArgumentException : revokeCertificateExecution(issuer, serialNumber, reason) {
		if(issuer == null) {
			throw new NullArgumentException("The issuerDN argument cannot be null");
		}
		
		if(issuer.isEmpty()) {
			throw new InvalidArgumentException("The issuerDN argument cannot be empty");
		}
		
		try {
			Rdn dn = new Rdn(issuer);
			if(dn.size() == 0) {
				throw new InvalidArgumentException("The issuerDN is not valid");
			}
		} catch (InvalidNameException exception) {
			StringBuffer message = new StringBuffer();
			message.append("The issuerDN is not valid. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidArgumentException(message.toString(), exception);
		}
		
		
		if(serialNumber == null) {
			throw new NullArgumentException("The serialNumber argument cannot be null");
		}
		
		if(serialNumber.isEmpty()) {
			throw new InvalidArgumentException("The serialNumber argument cannot be empty");
		}
		
		Pattern hex = Pattern.compile("[0-9A-Fa-f]+");
		if(!hex.matcher(serialNumber).matches()) {
			throw new InvalidArgumentException("The serialNumber argument must be a valid hex number");
		}
				
		if(reason == null) {
			throw new NullArgumentException("The certificate reason argument cannot be null");
		}
		
		if(reason == CertificateRevocationReason.NOT_REVOKED) {
			throw new InvalidArgumentException("The revocation reason cannot be NOT_REVOKED");
		}
	}
	
	pointcut findCertificatesExec(String userName, boolean validOnly) : execution(public List<Certificate> CertificateManagementService.findCertificates(String, boolean)) && args(userName, validOnly);
	before(String userName, boolean validOnly) throws NullArgumentException, InvalidArgumentException : findCertificatesExec(userName, validOnly) {
		if(userName == null) {
			throw new NullArgumentException("The userName argument cannot be null");
		}
		
		if(userName.isEmpty()) {
			throw new InvalidArgumentException("The userName argument cannot be empty");
		}
	}
	
	pointcut findCertificatesBase64StringExec(String userName, boolean validOnly) : execution(public List<String> CertificateManagementService.findCertificatesBase64String(String, boolean)) && args(userName, validOnly);
	before(String userName, boolean validOnly) throws NullArgumentException, InvalidArgumentException : findCertificatesBase64StringExec(userName, validOnly) {
		if(userName == null) {
			throw new NullArgumentException("The userName argument cannot be null");
		}
		
		if(userName.isEmpty()) {
			throw new InvalidArgumentException("The userName argument cannot be empty");
		}
	}
	
	pointcut requestCertificateX509FormatExec(String userName, String password, String csr) : execution(public String CertificateManagementService.requestCertificate*(String, String, String)) && args(userName, password, csr);
	before(String userName, String password, String csr) throws NullArgumentException, InvalidArgumentException : requestCertificateX509FormatExec(userName, password, csr) {
		if(userName == null) {
			throw new NullArgumentException("The userName argument cannot be null");
		}
		
		if(userName.isEmpty()) {
			throw new InvalidArgumentException("The userName argument cannot be empty");
		}
		
		if(password == null) {
			throw new NullArgumentException("The password argument cannot be null");
		}
		
		if(password.isEmpty()) {
			throw new InvalidArgumentException("The password argument cannot be empty");
		}
		
		if(csr == null) {
			throw new NullArgumentException("The CSR argument cannot be null");
		}
		
		if(csr.isEmpty()) {
			throw new InvalidArgumentException("The CSR argument cannot be empty");
		}
	}
	
	pointcut getCertificateExec(String serialNumber, String issuer) : execution(public * CertificateManagementService.getCertificate*(String, String)) && args(serialNumber, issuer);
	before(String serialNumber, String issuer) throws NullArgumentException, InvalidArgumentException : getCertificateExec(serialNumber, issuer) {
		if(serialNumber == null) {
			throw new NullArgumentException("The serialNumber argument cannot be null");
		}
		
		if(serialNumber.isEmpty()) {
			throw new InvalidArgumentException("The serialNumber argument cannot be empty");
		}

		Pattern hex = Pattern.compile("[0-9A-Fa-f]+");
		if(!hex.matcher(serialNumber).matches()) {
			throw new InvalidArgumentException("The serialNumber argument must be a valid hex number");
		}

		if(issuer == null) {
			throw new NullArgumentException("The issuer argument cannot be null");
		}
		
		if(issuer.isEmpty()) {
			throw new InvalidArgumentException("The issuer argument cannot be empty");
		}
		
		try {
			Rdn dn = new Rdn(issuer);
			if(dn.size() == 0) {
				throw new InvalidArgumentException("The issuerDN is not valid");
			}
		} catch (InvalidNameException exception) {
			StringBuffer message = new StringBuffer();
			message.append("The issuerDN is not valid. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidArgumentException(message.toString(), exception);
		}
	}
	
	pointcut getCertAuthChainExec(String ca) : execution(public * CertificateManagementService.getCertificateAuthorityChain(String)) && args(ca);
	before(String ca) throws NullArgumentException, InvalidArgumentException : getCertAuthChainExec(ca) {
		if(ca == null) {
			throw new NullArgumentException("The authority argument cannot be null");
		}
		
		if(ca.isEmpty()) {
			throw new InvalidArgumentException("The authority argument cannot be empty");
		}
	}
}
