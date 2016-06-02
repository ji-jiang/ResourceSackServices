package com.techmask.ressack.statisticsmanager.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.techmask.ressack.statisticsmanager.repository.StatisticsRepository;

@Service
@Configuration
public class StatisticsServiceImpl implements StatisticsService{

	@Autowired
	private StatisticsRepository statisticsRepository;
	
	@Override
	public void addStatistics(Map<String, Object> requestMap) {
		statisticsRepository.addStatistics(requestMap);
	}

	@Override
	public void updateStatistics(Map<String, Object> requestMap) {
		statisticsRepository.updateStatistics(requestMap);
	}
	
}
