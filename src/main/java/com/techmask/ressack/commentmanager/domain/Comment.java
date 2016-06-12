package com.techmask.ressack.commentmanager.domain;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Comment {
	@Id
	private String id;
	private Integer rating;
	private String comment;
	private String ownerId;
	private String refId;
	private String parentId;
	private Date createdDate;
	private String ownerName;
	private String refTitle;
	private List<Comment> childComments;
}
