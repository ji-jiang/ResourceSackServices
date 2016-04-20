package com.techmask.ressack.resourcemanager.service;

import java.util.List;
import java.util.Map;

import com.techmask.ressack.resourcemanager.domain.Resource;

public interface ResourceService {
	public Resource loadResourceById(String resourceId);
    public Resource addResource(Resource resource);

    public List<Resource> loadAllResource(Map<String, Object> requestMap);
}
