package org.sharpsw.ejbcacli.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.sharpsw.ejbcacli.data.CertificateProfileVO;

public aspect CertificateProfileListingServiceLoggingAspect {
	private static final Logger logger = Logger.getLogger(CertificateProfileListingService.class);
	
	pointcut getCertificateProfilesExec(int endEntity) : execution(public List<CertificateProfileVO> CertificateProfileListingService.getCertificateProfiles(int)) && args(endEntity);
	
	before(int endEntity) : getCertificateProfilesExec(endEntity) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CertificateProfileListingService.getCertificateProfiles() method.");
		}
	}
	
	after(int endEntity) : getCertificateProfilesExec(endEntity) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CertificateProfileListingService.getCertificateProfiles() method.");
		}
	}
}
