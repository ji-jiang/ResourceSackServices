package com.techmask.ressack.core.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix="app")
public class AppConfiguration {
	private String env;
	private int maxResouceAddCount=3;
	private int maxContributorResouceAddCount=10;
	private int maxCoreResouceAddCount=20;
	private int maxFeedbackAddCount;
	private String resourceImageUploadPath;
	private boolean useCloudStorage;
	private String cloudStorageImgUploadPath;

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

	public String getResourceImageUploadPath() {
		return resourceImageUploadPath;
	}

	public void setResourceImageUploadPath(String resourceImageUploadPath) {
		this.resourceImageUploadPath = resourceImageUploadPath;
	}

	public int getMaxFeedbackAddCount() {
		return maxFeedbackAddCount;
	}

	public void setMaxFeedbackAddCount(int maxFeedbackAddCount) {
		this.maxFeedbackAddCount = maxFeedbackAddCount;
	}

	public boolean isUseCloudStorage() {
		return useCloudStorage;
	}

	public void setUseCloudStorage(boolean useCloudStorage) {
		this.useCloudStorage = useCloudStorage;
	}

	public String getCloudStorageImgUploadPath() {
		return cloudStorageImgUploadPath;
	}

	public void setCloudStorageImgUploadPath(String cloudStorageImgUploadPath) {
		this.cloudStorageImgUploadPath = cloudStorageImgUploadPath;
	}

	public int getMaxContributorResouceAddCount() {
		return maxContributorResouceAddCount;
	}

	public void setMaxContributorResouceAddCount(int maxContributorResouceAddCount) {
		this.maxContributorResouceAddCount = maxContributorResouceAddCount;
	}

	public int getMaxCoreResouceAddCount() {
		return maxCoreResouceAddCount;
	}

	public void setMaxCoreResouceAddCount(int maxCoreResouceAddCount) {
		this.maxCoreResouceAddCount = maxCoreResouceAddCount;
	}

	
}
