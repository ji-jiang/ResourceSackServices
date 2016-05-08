package com.techmask.ressack.feedbackmanager.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;


public interface FeedBackRepository {
	
	@Insert("insert into feedback(name,email,message,created_by,updated_by ) values(#{name},#{email},#{message},#{userName},#{userName})")
	public int addFeedBack(Map<String,Object> feedbackMap);
	

}
