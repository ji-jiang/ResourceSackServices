package com.techmask.ressack.core.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.techmask.ressack.core.configuration.AppConfiguration;
import com.techmask.ressack.core.configuration.Environment;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	AppConfiguration appConfiguration;
	@Autowired
	LogoutSuccessHandler logoutSuccessHandler;
	
	private final Log logger = LogFactory.getLog(getClass());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Application environment is: "
					+ appConfiguration.getEnv());
		}

		if (appConfiguration.getEnv().equals(Environment.DEV)) {
			http.httpBasic().and().authorizeRequests()
					.antMatchers("/", "/currentuser", "/book/buy","/resource")
					.permitAll().anyRequest().authenticated()
					.and()
					.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessHandler(logoutSuccessHandler)
					.and().csrf()
					.disable();
		} else {
			http.httpBasic().and().authorizeRequests()
					.antMatchers("/", "/currentuser", "/book/buy","/resource").permitAll()
					.anyRequest().authenticated()
					.and()
					.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessHandler(logoutSuccessHandler)
					.and()
					.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)					
					.csrf().csrfTokenRepository(csrfTokenRepository())
					;

		}

	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

	private CsrfTokenRepository csrfTokenRepository() {
		CustomizedHttpSessionCsrfTokenRepository repository = new CustomizedHttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");

		return repository;
	}
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
//	    authProvider.setPasswordEncoder(passwordEncoder());
	    return authProvider;
	}
	
	

}
