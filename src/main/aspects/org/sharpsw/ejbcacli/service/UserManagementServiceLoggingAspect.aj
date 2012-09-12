package org.sharpsw.ejbcacli.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.sharpsw.ejbcacli.data.UserVO;
import org.sharpsw.ejbcacli.service.exception.ApprovalException;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.IllegalQueryException;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.InvalidCertificateAuthorityException;
import org.sharpsw.ejbcacli.service.exception.MissingProfileInformationException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.PendingApprovalException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;

public aspect UserManagementServiceLoggingAspect {
	private static final Logger logger = Logger.getLogger(UserManagementService.class);
	
	pointcut createUserExec(UserVO user) : execution(public void UserManagementService.createUser(UserVO)) && args(user);
	
	
	before(UserVO user) : createUserExec(user) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering UserManagementService.createUser() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer("Creating a new user identified by '");
			log.append(user.getUserName()).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(UserVO user) : createUserExec(user) {
		logCreateUserLeaving();
	}
	
	after(UserVO user) throwing(ServiceAuthDeniedException exception) : createUserExec(user) {
		StringBuffer message = new StringBuffer();
		message.append("A ServiceAuthDeniedException has been raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(message.toString(), exception);
		
		logCreateUserLeaving();
	}
	
	after(UserVO user) throwing (NullArgumentException exception) : createUserExec(user) {
		StringBuffer message = new StringBuffer();
		message.append("A NullArgumentException has been raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(message.toString(), exception);
		
		logCreateUserLeaving();
	}
	
	after(UserVO user) throwing (InvalidCertificateAuthorityException exception) : createUserExec(user) {
		StringBuffer message = new StringBuffer();
		message.append("An InvalidCertificateAuthorityException has been raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(message.toString(), exception);
		
		logCreateUserLeaving();
	}
	
	after(UserVO user) throwing (ApprovalException exception) : createUserExec(user) {
		StringBuffer message = new StringBuffer();
		message.append("An ApprovalException has been raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(message.toString(), exception);
		
		logCreateUserLeaving();
	}
	
	after(UserVO user) throwing (PendingApprovalException exception) : createUserExec(user) {
		StringBuffer message = new StringBuffer();
		message.append("A PendingApprovalException has been raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(message.toString(), exception);
		
		logCreateUserLeaving();
	}
	
	after(UserVO user) throwing (MissingProfileInformationException exception) : createUserExec(user) {
		StringBuffer message = new StringBuffer();
		message.append("A MissingProfileInformationException has been raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(message.toString(), exception);
		
		logCreateUserLeaving();
	}
	
	after(UserVO user) throwing (EJBCAException exception) : createUserExec(user) {
		StringBuffer message = new StringBuffer();
		message.append("An EJBCAException has been raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(message.toString(), exception);

		logCreateUserLeaving();
	}
	
	after(UserVO user) throwing (IllegalQueryException exception) : createUserExec(user) {
		StringBuffer message = new StringBuffer();
		message.append("An IllegalQueryException has been raised. Message: '").append(exception.getMessage()).append("'.");
		logger.error(message.toString(), exception);
		
		logCreateUserLeaving();
	}
	
	private void logCreateUserLeaving() {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving UserManagementService.createUser() method.");
		}		
	}

	pointcut findUsersByCertAuthorityExec(String certificateAuthority, SearchMode mode) : execution(public List<UserVO> UserManagementService.findUsersByCertificateAuthority(String, SearchMode)) && args(certificateAuthority, mode);
	before(String certificateAuthority, SearchMode mode) : findUsersByCertAuthorityExec(certificateAuthority, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering UserManagementService.findUsersByCertificateAuthority() method.");
		}
		
		// Debug logging
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer("Finding users by certificate authority using the following parameters -> certificate authority = '");
			log.append(certificateAuthority).append("'; search mode = '").append(mode.toString()).append("'.");
			logger.debug(log.toString());
		}
	}
	
	private void logFindUsersByCertAuthorityExit() {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving UserManagementService.findUsersByCertificateAuthority() method.");
		}
	}
	
	after(String certificate, SearchMode mode) : findUsersByCertAuthorityExec(certificate, mode) {
		logFindUsersByCertAuthorityExit();
	}
	
	after(String ca, SearchMode mode) throwing (NullArgumentException exception) : findUsersByCertAuthorityExec(ca, mode) {
		StringBuffer log = new StringBuffer();
		log.append("A null argument has been supplied. Message: '").append(exception.getMessage()).append("'.");
		logger.error(log.toString(), exception);
		
		logFindUsersByCertAuthorityExit();
	}
	
	after(String ca, SearchMode mode) throwing (ServiceAuthDeniedException exception) : findUsersByCertAuthorityExec(ca, mode) {
		StringBuffer log = new StringBuffer();
		log.append("The request was denied due to lack of authorization. Message: '").append(exception.getMessage()).append("'.");
		logger.error(log.toString(), exception);
		
		logFindUsersByCertAuthorityExit();
	}
	
	after(String ca, SearchMode mode) throwing (InvalidArgumentException exception) : findUsersByCertAuthorityExec(ca, mode) {
		StringBuffer log = new StringBuffer();
		log.append("The search mode argument is not valid. Message: '").append(exception.getMessage()).append("'.");
		logger.error(log.toString(), exception);
		
		logFindUsersByCertAuthorityExit();
	}
	
	after(String ca, SearchMode mode) throwing (EJBCAException exception) : findUsersByCertAuthorityExec(ca, mode) {
		StringBuffer log = new StringBuffer();
		log.append("The EJBCA server returned an error. Message: '").append(exception.getMessage()).append("'.");
		logger.error(log.toString(), exception);
		
		logFindUsersByCertAuthorityExit();
	}
	
	after(String ca, SearchMode mode) throwing (IllegalQueryException exception) : findUsersByCertAuthorityExec(ca, mode) {
		StringBuffer log = new StringBuffer();
		log.append("Illegal query detected. Message: '").append(exception.getMessage()).append("'.");
		logger.error(log.toString(), exception);
		
		logFindUsersByCertAuthorityExit();
	}
	
	pointcut findUsersByCommonNameExec(String name, SearchMode mode) : execution(public List<UserVO> UserManagementService.findUsersByCommonName(String, SearchMode)) && args(name, mode);
	before(String name, SearchMode mode) : findUsersByCommonNameExec(name, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering UserManagementService.findUsersByCommonName() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Finding users by the common name information using the following parameters -> common name '").append(name).append("'; mode = '").append(mode.toString()).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String name, SearchMode mode) : findUsersByCommonNameExec(name, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving UserManagementService.findUsersByCommonName() method.");
		}
	}
	
	pointcut findUsersByEmailExec(String email, SearchMode mode) : execution(public List<UserVO> UserManagementService.findUsersByEmail(String, SearchMode)) && args(email, mode);
	before(String email, SearchMode mode) : findUsersByEmailExec(email, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering UserManagementService.findUsersByEmail() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Finding users by email using the following parameters -> email = '").append(email).append("'; mode = '").append(mode.toString()).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String email, SearchMode mode) : findUsersByEmailExec(email, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving UserManagementService.findUsersByEmail() method.");
		}
	}
	
	pointcut findUsersByEndEntityProfileExec(String profile, SearchMode mode) : execution(public List<UserVO> UserManagementService.findUsersByEndEntityProfile(String, SearchMode)) && args(profile, mode);
	before(String profile, SearchMode mode) : findUsersByEndEntityProfileExec(profile, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering UserManagementService.findUsersByEndEntityProfile() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Finding users based on the end entity profile name using the parameters -> profile = '").append(profile).append("'; mode = '").append(mode.toString()).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String profile, SearchMode mode) : findUsersByEndEntityProfileExec(profile, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving UserManagementService.findUsersByEndEntityProfile() method.");
		}
	}
	
	pointcut findUsersBySubjectDNExec(String dn, SearchMode mode) : execution(public List<UserVO> UserManagementService.findUsersBySubjectDN(String, SearchMode)) && args(dn, mode);
	before(String dn, SearchMode mode) : findUsersBySubjectDNExec(dn, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering UserManagementService.findUsersBySubjectDN() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Finding users by subject DN using the following parameters -> dn = '").append(dn).append("'; mode = '").append(mode.toString()).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String dn, SearchMode mode) : findUsersBySubjectDNExec(dn, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving UserManagementService.findUsersBySubjectDN() method.");
		}
	}
	
	pointcut findUsersByUserNameExec(String userName, SearchMode mode) : execution(public List<UserVO> UserManagementService.findUsersByUserName(String, SearchMode)) && args(userName, mode);
	before(String userName, SearchMode mode) : findUsersByUserNameExec(userName, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering UserManagementService.findUsersByUserName() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Finding users by user name information using the following parameters -> user name = '").append(userName).append("'; mode = '").append(mode.toString()).append("'.");
			logger.debug(log.toString());
		}
	}
	
	after(String userName, SearchMode mode) : findUsersByUserNameExec(userName, mode) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving UserManagementService.findUsersByUserName() method.");
		}
	}
	
	pointcut deleteUserExec(String userName) : execution(public void UserManagementService.deleteUser(String)) && args(userName);
	before(String user) : deleteUserExec(user) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering UserManagementService.deleteUser() method.");
		}
		
		if(logger.isDebugEnabled()) {
			StringBuffer log = new StringBuffer();
			log.append("Deleting user '").append(user).append("' from the EJBCA server.");
			logger.debug(log.toString());
		}
	}
	
	after(String user) : deleteUserExec(user) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving UserManagementService.deleteUser() method.");
		}		
	}
 }
