package com.techmask.ressack.resourcemanager.service;

import java.util.Map;




import com.techmask.ressack.core.utils.StringUtils;
import com.techmask.ressack.resourcemanager.domain.Resource;

public class ResouceLoadProcessor {
	
	private Map<String, Object> requestMap;
	private boolean isAuthenticatedUser = false;

	public ResouceLoadProcessor(Map<String, Object> requestMap) {
		this.setRequestMap(requestMap);
		
		if(this.getRequestMap().containsKey("userId") && (!StringUtils.isBlank((String)this.getRequestMap().get("userId")))){
			this.setAuthenticatedUser(true);
		}
	}
	
	
	public void postProcessResource(Resource resource){
		if(!this.isAuthenticatedUser()){
			resource.setDownloadPassword("XXXXXXXXX");
		}
	}
	

	public Map<String, Object> getRequestMap() {
		return requestMap;
	}

	public void setRequestMap(Map<String, Object> requestMap) {
		this.requestMap = requestMap;
	}


	public boolean isAuthenticatedUser() {
		return isAuthenticatedUser;
	}


	public void setAuthenticatedUser(boolean isAuthenticatedUser) {
		this.isAuthenticatedUser = isAuthenticatedUser;
	}

}