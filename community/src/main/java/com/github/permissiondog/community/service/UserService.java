package com.github.permissiondog.community.service;

import java.util.List;

import com.github.permissiondog.community.model.User;

public interface UserService {
	public User login(String username, String password);
	public List<User> getAllUsers();
}
