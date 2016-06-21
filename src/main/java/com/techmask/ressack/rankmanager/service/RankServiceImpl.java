package com.techmask.ressack.rankmanager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.techmask.ressack.rankmanager.domain.Rank;
import com.techmask.ressack.rankmanager.repository.RankRepository;

@Service
@Configuration
public class RankServiceImpl implements RankService{
	
	@Autowired
	private RankRepository rankRepository;
	
	@Override
	public List<Rank> loadAllRankByYearWeek(Map<String, Object> requestMap) {		
		String yearWeekInd = (String)requestMap.get("yearWeekInd");

		
		if(!StringUtils.isBlank(yearWeekInd) ){
			yearWeekInd = "THISWEEK";
		}else{
			yearWeekInd = yearWeekInd.toUpperCase();
			if(!yearWeekInd.equalsIgnoreCase("THISWEEK") && (!yearWeekInd.equalsIgnoreCase("LASTWEEK"))){
				yearWeekInd = "THISWEEK";
			}
		}
		
		return rankRepository.loadAllRankByYearWeek(requestMap);
	}

	@Override
	public List<Rank> loadAllRank(Map<String, Object> requestMap) {
		return rankRepository.loadAllRank(requestMap);
	}

	@Override
	@Scheduled(cron="0 0/60 * * * ?")
	public void processRankCalculate() {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("yearWeek",0);
		rankRepository.processRankCalculate(requestMap);
	}
	
	

}
