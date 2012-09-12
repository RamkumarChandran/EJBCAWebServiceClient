package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.CADoesntExistsException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.EjbcaWS;
import org.sharpsw.ejbcacli.RevokeStatus;
import org.sharpsw.ejbcacli.data.RevocationReason;
import org.sharpsw.ejbcacli.data.RevocationStatusVO;
import org.sharpsw.ejbcacli.service.exception.EJBCAException;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.InvalidCertificateAuthorityException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;
import org.sharpsw.ejbcacli.service.exception.ServiceAuthDeniedException;

/**
 * This service verifies if a certificate is revoked against the EJBCA server.
 * @author andersonkmi
 *
 */
public class CertificateRevocationStatusCheckingService {
	private EJBCAWebService service;
	private static final int NOT_REVOKED = -1;
	private static final int REVOCATION_REASON_AACOMPROMISE = 10;
	private static final int REVOCATION_REASON_AFFILIATIONCHANGED = 3;
	private static final int REVOCATION_REASON_CACOMPROMISE = 2;
	private static final int REVOCATION_REASON_CERTIFICATEHOLD = 6;
	private static final int REVOCATION_REASON_CESSATIONOFOPERATION = 5;
	private static final int REVOCATION_REASON_KEYCOMPROMISE = 1;
	private static final int REVOCATION_REASON_PRIVILEGESWITHDRAWN = 9;
	private static final int REVOCATION_REASON_REMOVEFROMCRL = 8;
	private static final int REVOCATION_REASON_SUPERSEDED = 4;
	private static final int REVOCATION_REASON_UNSPECIFIED = 0;
	
	/**
	 * Constructor that receives an instance of the EJBCA web service.
	 * @param service EJBCA web service wrapper.
	 * @throws NullArgumentException If the web service wrapper is null.
	 */
	public CertificateRevocationStatusCheckingService(EJBCAWebService service) throws NullArgumentException {
		if(service == null) {
			throw new NullArgumentException("EJBCAWebService object cannot be null");
		}
		this.service = service;
	}
	
	/**
	 * Checks the certificate revocation status.
	 * @param certificateSerialNumber Certificate serial number to check
	 * @param issuer Certificate issuer DN.
	 * @return RevocationStatusVO instance containing the revocation status.
	 * @throws ServiceAuthDeniedException If the caller does not have the authorization required.
	 * @throws NullArgumentException If the supplied arguments are null.
	 * @throws InvalidArgumentException If the supplied arguments are invalid (blank values).
	 * @throws InvalidCertificateAuthorityException If the certificate authority information is not present in the server.
	 * @throws EJBCAException If a generic EJBCA server error occurs.
	 */
	public RevocationStatusVO checkRevocationStatus(String certificateSerialNumber, String issuer) throws ServiceAuthDeniedException, NullArgumentException, InvalidArgumentException, InvalidCertificateAuthorityException, EJBCAException {
		if(certificateSerialNumber == null) {
			throw new NullArgumentException("The certificate serial number argument cannot be null");
		}
		
		if(certificateSerialNumber == "") {
			throw new InvalidArgumentException("The certificate serial number argument cannot be empty");
		}
		
		if(issuer == null) {
			throw new NullArgumentException("The issuer argument cannot be null");
		}
		
		if(issuer == "") {
			throw new InvalidArgumentException("The issuer argument cannot be empty");
		}
		
		try {
			EjbcaWS ws = this.service.getService();
			RevokeStatus status = ws.checkRevokationStatus(certificateSerialNumber, issuer);
			RevocationStatusVO info = new RevocationStatusVO();
			info.setSerialNumber(status.getCertificateSN());
			info.setDate(status.getRevocationDate().toGregorianCalendar());
			switch(status.getReason()) {
			case NOT_REVOKED:
				info.setReason(RevocationReason.NOT_REVOKED);
				break;
			case REVOCATION_REASON_AACOMPROMISE:
				info.setReason(RevocationReason.AA_COMPROMISE);
				break;
			case REVOCATION_REASON_AFFILIATIONCHANGED:
				info.setReason(RevocationReason.AFFILIATION_CHANGED);
				break;
			case REVOCATION_REASON_CACOMPROMISE:
				info.setReason(RevocationReason.CA_COMPROMISE);
				break;
			case REVOCATION_REASON_CERTIFICATEHOLD:
				info.setReason(RevocationReason.CERTIFICATE_HOLD);
				break;
			case REVOCATION_REASON_CESSATIONOFOPERATION:
				info.setReason(RevocationReason.CESSATION_OF_OPERATION);
				break;
			case REVOCATION_REASON_KEYCOMPROMISE:
				info.setReason(RevocationReason.KEY_COMPROMISE);
				break;
			case REVOCATION_REASON_PRIVILEGESWITHDRAWN:
				info.setReason(RevocationReason.PRIVILEGES_WITHDRAWN);
				break;
			case REVOCATION_REASON_REMOVEFROMCRL:
				info.setReason(RevocationReason.REMOVE_FROM_CRL);
				break;
			case REVOCATION_REASON_SUPERSEDED:
				info.setReason(RevocationReason.SUPERSEDED);
				break;
			case REVOCATION_REASON_UNSPECIFIED:
				info.setReason(RevocationReason.UNSPECIFIED);
				break;
			}
			return info;
		} catch (AuthorizationDeniedException_Exception exception) {
			StringBuffer log = new StringBuffer();
			log.append("Authorization denied when requesting revocation status. Message: '").append(exception.getMessage()).append("'.");
			throw new ServiceAuthDeniedException(log.toString(), exception);
		} catch (CADoesntExistsException_Exception exception) {
			StringBuffer log = new StringBuffer();
			log.append("The certificate authority supplied does not exist. Message: '").append(exception.getMessage()).append("'.");
			throw new InvalidCertificateAuthorityException(log.toString(), exception);
		} catch (EjbcaException_Exception exception) {
			StringBuffer log = new StringBuffer();
			log.append("The EJBCA server raised an error. Message: '").append(exception.getMessage()).append("'.");
			throw new EJBCAException(log.toString(), exception);
		}
	}
}
