package com.techmask.ressack.flagmanager.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Flag {
	@Id
	private String id;
	private String refId;
	private String flagType;
	private Date createdDate;
	private String ownerId;
}
