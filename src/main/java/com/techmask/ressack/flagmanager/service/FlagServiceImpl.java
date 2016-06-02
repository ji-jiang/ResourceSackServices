package com.techmask.ressack.flagmanager.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.error.AppException;
import com.techmask.ressack.flagmanager.domain.Flag;
import com.techmask.ressack.flagmanager.repository.FlagRepository;
import com.techmask.ressack.statisticsmanager.service.StatisticsService;

@Service
public class FlagServiceImpl implements FlagService{
	
	@Autowired
	private FlagRepository flagRepository; 
	
	@Autowired
	private StatisticsService statisticsService;

	@Override
	public void addFlag(Map<String, Object> flagMap) {
		
		vlaidateAddOrRemoveFlag(flagMap);
		flagRepository.addFlag(flagMap);
		
		flagMap.put("changedCount", 1);
		flagMap.put("statisticsType", flagMap.get("flagType"));
		statisticsService.updateStatistics(flagMap);
	}
	
	@Override
	public void removeFlag(Map<String, Object> flagMap) {
		vlaidateAddOrRemoveFlag(flagMap);
		flagRepository.deleteFlag(flagMap);
		
		flagMap.put("changedCount", -1);
		flagMap.put("statisticsType", flagMap.get("flagType"));
		statisticsService.updateStatistics(flagMap);
	}
	
	@Override
	public List<Flag> loadAllFlagByUser(Map<String, Object> requestMap) {
		return flagRepository.loadAllFlagByUser(requestMap);
	}
	
	protected void vlaidateAddOrRemoveFlag(Map<String, Object> flagMap){
		String userId = (String) flagMap.get("userId");
		if (StringUtils.isBlank(userId)) {
			throw new AppException(AppException.PERMISSION_DENIED_ERROR);
		}
	}



}
