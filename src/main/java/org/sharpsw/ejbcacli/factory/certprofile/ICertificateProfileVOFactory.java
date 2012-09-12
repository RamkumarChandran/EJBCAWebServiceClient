package org.sharpsw.ejbcacli.factory.certprofile;

import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateProfileVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

/**
 * Interface definition for building instances of the class CertificateProfileVO based on the NameAndId instance
 * returned  by the EJBCA web service.
 * @author andersonkmi
 *
 */
public interface ICertificateProfileVOFactory {
	
	/**
	 * Builds an instance of the class CertificateProfileVO based on the NameAndId class.
	 * @param record NameAndId instance received from the EJBCA web service.
	 * @return CertificateProfileVO instance built
	 * @throws NullArgumentException If the NameAndId argument is null.
	 * @throws InvalidArgumentException If the name field of the NameAndInstance is null.
	 */
	public CertificateProfileVO build(NameAndId record) throws NullArgumentException, InvalidArgumentException;
}
