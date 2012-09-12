package org.sharpsw.ejbcacli.factory.entityprofile;

import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.EndEntityProfileVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

/**
 * Default implementation of the interface for creating EndEntityProfile instances.
 * @author andersonkmi
 *
 */
public class EndEntityProfileVOFactoryDefaultImpl implements IEndEntityProfileVOFactory {
	
	/**
	 * Builds an instance of the class EndEntityProfileVO given a NameAndId instance.
	 * @param record Original NameAndId instance.
	 * @return EndEntityProfileVO created.
	 * @throws NullArgumentException If the NameAndId instance is null.
	 * @throws InvalidArgumentException If the field name in the supplied instance is null.
	 */
	public EndEntityProfileVO build(NameAndId record) throws InvalidArgumentException, NullArgumentException {
		if(record == null) {
			throw new NullArgumentException("NameAndId argument is null");
		}
		
		if(record.getName() == null) {
			throw new InvalidArgumentException("The NameAndId name field is null");
		}
		
		EndEntityProfileVO item = new EndEntityProfileVO();
		item.setId(record.getId());
		item.setName(record.getName());
		return item;
	}

}
