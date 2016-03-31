package com.techmask.ressack.resourcemanager.service;

import java.util.List;

import com.techmask.ressack.resourcemanager.domain.Resource;

public interface ResourceService {
	public Resource getResourceById(String resourceId);
    public Resource addResource(Resource resource);
    public Resource updateResource(Resource resource);
    public void deleteResource(String resourceId);
    public List<Resource> getAllResources();
}
