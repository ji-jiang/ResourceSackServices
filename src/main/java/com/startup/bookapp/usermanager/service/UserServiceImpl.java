package com.startup.bookapp.usermanager.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.startup.bookapp.core.util.CryptUtil;
import com.startup.bookapp.usermanager.domain.User;
import com.startup.bookapp.usermanager.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	


	@Autowired
	private UserRepository userRepository;

	public User getUserById(String userId) {
		return userRepository.findOne(userId);
	}

	public User getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	public User addUser(User user) {
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
