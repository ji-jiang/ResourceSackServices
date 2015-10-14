package com.startup.bookapp.resourcemanager.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.startup.bookapp.resourcemanager.domain.Resource;
import com.startup.bookapp.resourcemanager.service.ResourceService;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> createResource(@RequestBody Map<String, Object> resourceMap) {
		Resource resource = new Resource();
		resource.setTitle((String)resourceMap.get("title"));
		resource.setType((String)resourceMap.get("type"));
		resource.setUrl((String)resourceMap.get("url"));
		resource.setDescription((String)resourceMap.get("description"));
		resource.setImage((String)resourceMap.get("image"));
		
		resourceService.addResource(resource);

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Resource created successfully");
		response.put("resource", resource);

		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> getAllResource() {
		List<Resource> resources = resourceService.getAllResources();

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("totalResources", resources.size());
		response.put("resources", resources);

		return response;

	}
}
