/**  
* @Copyright (C) 
* @Title: FeedBackRepository.java 
* @author WenKe  
* @Package com.techmask.ressack.feedback.repository 
* @Description: TODO(用一句话描述该文件做什么)  
* @date 2016年4月17日 下午9:16:11 
* @History 历史  
* <author>     <time>           <version>         <desc>
* 修改人        时间               版本            描叙    
*/
package com.techmask.ressack.feedback.repository;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.techmask.ressack.feedback.domain.FeedBack;

/** 
* @ClassName: FeedBackRepository 
* @Description(描叙): 
* @author Wenke 
* @date 2016年4月17日 下午9:16:11  
*/
public interface FeedBackRepository {
	
	@Results(value = { @Result(property = "feedbackId", column = "feedback_id"),
			 @Result(property = "name", column = "name"),
			 @Result(property = "email", column = "email"),
			 @Result(property = "content", column = "content"),
			 @Result(property = "createdDate",column = "created_date"),
			 @Result(property = "createdBy",column = "created_by"),
			 @Result(property = "updatedDate",column = "updated_date"),
			 @Result(property = "updatedBy",column = "updated_by"),})
	@Select("select * from feedback")
	public List<FeedBack> loadAllFeedBack();
}
