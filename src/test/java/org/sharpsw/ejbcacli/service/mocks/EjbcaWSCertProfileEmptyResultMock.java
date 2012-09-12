package org.sharpsw.ejbcacli.service.mocks;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSCertProfileEmptyResultMock extends EjbcaWSMock {
	public List<NameAndId> getAvailableCertificateProfiles(int id) {
		List<NameAndId> results = new ArrayList<NameAndId>();
		return results;
	}
}
