package com.techmask.ressack.resourcemanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.resourcemanager.domain.Resource;
import com.techmask.ressack.resourcemanager.repository.ResourceRepository;
@Service
public class ResourceServiceImpl implements ResourceService{

	@Autowired
	private ResourceRepository resourceRepository;
	@Override
	public Resource getResourceById(String resourceId) {
		return resourceRepository.findOne(resourceId);
	}

	@Override
	public Resource addResource(Resource resource) {
		return resourceRepository.save(resource);
	}

	@Override
	public Resource updateResource(Resource resource) {
		return resourceRepository.save(resource);
	}

	@Override
	public void deleteResource(String resourceId) {
		resourceRepository.delete(resourceId);
	}

	@Override
	public List<Resource> getAllResources() {
		return resourceRepository.findAll();
	}

}
