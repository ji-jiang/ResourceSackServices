package com.techmask.ressack.resourcemanager.service;

import java.util.List;
import java.util.Map;

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
	public Resource addResource(Resource resource) {
		return resourceRepository.addResource(resource);
	}


	@Override
	public List<Resource> loadAllResource(Map<String, Object> requestMap) {
		List<Resource> resources = null;
		PageHelper.preparePageQuery(requestMap);
		System.out.println(requestMap);
		if(requestMap!=null){
			if(requestMap.containsKey("category") && (!requestMap.get("category").equals("ALL"))){
				resources = resourceRepository.loadAllResourceByCategory(requestMap);
			}
		}
		
		if(resources == null){
			resources =  resourceRepository.loadAllResource(requestMap);
		}
		return resources;
	}

}
