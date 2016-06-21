package com.techmask.ressack.rankmanager.service;

import java.util.List;
import java.util.Map;

import com.techmask.ressack.rankmanager.domain.Rank;

public interface RankService {
	public List<Rank> loadAllRankByYearWeek(Map<String,Object> requestMap);
	public List<Rank> loadAllRank(Map<String,Object> requestMap);
	public void processRankCalculate();
}
