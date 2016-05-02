package com.techmask.ressack.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="app")
public class AppConfiguration {
	private String env;

	public Environment getEnv() {
		if(env == null){
			env = Environment.DEV.name();
		}
		return Environment.valueOf(env);
	}

	public void setEnv(String env) {
		this.env = env;
	}
	
}
