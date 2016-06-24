package com.techmask.ressack.rankmanager.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Rank {
	@Id
	private String rankId;	
	private String userId;
	private String userName;
	private String role;
	private String headImgUrl;
	private Integer resourceCount;
	private Integer craftCount;
	private Integer commentCount;
	private Integer likeCount;
	private Integer downloadCount;
	private Integer totalScore;
	private BigDecimal amount;
	
}

