/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.system.admin.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import snnu.wechat.framework.log.Log;
import snnu.wechat.system.admin.entity.User;
import snnu.wechat.system.admin.util.WebUtils;
import snnu.wechat.system.admin.web.controller.BaseController;
import snnu.wechat.system.admin.web.form.LoginForm;


@Controller
public class SecurityController extends BaseController {
	

	@RequestMapping("/admin/index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, ModelMap map) {
		String exceptionClassName = (String) request
				.getAttribute("shiroLoginFailure");
		String error = null;
		Log.logError("exceptionClassName="+exceptionClassName);
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = "用户名或者密码错误";//或提示用户不存在
		} else if (IncorrectCredentialsException.class.getName().equals(
				exceptionClassName)) {
			error = "用户名或者密码错误";//或提示密码错误
		}else if(AuthenticationException.class.getName().equals(exceptionClassName)){
			error="用户名或者密码错误";//
		}
		else if (exceptionClassName != null) {
			error = "未知错误，请联系管理员" ;
		}
		if(error!=null){
		WebUtils.fillErrorMap(map, error);
		}

		return "login";
	}

	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		return "/";
	}
}
