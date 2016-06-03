package com.techmask.ressack.profilemanager.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionManager;
import com.techmask.ressack.profilemanager.domain.Profile;
import com.techmask.ressack.profilemanager.service.ProfileService;

@RestController
@Configuration
@RequestMapping("/profile")
public class UserProfileController extends BaseController{
	
	@Autowired
	private ProfileService profileService;

	@RequestMapping(method = RequestMethod.GET,value = "/{userId}")
	public Map<String, Object> loadProfileByUserId(HttpServletRequest request, 
			@PathVariable("userId") String userId
			) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();	

		try {

			Profile profile = profileService.loadProfileByUserId(userId);
			resultMap.put("profile", profile);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}
	
	
	@RequestMapping(method = RequestMethod.PUT)
	public Map<String, Object> updateProfile(
			HttpServletRequest request, @RequestBody Map<String, Object> profileMap) {
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			
			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
			
			profileMap.put("userId", userSession.getUserId());
			profileMap.put("userRole", userSession.getUserRole());
			
			profileService.updateProfile(profileMap);

			
			resultMap.put(RESULT_CODE, "SUCCESS");
			resultMap.put("profile", profileMap);
			
		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;


	}
}
