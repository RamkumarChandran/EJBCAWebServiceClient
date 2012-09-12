package org.sharpsw.ejbcacli.service.mocks;

import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException;
import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSEndEntityProfileListingAuthDeniedMock extends EjbcaWSMock {
	public List<NameAndId> getAuthorizedEndEntityProfiles() throws AuthorizationDeniedException_Exception, EjbcaException_Exception {
		throw new AuthorizationDeniedException_Exception("Authorization exception", new AuthorizationDeniedException());
	}
}
