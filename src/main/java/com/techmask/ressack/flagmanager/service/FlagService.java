package com.techmask.ressack.flagmanager.service;

import java.util.List;
import java.util.Map;

import com.techmask.ressack.flagmanager.domain.Flag;

public interface FlagService {
	public void addFlag(Map<String,Object> flagMap);
	public void removeFlag(Map<String,Object> flagMap);
	public List<Flag> loadAllFlagByUser(Map<String,Object> requestMap);
}
