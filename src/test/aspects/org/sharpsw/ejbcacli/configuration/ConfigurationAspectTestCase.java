package org.sharpsw.ejbcacli.configuration;

import org.junit.Test;

// Anderson
public class ConfigurationAspectTestCase {
	
	@Test
	public void testConfigurationConstructionOK() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration("server.jks", "senha", "admin.pfx", "senha2");
	}

	@Test(expected = InvalidConfigParameterException.class)
	public void testConfigCtorNullServerFileFail() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration(null, "senha", "admin.pfx", "senha2.pfx");
	}
	
	@Test(expected = InvalidConfigParameterException.class)
	public void testConfigCtorEmptyServerFileFail() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration("", "senha", "admin.pfx", "senha2.pfx");
	}
	
	@Test(expected = InvalidConfigParameterException.class)
	public void testConfigCtorNullServerFilePwdFail() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration("server.jks", null, "admin.pfx", "senha2.pfx");
	}
	
	@Test
	public void testConfigCtorEmptyServerFilePwdOK() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration("server.jks", "", "admin.pfx", "senha2.pfx");
	}
	
	@Test(expected = InvalidConfigParameterException.class)
	public void testConfigCtorNullAdminCertFileFail() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration("server.jks", "senha", null, "senha2.pfx");
	}
	
	@Test(expected = InvalidConfigParameterException.class)
	public void testConfigCtorEmptyAdminUserCertFileFail() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration("server.jks", "senha", "", "senha2.pfx");
	}
	
	@Test(expected = InvalidConfigParameterException.class)
	public void testConfigCtorNullAdminCertFilePwdFail() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration("server.jks", "senha", "admin.pfx", null);
	}

	@Test
	public void testConfigCtorEmptyAdminCertFilePwdOK() {
		@SuppressWarnings("unused")
		Configuration config = new Configuration("server.jks", "senha", "admin.pfx", "");
	}	
}
