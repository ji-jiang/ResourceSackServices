package com.techmask.ressack.rankmanager.controller;

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
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionManager;
import com.techmask.ressack.rankmanager.domain.Rank;
import com.techmask.ressack.rankmanager.service.RankService;

@RestController
@Configuration
@RequestMapping("/rank")
public class RankController extends BaseController {
	
	@Autowired
	private RankService rankService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/{yearWeekInd}/{pageNo}")
	public Map<String, Object> loadAllRankByYearWeek(HttpServletRequest request, 
			@PathVariable("yearWeekInd") String yearWeekInd,
			@PathVariable("pageNo") String pageNo) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();
		
		requestMap.put("yearWeekInd", yearWeekInd);
		requestMap.put("pageNo", pageNo);
		
		UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
		requestMap.put("userId", userSession.getUserId());

		try {
			
			List<Rank> ranks = rankService.loadAllRankByYearWeek(requestMap);
			resultMap.put("ranks", ranks);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{pageNo}")
	public Map<String, Object> loadAllRankByYearWeek(HttpServletRequest request, 
			@PathVariable("pageNo") String pageNo) {

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		Map<String, Object> requestMap = new LinkedHashMap<String, Object>();

		requestMap.put("pageNo", pageNo);
		
		UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
		requestMap.put("userId", userSession.getUserId());

		try {
			
			List<Rank> ranks = rankService.loadAllRank(requestMap);
			resultMap.put("Rank", ranks);

		} catch (ValidationException ve) {
			return this.handleValidationExcpetion(ve, resultMap);
		} catch (Exception e) {
			return this.handleException(e, resultMap);
		}

		return resultMap;
	}
}
