package com.admin.config;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executor;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Controller;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.bind.annotation.ControllerAdvice;

import com.admin.config.persistence.PersistencceConfig;
import com.admin.config.secure.SpringSecurityConfig;


@Configuration
@EnableScheduling
@EnableAsync(mode=AdviceMode.PROXY, proxyTargetClass=false
								, order=Ordered.HIGHEST_PRECEDENCE)
@ComponentScan(
			basePackages={"com.core", "com.common"}
			, excludeFilters=@ComponentScan.Filter({Controller.class, ControllerAdvice.class})
)
@Import({PersistencceConfig.class, SpringSecurityConfig.class})
@PropertySource("application.properties")
public class RootContextConfig implements AsyncConfigurer, SchedulingConfigurer {

	@Autowired
	private Environment env;
	

	
	@Bean
	public JavaMailSenderImpl javaMailService() {	
		String username = env.getProperty("EMAIL_USERNAME");
		String password = env.getProperty("EMAIL_PASSWORD");
		int port = 587;
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(port);
		mailSender.setUsername(username);
		mailSender.setPassword(password);
		Properties prop = mailSender.getJavaMailProperties();
		prop.put("mail.transport.protocol", "smtp");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		return mailSender;
	}
	
	@Bean
	public VelocityEngine velocityEngine() throws VelocityException, IOException {
		VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
		Properties props = new Properties();
		props.put("resource.loader", "class");
		props.put("class.resource.loader.class", 
				  "org.apache.velocity.runtime.resource.loader." + 
				  "ClasspathResourceLoader");
		factory.setVelocityProperties(props);
		
		return factory.createVelocityEngine();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**************************************************************************
	 * ************************************************************************
	 * 
	 * Enable Scheduler configuration
	 * 
	 * ************************************************************************ 
	 * ************************************************************************/
	
	@Bean
	public ThreadPoolTaskScheduler taskScheduler() {
		ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
		scheduler.setPoolSize(20);
		scheduler.setThreadNamePrefix("task-");
		scheduler.setAwaitTerminationSeconds(60);
		scheduler.setWaitForTasksToCompleteOnShutdown(true);
		return scheduler;
	}
	
	@Override
	public void configureTasks(ScheduledTaskRegistrar registrar) {
		TaskScheduler scheduler = this.taskScheduler();
		registrar.setTaskScheduler(scheduler);
	}

	@Override
	public Executor getAsyncExecutor() {
		Executor executor = this.taskScheduler();
		return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return null;
	}


}
