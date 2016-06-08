package com.techmask.ressack.resourcemanager.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Resource {
	@Id
	private String id;
	private String title;
	private String desc;
	private String tags;
	private String origUrl;
	private String downloadUrl;
	private String downloadPassword;
	private String paymentType;
	private Integer paymentAmount;
	private String category;
	private String subCategory;
	private String status;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	private Integer ownerId;
	private String ownerName;
	private Integer weight;
	private String imageInd;
	private String imageVersion;
	private String imageUrl;
	private String imageSmUrl;
	private Integer viewCount;
	private Integer likeCount;
	private Integer bookmarkCount;
	private Integer downloadCount;
	private String type;

}
