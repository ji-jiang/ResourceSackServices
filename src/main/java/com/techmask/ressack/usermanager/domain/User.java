package com.techmask.ressack.usermanager.domain;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	private String id;
	private String email;
	private String role;
	private String tokenKey;
	private String userName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return String.format("User[id=%s,name='%s',email='%s',role='$s',token='$s']", id, userName,email, role,tokenKey);
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
