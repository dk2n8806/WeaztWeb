package com.admin.config.secure;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.ThreadContext;

public class PreSecurityLoggingFilter implements Filter 
{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String id = UUID.randomUUID().toString();
		ThreadContext.put("id", id);
		try {
			((HttpServletResponse) response).setHeader("X-MINGO-Request-id", id);
			chain.doFilter(request, response);
		} finally {
			ThreadContext.remove("id");
			ThreadContext.remove("username");
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
