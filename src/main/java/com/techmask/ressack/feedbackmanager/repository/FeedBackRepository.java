package com.techmask.ressack.feedbackmanager.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;


public interface FeedBackRepository {
	
	@Insert("insert into feedback(name,email,content,created_by,updated_by ) values(#{name},#{email},#{content},#{createdBy},#{updatedBy})")
	public int addFeedBack(Map<String,Object> feedbackMap);
	

}
