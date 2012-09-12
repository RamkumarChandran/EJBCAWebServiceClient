package org.sharpsw.ejbcacli.factory.entityprofile;

import org.apache.log4j.Logger;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.EndEntityProfileVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public aspect IEndEntityProfileVOFactoryLoggingAspect {
	private static final Logger logger = Logger.getLogger(IEndEntityProfileVOFactory.class);
	
	pointcut buildExec(NameAndId record) : execution(public EndEntityProfileVO IEndEntityProfileVOFactory+.build(NameAndId)) && args(record);
	
	before(NameAndId record) : buildExec(record) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering IEndEntityProfileVOFactory.build() method");
		}
	}
	
	after(NameAndId record) : buildExec(record) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving IEndEntityProfileVOFactory.build() method");
		}
	}
	
	after(NameAndId record) throwing(NullArgumentException exception) : buildExec(record) {
		String log = String.format("Cannot pass a null NameAndId argument. Message: %s", exception.getMessage());
		logger.error(log, exception);
		
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving IEndEntityProfileVOFactory.build() method");
		}
	}
	
	after(NameAndId record) throwing(InvalidArgumentException exception) : buildExec(record) {
		String log = String.format("Cannot pass a NameAndId argument with null value. Message: %s", exception.getMessage());
		logger.error(log, exception);
		
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving IEndEntityProfileVOFactory.build() method");
		}
	}
}
