package com.techmask.ressack.core.codetablemanager.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;

public @Data class CodeField implements Serializable{

	private static final long serialVersionUID = 1651921349354255743L;
	
	@Id
	private String fieldId;
	private String fieldName;
	private String loadScript;
	private String fieldDesc;

}
