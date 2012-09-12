package org.sharpsw.ejbcacli.factory.certprofile;

import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateProfileVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

/**
 * Default implementation of the interface for building instances of the class
 * CertificateProfileVO.
 * @author andersonkmi
 *
 */
public class CertificateProfileVOFactoryDefaultImpl implements ICertificateProfileVOFactory {
	
	/**
	 * Builds an instance of the class CertificateProfileVO based on the NameAndId class.
	 * @param record NameAndId instance received from the EJBCA web service.
	 * @return CertificateProfileVO instance built
	 * @throws NullArgumentException If the NameAndId argument is null.
	 * @throws InvalidArgumentException If the name field of the NameAndInstance is null.
	 */
	public CertificateProfileVO build(NameAndId record) throws NullArgumentException, InvalidArgumentException {
		if(record == null) {
			throw new NullArgumentException("The NameAndId argument is null");
		}
		
		if(record.getName() == null) {
			throw new InvalidArgumentException("The NameAndId argument name field is null");
		}
		
		CertificateProfileVO item = new CertificateProfileVO();
		item.setId(record.getId());
		item.setName(record.getName());
		return item;
	}

}
