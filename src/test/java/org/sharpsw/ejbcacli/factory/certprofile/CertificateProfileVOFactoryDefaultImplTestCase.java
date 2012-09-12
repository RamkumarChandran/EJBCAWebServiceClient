package org.sharpsw.ejbcacli.factory.certprofile;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateProfileVO;
import org.sharpsw.ejbcacli.factory.certprofile.CertificateProfileVOFactoryDefaultImpl;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class CertificateProfileVOFactoryDefaultImplTestCase {
	private CertificateProfileVOFactoryDefaultImpl factory;
	
	@Before
	public void setUp() throws Exception {
		this.factory = new CertificateProfileVOFactoryDefaultImpl();
	}
	
	@Test
	public void testBuildOK() throws NullArgumentException, InvalidArgumentException {
		NameAndId record = new NameAndId();
		record.setId(1);
		record.setName("teste");
		CertificateProfileVO profile = this.factory.build(record);
		
		Assert.assertNotNull(profile);
		Assert.assertEquals(1, profile.getId());
		Assert.assertEquals("teste", profile.getName());
	}
	
	@Test(expected = NullArgumentException.class)
	public void testBuildNullArgumentFail() throws NullArgumentException, InvalidArgumentException {
		this.factory.build(null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testBuildInvalidArgFail() throws InvalidArgumentException, NullArgumentException {
		NameAndId record = new NameAndId();
		record.setId(0);
		record.setName(null);
		
		@SuppressWarnings("unused")
		CertificateProfileVO profile = this.factory.build(record);
	}
}
