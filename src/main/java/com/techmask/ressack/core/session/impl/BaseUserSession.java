package com.techmask.ressack.core.session.impl;

import com.techmask.ressack.core.session.BaseUser;
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionIds;


public abstract class BaseUserSession implements UserSession {

	private String sessionId; 
    private long creationTime = System.currentTimeMillis();
	

    protected BaseUserSession() {
    }

    protected void setRequiredAttributes(String sessionId, BaseUser netsUser) {
        setSessionId(sessionId);
        setUser(netsUser);        
    }

    public String getSessionId() {
        return sessionId;
    }

    protected void setSessionId(String sessionId) {
        this.sessionId = sessionId;        
    }

   

    protected void setUser(BaseUser user) {        
        set(UserSessionIds.USER_ID, user.getId());
        set(UserSessionIds.USER_NAME, user.getUserName());
        set(UserSessionIds.USER_ROLE,user.getRole());
    }

    public String getUserId() {
        String userId = (String)get(UserSessionIds.USER_ID);
        return userId;
    }

    public String getUserName() { 
        String userName = (String)get(UserSessionIds.USER_NAME);    
        return userName;
    }  
    
    public String getUserRole() {
        String userId = (String)get(UserSessionIds.USER_ROLE);   
        return userId;
    }

    public long getCreationTime() {        
        return creationTime;
    }

    public int hashCode() {        
        return getSessionId().hashCode();
    }   

    
    
}
