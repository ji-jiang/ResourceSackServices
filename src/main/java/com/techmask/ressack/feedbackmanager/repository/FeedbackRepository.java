package com.techmask.ressack.feedbackmanager.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;


public interface FeedbackRepository {
	
	@Insert("insert into feedback(name,email,subject,message,created_by,updated_by,owner_id ) values(#{name},#{email},#{subject},#{message},#{userName},#{userName},#{userId})")
	public int addFeedback(Map<String,Object> feedbackMap);
	
	@Select("SELECT count(*) from feedback where created_date>curdate() and owner_id=#{userId}")
	public int getNewFeedbackCount(Map<String,Object> requestMap);
	

}
