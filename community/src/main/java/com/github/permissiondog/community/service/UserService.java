package com.github.permissiondog.community.service;

import com.github.permissiondog.community.model.User;

public interface UserService {
	public User login(String username, String password);
}
