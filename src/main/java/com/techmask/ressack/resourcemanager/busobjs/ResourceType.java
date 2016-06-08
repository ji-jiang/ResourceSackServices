package com.techmask.ressack.resourcemanager.busobjs;

import com.techmask.ressack.core.utils.StringUtils;

public enum ResourceType {
	R, C;

	public static ResourceType getInstance(String type) {
		if (StringUtils.isBlank(type))
			return ResourceType.R;
		else if ("R".equalsIgnoreCase(type) ) {
			return ResourceType.R;
		}else if ("C".equalsIgnoreCase(type) ) {
			return ResourceType.C;
		}else{
			return ResourceType.R;
		}
	}
	
	public boolean isResource(){
		return this.name().equals(R.name());
	}
	
	public boolean isCraft(){
		return this.name().equals(C.name());
	}
}
