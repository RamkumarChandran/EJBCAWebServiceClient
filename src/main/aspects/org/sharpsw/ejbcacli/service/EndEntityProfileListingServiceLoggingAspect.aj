package org.sharpsw.ejbcacli.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.sharpsw.ejbcacli.data.EndEntityProfileVO;

public aspect EndEntityProfileListingServiceLoggingAspect {
	private static final Logger logger = Logger.getLogger(EndEntityProfileListingService.class);
	
	pointcut getEndEntityProfilesExec() : execution(public List<EndEntityProfileVO> EndEntityProfileListingService.getEndEntityProfiles());
	
	before() : getEndEntityProfilesExec() {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering EndEntityProfileListingService.getEndEntityProfiles() method.");
		}
	}
	
	after() : getEndEntityProfilesExec() {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving EndEntityProfileListingService.getEndEntityProfiles() method.");
		}
	}
}
