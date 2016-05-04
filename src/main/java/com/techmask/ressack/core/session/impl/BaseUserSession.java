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

    public BaseUser getUser() {
        return (BaseUser) get(UserSessionIds.CURRENT_USER);
    }

    protected void setUser(BaseUser netsUser) {        
        set(UserSessionIds.CURRENT_USER, netsUser);
    }

    public String getUserId() {
        String userId = getUser().getId();
        return userId;
    }

    public String getUserName() {
        String userName = getUser().getUserName();        
        return userName;
    }  

    public long getCreationTime() {        
        return creationTime;
    }

    public int hashCode() {        
        return getSessionId().hashCode();
    }   

    
    
}
