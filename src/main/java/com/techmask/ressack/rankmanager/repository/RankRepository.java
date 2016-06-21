package com.techmask.ressack.rankmanager.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.techmask.ressack.rankmanager.domain.Rank;

@Mapper
public interface RankRepository {
	public List<Rank> loadAllRankByYearWeek(Map<String,Object> requestMap);
	public List<Rank> loadAllRank(Map<String,Object> requestMap);
	public void processRankCalculate(Map<String,Object> requestMap); 
}
