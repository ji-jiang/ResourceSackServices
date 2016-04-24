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


	@Override
	public User loadUserById(String userId) {
		return userRepository.loadUserById(userId);
	}

	@Override
	public User loadUserByTokenKey(String tokenKey) {
		return userRepository.loadUserByTokenKey(tokenKey);
	}

	@Override
	public User addUser(User user) {
		if(StringUtil.isBlank(user.getRole())){
			user.setRole(UserRole.USER.name());
		}

		return userRepository.addUser(user); 
	}

	@Override
	public User updateUser(User user) {
		return userRepository.updateUser(user);
	}

	@Override
	public void invalidateUser(String userId) {
		userRepository.invalidateUser(userId);
	}

	

	@Override
	public List<User> loadAllUser() {
		
		System.out.println(userRepository.loadAllUser());
		
		return userRepository.loadAllUser();
	}



}
