package com.admin.bootstrap;

import javax.servlet.FilterRegistration;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.core.annotation.Order;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.admin.config.RootContextConfig;
import com.admin.config.ServletContextConfig;
import com.admin.config.secure.PreSecurityLoggingFilter;

@Order(1)
public class Boostrap implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {

        container.getServletRegistration("default").addMapping("/resources/*");

        AnnotationConfigWebApplicationContext rootContext =
                new AnnotationConfigWebApplicationContext();
        rootContext.register(RootContextConfig.class);
        container.addListener(new ContextLoaderListener(rootContext));

        AnnotationConfigWebApplicationContext servletContext =
                new AnnotationConfigWebApplicationContext();
        servletContext.register(ServletContextConfig.class);
        ServletRegistration.Dynamic dispatcher = container.addServlet(
                "springDispatcher", new DispatcherServlet(servletContext)
                );
        dispatcher.setLoadOnStartup(1);
        dispatcher.setMultipartConfig(
				new MultipartConfigElement(
						null, 1024*1024*5, 1024*1024*5*5, 1024*1024));
        dispatcher.addMapping("/");
        
        
        FilterRegistration.Dynamic registration =
        			container.addFilter("preSecurityLoginFilter", PreSecurityLoggingFilter.class);
        
        registration.addMappingForUrlPatterns(null, false, "/*");
       
        
        
        
	}

}
