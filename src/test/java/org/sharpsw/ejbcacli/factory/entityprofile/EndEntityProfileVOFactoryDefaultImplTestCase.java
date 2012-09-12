package org.sharpsw.ejbcacli.factory.entityprofile;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.sharpsw.ejbcacli.NameAndId;
import org.sharpsw.ejbcacli.data.EndEntityProfileVO;
import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public class EndEntityProfileVOFactoryDefaultImplTestCase {
	private EndEntityProfileVOFactoryDefaultImpl factory;
	
	@Before
	public void setUp() throws Exception {
		this.factory = new EndEntityProfileVOFactoryDefaultImpl();
	}

	@Test
	public void testBuildOK() throws NullArgumentException, InvalidArgumentException {
		NameAndId record = new NameAndId();
		record.setId(0);
		record.setName("Teste");
		
		EndEntityProfileVO profile = this.factory.build(record);
		Assert.assertNotNull(profile);
		Assert.assertEquals(0, profile.getId());
		Assert.assertEquals("Teste", profile.getName());
	}
	
	@Test(expected = NullArgumentException.class)
	public void testBuildNullArgFail() throws NullArgumentException, InvalidArgumentException {
		this.factory.build(null);
	}
	
	@Test(expected = InvalidArgumentException.class)
	public void testBuildInvalidArgFail() throws InvalidArgumentException, NullArgumentException {
		NameAndId record = new NameAndId();
		record.setId(1);
		record.setName(null);
		
		@SuppressWarnings("unused")
		EndEntityProfileVO item = this.factory.build(record);
	}
}
