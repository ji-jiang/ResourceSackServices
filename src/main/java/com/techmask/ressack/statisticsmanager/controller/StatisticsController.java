package com.techmask.ressack.statisticsmanager.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionManager;
import com.techmask.ressack.statisticsmanager.service.StatisticsService;

@RestController
@Configuration
@RequestMapping("/statistics")
public class StatisticsController  extends BaseController{
	@Autowired
	private StatisticsService statisticsService;
	
	@RequestMapping(method = RequestMethod.POST,value = "/view")
	public Map<String, Object> addViewCount(
			HttpServletRequest request, @RequestBody Map<String, Object> statisticsMap) {
		
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try {
			
			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
			statisticsMap.put("userId", userSession.getUserId());
			
			statisticsMap.put("statisticsType", "V");
			statisticsMap.put("changedCount", 1);
			
			statisticsService.updateStatistics(statisticsMap);
			
			resultMap.put(RESULT_CODE, "SUCCESS");

			
		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;

	}
}
