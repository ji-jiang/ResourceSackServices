package com.startup.bookapp.core.security;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.startup.bookapp.core.configuration.AppConfiguration;
import com.startup.bookapp.core.configuration.Environment;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	AppConfiguration appConfiguration;
	
	private final Log logger = LogFactory.getLog(getClass());

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("Application environment is: "
					+ appConfiguration.getEnv());
		}

		if (appConfiguration.getEnv().equals(Environment.DEV)) {
			http.httpBasic().and().authorizeRequests()
					.antMatchers("/", "/currentuser", "/book/buy", "/book")
					.permitAll().anyRequest().authenticated().and().csrf()
					.disable();
		} else {
			http.httpBasic().and().authorizeRequests()
					.antMatchers("/", "/currentuser", "/book/buy").permitAll()
					.anyRequest().authenticated().and()
					.addFilterAfter(new CsrfHeaderFilter(), CsrfFilter.class)
					.csrf().csrfTokenRepository(csrfTokenRepository());

		}

	}

	private CsrfTokenRepository csrfTokenRepository() {
		CustomizedHttpSessionCsrfTokenRepository repository = new CustomizedHttpSessionCsrfTokenRepository();
		repository.setHeaderName("X-XSRF-TOKEN");

		return repository;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userDetailsService;
	}

	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

}
