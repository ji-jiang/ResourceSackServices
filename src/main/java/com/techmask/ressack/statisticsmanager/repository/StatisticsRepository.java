package com.techmask.ressack.statisticsmanager.repository;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsRepository {
	public int addStatistics(Map<String, Object> requestMap);
	public int updateStatistics(Map<String, Object> requestMap);
}
