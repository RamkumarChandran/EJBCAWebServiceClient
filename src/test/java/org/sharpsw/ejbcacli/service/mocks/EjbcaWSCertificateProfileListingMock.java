package org.sharpsw.ejbcacli.service.mocks;

import java.util.ArrayList;
import java.util.List;

import org.sharpsw.ejbcacli.NameAndId;

public class EjbcaWSCertificateProfileListingMock extends EjbcaWSMock {
	public List<NameAndId> getAvailableCertificateProfiles(int id) {
		List<NameAndId> results = new ArrayList<NameAndId>();
		
		NameAndId item1 = new NameAndId();
		item1.setId(1);
		item1.setName("Certificate profile 1");
		
		NameAndId item2 = new NameAndId();
		item2.setId(2);
		item2.setName("Certificate profile 2");
		
		results.add(item1);
		results.add(item2);
		return results;
	}
}
