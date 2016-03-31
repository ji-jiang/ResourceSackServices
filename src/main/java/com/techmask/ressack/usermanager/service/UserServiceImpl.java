package com.techmask.ressack.usermanager.service;

import java.util.List;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.security.UserRole;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;

	public User getUserById(String userId) {
		return userRepository.findOne(userId);
	}

	public User getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	public User addUser(User user) {
		if(StringUtil.isBlank(user.getRole())){
			user.setRole(UserRole.USER.name());
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User updateUser(User user) {
		return userRepository.save(user);
	}

	public void deleteUser(String userId) {
		userRepository.delete(userId);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}



}
