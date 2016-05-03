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

import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.resourcemanager.domain.Resource;
import com.techmask.ressack.resourcemanager.service.ResourceService;

@RestController
@RequestMapping("/resource")
public class ResourceController extends BaseController {
	@Autowired
	private ResourceService resourceService;

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addResource(@RequestBody Map<String, Object> resourceMap) {
		Resource resource = new Resource();
		resource.setTitle((String) resourceMap.get("title"));

		resourceService.addResource(resource);

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Resource created successfully");
		response.put("resource", resource);

		return response;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/search/{keywords}/{pageNo}")
	public Map<String, Object> loadAllResourceByKeywords(@PathVariable("keywords") String keywords,
			 @PathVariable("pageNo") String pageNo) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

		try {

			Map<String, Object> requestMap = new LinkedHashMap<String, Object>();
			requestMap.put("keywords", keywords);
			requestMap.put("pageNo", pageNo);

			List<Resource> resources = resourceService.loadAllResourceByKeywords(requestMap);
			resultMap.put("resources", resources);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		
		return resultMap;

	}

	@RequestMapping(method = RequestMethod.GET, value = "/{category}/{subCategory}/{pageNo}")
	public Map<String, Object> loadAllResource(@PathVariable("category") String category,
			@PathVariable("subCategory") String subCategory, @PathVariable("pageNo") String pageNo) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

		try {

			Map<String, Object> requestMap = new LinkedHashMap<String, Object>();
			requestMap.put("category", category);
			requestMap.put("subCategory", subCategory);
			requestMap.put("pageNo", pageNo);

			List<Resource> resources = resourceService.loadAllResource(requestMap);
			resultMap.put("resources", resources);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		
		return resultMap;

	}
}
