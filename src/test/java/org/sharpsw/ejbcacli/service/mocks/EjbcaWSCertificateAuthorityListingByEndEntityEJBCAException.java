package org.sharpsw.ejbcacli.service.mocks;

import java.util.List;

import org.sharpsw.ejbcacli.AuthorizationDeniedException_Exception;
import org.sharpsw.ejbcacli.EjbcaException;
import org.sharpsw.ejbcacli.EjbcaException_Exception;
import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSCertificateAuthorityListingByEndEntityEJBCAException extends EjbcaWSMock {
	public List<NameAndId> getAvailableCAsInProfile(int profileId) throws AuthorizationDeniedException_Exception, EjbcaException_Exception {
		throw new EjbcaException_Exception("Erro genérico", new EjbcaException());
	}
}
