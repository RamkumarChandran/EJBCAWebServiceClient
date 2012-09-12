package org.sharpsw.ejbcacli.configuration;

import org.apache.log4j.Logger;

public aspect ConfigurationLoggingAspect {
	private static final Logger logger = Logger.getLogger(Configuration.class);
	
	pointcut configurationCtorCall(String serverCertFile, String serverCertFilePwd, String adminUserCertFile, String adminUserCertFilePwd) : execution(public Configuration.new(String, String, String, String)) && args(serverCertFile, serverCertFilePwd, adminUserCertFile, adminUserCertFilePwd);
	pointcut configSSLExec() : execution(public void Configuration.configureSSL());
	pointcut configItem(String key, String value) : execution(private void Configuration.configureItem(String, String)) && args(key, value);
	
	before(String serverCertFile, String serverCertFilePwd, String adminUserCertFile, String adminUserCertFilePwd) : configurationCtorCall(serverCertFile, serverCertFilePwd, adminUserCertFile, adminUserCertFilePwd) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering Configuration constructor method.");
		}
		
		if(logger.isDebugEnabled()) {
			logger.debug("Initializing a Configuration class with following values:");
			StringBuffer buffer = new StringBuffer();
			buffer.append("Server certificate file: '").append(serverCertFile).append("'");
			logger.debug(buffer.toString());
			
			buffer = new StringBuffer();
			buffer.append("Server certificate file password: '").append(serverCertFilePwd).append("'.");
			logger.debug(buffer.toString());
			
			buffer = new StringBuffer();
			buffer.append("Admin user certificate file: '").append(adminUserCertFile).append("'.");
			logger.debug(buffer.toString());
			
			buffer = new StringBuffer();
			buffer.append("Admin user certificate file password: '").append(adminUserCertFilePwd).append("'.");
			logger.debug(buffer.toString());
		}
	}
	
	after(String serverCertFile, String serverCertFilePwd, String adminUserCertFile, String adminUserCertFilePwd) : 
		configurationCtorCall(serverCertFile, serverCertFilePwd, adminUserCertFile, adminUserCertFilePwd) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving Configuration constructor method.");
		}
	}
	
	before() : configSSLExec() {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering Configuration.configureSSL() method.");
		}
	}
	
	after() : configSSLExec() {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving Configuration.configureSSL() method.");
		}
	}
	
	after() throwing(SSLEnvSetupException exception) : configSSLExec() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("An SSLEnvSetupException has been raised. Details: '").append(exception.getMessage()).append("'.");
		logger.error(buffer.toString(), exception);
	}
	
	before(String key, String value) : configItem(key, value) {
		if(logger.isTraceEnabled()) {
			logger.trace("Entering Configuration.configureItem() method.");
		}
		
		if(logger.isDebugEnabled()) {
			String log = String.format("Configuring the property '%s' with value '%s'", key, value);
			logger.debug(log);
		}
	}
	
	after(String key, String value) : configItem(key, value) {
		if(logger.isTraceEnabled()) {
			logger.trace("Leaving Configuration.configureItem() method.");
		}
	}
	
	after(String key, String value) throwing(SSLEnvSetupException exception) : configItem(key, value) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("An SSLEnvSetupException has been raised. Details: '").append(exception.getMessage()).append("'.");
		logger.error(buffer.toString(), exception);
	}
}
