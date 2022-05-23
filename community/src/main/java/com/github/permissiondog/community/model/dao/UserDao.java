package com.github.permissiondog.community.model.dao;

import java.util.List;

import com.github.permissiondog.community.model.User;

public interface UserDao {
	public User findUserByUserName(String username);
	public List<User> getAllUsers();
}
