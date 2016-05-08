package com.techmask.ressack.core.session;

import java.io.Serializable;

import org.springframework.data.annotation.Id;



public abstract class BaseUser implements Serializable {
	

	private static final long serialVersionUID = -2772339211606024312L;
	
	@Id
	private String id;
	private String userName;
	private String role;

    
    public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

}
