/**  
* @Copyright (C) 
* @Title: FeedBackController.java 
* @author WenKe  
* @Package com.techmask.ressack.feedback.controller 
* @Description: TODO(用一句话描述该文件做什么)  
* @date 2016年4月17日 下午9:14:36 
* @History 历史  
* <author>     <time>           <version>         <desc>
* 修改人        时间               版本            描叙    
*/
package com.techmask.ressack.feedback.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.feedback.domain.FeedBack;
import com.techmask.ressack.feedback.service.FeedBackService;
import com.techmask.ressack.resourcemanager.domain.Resource;

@RestController
@RequestMapping("/feedback")
public class FeedBackController {

	@Autowired
	private FeedBackService  feedBackService;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> addFeedBack(@RequestBody Map<String, Object> resourceMap) {
		Resource resource = new Resource();
		resource.setTitle((String)resourceMap.get("title"));

		

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "Resource created successfully");
		response.put("resource", resource);

		return response;
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public Map<String, Object> getAllFeedBack()
	{
		System.out.println("feedback come in");
		List<FeedBack> feedBacks = feedBackService.loadAllFeedBack();
		
		
		System.out.println(feedBacks);
		Map<String, Object> reuslt = new HashMap<String,Object>();
		reuslt.put("feedback", feedBacks);
		
		return reuslt;
	}
	
}
