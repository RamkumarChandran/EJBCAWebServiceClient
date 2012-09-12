package org.sharpsw.ejbcacli.configuration;

/**
 * The Configuration class holds the information related to the key stores to handle the SSL connection 
 * to the server and the admin user certificate file information.
 * @author andersonkmi
 *
 */
public class Configuration {	
	private String serverCertificateStoreFile;
	private String serverCertificateStoreFilePassword;
	private String adminUserCertificateStoreFile;
	private String adminUserCertificateStoreFilePassword;
	
	private static final String TRUST_STORE_FILE = "javax.net.ssl.trustStore";
	private static final String TRUST_STORE_PASSWORD = "javax.net.ssl.trustStorePassword";
	private static final String TRUST_STORE_TYPE = "javax.net.ssl.trustStoreType";
	private static final String KEY_STORE_FILE = "javax.net.ssl.keyStore";
	private static final String KEY_STORE_TYPE = "javax.net.ssl.keyStoreType";
	private static final String KEY_STORE_PASSWORD = "javax.net.ssl.keyStorePassword";
	
	/**
	 * Constructor that is initialized with the information reagarding the certificate file store for SSL communication
	 * and the admin user.
	 * @param serverCertStoreFile String containing the server certificate file name.
	 * @param serverCertStoreFilePassword String containing the server file name.
	 * @param adminUserCertStoreFile String containing the admin user certificate file name.
	 * @param adminUserCertStoreFilePassword String containing the admin user certificate file password.
	 */
	public Configuration(String serverCertStoreFile, String serverCertStoreFilePassword, String adminUserCertStoreFile, String adminUserCertStoreFilePassword) {
		this.serverCertificateStoreFile = serverCertStoreFile;
		this.serverCertificateStoreFilePassword = serverCertStoreFilePassword;
		this.adminUserCertificateStoreFile = adminUserCertStoreFile;
		this.adminUserCertificateStoreFilePassword = adminUserCertStoreFilePassword;
	}
	
	/**
	 * Configures the SSL connection and the user admin certificate information.
	 * @throws SSLEnvSetupException If an error occurs when setting up the files and passwords.
	 */
	public void configureSSL() throws SSLEnvSetupException {		
		if(!isConfigured()) {			
			configureTrustStore();
			configureTrustStorePassword();
			configureTrustStoreType();
			configureKeyStoreFile();
			configureKeyStorePassword();
			configureKeyStoreType();
		}
	}
	
	/**
	 * Verifies if the configuration is already done based on the names passed in the class constructor.
	 * @return true if it is already configured; false otherwise.
	 */
	public boolean isConfigured() {
		return isTrustStoreConfigured() && 
			   isTrustStorePasswordConfigured() && 
			   isTrustStoreTypeConfigured() &&
			   isKeyStoreConfigured() &&
			   isKeyStorePasswordConfigured() &&
			   isKeyStoreTypeConfigured();
	}
	

	private boolean isTrustStoreConfigured() {
		return isItemConfigured(TRUST_STORE_FILE, this.serverCertificateStoreFile);
	}
	
	private boolean isTrustStorePasswordConfigured() {
		return isItemConfigured(TRUST_STORE_PASSWORD, this.serverCertificateStoreFilePassword);
	}
	
	private boolean isTrustStoreTypeConfigured() {
		return isItemConfigured(TRUST_STORE_TYPE, "JKS");
	}
	
	private boolean isKeyStoreConfigured() {
		return isItemConfigured(KEY_STORE_FILE, this.adminUserCertificateStoreFile);
	}
	
	private boolean isKeyStorePasswordConfigured() {
		return isItemConfigured(KEY_STORE_PASSWORD, this.adminUserCertificateStoreFilePassword);
	}
	
	private boolean isKeyStoreTypeConfigured() {
		return isItemConfigured(KEY_STORE_TYPE, "pkcs12");
	}
	
	private boolean isItemConfigured(String item, String expectedValue) {
		try {
			String value = System.getProperty(item);
			if(expectedValue.equals(value)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			return false;
		}
	}
	
	
	private void configureItem(String item, String value) throws SSLEnvSetupException {
		try {
			String currentValue = System.getProperty(item);
			if(currentValue != null) {
				if(!currentValue.equals(value)) {
					System.setProperty(item, value);
				}
			} else {
				System.setProperty(item, value);
			}
		} catch (SecurityException exception) {
			String message = String.format("Error when configuring the '%s' configuration", item);
			throw new SSLEnvSetupException(message, exception);
		} catch (NullPointerException exception) {
			String message = String.format("Error when configuring the '%s' configuration", item);
			throw new SSLEnvSetupException(message, exception);
		} catch (IllegalArgumentException exception) {
			String message = String.format("Error when configuring the '%s' configuration", item);
			throw new SSLEnvSetupException(message, exception);
		}		
	}
	
	private void configureTrustStore() throws SSLEnvSetupException {
		configureItem(TRUST_STORE_FILE, this.serverCertificateStoreFile);
	}
	
	private void configureTrustStorePassword() throws SSLEnvSetupException {
		configureItem(TRUST_STORE_PASSWORD, this.serverCertificateStoreFilePassword);
	}
	
	private void configureTrustStoreType() throws SSLEnvSetupException {
		configureItem(TRUST_STORE_TYPE, "JKS");
	}
	
	private void configureKeyStoreFile() throws SSLEnvSetupException {
		configureItem(KEY_STORE_FILE, this.adminUserCertificateStoreFile);
	}
	
	private void configureKeyStorePassword() throws SSLEnvSetupException {
		configureItem(KEY_STORE_PASSWORD, this.adminUserCertificateStoreFilePassword);
	}
	
	private void configureKeyStoreType() throws SSLEnvSetupException {
		configureItem(KEY_STORE_TYPE, "pkcs12");
	}
}
