package com.admin.bootstrap;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;

import com.admin.config.secure.PostSecurityLoggingFilter;

@Order(3)
public class LogginBootstrap implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
        FilterRegistration.Dynamic registration = container.addFilter(
                "postSecurityLoggingFilter", new PostSecurityLoggingFilter()
        );
        registration.addMappingForUrlPatterns(null, false, "/*");
	}

}
