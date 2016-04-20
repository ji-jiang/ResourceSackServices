package com.techmask.ressack.resourcemanager.service;

import java.util.List;

import com.techmask.ressack.resourcemanager.domain.Resource;

public interface ResourceService {
	
	
	public Resource loadResourceById(String resourceId);
    public Resource addResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(String resourceId);
    public List<Resource> laodAllResource();
}
