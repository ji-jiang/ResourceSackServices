package com.techmask.ressack.flagmanager.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FlagRepository {
	public int addFlag(Map<String,Object> resourceMap);
	public void deleteFlag(Map<String,Object> resourceMap);
	
}
