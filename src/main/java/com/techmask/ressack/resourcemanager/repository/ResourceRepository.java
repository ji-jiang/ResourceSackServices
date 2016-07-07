package com.techmask.ressack.resourcemanager.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.techmask.ressack.resourcemanager.domain.Resource;

@Mapper
public interface ResourceRepository {
	
	public List<Resource> loadResource();
	public int getNewCreatedCount(Map<String, Object> requestMap);
	public int getSameOrigUrlCount(Map<String,Object> requestMap);
	public long getLastInsertId();
	public List<Resource> loadAllResource(Map<String,Object> requestMap);
	public int addResource(Map<String,Object> resourceMap);
	public int updateResource(Map<String,Object> resourceMap);
	public Resource loadResourceById(String resourceId);
	public int setImageInd(String resourceId);
	public List<Resource> loadAllResourceForRssFeed(Map<String, Object> requestMap);

	
}
