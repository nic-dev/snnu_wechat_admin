/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-21  Create	
 */
package snnu.wechat.system.admin.web.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 *
 */
@Controller
public class ExceptionController {

	@ExceptionHandler(RuntimeException.class)
	public String handleRuntimeException(){
		return "unauthorized";
	}
	@ExceptionHandler(AuthorizationException.class)
	public String handleException(){
		return "unauthorized";
	}

}
