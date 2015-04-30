/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.dao;

import snnu.wechat.framework.core.BaseException;

public class ConstraintViolationServiceException extends BaseException {

	public ConstraintViolationServiceException() {
		super();
	}

	public ConstraintViolationServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ConstraintViolationServiceException(String message) {
		super(message);
	}

	public ConstraintViolationServiceException(Throwable cause) {
		super(cause);
	}
}
