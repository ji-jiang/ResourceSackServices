package com.techmask.ressack.profilemanager.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Profile {
	@Id
	private String id;
	private String userId;
	private String jobTitle;
	private String companyName;
	private String desc;
	private String role;
	private String userName;
	private String email;
}
