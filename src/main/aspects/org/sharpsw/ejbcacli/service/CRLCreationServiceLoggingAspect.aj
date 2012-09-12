package org.sharpsw.ejbcacli.service;

import org.apache.log4j.Logger;

public aspect CRLCreationServiceLoggingAspect {
	private static final Logger logger = Logger.getLogger(CRLCreationService.class);
	
	pointcut createCRLExec(String name) : execution(public void CRLCreationService.createCRL(String)) && args(name);
	
	before(String name) : createCRLExec(name) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering CRLCreationService.createCRL() method.");
		}
	}
	
	after(String name) : createCRLExec(name) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving CRLCreationService.createCRL() method.");
		}
	}
}
