package com.techmask.ressack.flagmanager.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.techmask.ressack.flagmanager.domain.Flag;

@Mapper
public interface FlagRepository {
	public int addFlag(Map<String,Object> flagMap);
	public void deleteFlag(Map<String,Object> flagMap);
	public List<Flag> loadAllFlagByUser(Map<String,Object> requestMap);
}
