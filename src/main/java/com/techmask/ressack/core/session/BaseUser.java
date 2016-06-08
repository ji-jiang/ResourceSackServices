package com.techmask.ressack.core.session;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import lombok.Data;



public abstract  @Data class BaseUser implements Serializable {
	

	private static final long serialVersionUID = -2772339211606024312L;
	
	@Id
	private String id;
	private String userName;
	private String role;
	private String email;

   

}
