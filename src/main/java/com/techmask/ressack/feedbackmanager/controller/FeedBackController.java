package com.techmask.ressack.feedbackmanager.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.core.busobjs.ResultCode;
import com.techmask.ressack.core.controller.BaseController;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.feedbackmanager.service.FeedBackService;

@RestController
@RequestMapping("/feedback")
public class FeedBackController extends BaseController{

	@Autowired
	private FeedBackService  feedBackService;


	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addFeedBack(@RequestBody Map<String, Object> feedBackParamMap) {
		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		try{
			feedBackService.addFeedBack(feedBackParamMap);
		}catch(ValidationException ve){
			return this.handleValidationExcpetion(ve, resultMap);
		}catch(Exception e){
			return this.handleException(e, resultMap);
		}
		
		resultMap.put(RESULT_CODE, ResultCode.SUCCESS);
		return resultMap;
	
	}
	
}
