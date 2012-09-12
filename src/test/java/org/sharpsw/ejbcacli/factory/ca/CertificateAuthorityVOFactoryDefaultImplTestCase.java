package org.sharpsw.ejbcacli.factory.ca;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.CertificateAuthorityVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class CertificateAuthorityVOFactoryDefaultImplTestCase {
	private CertificateAuthorityVOFactoryDefaultImpl factory;
	
	@Before
	public void setUp() throws Exception {
		this.factory = new CertificateAuthorityVOFactoryDefaultImpl();
	}

	@Test
	public void testBuildOK() throws NullArgumentException, InvalidArgumentException {
		NameAndId original = new NameAndId();
		original.setId(10);
		original.setName("teste");
		
		CertificateAuthorityVO item = this.factory.build(original);
		Assert.assertNotNull(item);
		Assert.assertEquals(10, item.getId());
		Assert.assertEquals("teste", item.getName());
		Assert.assertEquals(10, item.hashCode());
		Assert.assertEquals("[id = '10'; name = 'teste']", item.toString());
	}

	@Test(expected=NullArgumentException.class)
	public void testBuildNullArgumentFail() throws NullArgumentException, InvalidArgumentException {
		this.factory.build(null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testBuildInvalidArgumentFail() throws NullArgumentException, InvalidArgumentException {
		NameAndId record = new NameAndId();
		record.setId(0);
		record.setName(null);
		this.factory.build(record);
	}
}
