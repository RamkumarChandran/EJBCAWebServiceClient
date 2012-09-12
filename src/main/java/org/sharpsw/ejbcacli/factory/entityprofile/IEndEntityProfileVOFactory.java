package org.sharpsw.ejbcacli.factory.entityprofile;

import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.EndEntityProfileVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

/**
 * Interface definition related to the building of the EndEntityProfileVO instances based
 * on the NameAndId objects.
 * @author andersonkmi
 *
 */
public interface IEndEntityProfileVOFactory {
	/**
	 * Builds an instance of the class EndEntityProfileVO given a NameAndId instance.
	 * @param record Original NameAndId instance.
	 * @return EndEntityProfileVO created.
	 * @throws NullArgumentException If the NameAndId instance is null.
	 * @throws InvalidArgumentException If the field name in the supplied instance is null.
	 */
	public EndEntityProfileVO build(NameAndId record) throws NullArgumentException, InvalidArgumentException;
}
