package org.sharpsw.ejbcacli.service;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.EndEntityProfileVO;
import org.sharpsw.ejbcacli.factory.entityprofile.IEndEntityProfileVOFactory;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;

/**
 * This class implements the service responsible for listing the end entity 
 * profiles registered in the EJBCA server.
 * @author andersonkmi
 *
 */
public class EndEntityProfileListingService {
	private EJBCAWebService service;
	private IEndEntityProfileVOFactory factory;

	/**
	 * Constructor that receives the EJBCA wrapper service instance.
	 * @param service EJBCA web service wrapper object.
	 * @throws NullArgumentException If the supplied argument is null.
	 */
	public EndEntityProfileListingService(EJBCAWebService service) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The supplied EJBCAWebService argument is null");
		}
		this.service = service;		
	}
	
	/**
	 * Constructor that receives an instance of the EJBCA web service wrapper and the object factory for end entity profile 
	 * value objects.
	 * @param service EJBCA web service wrapper instance.
	 * @param factory Object factory for IEndEntityProfileVO objects.
	 * @throws NullArgumentException If one of the arguments is null
	 */
	public EndEntityProfileListingService(EJBCAWebService service, IEndEntityProfileVOFactory factory) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The supplied EJBCAWebService argument is null");
		}
		
		if(factory == null) {
			throw new NullArgumentException("The supplied IEndEntityProfileVOFactory argument is null");
		}
		
		this.service = service;
		this.factory = factory;
	}
	
	/**
	 * Returns a list of the end entity profiles registered in the EJBCA server.
	 * @return List of instances of the class EndEntityProfileVO.
	 * @throws ServiceAuthDeniedException If the caller does not have authorization to perform the operation.
	 * @throws EJBCAException Generic EJBCA server error.
	 */
	public List<EndEntityProfileVO> getEndEntityProfiles() throws ServiceAuthDeniedException, EJBCAException {
		List<EndEntityProfileVO> results = new ArrayList<EndEntityProfileVO>();
		try {
			List<NameAndId> elements = this.service.getService().getAuthorizedEndEntityProfiles();
			if(elements != null) {
				try {					
					for(NameAndId element : elements) {
						EndEntityProfileVO item = this.factory.build(element);
						results.add(item);
					}				
				} catch (NullArgumentException exception) {
					// No action performed
				} catch (InvalidArgumentException exception) {
					// No action performed
				}
			}
		} catch (AuthorizationDeniedException_Exception exception) {
			String message = String.format("Authorization denied: %s", exception.getMessage());
			throw new ServiceAuthDeniedException(message, exception);
		} catch (EjbcaException_Exception exception) {
			String message = String.format("EJBCA exception: %s", exception.getMessage());
			throw new EJBCAException(message, exception);
		}
		return results;
	}
}
