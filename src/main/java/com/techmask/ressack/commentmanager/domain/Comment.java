package com.techmask.ressack.commentmanager.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Comment implements Serializable{

	private static final long serialVersionUID = -789982110063785024L;
	
	@Id
	private String id;
	private Integer rating;
	private String comment;
	private String ownerId;
	private String refId;
	private String parentId;
	private Date createdDate;
	private String ownerName;
	private String ownerAvatarUrl;
	private String refTitle;
	private List<Comment> childComments;
}
