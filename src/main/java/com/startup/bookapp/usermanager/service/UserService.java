package com.startup.bookapp.usermanager.service;

import java.util.List;
import com.startup.bookapp.usermanager.domain.User;

public interface UserService {
	public User getUserById(String userId);
    public User getUserByEmail(String email);
    public User addUser(User user);
    public User updateUser(User user);
    public void deleteUser(String userId);
    public List<User> getAllUsers();

}
