package com.techmask.ressack.usermanager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jetty.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmask.ressack.core.security.UserRole;
import com.techmask.ressack.usermanager.domain.User;
import com.techmask.ressack.usermanager.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

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
		if (StringUtil.isBlank(user.getRole())) {
			user.setRole(UserRole.USER.name());
		}

		userRepository.addUser(user);

		return user;
	}

	@Override
	public User updateUser(User user) {
		userRepository.updateUser(user);
		return user;
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

	@Override
	public User loadUserByOAtuth(User user) {
		return userRepository.loadUserByOAtuth(user);
	}

	@Override
	public void updateUserLoginInfo(User user) {
		userRepository.updateUserLoginInfo(user);
	}

	@Override
	public User loadUserByAccessTokenAndOauthType(String tokenKey, String oauthType) {
		Map<String,Object> authMap = new HashMap<String,Object>();
		authMap.put("tokenKey", tokenKey);
		authMap.put("oauthType", oauthType);
		return userRepository.loadUserByAccessTokenAndOauthType(authMap);
	}

}
