/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.log.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import snnu.wechat.framework.log.Log;


public class RequestParamLogFilter implements Filter {

	public void init(FilterConfig config) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String uri = "";
		if (request instanceof HttpServletRequest) {
			uri = ((HttpServletRequest) request).getRequestURI();
			String referer = ((HttpServletRequest) request)
					.getHeader("Referer");
			Log.logInfo("request(uri={0},referer={1})", uri, referer);
		}
		Map params = request.getParameterMap();
		StringBuffer buffer = new StringBuffer();
		int index = 0;

		for (Object key : params.keySet()) {
			if (index > 0) {
				buffer.append(";");
			}
			buffer.append(key).append("=");
			Object value = params.get(key);
			if (value instanceof String) {
				buffer.append(value);
			} else if (value instanceof String[]) {
				String[] array = (String[]) value;
				for (int i = 0; i < array.length; i++) {
					if (i > 0) {
						buffer.append(",");
					}
					buffer.append(array[i]);
				}
			} else {
				buffer.append(value);
			}
			index++;
		}
		Log.logInfo("request(uri={0}) param[{1}]", uri, buffer.toString());
	/*	if (!StringUtils.isEmpty(request.getParameter("signature"))) {
			BufferedReader reader = request.getReader();
			StringBuffer requestBody = new StringBuffer();
			String temp = reader.readLine();
			while (!StringUtils.isEmpty(temp)) {
				requestBody.append(temp);
				temp = reader.readLine();
			}
			Log.logInfo("wechat request body="+requestBody.toString());
		}*/
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
