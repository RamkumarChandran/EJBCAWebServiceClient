package org.sharpsw.ejbcacli.service;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateProfileVO;
import org.sharpsw.ejbcacli.factory.certprofile.CertificateProfileVOFactoryDefaultImpl;
import org.sharpsw.ejbcacli.factory.certprofile.ICertificateProfileVOFactory;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;

/**
 * This service retrieves the certificate profiles registered in the EJBCA server.
 * @author andersonkmi
 *
 */
public class CertificateProfileListingService {
	private EJBCAWebService service;
	private ICertificateProfileVOFactory factory;
	
	/**
	 * Class constructor that receives the EJBCA web service wrapper ready to be used.
	 * @param service EJBCA web service wrapper.
	 * @throws NullArgumentException If the supplied instance is null.
	 */
	public CertificateProfileListingService(EJBCAWebService service) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The supplied EJBCAWebService argument is null");
		}
		this.service = service;
		this.factory = new CertificateProfileVOFactoryDefaultImpl();
	}
	
	/**
	 * Class constructor that receives a EJBCA web service wrapper and the instance builder for the CertificateProfileVO objects.
	 * @param service EJBCA web service wrapper.
	 * @param factory CertificateProfileVO factory instance.
	 * @throws NullArgumentException If the one of the supplied arguemnts is null.
	 */
	public CertificateProfileListingService(EJBCAWebService service, ICertificateProfileVOFactory factory) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The supplied EJBCAWebService argument is null");
		}
		
		if(factory == null) {
			throw new NullArgumentException("The supplied ICertificateProfileVOFactory argument is null");
		}
		
		this.service = service;
		this.factory = factory;
	}
	
	/**
	 * Returns a list of the certificate profiles registered in the EJBCA server given an end entity profile identification number.
	 * @param endEntityProfileId Identification number of an end entity profile.
	 * @return List of the CertificateProfileVO instances associated with the end entity profile.
	 * @throws ServiceAuthDeniedException If the service does not have the authorization.
	 * @throws EJBCAException If an error occurs during the service request.
	 */
	public List<CertificateProfileVO> getCertificateProfiles(int endEntityProfileId) throws ServiceAuthDeniedException, EJBCAException {
		List<CertificateProfileVO> results = new ArrayList<CertificateProfileVO>();
		try {
			EjbcaWS ws = this.service.getService();
			if(ws != null) {
				List<NameAndId> elements = this.service.getService().getAvailableCertificateProfiles(endEntityProfileId);
				if(elements != null) {
					for(NameAndId element : elements) {
						try {
							CertificateProfileVO item = this.factory.build(element);
							results.add(item);						
						} catch (NullArgumentException exception) {
							// No action performed
						} catch (InvalidArgumentException exception) {
							// No action performed
						}
					}									
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
