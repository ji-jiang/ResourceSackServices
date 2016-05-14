/**  
* @Copyright (C) 
* @Title: FeedBackServiceImpl.java 
* @author WenKe  
* @Package com.techmask.ressack.feedback.service 
* @Description: TODO(用一句话描述该文件做什么)  
* @date 2016年4月17日 下午9:23:58 
* @History 历史  
* <author>     <time>           <version>         <desc>
* 修改人        时间               版本            描叙    
*/
package com.techmask.ressack.feedbackmanager.service;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.configuration.AppConfiguration;
import com.techmask.ressack.core.error.AppException;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.utils.ValidateUtils;
import com.techmask.ressack.feedbackmanager.repository.FeedbackRepository;

@Service
@Configuration
public class FeedbackServiceImpl implements FeedbackService {
	
	@Autowired
	private FeedbackRepository  feedbackRepository;
	@Autowired
	AppConfiguration appConfiguration;

	
	@Override
	public void addFeedback(Map<String, Object> feedbackParamMap) { 
		
		StringBuffer errorMsg = new StringBuffer();
		

		
		validateAddFeedback(errorMsg, feedbackParamMap);
		
		feedbackRepository.addFeedback(feedbackParamMap);
	
	}
	
	
	protected void validateAddFeedback(StringBuffer errorMsg, Map<String, Object> feedbackParamMap){
		
		String userId = (String)feedbackParamMap.get("userId");
		if(StringUtils.isBlank(userId)){
			throw new AppException(AppException.PERMISSION_DENIED_ERROR);
		}
		
		String name = (String)feedbackParamMap.get("name");
		String email = (String)feedbackParamMap.get("email");
		String content = (String)feedbackParamMap.get("message");
		
		ValidateUtils.validateField(errorMsg, "name", name, true, 20);
		ValidateUtils.validateField(errorMsg, "email", email, true, 50);
		ValidateUtils.validateField(errorMsg, "message", content, true, 500);
		
		if(errorMsg.length()>0){
			throw new ValidationException(errorMsg.toString());
		}
		
		int newCreatedCount = feedbackRepository.getNewFeedbackCount(feedbackParamMap);
		int maxFeedbackAddCount = appConfiguration.getMaxFeedbackAddCount();
		int remainAddCount = maxFeedbackAddCount - newCreatedCount-1;
		
		if( remainAddCount<0){
			throw new ValidationException("error.feedback.exceedAddLimit");
		}
		
	}

	

}
