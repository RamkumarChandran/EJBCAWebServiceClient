package org.sharpsw.ejbcacli.service;

import org.sharpsw.ejbcacli.service.exception.InvalidArgumentException;
import org.sharpsw.ejbcacli.service.exception.NullArgumentException;

public aspect UserManagementServiceValidationAspect {
	pointcut deleteUserCall(String user) : execution(public void UserManagementService.deleteUser(String)) && args(user);
	void around(String user) throws NullArgumentException, InvalidArgumentException : deleteUserCall(user) {
		if(user == null) {
			throw new NullArgumentException("The user name argument cannot be null");
		}
		
		if(user.isEmpty()) {
			throw new InvalidArgumentException("The user name argument cannot be empty");
		}
	}
}
