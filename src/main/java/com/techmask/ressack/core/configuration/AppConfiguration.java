package com.techmask.ressack.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="app")
public class AppConfiguration {
	private String env;

	private int maxResouceAddCount;

	public Environment getEnv() {
		if(env == null){
			env = Environment.DEV.name();
		}
		return Environment.valueOf(env);
	}

	public void setEnv(String env) {
		this.env = env;
	}

	public int getMaxResouceAddCount() {
		return maxResouceAddCount;
	}

	public void setMaxResouceAddCount(int maxResouceAddCount) {
		this.maxResouceAddCount = maxResouceAddCount;
	}

	
}
