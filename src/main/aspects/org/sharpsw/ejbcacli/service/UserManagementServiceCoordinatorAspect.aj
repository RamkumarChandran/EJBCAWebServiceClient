package org.sharpsw.ejbcacli.service;

public aspect UserManagementServiceCoordinatorAspect {
	declare precedence : UserManagementServiceValidationAspect, UserManagementServiceLoggingAspect;
}
