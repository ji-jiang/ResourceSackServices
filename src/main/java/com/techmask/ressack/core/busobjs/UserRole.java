package com.techmask.ressack.core.busobjs;

import com.techmask.ressack.core.utils.StringUtils;

public enum UserRole {
	ADMIN,USER,ANONYMOUS;
	
	  public static UserRole getInstance(String userRole) {
	      UserRole resultUserRole = null;
		  if (StringUtils.isBlank(userRole))
			  resultUserRole = UserRole.ANONYMOUS;
	      else {
	          resultUserRole = UserRole.valueOf(userRole);
	      } 
		  
		  if(userRole == null){
			  resultUserRole =  UserRole.ANONYMOUS;
		  }
		  
		  return resultUserRole;
	  }
	  
	  public boolean isAdmin(){
		  return this.name().equalsIgnoreCase(ADMIN.name());
	  }
	  
	  public boolean isUser(){
		  return this.name().equalsIgnoreCase(USER.name());
	  }
	  
	  public boolean isAnonymous(){
		  return this.name().equalsIgnoreCase(ANONYMOUS.name());
	  }

}
