package com.techmask.ressack.usermanager.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.techmask.ressack.core.session.BaseUser;

import lombok.Data;

public @Data class User extends BaseUser {

	
	private static final long serialVersionUID = -5007305353575046872L;
	
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

}
