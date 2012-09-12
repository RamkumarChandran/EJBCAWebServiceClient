package org.sharpsw.ejbcacli.service.mocks;

import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSCertProfileListingEjbcaExceptionMock extends EjbcaWSMock {
	public List<NameAndId> getAvailableCertificateProfiles(int id) throws AuthorizationDeniedException_Exception, EjbcaException_Exception {
		throw new EjbcaException_Exception("EJBCA exception", new EjbcaException());
	}
}
