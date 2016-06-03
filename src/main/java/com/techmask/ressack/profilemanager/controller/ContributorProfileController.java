package com.techmask.ressack.profilemanager.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.profilemanager.domain.Profile;
import com.techmask.ressack.profilemanager.service.ProfileService;

@RestController
@Configuration
@RequestMapping("/contributor")

public class ContributorProfileController extends BaseController{
	
	@Autowired
	private ProfileService profileService;
	
	
	@RequestMapping(method = RequestMethod.GET,value = "/{pageNo}")
	public Map<String, Object> loadAllContributorProfile(HttpServletRequest request, 
			@PathVariable("pageNo") String pageNo
			) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();

		requestMap.put("pageNo", pageNo);

		try {

			List<Profile> contributors = profileService.loadAllContributorProfile(requestMap);
			resultMap.put("contributors", contributors);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}
}
