package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.ApprovalException_Exception;
import org.sharpsw.ejbcacli.ApprovalRequestExpiredException_Exception;
import org.sharpsw.ejbcacli.CADoesntExistsException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.service.exception.CRLCreationException;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

/**
 * This service is responsible for requesting the creation of the certificate revocation 
 * list for a given certificate authority.
 * @author andersonkmi
 *
 */
public class CRLCreationService {
	private EJBCAWebService service;
	
	/**
	 * Constructor that receives an instance of the EJBCA web service wrapper.
	 * @param service EJBCA web service wrapper instance.
	 * @throws NullArgumentException If the supplied argument is null.
	 */
	public CRLCreationService(EJBCAWebService service) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("The service argument cannot be null");
		}
		this.service = service;
	}
	
	/**
	 * Creates the CRL for a given certificate authority.
	 * @param certificateAuthorityName Certificate authority name to create a CRL.
	 * @throws EJBCAException If an EJBCA server error occurs.
	 * @throws CRLCreationException If an error occurs when creating a CRL.
	 * @throws NullArgumentException If the supplied argument is null.
	 * @throws InvalidArgumentException If the supplied argument is empty.
	 */
	public void createCRL(String certificateAuthorityName) throws EJBCAException, CRLCreationException, NullArgumentException, InvalidArgumentException {
		if(certificateAuthorityName == null) {
			throw new NullArgumentException("The certificateAuthorityName argument cannot be null");
		}
		
		if(certificateAuthorityName.isEmpty()) {
			throw new InvalidArgumentException("The certificate authority name argument cannot be empty");
		}
		
		EjbcaWS service = this.service.getService();
		try {
			if(service != null) {				
				service.createCRL(certificateAuthorityName);
			} else {
				throw new CRLCreationException("The web service instance is null");
			}
		} catch (ApprovalException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Error when creating the CRL for certificate authority '").append(certificateAuthorityName).append("'.");
			throw new CRLCreationException(message.toString(), exception);
		} catch (ApprovalRequestExpiredException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Error when creating the CRL for certificate authority '").append(certificateAuthorityName).append("'.");
			throw new CRLCreationException(message.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Error when creating the CRL for certificate authority '").append(certificateAuthorityName).append("'.");
			throw new CRLCreationException(message.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer message = new StringBuffer();
			message.append("Error when creating the CRL for certificate authority '").append(certificateAuthorityName).append("'.");
			throw new EJBCAException(message.toString(), exception);
		}
	}
}
