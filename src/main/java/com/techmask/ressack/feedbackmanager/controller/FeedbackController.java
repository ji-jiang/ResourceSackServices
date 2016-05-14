package com.techmask.ressack.feedbackmanager.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.core.busobjs.ResultCode;
import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionManager;
import com.techmask.ressack.feedbackmanager.service.FeedbackService;

@RestController
@RequestMapping("/feedback")
public class FeedbackController extends BaseController{

	@Autowired
	private FeedbackService  feedbackService;


	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addFeedBack(HttpServletRequest request, @RequestBody Map<String, Object> feedBackParamMap) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try{
			UserSession userSession = UserSessionManager.getInstance().getUserSession(request);
			
			feedBackParamMap.put("userId", userSession.getUserId());
			feedBackParamMap.put("userName", userSession.getUserName());
			
			
			feedbackService.addFeedback(feedBackParamMap);
		}catch(ValidationException ve){
			return this.handleValidationExcpetion(ve, resultMap);
		}catch(Exception e){
			return this.handleException(e, resultMap);
		}
		
		resultMap.put(RESULT_CODE, ResultCode.SUCCESS);
		return resultMap;
	
	}
	
}
