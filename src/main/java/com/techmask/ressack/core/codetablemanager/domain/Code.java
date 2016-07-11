package com.techmask.ressack.core.codetablemanager.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class Code implements Serializable{
	
	
	private static final long serialVersionUID = -2252251043818638038L;
	
	@Id
	private String codeId;
	private String codeType;
	private String code;
	private String codeDesc;
}
