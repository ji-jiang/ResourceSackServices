package com.techmask.ressack.core.session.impl;

import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.techmask.ressack.core.log.LogUtils;
import com.techmask.ressack.core.session.BaseUser;
import com.techmask.ressack.core.session.DefaultUser;
import com.techmask.ressack.core.session.UserSession;
import com.techmask.ressack.core.session.UserSessionIds;
import com.techmask.ressack.core.session.UserSessionManager;
import com.techmask.ressack.core.session.UserSessionManagerAdmin;

public class UserSessionManagerImpl extends UserSessionManager implements UserSessionManagerAdmin {

	private Map<String, UserSession> userSessions = new Hashtable<String, UserSession>();

	private boolean skipLoadAndAuthenticateUser = true;

	public static String ANONYMOUS_USER_NAME = "Anonymous";

	public void setupByUser(HttpServletRequest request, BaseUser currentUser) {

		UserSession userSession = new HttpUserSession(request.getSession(), currentUser);
		String httpSessionId = request.getSession().getId();

		userSessions.put(httpSessionId, userSession);
	}

	public UserSession getUserSession(HttpServletRequest request) {
		UserSession userSession = null;
		String httpSessionId = request.getSession().getId();

		if (userSessions.containsKey(httpSessionId)) {
			userSession = (HttpUserSession) userSessions.get(httpSessionId);
		} else {
			String userId = (String) request.getSession().getAttribute(UserSessionIds.USER_ID);

			BaseUser currentUser = null;

			if (userId == null && isSkipLoadAndAuthenticateUser()) {
				currentUser = new DefaultUser();
				if (request.getUserPrincipal() == null) {
					currentUser.setUserName(ANONYMOUS_USER_NAME);
				} else {
					currentUser.setUserName(request.getUserPrincipal().getName());
				}

			} else if (currentUser == null) {
				LogUtils.error("Could not fectch user object from session.");
			}

			userSession = new HttpUserSession(request.getSession(), currentUser);

			userSessions.put(httpSessionId, userSession);
		}

		return userSession;
	}

	public UserSessionManagerImpl() {
	}

	public boolean isSkipLoadAndAuthenticateUser() {
		return skipLoadAndAuthenticateUser;
	}

	public void setSkipLoadAndAuthenticateUser(boolean skipLoadAndAuthenticateUser) {
		this.skipLoadAndAuthenticateUser = skipLoadAndAuthenticateUser;
	}

}