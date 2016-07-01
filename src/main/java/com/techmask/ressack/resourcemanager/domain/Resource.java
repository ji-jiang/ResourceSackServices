package com.techmask.ressack.resourcemanager.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Resource implements Serializable{

	private static final long serialVersionUID = 931226250881355472L;
	
	@Id
	private String id;
	private String title;
	private String desc;
	private String tags;
	private String origUrl;
	private String origAdviceUrl;
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
	private String ownerAvatarUrl;
	private Integer weight;
	private String imageInd;
	private String imageVersion;
	private String imageUrl;
	private String imageSmUrl;
	
	private Integer viewCount;
	private Integer likeCount;
	private Integer bookmarkCount;
	private Integer downloadCount;
	private Integer commentCount;
	private Integer ratingCount;
	private Double rating;
	private String type;

}
