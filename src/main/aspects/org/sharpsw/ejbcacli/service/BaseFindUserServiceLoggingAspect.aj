package org.sharpsw.ejbcacli.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.sharpsw.ejbcacli.data.UserVO;

public aspect BaseFindUserServiceLoggingAspect {
	private static final Logger logger = Logger.getLogger(BaseFindUserService.class);
	
	pointcut findUsersExecution() : execution(public List<UserVO> BaseFindUserService.findUsers());
	before() : findUsersExecution() {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering BaseFindUserService.findUsers() method.");
		}
	}
	
	after() returning(List<UserVO> elements) : findUsersExecution() {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving BaseFindUserService.findUsers() method.");
		}
		
		if(logger.isDebugEnabled()) {
			if(elements != null) {
				logger.debug("The UserVO list is not null");
				if(!elements.isEmpty()) {
					logger.debug("And the list is not empty");
					for(UserVO item : elements) {
						StringBuffer line = new StringBuffer();
						line.append("User retrieved from the server -> userName = '").append(item.getUserName()).append("'; email = '").append(item.getEmail()).append("'; subjectDN = '").append(item.getSubjectDN()).append("'.");
						logger.debug(line.toString());
					}
				} else {
					logger.debug("But the list is empty");
				}
			} else {
				logger.debug("The UserVO list is null");
			}
		}
	}
}
