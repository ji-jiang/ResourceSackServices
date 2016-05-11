package com.techmask.ressack.feedbackmanager.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;


public interface FeedBackRepository {
	
	@Insert("insert into feedback(name,email,subject,message,created_by,updated_by ) values(#{name},#{email},#{subject},#{message},#{userName},#{userName})")
	public int addFeedBack(Map<String,Object> feedbackMap);
	

}
