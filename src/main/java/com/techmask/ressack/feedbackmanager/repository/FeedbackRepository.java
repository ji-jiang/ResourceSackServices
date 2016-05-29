package com.techmask.ressack.feedbackmanager.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FeedbackRepository {
	
	
	public int addFeedback(Map<String,Object> feedbackMap);

	public int getNewFeedbackCount(Map<String,Object> requestMap);
	

}
