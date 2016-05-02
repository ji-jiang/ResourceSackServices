package com.techmask.ressack.usermanager.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.service.UserService;

@SpringBootApplication
@RestController
public class CurrentUserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/currentuser")
	public User user(Principal principal) {
		String tokenKey = principal.getName();
		User user = userService.loadUserByTokenKey(tokenKey);
		return user;
	}
	

}
