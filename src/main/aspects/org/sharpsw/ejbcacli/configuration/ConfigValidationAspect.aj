package org.sharpsw.ejbcacli.configuration;


public aspect ConfigValidationAspect {	
	pointcut configurationInitCall(String serverCertFile, String serverCertFilePwd, String adminUserCertFile, String adminUserCertFilePwd) : execution(public Configuration.new(String, String, String, String)) && args(serverCertFile, serverCertFilePwd, adminUserCertFile, adminUserCertFilePwd);
	
	void around(String serverCertFile, String serverCertFilePwd, String adminUserCertFile, String adminUserCertFilePwd) throws InvalidConfigParameterException : configurationInitCall(serverCertFile, serverCertFilePwd, adminUserCertFile, adminUserCertFilePwd) {
		if(serverCertFile == null) {
			throw new InvalidConfigParameterException("The server certificate file name argument cannot be null");
		}
		
		if(serverCertFile.isEmpty()) {
			throw new InvalidConfigParameterException("The server certificate file name cannot be an empty string");
		}
		
		if(serverCertFilePwd == null) {
			throw new InvalidConfigParameterException("The server certificate file password argument cannot be null");
		}
		
		if(adminUserCertFile == null) {
			throw new InvalidConfigParameterException("The admin user certificate file argument cannot be null");
		}
		
		if(adminUserCertFile.isEmpty()) {
			throw new InvalidConfigParameterException("The admin user certificate file name cannot be an empty string");
		}
		
		if(adminUserCertFilePwd == null) {
			throw new InvalidConfigParameterException("The admin user certificate file password argument cannot be null");
		}
	}	
}
