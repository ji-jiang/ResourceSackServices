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

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.core.util.ParamVerifiesUntil;
import com.techmask.ressack.core.util.ResultEntity;
import com.techmask.ressack.feedback.service.FeedBackService;

@RestController
@RequestMapping("/feedback")
public class FeedBackController {

	@Autowired
	private FeedBackService  feedBackService;
	@Autowired
	private ParamVerifiesUntil paramVerifiesUntil;
	
	
	/** 
	* @MethodName: addFeedBack 
	* @Description(描叙):   添加意见反馈
	* @author Wenke 
	* @param @param feedBackParamMap
	* @param @return 
	* @return ResultEntity
	* @throws
	* @date 2016年4月19日 下午9:28:54  
	*/
	@RequestMapping(method = RequestMethod.POST)
	public ResultEntity addFeedBack(@RequestBody Map<String, Object> feedBackParamMap) {
		
		ResultEntity resultEntity = new ResultEntity("seven");
		
		String name = feedBackParamMap.get("name")+"";
		String email = feedBackParamMap.get("email")+"";
		String content = feedBackParamMap.get("content")+"";
		String createBy = feedBackParamMap.get("createBy")+"";
		String updateBy = feedBackParamMap.get("updateBy")+"";
		
		if("null".equals(name) || "".equals(name)  ||
		   "null".equals(content) || "".equals(content) ||
		   "null".equals(createBy) || "".equals(createBy) ||
		   "null".equals(updateBy) || "".equals(updateBy)){
			resultEntity.setCode("101");
			resultEntity.setMessage("主要参数为空");
		}else if(paramVerifiesUntil.isEmail(email) == false){
			resultEntity.setCode("102");
			resultEntity.setMessage("邮件格式不正确");
		}else{
			feedBackService.addFeedBack(name, email, content, createBy, updateBy, resultEntity);
		}

		return resultEntity;
	}
	
	@RequestMapping( method = RequestMethod.GET)
	public ResultEntity getAllFeedBack()
	{
		
		ResultEntity  resultEntity = new ResultEntity("seven");
		feedBackService.loadAllFeedBack(resultEntity);
		return resultEntity;
	}
	
	
	/** 
	* @MethodName: updateFeedBack 
	* @Description(描叙):   修改意见反馈
	* @author Wenke 
	* @param @param feedBackId
	* @param @param feedBackParamMap
	* @param @return 
	* @return ResultEntity
	* @throws
	* @date 2016年4月19日 下午9:28:30  
	*/
	@RequestMapping(method = RequestMethod.PUT, value = "/{feedBackId}")
	public ResultEntity updateFeedBack(@PathVariable("feedBackId") String feedBackId,@RequestBody Map<String, Object> feedBackParamMap) {
	
		ResultEntity resultEntity = new ResultEntity("seven");
		
		String content = feedBackParamMap.get("content")+"";
		if("null".equals(content) || "".equals(content) ||
		  feedBackId == null || "".equals(feedBackId) ){
			resultEntity.setCode("101");
			resultEntity.setMessage("主要参数为空");
		}else{
			feedBackService.updateFeedBack(feedBackId, content, resultEntity);
		}
		return resultEntity;
	}
	
	
	/** 
	* @MethodName: deleteFeedBack 
	* @Description(描叙):   删除  意见反馈
	* @author Wenke 
	* @param @param feedBackId
	* @param @param feedBackParamMap
	* @param @return 
	* @return ResultEntity
	* @throws
	* @date 2016年4月19日 下午9:30:23  
	*/
	@RequestMapping(method = RequestMethod.DELETE, value = "/{feedBackId}")
	public ResultEntity deleteFeedBack(@PathVariable("feedBackId") String feedBackId,@RequestBody Map<String, Object> feedBackParamMap) {
	
		ResultEntity resultEntity = new ResultEntity("seven");
		if(feedBackId == null || "".equals(feedBackId) ){
			resultEntity.setCode("101");
			resultEntity.setMessage("主要参数为空");
		}else{
			feedBackService.deleteFeedBack(feedBackId, resultEntity);
		}
		return resultEntity;
	}
	
	
}
