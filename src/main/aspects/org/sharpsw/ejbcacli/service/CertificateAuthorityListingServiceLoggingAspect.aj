package org.sharpsw.ejbcacli.service;

import org.apache.log4j.Logger;

public aspect CertificateAuthorityListingServiceLoggingAspect {
	private static final Logger logger = Logger.getLogger(CertificateAuthorityListingService.class);
	
	pointcut getCertAuthoritiesExecution() : execution(public * CertificateAuthorityListingService.getCertificateAuthorities());
	
	before() : getCertAuthoritiesExecution() {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateAuthorityListingService.getCertificateAuthorities() method.");
		}
	}
	
	after() : getCertAuthoritiesExecution() {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateAuthorityListingService.getCertificateAuthorities() method.");
		}
	}
	
	pointcut getCertificateAuthoritiesEndEntityProfile(int endEntity) : execution(public * CertificateAuthorityListingService.getCertificateAuthoritiesInProfile(int)) && args(endEntity);
	
	before(int endEntity) : getCertificateAuthoritiesEndEntityProfile(endEntity) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateAuthorityListingService.getCertificateAuthoritiesInProfile() method");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Searching for certificate authorities related to end entity profile id = '").append(endEntity).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(int endEntity) : getCertificateAuthoritiesEndEntityProfile(endEntity) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateAuthorityListingService.getCertificateAuthoritiesInProfile() method");
		}
	}
}
