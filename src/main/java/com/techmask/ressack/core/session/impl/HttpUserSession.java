package com.techmask.ressack.core.session.impl;

import java.util.Iterator;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.iterators.EnumerationIterator;

import com.techmask.ressack.core.session.BaseUser;



public class HttpUserSession extends BaseUserSession {

	private HttpSession httpSession;
	

	public HttpUserSession(HttpSession httpSession, BaseUser netsUser) {
		super();

		this.httpSession = httpSession;
		setRequiredAttributes(httpSession.getId(), netsUser);

	}


	public boolean has(String key) {
		return httpSession.getAttribute(key) != null;
	}


	public Object get(String key) {
		Object value = httpSession.getAttribute(key);				
		return value;
	}


	public Iterator getKeyNames() {
		return new EnumerationIterator(httpSession.getAttributeNames());
	}


	public void set(String key, Object value) {		
		httpSession.setAttribute(key, value);
	}


	public void remove(String key) {		
		get(key); 
		httpSession.removeAttribute(key);
	}


	protected void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

	
}