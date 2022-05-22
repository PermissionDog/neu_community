package com.github.permissiondog.community.model.dao;

import com.github.permissiondog.community.model.User;

public interface UserDao {
	public User findUserByUserName(String username);
}
