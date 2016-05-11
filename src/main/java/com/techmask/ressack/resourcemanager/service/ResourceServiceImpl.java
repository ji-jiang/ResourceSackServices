package com.techmask.ressack.resourcemanager.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.configuration.AppConfiguration;
import com.techmask.ressack.core.data.PageHelper;
import com.techmask.ressack.core.error.AppException;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.utils.ValidateUtils;
import com.techmask.ressack.resourcemanager.domain.Resource;
import com.techmask.ressack.resourcemanager.repository.ResourceRepository;


@Service
@Configuration
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	AppConfiguration appConfiguration;
	
	@Autowired
	private ResourceRepository resourceRepository;
	@Override
	public Resource loadResourceById(String resourceId) {
		return resourceRepository.loadResourceById(resourceId);
	}

	@Override
	public Map<String, Object> addResource(Map<String,Object> resourceMap) {
		
		validateAddResource(resourceMap);
		
		int resourceId =  resourceRepository.addResource(resourceMap);
		long lastInserId = resourceRepository.getLastInsertId();
		resourceMap.put("id" , lastInserId);
		
		return resourceMap;
		
	}
	
	
	protected void validateAddResource(Map<String,Object> resourceMap){
		
		String userId = (String)resourceMap.get("userId");
		if(StringUtils.isBlank(userId)){
			throw new AppException(AppException.PERMISSION_DENIED_ERROR);
		}
		
		StringBuffer errorMsg = new StringBuffer();
				
		String title = (String)resourceMap.get("title");
		String category = (String)resourceMap.get("category");
		String subCategory = (String)resourceMap.get("subCategory");
		String desc = (String)resourceMap.get("desc");
		String origUrl = (String)resourceMap.get("origUrl");
		String downloadUrl = (String)resourceMap.get("downloadUrl");
		String downloadPassword = (String)resourceMap.get("downloadPassword");
		String tags = (String)resourceMap.get("tags");
		
		ValidateUtils.validateField(errorMsg, "title", title, true, 50);
		ValidateUtils.validateField(errorMsg, "category", category, true, 20);
		ValidateUtils.validateField(errorMsg, "subCategory", subCategory, true, 20);
		ValidateUtils.validateField(errorMsg, "desc", desc, true, 300);
		ValidateUtils.validateField(errorMsg, "origUrl", origUrl, true, 100);
		ValidateUtils.validateField(errorMsg, "downloadUrl", downloadUrl, false, 100);
		ValidateUtils.validateField(errorMsg, "downloadPassword", downloadPassword, false, 100);
		ValidateUtils.validateField(errorMsg, "tags", tags, false, 100);
		
		if(errorMsg.length()>0){
			throw new ValidationException(errorMsg.toString());
		}
		
		
		int newCreatedCount = resourceRepository.getNewCreatedCount(resourceMap);
		int maxResouceAddCount = appConfiguration.getMaxResouceAddCount();
		int remainAddCount = maxResouceAddCount - newCreatedCount-1;
		
		
		System.out.println(resourceMap);
		System.out.println(newCreatedCount);
		
		
		if( remainAddCount<0){
			throw new ValidationException("error.resouce.exceedAddLimit");
		}else{
			resourceMap.put("remainAddCount", remainAddCount);
		}
		
	}


	@Override
	public List<Resource> loadAllResource(Map<String, Object> requestMap) {
		List<Resource> resources = null;
		PageHelper.preparePageQuery(requestMap);
		
		
		String category = (String)requestMap.get("category");
		String subCategory = (String)requestMap.get("subCategory");
		
		if(StringUtils.isBlank(category)){
			category = "ALL";
			requestMap.put("category", category);
		}
		if(StringUtils.isBlank(subCategory)){
			subCategory = "ALL";
			requestMap.put("subCategory", subCategory);
		}
		
		
		
		resources =  resourceRepository.loadAllResource(requestMap);
		postProcessResources(resources,requestMap);
		
		return resources;
	}
	
	@Override
	public List<Resource> loadAllResourceByKeywords(Map<String, Object> requestMap) {
		List<Resource> resources = null;
		PageHelper.preparePageQuery(requestMap);
		
		
		String keywords = (String)requestMap.get("keywords");
		
		
		if(StringUtils.isBlank(keywords)){
			keywords = "ALL";
			requestMap.put("keywords", keywords);
		}
		
		
		resources =  resourceRepository.loadAllResourceByKeywords(requestMap);
		postProcessResources(resources,requestMap);
		
		return resources;
	}
	
	protected void postProcessResources(List<Resource> resources, Map<String, Object> requestMap){
		
		if(resources != null){
			
			ResouceLoadProcessor rlp = new ResouceLoadProcessor(requestMap);
			
			for(int i=0;i<resources.size();i++){
				Resource resource = resources.get(i);
				rlp.postProcessResource(resource);
				
			}
		}
	}

	@Override
	public void setResourceImageInd(String resourceId) {
		resourceRepository.setImageInd(resourceId);
		
	}

}
