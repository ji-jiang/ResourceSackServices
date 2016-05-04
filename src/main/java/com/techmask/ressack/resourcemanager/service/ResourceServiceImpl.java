package com.techmask.ressack.resourcemanager.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.data.PageHelper;
import com.techmask.ressack.resourcemanager.domain.Resource;
import com.techmask.ressack.resourcemanager.repository.ResourceRepository;
@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceRepository resourceRepository;
	@Override
	public Resource loadResourceById(String resourceId) {
		return resourceRepository.loadResourceById(resourceId);
	}

	@Override
	public Map<String, Object> addResource(Map<String,Object> resourceMap) {
		
		int resourceId =  resourceRepository.addResource(resourceMap);
		long lastInserId = resourceRepository.getLastInsertId();
		resourceMap.put("id" , lastInserId);
		
		return resourceMap;
		
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
		
		return resources;
	}

}
