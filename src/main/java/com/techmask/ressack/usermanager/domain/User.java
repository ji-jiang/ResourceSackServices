package com.techmask.ressack.usermanager.domain;

import org.springframework.data.annotation.Id;

public class User {
	@Id
	private String id;
	private String email;
	private String password;
	private String role;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return String.format("User[id=%s,email='%s',password='$s',role='$s']", id, email, password, role);
	}
}
