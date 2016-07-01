package com.techmask.ressack.feedbackmanager.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;


public @Data class Feedback implements Serializable{
	
	private static final long serialVersionUID = -922769323988689491L;
	
	@Id
	private String feedbackId;
	private String name;
	private String email;
	private String message;
	private String createdDate;
	private String createdBy;
	private String updatedDate;
	private String updatedBy;
	private String ownerId;

}
