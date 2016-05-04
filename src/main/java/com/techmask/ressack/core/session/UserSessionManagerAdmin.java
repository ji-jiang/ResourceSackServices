package com.techmask.ressack.core.session;

import javax.servlet.http.HttpServletRequest;

public interface UserSessionManagerAdmin {

	public void setupByUser(HttpServletRequest request, BaseUser user);

}