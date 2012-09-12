package org.sharpsw.ejbcacli.factory.ca;

import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateAuthorityVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

/**
 * Interface definition for building instances of CertificateAuthorityVO class.
 * @author Anderson Ito
 *
 */
public interface ICertificateAuthorityVOFactory {
	/**
	 * Builds an instance of the class CertificateAuthorityVO based on an instance of the class
	 * NameAndId.
	 * @param record NameAndId instance received from the EJBCA web service.
	 * @return CertificateAuthorityVO instance.
	 * @throws NullArgumentException If the NameAndId argument is null.
	 * @throws InvalidArgumentException If the name field of the NameAndId object is null.
	 */
	public CertificateAuthorityVO build(NameAndId record) throws NullArgumentException, InvalidArgumentException;
}
