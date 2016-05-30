package com.techmask.ressack.flagmanager.controller;

import java.util.LinkedHashMap;
import java.util.List;
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
import com.techmask.ressack.flagmanager.domain.Flag;
import com.techmask.ressack.flagmanager.service.FlagService;

@RestController
@Configuration
@RequestMapping("/flag")
public class FlagController extends BaseController{
	@Autowired
	private FlagService flagService;
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addFlag(
			HttpServletRequest request, @RequestBody Map<String, Object> flagMap) {
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			
			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
			flagMap.put("userId", userSession.getUserId());
			
			flagService.addFlag(flagMap);
			
			resultMap.put(RESULT_CODE, "SUCCESS");

			
		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{flagType}/{refId}")
	public Map<String, Object> deleteFlag(
			
			HttpServletRequest request,
			@PathVariable("flagType") String flagType,
			@PathVariable("refId") String refId) {
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();
		try {
			
			requestMap.put("flagType", flagType);
			requestMap.put("refId", refId);
			
			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
			requestMap.put("userId", userSession.getUserId());
			
			flagService.removeFlag(requestMap);
			
			resultMap.put(RESULT_CODE, "SUCCESS");

			
		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}
	
	@RequestMapping(method = RequestMethod.GET,value = "/{refId}")
	public Map<String, Object> loadAllFlagByUser(HttpServletRequest request, 
			@PathVariable("refId") String refId
			) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();
		
		requestMap.put("refId", refId);
		
		UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
		requestMap.put("userId", userSession.getUserId());

		try {

			List<Flag> flags = flagService.loadAllFlagByUser(requestMap);
			resultMap.put("flags", flags);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}
}
