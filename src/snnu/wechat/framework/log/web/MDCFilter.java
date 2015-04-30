/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  2014-8-12 Create	
 */
package snnu.wechat.framework.log.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.MDC;

import snnu.wechat.framework.log.URILogAppender;



public class MDCFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
			MDC.put(URILogAppender.KEY, ((HttpServletRequest)req).getRequestURI());
			chain.doFilter(req, resp);
			MDC.remove(URILogAppender.KEY);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
