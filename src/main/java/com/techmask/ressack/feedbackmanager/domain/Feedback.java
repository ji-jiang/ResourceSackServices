package com.techmask.ressack.feedbackmanager.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;


public @Data class Feedback {
	
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
