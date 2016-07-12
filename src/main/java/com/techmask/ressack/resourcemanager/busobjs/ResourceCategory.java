package com.techmask.ressack.resourcemanager.busobjs;

import com.techmask.ressack.core.codetablemanager.util.CodeTableUtils;

public enum ResourceCategory {
	DESIGN,DEVELOPMENT,MOBILE,TOOLS,CRAFT;
	
	public static ResourceCategory getInstance(String code){
		ResourceCategory resouceCategory = null;
		
		if(code==null){
			//do nothing
		}else if(code.equalsIgnoreCase(DESIGN.name())){
			resouceCategory = DESIGN;
		}else if(code.equalsIgnoreCase(DEVELOPMENT.name())){
			resouceCategory = DEVELOPMENT;
		}else if(code.equalsIgnoreCase(MOBILE.name())){
			resouceCategory = MOBILE;
		}else if(code.equalsIgnoreCase(TOOLS.name())){
			resouceCategory = TOOLS;
		}else if(code.equalsIgnoreCase(CRAFT.name())){
			resouceCategory = CRAFT;
		}
		
		return resouceCategory;
	}
	
	public String getCNDesc(){
		String desc = "";

		desc = CodeTableUtils.getCodeDesc("RESOURCE_CATEGORY", this.name());
		
		return desc;
	}
	
}
