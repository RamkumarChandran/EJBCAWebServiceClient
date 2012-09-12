package org.sharpsw.ejbcacli.service.mocks;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSEndEntityProfileListingMock extends EjbcaWSMock {
	public List<NameAndId> getAuthorizedEndEntityProfiles() throws AuthorizationDeniedException_Exception, EjbcaException_Exception {
		List<NameAndId> results = new ArrayList<NameAndId>();
		
		NameAndId item1 = new NameAndId();
		item1.setId(1);
		item1.setName("End entity profile 1");
		
		NameAndId item2 = new NameAndId();
		item2.setId(2);
		item2.setName("End entity profile 2");
		
		NameAndId item3 = new NameAndId();
		item3.setId(3);
		item3.setName("End entity profile 3");
		
		results.add(item1);
		results.add(item2);
		results.add(item3);
		return results;
	}
}
