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
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.error.AppException;
import com.techmask.ressack.core.error.ValidationException;
import com.techmask.ressack.core.utils.ValidateUtils;
import com.techmask.ressack.feedbackmanager.repository.FeedBackRepository;

@Service
public class FeedBackServiceImpl implements FeedBackService {
	
	@Autowired
	private FeedBackRepository  feedBackRepository;

	
	@Override
	public void addFeedBack(Map<String, Object> feedBackParamMap) { 
		
		StringBuffer errorMsg = new StringBuffer();
		

		
		validateAddFeedback(errorMsg, feedBackParamMap);
		
		feedBackRepository.addFeedBack(feedBackParamMap);
	
	}
	
	
	protected void validateAddFeedback(StringBuffer errorMsg, Map<String, Object> feedBackParamMap){
		
		String userId = (String)feedBackParamMap.get("userId");
		if(StringUtils.isBlank(userId)){
			throw new AppException(AppException.PERMISSION_DENIED_ERROR);
		}
		
		String name = (String)feedBackParamMap.get("name");
		String email = (String)feedBackParamMap.get("email");
		String content = (String)feedBackParamMap.get("message");
		
		ValidateUtils.validateField(errorMsg, "name", name, true, 20);
		ValidateUtils.validateField(errorMsg, "email", email, true, 50);
		ValidateUtils.validateField(errorMsg, "message", content, true, 500);
		
		if(errorMsg.length()>0){
			throw new ValidationException(errorMsg.toString());
		}
		
	}

	

}
