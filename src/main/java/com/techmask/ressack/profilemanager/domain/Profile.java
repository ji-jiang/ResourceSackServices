package com.techmask.ressack.profilemanager.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Profile implements Serializable{

	private static final long serialVersionUID = 1268287610398158788L;

	@Id
	private String id;
	private String userId;
	private String jobTitle;
	private String companyName;
	private String desc;
	private String role;
	private String userName;
	private String email;
	private String headImgUrl;
	private BigDecimal totalIncome;
	private BigDecimal balance;
	private String accountInfo;
}
