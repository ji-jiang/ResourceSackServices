package com.techmask.ressack.resourcemanager.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.feedback.service.FeedBackService;
import com.techmask.ressack.resourcemanager.domain.Resource;
import com.techmask.ressack.resourcemanager.service.ResourceService;
import com.techmask.ressack.usermanager.repository.UserRepository;

@RestController
@RequestMapping("/resource")
public class ResourceController {
	@Autowired
	private ResourceService resourceService;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FeedBackService feedBackService;
	
	
//	@Autowired
//	private VelocityEngine velocityEngine;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addResource(@RequestBody Map<String, Object> resourceMap) {
		Resource resource = new Resource();
		resource.setTitle((String)resourceMap.get("title"));

		
		resourceService.addResource(resource);

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Resource created successfully");
		response.put("resource", resource);

		return response;
	}
	

	@RequestMapping(method = RequestMethod.GET,value = "/{category}/{subCategory}/{pageNo}")
	public Map<String, Object> loadAllResource(@PathVariable("category") String category,@PathVariable("subCategory") String subCategory,@PathVariable("pageNo") String pageNo) {
		
		Map<String,Object>requestMap = new HashMap<String,Object>();
		requestMap.put("category", category);
		requestMap.put("subCategory", subCategory);
		requestMap.put("pageNo", pageNo);
		
		System.out.println(requestMap);
		List<Resource> resources = resourceService.loadAllResource(requestMap); 


		
		Map<String, Object> response = new LinkedHashMap<String, Object>();
		
		response.put("seven", "seven");

		

		return response;

	}
}
