package com.techmask.ressack.core.data;

import java.util.Map;

public class PageHelper {
	private static final int PAGE_SIZE = 24;
	
	public static void preparePageQuery(Map<String,Object> requestMap){
		if(requestMap.containsKey("pageNo")){
			int pageNo = Integer.parseInt((String)requestMap.get("pageNo"));
			int startRowIndex = (pageNo-1)*PAGE_SIZE;
			
			requestMap.put("_startRowIndex", startRowIndex);
			requestMap.put("_pageSize", PAGE_SIZE);
			
		}
	}

}
