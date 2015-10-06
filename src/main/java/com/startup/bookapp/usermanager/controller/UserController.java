package com.startup.bookapp.usermanager.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.startup.bookapp.usermanager.domain.User;
import com.startup.bookapp.usermanager.domain.UserRole;
import com.startup.bookapp.usermanager.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.POST)
	public Map<String, Object> createUserAccount(@RequestBody Map<String, Object> userMap) {
		User user = new User();
		user.setEmail((String) userMap.get("email"));
		user.setPassword((String)userMap.get("password"));
		user.setRole(UserRole.valueOf((String)userMap.get("role")).name());
		
		userService.addUser(user);

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "User created successfully");
		response.put("uer", user);

		return response;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{userId}")
	public User getUserDetails(@PathVariable("userId") String userId) {
		return userService.getUserById(userId);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{userId}")
	public Map<String, Object> updateUser(@PathVariable("userId") String userId,
			@RequestBody Map<String, Object> userMap) {
		User user = new User();
		user.setId(userId);
		user.setEmail((String) userMap.get("email"));
		user.setPassword((String)userMap.get("password"));

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("message", "User updated successfully");
		response.put("uer", userService.updateUser(user));
		return response;
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{userId}")
	public Map<String, String> deleteUser(@PathVariable("userId") String userId) {
		userService.deleteUser(userId);

		Map<String, String> response = new HashMap<String, String>();
		response.put("message", "User deleted successfully");

		return response;

	}

	@RequestMapping(method = RequestMethod.GET)
	public Map<String, Object> getAllUser() {
		List<User> users = userService.getAllUsers();

		Map<String, Object> response = new LinkedHashMap<String, Object>();
		response.put("totalUsers", users.size());
		response.put("users", users);

		return response;

	}

}
