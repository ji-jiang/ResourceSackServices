package com.techmask.ressack.flagmanager.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Flag implements Serializable{
	
	private static final long serialVersionUID = -4516632434209512499L;
	
	@Id
	private String id;
	private String refId;
	private String flagType;
	private Date createdDate;
	private String ownerId;
}
