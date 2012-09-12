package org.sharpsw.ejbcacli.service;

public aspect CertificateManagementServicePrecedendeAspect {
	declare precedence : CertificateManagementServiceValidationAspect, CertificateManagementServiceLoggingAspect;
}
