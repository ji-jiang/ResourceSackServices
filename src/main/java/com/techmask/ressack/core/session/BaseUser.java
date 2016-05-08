package com.techmask.ressack.core.session;

import java.io.Serializable;

import com.techmask.ressack.core.session.impl.UserSessionManagerImpl;

/**
 * Title: UserSessionDTO 
 * 
 * @author chenyihe001
 * @version 1.0
 */

public abstract class BaseUser implements Serializable {
	
    

	private static final long serialVersionUID = -2776327615358890474L;
	
	
	private String id;
	private String userName;
	private String role;

   

	

	

    public boolean isAnonymousUser(){
        return this.getUserName().equals(UserSessionManagerImpl.ANONYMOUS_USER_NAME);
    }
    
    public boolean isUserInRole(String uRole){    	
    	String roles = this.getRole();
    	String[] roleArray = roles.split(",");
    	for(int i=0;i<roleArray.length;i++){
    		if(roleArray[i].equalsIgnoreCase(uRole)){
    			return true;
    		}
    	}
    	return false;
    }
    
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
