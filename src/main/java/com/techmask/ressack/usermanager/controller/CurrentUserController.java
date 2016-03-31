package com.techmask.ressack.usermanager.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CurrentUserController {

	@RequestMapping("/currentuser")
	public Principal user(Principal user) {
		return user;
	}
	

}
