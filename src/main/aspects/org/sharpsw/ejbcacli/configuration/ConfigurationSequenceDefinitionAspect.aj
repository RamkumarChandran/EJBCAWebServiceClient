package org.sharpsw.ejbcacli.configuration;

public aspect ConfigurationSequenceDefinitionAspect {
	declare precedence: ConfigValidationAspect, ConfigurationLoggingAspect;
}
