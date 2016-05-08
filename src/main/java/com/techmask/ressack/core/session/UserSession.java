package com.techmask.ressack.core.session;

import java.util.Iterator;

public interface UserSession {    

    public String getSessionId();
    public BaseUser getUser();
    public String getUserId();
    public String getUserName();
    public boolean has(String key);
    public Object get(String key);
    public Iterator<String> getKeyNames();
    public void set(String key, Object value);
    public void remove(String key);
    public long getCreationTime();
    public int hashCode();
}