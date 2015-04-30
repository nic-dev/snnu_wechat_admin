/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-19  Create	
 */
package snnu.wechat.system.admin.security.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import snnu.wechat.commbiz.common.OperatorLogEventType;
import snnu.wechat.commbiz.common.SysConstants;
import snnu.wechat.commbiz.entity.OperationLog;
import snnu.wechat.system.admin.service.UserService;
import snnu.wechat.system.admin.util.WebUtils;


public class UserFilter extends PathMatchingFilter {

	@Autowired
	private UserService userService;

	@Override
	protected boolean onPreHandle(ServletRequest request,
			ServletResponse response, Object mappedValue) throws Exception {
		String userName = (String) SecurityUtils.getSubject().getPrincipal();
		if (isLoginOperation((HttpServletRequest) request)) {
			userService.saveLog(new OperationLog(userName,OperatorLogEventType.LOG_IN.getId(),OperatorLogEventType.LOG_IN.getDesc()+":["+WebUtils.getClientIp((HttpServletRequest)request)+"]"));
		}
		request.setAttribute(SysConstants.CURRENT_USER,
				userService.findByUserName(userName));
		return true;
	}

	protected boolean isLoginOperation(HttpServletRequest request) {
		String sourceUrl = request.getHeader("Referer");
		if (sourceUrl!=null&&sourceUrl.indexOf(LOGIN_URL_SYMBOL) > 0) {
			return true;
		}
		return false;
	}

	public static final String LOGIN_URL_SYMBOL = "login.html";
}
