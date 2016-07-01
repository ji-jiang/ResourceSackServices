package com.techmask.ressack.accountmanager.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Account implements Serializable{

	private static final long serialVersionUID = 245553470588339666L;

	@Id
	private String id;
	private BigDecimal balance;
	private BigDecimal totalIncome;
	private BigDecimal totalWithdraw;
	private String userId;
	private String accountInfo;
	private Date createdDate;
	private Date updatedDate;
}
