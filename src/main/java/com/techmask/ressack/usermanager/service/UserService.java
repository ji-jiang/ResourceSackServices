package com.techmask.ressack.usermanager.service;

import java.util.List;

import com.techmask.ressack.usermanager.domain.User;

public interface UserService {
	public User getUserById(String userId);
    public User getUserByEmail(String email);
    public User addUser(User user);
    public User updateUser(User user);
    public void deleteUser(String userId);
    public List<User> getAllUsers();

}
