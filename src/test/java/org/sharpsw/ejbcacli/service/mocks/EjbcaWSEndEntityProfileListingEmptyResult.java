package org.sharpsw.ejbcacli.service.mocks;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSEndEntityProfileListingEmptyResult extends EjbcaWSMock {
	public List<NameAndId> getAuthorizedEndEntityProfiles() throws AuthorizationDeniedException_Exception, EjbcaException_Exception{
		List<NameAndId> results = new ArrayList<NameAndId>();
		return results;
	}
}
