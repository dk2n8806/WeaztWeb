package com.admin.config.secure;


import javax.inject.Inject;

import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.core.service.account.auth.AccountAuthenticationService;




@SuppressWarnings("deprecation")
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(
			prePostEnabled=true, order = 0, mode = AdviceMode.PROXY,
			proxyTargetClass=false
		)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{


	@Inject AccountAuthenticationService authService;
	
    @Bean
    protected SessionRegistry sessionRegistryImpl()
    {
        return new SessionRegistryImpl();
    }

    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
    	return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception
    {
    	builder
    		.userDetailsService(this.authService)
    		.passwordEncoder(new BCryptPasswordEncoder())
    		.and()
    		.eraseCredentials(true)
    		;
    }

    @Override
    public void configure(WebSecurity security)
    {
        security.ignoring().antMatchers("/resource/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception
    {
        security
                .authorizeRequests()
            		.antMatchers("/user/**", "/merchant/**").hasRole("USER")
                	.antMatchers("/**").permitAll()
                	.anyRequest().authenticated()
                .and().formLogin()
                    .loginPage("/login").failureUrl("/login?loginFailed")
                    .defaultSuccessUrl("/shop", true)
                    .usernameParameter("email")
                    .passwordParameter("password")
                    .permitAll()
                .and().logout()
                    .logoutUrl("/logout").logoutSuccessUrl("/shop")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID")
                    .permitAll()
                .and().sessionManagement()
                    //.sessionFixation().changeSessionId()
                    .maximumSessions(1)//.maxSessionsPreventsLogin(true)
                    .sessionRegistry(this.sessionRegistryImpl())
                .and()
                .and()
                	.csrf().disable()
         //       	.requiresChannel()
         //       		.anyRequest().requiresSecure()
                	//.rememberMe().key("remember_me")
                	//.useSecureCookie(true)															// For using SSL
                	;

       // security.portMapper().http(8080).mapsTo(8443);
    }
}
