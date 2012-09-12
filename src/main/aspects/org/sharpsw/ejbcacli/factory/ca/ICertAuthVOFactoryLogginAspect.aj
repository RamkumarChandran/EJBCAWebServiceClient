package org.sharpsw.ejbcacli.factory.ca;

import org.apache.log4j.Logger;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateAuthorityVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public aspect ICertAuthVOFactoryLogginAspect {
	private static final Logger logger = Logger.getLogger(ICertificateAuthorityVOFactory.class);
	
	pointcut buildExecution(NameAndId original) : execution(public CertificateAuthorityVO ICertificateAuthorityVOFactory+.build(NameAndId)) && args(original);
	
	before(NameAndId original) : buildExecution(original) {
		if(logger.isTraceEnabled()) {
			logger.trace("Enering ICertificateAuthorityVOFactory.build() method.");
		}
		
		if(logger.isDebugEnabled() && original != null) {
			StringBuffer buffer = new StringBuffer();
			buffer.append("Creating a new instance of CertificateAuthorityVO using the following values: id = '").append(original.getId()).append("'; name = '").append(original.getName()).append("'.");
			logger.debug(buffer.toString());
		}
	}
	
	after(NameAndId record) throwing(NullArgumentException exception) : buildExecution(record) {
		StringBuffer log = new StringBuffer();
		log.append("A NullArgumentException has raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(log.toString(), exception);
		
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving ICertificateAuthorityVOFactory.build() method.");
		}
	}
	
	after(NameAndId record) throwing(InvalidArgumentException exception) : buildExecution(record) {
		StringBuffer log = new StringBuffer();
		log.append("An InvalidArgumentException has raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(log.toString(), exception);
		
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving ICertificateAuthorityVOFactory.build() method.");
		}
	}
	
	after(NameAndId record) : buildExecution(record) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving ICertificateAuthorityVOFactory.build() method.");
		}
	}
}
