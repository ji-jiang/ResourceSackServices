package com.techmask.ressack.usermanager.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.techmask.ressack.usermanager.domain.User;

@Mapper
public interface UserRepository {

	public List<User> loadAllUser();
	
	public int addUser(User user);

	public User loadUserById(String userId);

	public User loadUserByTokenKey(String tokenKey);

	public int updateUser(User user);

	public void invalidateUser(String userId);
	public void updateUserLoginInfo(User user);
	public User loadUserByOAtuth(User user);
	public User loadUserByAccessTokenAndOauthType(Map<String,Object> authMap);

}

