package org.sharpsw.ejbcacli.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateAuthorityVO;
import org.sharpsw.ejbcacli.factory.ca.CertificateAuthorityVOFactoryDefaultImpl;
import org.sharpsw.ejbcacli.factory.ca.ICertificateAuthorityVOFactory;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;

/**
 * Service class related to listing the certificate authorities registered in the EJBCA server.
 * @author andersonkmi
 *
 */
public class CertificateAuthorityListingService {
	private static final Logger logger = Logger.getLogger(CertificateAuthorityListingService.class);
	private EJBCAWebService service;
	private ICertificateAuthorityVOFactory factory;
	
	/**
	 * Constructor that receives an instance of web service wrapper.
	 * @param service Instance of the EJBCAWebService wrapper class.
	 * @throws NullArgumentException If the supplied argument is null.
	 */
	public CertificateAuthorityListingService(EJBCAWebService service) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The supplied EJBCAWebService argument is null");
		}
		
		this.service = service;
		this.factory = new CertificateAuthorityVOFactoryDefaultImpl();
	}
	
	/**
	 * Constructor that receives an instance of the web service wrapper and the CertificateAuthorityVO object factory.
	 * @param service EJBCAWebService wrapper instance.
	 * @param factory CertificateAuthorityVO factory instance.
	 * @throws NullArgumentException If one of the arguments is null.
	 */
	public CertificateAuthorityListingService(EJBCAWebService service, ICertificateAuthorityVOFactory factory) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The supplied EJBCAWebService argument is null");
		}
		
		if(factory == null) {
			throw new NullArgumentException("The supplied ICertificateAuthorityVOFactory argument is null");
		}
		
		this.service = service;
		this.factory = factory;
	}
	
	/**
	 * Obtains a list of the certificate authorities in the EJBCA server.
	 * @return List of objects of the class CertificateAuthorityVO representing each of the CAs in the EJBCA server.
	 * @throws ServiceAuthDeniedException If the user does not have access to obtain a list of CAs
	 * @throws EJBCAException If a generic error happens during this operation.
	 */
	public List<CertificateAuthorityVO> getCertificateAuthorities() throws ServiceAuthDeniedException, EJBCAException {
		List<CertificateAuthorityVO> elements = new ArrayList<CertificateAuthorityVO>();
		try {
			EjbcaWS webService = this.service.getService();
			if(webService != null) {				
				List<NameAndId> results = webService.getAvailableCAs();
				if(results != null) {				
					for(NameAndId item : results) {
						try {
							CertificateAuthorityVO element = this.factory.build(item);
							elements.add(element);					
						} catch (InvalidArgumentException exception) {
							// No action performed
						} catch (NullArgumentException exception) {
							// No action performed
						}
					}
				} else {
					logger.warn("The NameAndId result list is null");
				}
			} else {
				logger.fatal("The EJBCA web service instance is null");
			}
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuilder message = new StringBuilder();
			message.append("Authorization denied: ").append(exception.getMessage());
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuilder message = new StringBuilder();
			message.append("EJBCA error: ").append(exception.getMessage());
			throw new EJBCAException(message.toString(), exception);
		}
		return elements;
	}
	
	/**
	 * Returns a list of certificate authorities related to an end entity profile passed as argument to the method.
	 * @param endEntityProfileId End entity profile id number.
	 * @return List of CertificateProfileVO objects.
	 * @throws ServiceAuthDeniedException If the user does not have access to the service.
	 * @throws EJBCAException If a generic error happens.
	 */
	public List<CertificateAuthorityVO> getCertificateAuthoritiesInProfile(int endEntityProfileId) throws ServiceAuthDeniedException, EJBCAException {
		List<CertificateAuthorityVO> elements = new ArrayList<CertificateAuthorityVO>();
		try {
			EjbcaWS webService = this.service.getService();
			if(webService != null) {
				List<NameAndId> results = webService.getAvailableCAsInProfile(endEntityProfileId);
				if(results != null) {
					for(NameAndId item : results) {
						try {
							CertificateAuthorityVO element = this.factory.build(item);
							elements.add(element);					
						} catch (InvalidArgumentException exception) {
							// No action performed
						} catch (NullArgumentException exception) {
							// No action performed
						}
					}									
				}
			}
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuilder message = new StringBuilder();
			message.append("Authorization denied: ").append(exception.getMessage());
			throw new ServiceAuthDeniedException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuilder message = new StringBuilder();
			message.append("EJBCA error: ").append(exception.getMessage());
			throw new EJBCAException(message.toString(), exception);
		}
		return elements;
	}
}
