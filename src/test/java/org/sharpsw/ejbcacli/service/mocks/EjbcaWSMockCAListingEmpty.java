package org.sharpsw.ejbcacli.service.mocks;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSMockCAListingEmpty extends EjbcaWSMock {

	public EjbcaWSMockCAListingEmpty() {
	}

	public List<NameAndId> getAvailableCAs()
			throws AuthorizationDeniedException_Exception,
			EjbcaException_Exception {
		List<NameAndId> results = new ArrayList<NameAndId>();
		return results;
	} 
	
	public List<NameAndId> getAvailableCAsInProfile(int profileId) throws AuthorizationDeniedException_Exception, EjbcaException_Exception {
		List<NameAndId> results = new ArrayList<NameAndId>();
		return results;
	}
}
