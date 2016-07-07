package com.techmask.ressack.resourcemanager.service;

import java.util.Map;

import com.techmask.ressack.core.busobjs.BooleanFlag;
import com.techmask.ressack.core.configuration.AppConfiguration;
import com.techmask.ressack.core.utils.StringUtils;
import com.techmask.ressack.resourcemanager.domain.Resource;

public class ResouceLoadProcessor {
	private AppConfiguration appConfiguration;
	private Map<String, Object> requestMap;
	private boolean isAuthenticatedUser = false;
	
	private static final String QINIU_THUMBNAIL_100 = "?imageMogr2/thumbnail/250x"; 

	public ResouceLoadProcessor(Map<String, Object> requestMap,AppConfiguration appConfiguration) {
		this.setRequestMap(requestMap);
		
		if(this.getRequestMap().containsKey("userId") && (!StringUtils.isBlank((String)this.getRequestMap().get("userId")))){
			this.setAuthenticatedUser(true);
		}
		
		this.appConfiguration = appConfiguration;
	}
	
	
	public void postProcessResource(Resource resource){
		if(!this.isAuthenticatedUser()){
			resource.setDownloadPassword("XXXXXXXXX");
		}
		String imageVersion = "?version="+resource.getImageVersion();
		
		if(appConfiguration.isUseCloudStorage()){
			if(BooleanFlag.getInstance(resource.getImageInd()).booleanValue()){
				String resourceId = resource.getId();
				resource.setImageUrl(appConfiguration.getCloudStorageImgUploadPath()+"resources/R00000"+resourceId+"_md.png"+imageVersion);
				resource.setImageSmUrl(appConfiguration.getCloudStorageImgUploadPath()+"resources/R00000"+resourceId+"_md.png"+QINIU_THUMBNAIL_100);
			}else{
				resource.setImageUrl("/img/portfolio-page-5/default.png");
			}
			
		}else{
			if(BooleanFlag.getInstance(resource.getImageInd()).booleanValue()){
				String resourceId = resource.getId();
				resource.setImageUrl(appConfiguration.getHostUrl()+"/static/resources/R00000"+resourceId+"_md.png"+imageVersion);
				resource.setImageSmUrl(appConfiguration.getHostUrl()+"/static/resources/R00000"+resourceId+"_sm.png"+imageVersion);
			}else{
				resource.setImageUrl(appConfiguration.getHostUrl()+"/img/portfolio-page-5/default.png");
			}
		}
		
		String origAdviceUrl =resource.getOrigUrl();
		if(!StringUtils.isBlank(origAdviceUrl)){
			if(origAdviceUrl.indexOf("?")<=0){
				origAdviceUrl+="?";
			}
			if(origAdviceUrl.indexOf("&")>0){
				origAdviceUrl+="&";
			}
			origAdviceUrl+="utm_source=jijiangshe";
			resource.setOrigAdviceUrl(origAdviceUrl);
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
