package com.techmask.ressack.usermanager.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.techmask.ressack.core.session.BaseUser;

public class User extends BaseUser{
	@Id
	private String id;
	private String email;
	private String role;
	private String tokenKey;
	private String userName;
	private String oauthId;
	private String oauthType;
	private String oauthName;
	private String headImgUrl;
	private Date lastLoginDate;

	public String getOauthType() {
		return oauthType;
	}

	public void setOauthType(String oauthType) {
		this.oauthType = oauthType;
	}

	public String getOauthName() {
		return oauthName;
	}

	public void setOauthName(String oauthName) {
		this.oauthName = oauthName;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

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

	public String getOauthId() {
		return oauthId;
	}

	public void setOauthId(String oauthId) {
		this.oauthId = oauthId;
	}
}
