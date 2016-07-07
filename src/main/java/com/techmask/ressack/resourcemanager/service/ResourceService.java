package com.techmask.ressack.resourcemanager.service;

import java.util.List;
import java.util.Map;

import com.techmask.ressack.resourcemanager.domain.Resource;

public interface ResourceService {
	
	
	public Resource loadResourceById(String resourceId);
	public Resource loadResourceById(String resourceId, String userId);
    public Map<String, Object> addResource(Map<String,Object> resourceMap);
    public Map<String, Object> updateResource(Map<String,Object> resourceMap);
    public void setResourceImageInd(String resourceId);
    
    public List<Resource> loadAllResource(Map<String, Object> requestMap);
    public List<Resource> loadAllUserResource(Map<String, Object> requestMap);
    public List<Resource> loadAllResourceByKeywords(Map<String, Object> requestMap);
    public List<Resource> loadAllResourceForRssFeed(Map<String, Object> requestMap);
}
