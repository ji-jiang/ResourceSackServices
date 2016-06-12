package com.techmask.ressack.core.security;


import com.techmask.ressack.core.utils.StringUtils;

public enum UserRole {
	ADMIN, USER, CONTRIBUTOR, CORE, ANONYMOUS;
	
	public static UserRole getInstance(String role) {
		if (StringUtils.isBlank(role))
			return UserRole.ANONYMOUS;
		else if ("ADMIN".equalsIgnoreCase(role) ) {
			return UserRole.ADMIN;
		}else if ("USER".equalsIgnoreCase(role) ) {
			return UserRole.USER;
		}else if ("CONTRIBUTOR".equalsIgnoreCase(role) ) {
			return UserRole.CONTRIBUTOR;
		}else if ("CORE".equalsIgnoreCase(role) ) {
			return UserRole.CORE;
		}else{
			return UserRole.ANONYMOUS;
		}
	}
	
	public boolean isAnonymous(){
		return this.name().equals(ANONYMOUS.name());
	}
	public boolean isUser(){
		return !this.name().equals(ANONYMOUS.name());
	}
	public boolean isContributor(){
		return (this.name().equals(CONTRIBUTOR.name()) || this.name().equals(ADMIN.name()) || this.name().equals(CORE.name()));
	}
	public boolean isCore(){
		return (this.name().equals(CORE.name())||this.name().equals(ADMIN.name()));
	}
	public boolean isAdmin(){
		return this.name().equals(ADMIN.name());
	}
}

