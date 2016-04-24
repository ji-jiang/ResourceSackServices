package com.techmask.ressack.usermanager.service;

import java.util.List;

import com.techmask.ressack.usermanager.domain.User;

public interface UserService {
	public User loadUserById(String userId);
    public User loadUserByTokenKey(String tokenKey);
    public User addUser(User user);
    public User updateUser(User user);
    public void invalidateUser(String userId);
    public List<User> loadAllUser();

}
