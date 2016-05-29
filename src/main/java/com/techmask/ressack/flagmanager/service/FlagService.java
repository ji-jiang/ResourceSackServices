package com.techmask.ressack.flagmanager.service;

import java.util.Map;

public interface FlagService {
	public void addFlag(Map<String,Object> flagMap);
	public void removeFlag(Map<String,Object> flagMap);
}
