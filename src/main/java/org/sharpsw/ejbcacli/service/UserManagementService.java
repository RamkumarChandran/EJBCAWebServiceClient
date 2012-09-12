package org.sharpsw.ejbcacli.service;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AlreadyRevokedException_Exception;
import org.sharpsw.ejbcacli.ApprovalException_Exception;
import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.CADoesntExistsException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.NotFoundException_Exception;
import org.sharpsw.ejbcacli.UserDataVOWS;
import org.sharpsw.ejbcacli.UserDoesntFullfillEndEntityProfile_Exception;
import org.sharpsw.ejbcacli.WaitingForApprovalException_Exception;
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
import org.sharpsw.ejbcacli.service.exception.UserAlreadyRevokedException;
import org.sharpsw.ejbcacli.service.exception.UserNotFoundException;

/**
 * This class is responsible for the operations performed based on
 * the user information in the EJBCA server.
 * @author andersonkmi
 *
 */
public class UserManagementService {
	private EJBCAWebService service;
	
	/**
	 * Class constructor that received the EJBCA web service wrapper.
	 * @param service
	 * @throws NullArgumentException If the EJBCA web service wrapper is null.
	 */
	public UserManagementService(EJBCAWebService service) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The web service wrapper cannot be null");
		}
		this.service = service;
	}
	
	/**
	 * Creates a new user in the EJBCA server.
	 * @param user UserVO object containing the information required to create a new user.
	 * @throws ServiceAuthDeniedException If the client is not authorized to create a new user.
	 * @throws NullArgumentException If the supplied argument is null.
	 * @throws InvalidCertificateAuthorityException If the certificate authority associated to the user is not valid.
	 * @throws org.sharpsw.ejbcacli.service.exception.ApprovalException If there is already another request to create the same user.
	 * @throws PendingApprovalException If the request is put into an approval list.
	 * @throws MissingProfileInformationException If there is any information missing in order to create the user.
	 * @throws EJBCAException If a generic error occurs.
	 * @throws IllegalQueryException If the query is illegal.
	 */
	public void createUser(UserVO user) throws ServiceAuthDeniedException, NullArgumentException, InvalidCertificateAuthorityException, org.sharpsw.ejbcacli.service.exception.ApprovalException, PendingApprovalException, MissingProfileInformationException, EJBCAException, IllegalQueryException {
		if(user == null) {
			throw new NullArgumentException("UserVO argument is null");
		}
		
		try {
			EjbcaWS ws = this.service.getService();
			UserDataVOWS userVO = new UserDataVOWS();
			userVO.setCaName(user.getCertificateAuthority());
			userVO.setCertificateProfileName(user.getCertificateProfile());
			userVO.setClearPwd(user.isClearPassword());
			userVO.setEmail(user.getEmail());
			userVO.setEndEntityProfileName(user.getEndEntityProfile());
			userVO.setPassword(user.getPassword());
			userVO.setSubjectAltName(user.getSubjectAlternativeName());
			userVO.setSubjectDN(user.getSubjectDN());
			userVO.setTokenType(user.getTokenType().getType());
			userVO.setUsername(user.getUserName());
			ws.editUser(userVO);			
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("A generic error has occurred. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} catch (ApprovalException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("An approval exception has occured. Message: '").append(exception.getMessage()).append("'.");
			throw new ApprovalException(message.toString(), exception);
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("An authorization exception has occured. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Invalid certificate authority exception has occured. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (UserDoesntFullfillEndEntityProfile_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("User is not applicable to the end entity profile. Message: '").append(exception.getMessage()).append("'.");
			throw new MissingProfileInformationException(message.toString(), exception);
		} catch (WaitingForApprovalException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Waiting for approval. Message: '").append(exception.getMessage()).append("'.");
			throw new PendingApprovalException(message.toString(), exception);
		}
	}
	
	/**
	 * Removes an user from the EJBCA server and revokes all its certificates.
	 * @param userName String containing the user to be deleted.
	 * @throws NullArgumentException If the supplied user name is null.
	 * @throws InvalidArgumentException If the supplied user name string is empty.
	 * @throws UserAlreadyRevokedException If the user has already been revoked.
	 * @throws UserNotFoundException If the user does not exist in the EJBCA server.
	 * @throws PendingApprovalException If the request is put into an approval list.
	 * @throws InvalidCertificateAuthorityException If the certificate authority associated to the user is not valid.
	 * @throws EJBCAException If a generic error happens. 
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws ApprovalException If there is already another request in waiting for approval.
	 */
	public void deleteUser(String userName) throws NullArgumentException, 
	                                               InvalidArgumentException, 
	                                               UserAlreadyRevokedException, 
	                                               UserNotFoundException, 
	                                               PendingApprovalException, 
	                                               InvalidCertificateAuthorityException, 
	                                               EJBCAException,
	                                               ServiceAuthDeniedException,
	                                               ApprovalException {
		try {
			EjbcaWS ws = this.service.getService();
			ws.revokeUser(userName, CertificateRevocationReason.UNSPECIFIED.getCode(), true);
		} catch (AlreadyRevokedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The specified user has already been revoked. Message: '").append(exception.getMessage()).append("'.");
			throw new UserAlreadyRevokedException(message.toString(), exception);
		} catch (ApprovalException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("There is already an approval request. Message: '").append(exception.getMessage()).append("'.");
			throw new ApprovalException(message.toString(), exception);
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The client is not authorized to perform such operation. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The certificate authority does not exist. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The EJBCA server returned a generic error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} catch (NotFoundException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The user does not exist in the server. Message: '").append(exception.getMessage()).append("'.");
			throw new UserNotFoundException(message.toString(), exception);
		} catch (WaitingForApprovalException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("The request has been included in the approval queue. Message: '").append(exception.getMessage()).append("'.");
			throw new PendingApprovalException(message.toString(), exception);
		}
	}
		
	/**
	 * Returns a list of users that matches the certificate authority information passed to the method.
	 * @param certificateAuthority Certificate authority name.
	 * @param mode Search mode: equals, begins with or contains.
	 * @return List of objects that match the criteria informed; empty list if no elements are found.
	 * @throws NullArgumentException If the supplied arguments are null.
	 * @throws InvalidArgumentException If the certificate authority argument is an empty string.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws EJBCAException If a generic error happens.
	 * @throws IllegalQueryException Illegal query performed.
	 */
	public List<UserVO> findUsersByCertificateAuthority(String certificateAuthority, SearchMode mode) throws NullArgumentException, InvalidArgumentException, ServiceAuthDeniedException, EJBCAException, IllegalQueryException {
		List<UserVO> result = new ArrayList<UserVO>();
		BaseFindUserByCertificateAuthorityService service = null;
		if(mode == SearchMode.EQUALS) {
			service = new FindUserByCertificateAuthorityEqualsService(this.service, certificateAuthority);
		} else if(mode == SearchMode.BEGINS_WITH) {
			service = new FindUserByCertificateAuthorityBeginWithService(this.service, certificateAuthority);
		} else if(mode == SearchMode.CONTAINS) {
			service = new FindUserByCertificateAuthorityContainsService(this.service, certificateAuthority);
		} else {
			StringBuffer message = new StringBuffer();
			message.append("The value '").append(mode.toString()).append("' is not a valid search option");
			throw new InvalidArgumentException(message.toString());
		}		
		result = service.findUsers();
		return result;			
	}
	
	/**
	 * Finds the users that match the common name supplied.
	 * @param commonName Common name information.
	 * @param mode Search mode: equality, begins with or contains.
	 * @return List of UserVO instances which match the supplied criteria; empty list if not elements are found.
	 * @throws NullArgumentException If the common name argument is null.
	 * @throws InvalidArgumentException If the common name is an empty string.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws EJBCAException If a generic error happens.
	 * @throws IllegalQueryException Illegal query performed.
	 */
	public List<UserVO> findUsersByCommonName(String commonName, SearchMode mode) throws NullArgumentException, InvalidArgumentException, ServiceAuthDeniedException, EJBCAException, IllegalQueryException {
		List<UserVO> result = new ArrayList<UserVO>();
		BaseFindUserByCommonNameService service = null;
		if(mode == SearchMode.EQUALS) {
			service = new FindUserByCommonNameEqualsService(this.service, commonName);
		} else if(mode == SearchMode.BEGINS_WITH) {
			service = new FindUserByCommonNameBeginWithService(this.service, commonName);
		} else if(mode == SearchMode.CONTAINS) {
			service = new FindUserByCommonNameContainsService(this.service, commonName);
		} else {
			StringBuffer message = new StringBuffer();
			message.append("The value '").append(mode.toString()).append("' is not a valid search option");
			throw new InvalidArgumentException(message.toString());
		}
		result = service.findUsers();
		return result;
	}
	
	/**
	 * Finds the users that match the email informed.
	 * @param email Email information.
	 * @param mode Search mode: equals, begins with or contains.
	 * @return List of UserVO instances that match the criteria informed.
	 * @throws NullArgumentException If the email argument is null.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws EJBCAException If a generic error happens.
	 * @throws IllegalQueryException Illegal query performed.
	 * @throws InvalidArgumentException If the email is an empty string.
	 */
	public List<UserVO> findUsersByEmail(String email, SearchMode mode) throws NullArgumentException, ServiceAuthDeniedException, EJBCAException, IllegalQueryException, InvalidArgumentException {
		List<UserVO> result = new ArrayList<UserVO>();
		BaseFindUserByEmailService service = null;
		if(mode == SearchMode.EQUALS) {
			service = new FindUserByEmailEqualsService(this.service, email);
		} else if(mode == SearchMode.BEGINS_WITH) {
			service = new FindUserByEmailBeginWithService(this.service, email);
		} else if(mode == SearchMode.CONTAINS) {
			service = new FindUserByEmailContainsService(this.service, email);
		} else {
			StringBuffer message = new StringBuffer();
			message.append("The value '").append(mode.toString()).append("' is not a valid search option");
			throw new InvalidArgumentException(message.toString());
		}
		
		result = service.findUsers();
		return result;
	}
	
	/**
	 * Finds the users that match the end entity profile informed.
	 * @param profile End entity profile name.
	 * @param mode Search mode: equals, begins with or contains.
	 * @return List of UserVO instances that match the criteria above.
	 * @throws NullArgumentException If the profile argument is null.
	 * @throws InvalidArgumentException If the profile argument is an empty string.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws EJBCAException If a generic error happens.
	 * @throws IllegalQueryException Illegal query exception.
	 */
	public List<UserVO> findUsersByEndEntityProfile(String profile, SearchMode mode) throws NullArgumentException, InvalidArgumentException, ServiceAuthDeniedException, EJBCAException, IllegalQueryException {
		List<UserVO> result = new ArrayList<UserVO>();
		BaseFindUserByEndEntityProfileService service = null;
		if(mode == SearchMode.EQUALS) {
			service = new FindUserByEndEntityProfileEqualsService(this.service, profile);
		} else if(mode == SearchMode.BEGINS_WITH) {
			service = new FindUserByEndEntityProfileBeginWithService(this.service, profile);
		} else if(mode == SearchMode.CONTAINS) {
			service = new FindUserByEndEntityProfileContainsService(this.service, profile);
		} else {
			StringBuffer message = new StringBuffer();
			message.append("The value '").append(mode.toString()).append("' is not a valid search option");
			throw new InvalidArgumentException(message.toString());
		}
		
		result = service.findUsers();
		return result;
	}
	
	/**
	 * Finds the users that match the supplied subject DN.
	 * @param subjectDN Subject DN information.
	 * @param mode Search mode: equals, begins with or contains.
	 * @return List of UserVO instances that match the search criteria.
	 * @throws NullArgumentException If the subject DN argument is null.
	 * @throws InvalidArgumentException If the subject DN is an empty string.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws EJBCAException If a generic error happens.
	 * @throws IllegalQueryException Illegal query performed.
	 */
	public List<UserVO> findUsersBySubjectDN(String subjectDN, SearchMode mode) throws NullArgumentException, InvalidArgumentException, ServiceAuthDeniedException, EJBCAException, IllegalQueryException {
		List<UserVO> result = new ArrayList<UserVO>();
		BaseFindUserBySubjectDNService service = null;
		if(mode == SearchMode.EQUALS) {
			service = new FindUserBySubjectDNEqualsService(this.service, subjectDN);
		} else if(mode == SearchMode.BEGINS_WITH) {
			service = new FindUserBySubjectDNBeginWithService(this.service, subjectDN);
		} else if(mode == SearchMode.CONTAINS) {
			service = new FindUserBySubjectDNContainsService(this.service, subjectDN);
		} else {
			StringBuffer message = new StringBuffer();
			message.append("The value '").append(mode.toString()).append("' is not a valid search option");
			throw new InvalidArgumentException(message.toString());
		}
		
		result = service.findUsers();
		return result;
	}
	
	/**
	 * Finds the users that match the user name supplied.
	 * @param userName User name information.
	 * @param mode Search mode: equals, begins with or contains.
	 * @return List of UserVO instances that match the criteria.
	 * @throws NullArgumentException If the user name argument is null.
	 * @throws InvalidArgumentException If the user name argument is an empty string.
	 * @throws ServiceAuthDeniedException If the client is not authorized to perform such operation.
	 * @throws EJBCAException If a generic error happens.
	 * @throws IllegalQueryException Illegal query performed.
	 */
	public List<UserVO> findUsersByUserName(String userName, SearchMode mode) throws NullArgumentException, InvalidArgumentException, ServiceAuthDeniedException, EJBCAException, IllegalQueryException {
		List<UserVO> result = new ArrayList<UserVO>();
		BaseFindUserByUserNameService service = null;
		if(mode == SearchMode.EQUALS) {
			service = new FindUserByUserNameEqualsService(this.service, userName);
		} else if(mode == SearchMode.CONTAINS) {
			service = new FindUserByUserNameContainsService(this.service, userName);
		} else if(mode == SearchMode.BEGINS_WITH) {
			service = new FindUserByUserNameBeginWithService(this.service, userName);
		} else {
			StringBuffer message = new StringBuffer();
			message.append("The value '").append(mode.toString()).append("' is not a valid search option");
			throw new InvalidArgumentException(message.toString());
		}
		
		result = service.findUsers();
		return result;
	}
}
