package org.sharpsw.ejbcacli.factory.certprofile;

import org.apache.log4j.Logger;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateProfileVO;
import org.sharpsw.ejbcacli.factory.certprofile.ICertificateProfileVOFactory;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

// Anderson
public aspect ICertProfileFactoryLoggingAspect {
	private static final Logger logger = Logger.getLogger(ICertificateProfileVOFactory.class);

	pointcut buildExecution(NameAndId record) : execution(public CertificateProfileVO ICertificateProfileVOFactory+.build(NameAndId)) && args(record);
	
	before(NameAndId record) : buildExecution(record) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering ICertificateProfileVOFactory.build() method");
		}
	}
	
	after(NameAndId record) : buildExecution(record) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving ICertificateProfileVOFactory.build() method");
		}
	}
	
	after(NameAndId record) throwing(NullArgumentException exception) : buildExecution(record) {
		String log = String.format("Cannot supply a null NameAndId argument. Message: '%s'", exception.getMessage());
		logger.error(log, exception);
		
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving ICertificateProfileVOFactory.build() method");
		}
	}
	
	after(NameAndId record) throwing(InvalidArgumentException exception) : buildExecution(record) {
		String log = String.format("Cannot supply an NameAndId argument with null values. Message: '%s'", exception.getMessage());
		logger.error(log, exception);
		
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving ICertificateProfileVOFactory.build() method");
		}
	}
}
