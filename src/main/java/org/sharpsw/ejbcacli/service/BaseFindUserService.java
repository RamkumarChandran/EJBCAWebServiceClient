package org.sharpsw.ejbcacli.service;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.IllegalQueryException_Exception;
import org.sharpsw.ejbcacli.UserDataVOWS;
import org.sharpsw.ejbcacli.UserMatch;
import org.sharpsw.ejbcacli.data.TokenType;
import org.sharpsw.ejbcacli.data.UserStatus;
import org.sharpsw.ejbcacli.data.UserVO;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.IllegalQueryException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;

/**
 * Base class for user finding service.
 * @author andersonkmi
 *
 */
public abstract class BaseFindUserService {
	private EJBCAWebService service;
	
	/**
	 * Constructor that accepts the EJBCA web service wrapper.
	 * @param service EJBCA web service wrapper instance.
	 * @throws NullArgumentException If the supplied argument is null.
	 */
	public BaseFindUserService(EJBCAWebService service) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("EJBCA web service cannot be null");
		}
		this.service = service;
	}
	
	/**
	 * Executes the user search procedure.
	 * @return List of UserVO elements that match the search criteria.
	 * @throws ServiceAuthDeniedException If the requestor is not authorized.
	 * @throws EJBCAException If the EJBCA server returns an error.
	 * @throws IllegalQueryException Invalid query.
	 */
	public List<UserVO> findUsers() throws ServiceAuthDeniedException, EJBCAException, IllegalQueryException {
		List<UserVO> result = new ArrayList<UserVO>();
		EjbcaWS ws = this.service.getService();
		UserMatch criteria = new UserMatch();
		criteria.setMatchtype(getSearchMatchType());
		criteria.setMatchwith(getSearchMatchInformation());
		criteria.setMatchvalue(getCriteriaValue());
		try {
			List<UserDataVOWS> elements = ws.findUser(criteria);
			if(elements != null && !elements.isEmpty()) {
				for(UserDataVOWS element : elements) {
					UserVO item = new UserVO();
					item.setCertificateAuthority(element.getCaName());
					item.setCertificateProfile(element.getCertificateProfileName());
					item.setClearPassword(element.isClearPwd());
					item.setEmail(element.getEmail());
					item.setEndEntityProfile(element.getEndEntityProfileName());
					item.setPassword(element.getPassword());
					item.setSubjectAlternativeName(element.getSubjectAltName());
					item.setSubjectDN(element.getSubjectDN());					
					item.setUserName(element.getUsername());
					String token = element.getTokenType();
					if(token.equals("USERGENERATED")) {
						item.setTokenType(TokenType.USER_GENERATED);
					} else if(token.equals("PEM")) {
						item.setTokenType(TokenType.PEM);
					} else if(token.equals("P12")) {
						item.setTokenType(TokenType.P12);
					} else if(token.equals("JKS")) {
						item.setTokenType(TokenType.JKS);
					}
					
					if(element.getStatus() == UserStatus.NEW.getStatusCode()) {
						item.setUserStatus(UserStatus.NEW);
					} else if(element.getStatus() == UserStatus.FAILED.getStatusCode()) {
						item.setUserStatus(UserStatus.FAILED);
					} else if(element.getStatus() == UserStatus.GENERATED.getStatusCode()) {
						item.setUserStatus(UserStatus.GENERATED);
					} else if(element.getStatus() == UserStatus.HISTORICAL.getStatusCode()) {
						item.setUserStatus(UserStatus.HISTORICAL);
					} else if(element.getStatus() == UserStatus.IN_PROCESS.getStatusCode()) {
						item.setUserStatus(UserStatus.IN_PROCESS);						
					} else if(element.getStatus() == UserStatus.INITIALIZED.getStatusCode()) {
						item.setUserStatus(UserStatus.INITIALIZED);
					} else if(element.getStatus() == UserStatus.KEY_RECOVERY.getStatusCode()) {
						item.setUserStatus(UserStatus.KEY_RECOVERY);
					} else if(element.getStatus() == UserStatus.REVOKED.getStatusCode()) {
						item.setUserStatus(UserStatus.REVOKED);
					}
					
					result.add(item);
				}
			}
			return result;
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Authorization denied exception raised. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("A generic EJBCA server exception has raised. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(message.toString(), exception);
		} catch (IllegalQueryException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Illegal query exception has been raised. Message: '").append(exception.getMessage()).append("'.");
			throw new IllegalQueryException(message.toString(), exception);
		}
	}
	
	abstract protected int getSearchMatchType();
	
	abstract protected int getSearchMatchInformation();
	
	abstract protected String getCriteriaValue();
}

