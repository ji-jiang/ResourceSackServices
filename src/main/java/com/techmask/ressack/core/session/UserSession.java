package com.techmask.ressack.core.session;

import java.util.Iterator;

public interface UserSession {    

    public String getSessionId();

    public String getUserId();
    public String getUserName();
    public String getUserRole();
    public boolean has(String key);
    public Object get(String key);
    public Iterator<String> getKeyNames();
    public void set(String key, Object value);
    public void remove(String key);
    public long getCreationTime();
    public int hashCode();
}