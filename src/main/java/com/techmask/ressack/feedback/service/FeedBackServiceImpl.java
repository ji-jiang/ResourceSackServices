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
package com.techmask.ressack.feedback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.util.ResultEntity;
import com.techmask.ressack.feedback.domain.FeedBack;
import com.techmask.ressack.feedback.repository.FeedBackRepository;

/** 
* @ClassName: FeedBackServiceImpl 
* @Description(描叙): 
* @author Wenke 
* @date 2016年4月17日 下午9:23:58  
*/
@Service
public class FeedBackServiceImpl implements FeedBackService {
	
	@Autowired
	private FeedBackRepository  feedBackRepository;

	/** 
	* @Title: loadAllFeedBack 
	* @anthor Wenke
	* @Description(描述这个方法) : TODO 
	* @return  
	* @throws 
	*/
	@Override
	public void loadAllFeedBack(ResultEntity resultEntity) {
		
		
		List<FeedBack> feedBackList = feedBackRepository.loadAllFeedBack();
		if(feedBackList == null){
			resultEntity.setCode("201");
			resultEntity.setMessage("获取失败");
		}else if(feedBackList.size() == 0){
			resultEntity.setCode("301");
			resultEntity.setMessage("没有相应的数据");
		}else{
			resultEntity.setCode("0");
			resultEntity.setMessage("获取数据成功");
			resultEntity.setData(feedBackList);
		}
		return ;
	}

	/** 
	* @Title: addFeedBack 
	* @anthor Wenke
	* @Description(描述这个方法) :  添加意见反馈
	* @param name
	* @param email
	* @param content
	* @param createBy
	* @param updateBy
	* @param resulMap
	* @return  
	* @throws 
	*/
	@Override
	public void addFeedBack(String name,String email,String content,String createBy,String updateBy,ResultEntity resultEntity) {
		
		
		FeedBack addFeedBack = new FeedBack(); 
		addFeedBack.setName(name);
		addFeedBack.setEmail(email);
		addFeedBack.setContent(content);
		addFeedBack.setCreatedBy(createBy);
		addFeedBack.setUpdatedBy(updateBy);
		int count = feedBackRepository.addFeedBack(addFeedBack);
		if(count > 0){
			resultEntity.setCode("0");
			resultEntity.setMessage("添加成功");
		}else{
			resultEntity.setCode("201");
			resultEntity.setMessage("添加失败");
		}
		return ;
	}

	/** 
	* @Title: updateFeedBack 
	* @anthor Wenke
	* @Description(描述这个方法) : 修改意见反馈
	* @param feedBackId
	* @param content
	* @param resulMap
	* @return  
	* @throws 
	*/
	@Override
	public void updateFeedBack(String feedBackId ,String content,ResultEntity resultEntity) {
		
		FeedBack updateFeedBack = new FeedBack();
		updateFeedBack.setFeedbackId(feedBackId);
		updateFeedBack.setContent(content);
		int count = feedBackRepository.updateFeedBack(updateFeedBack);
		if(count > 0){
			resultEntity.setCode("0");
			resultEntity.setMessage("修改成功");
		}else{
			resultEntity.setCode("201");
			resultEntity.setMessage("修改失败");
		}
		return ;
	}

	/** 
	* @Title: deleteFeedBack 
	* @anthor Wenke
	* @Description(描述这个方法) :  根据id 删除意见反馈
	* @param feedBackId
	* @return  
	* @throws 
	*/
	@Override
	public void deleteFeedBack(String feedBackId,ResultEntity resultEntity) {
		FeedBack deletFeedBack = new FeedBack();
		deletFeedBack.setFeedbackId(feedBackId);
		
		int count = feedBackRepository.deleteFeedBack(deletFeedBack);
		if(count > 0){
			resultEntity.setCode("0");
			resultEntity.setMessage("删除成功");
		}else{
			resultEntity.setCode("201");
			resultEntity.setMessage("删除失败");
		}
		return ;
	}


}
