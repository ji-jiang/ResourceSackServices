package com.techmask.ressack.statisticsmanager.service;

import java.util.Map;

public interface StatisticsService {
	public void addStatistics(Map<String, Object> requestMap);
	public void updateStatistics(Map<String, Object> requestMap);
}
