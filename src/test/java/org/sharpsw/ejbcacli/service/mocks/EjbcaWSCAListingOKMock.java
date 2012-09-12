package org.sharpsw.ejbcacli.service.mocks;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSCAListingOKMock extends EjbcaWSMock {
	public List<NameAndId> getAvailableCAs()
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception {
		List<NameAndId> results = new ArrayList<NameAndId>();
		NameAndId ca1 = new NameAndId();
		ca1.setId(1);
		ca1.setName("Root CA Example");
		
		NameAndId ca2 = new NameAndId();
		ca2.setId(2);
		ca2.setName("Intermediate CA Example");
		
		results.add(ca1);
		results.add(ca2);
		return results;
	} 
}
