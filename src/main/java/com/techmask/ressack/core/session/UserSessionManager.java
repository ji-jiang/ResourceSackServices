package com.techmask.ressack.core.session;

import javax.servlet.http.HttpServletRequest;

import com.techmask.ressack.core.session.impl.UserSessionManagerImpl;


public abstract class UserSessionManager {

	private static UserSessionManager c_instance;

	public synchronized static UserSessionManager getInstance() {
		if (c_instance == null) {
			c_instance = new UserSessionManagerImpl();

		}
		return c_instance;
	}


	public abstract UserSession getUserSession(HttpServletRequest request);

	
}