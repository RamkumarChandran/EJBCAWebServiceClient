package org.sharpsw.ejbcacli.service.mocks;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSMockCAByEndEntityListingOK extends EjbcaWSMock {
	public List<NameAndId> getAvailableCAsInProfile(int profileId) throws AuthorizationDeniedException_Exception, EjbcaException_Exception {
		List<NameAndId> results = new ArrayList<NameAndId>();
		NameAndId item1 = new NameAndId();
		item1.setId(1);
		item1.setName("Root Certificate Authority");
		
		NameAndId item2 = new NameAndId();
		item2.setId(2);
		item2.setName("Intermediate certificate authority");
		
		results.add(item1);
		results.add(item2);
		return results;
	}
}
